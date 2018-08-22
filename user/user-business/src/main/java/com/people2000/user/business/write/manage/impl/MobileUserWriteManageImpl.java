package com.people2000.user.business.write.manage.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.read.dao.UPlatformGroupRelationDAORead;
import com.people2000.user.business.read.dao.ext.CaptchasDAORead2;
import com.people2000.user.business.utils.CacheCommUtils;
import com.people2000.user.business.utils.CacheKeyUtils;
import com.people2000.user.business.utils.CommonUtils;
import com.people2000.user.business.utils.ParamCheck;
import com.people2000.user.business.utils.PasswordUtil;
import com.people2000.user.business.utils.SecurityUtils;
import com.people2000.user.business.utils.TimeUtils;
import com.people2000.user.business.utils.UserActionLogEnum;
import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.business.write.dao.UUnionLoginDAOWrite;
import com.people2000.user.business.write.dao.UserExtDAOWrite;
import com.people2000.user.business.write.dao.UserMappingDAOWrite;
import com.people2000.user.business.write.dao.ext.CaptchasDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserActionLogDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserRoleDAOWrite2;
import com.people2000.user.business.write.manage.EncryptInfoWriteManage;
import com.people2000.user.business.write.manage.MobileUserWriteManage;
import com.people2000.user.business.write.manage.PushMessageWriteManage;
import com.people2000.user.business.write.manage.SmsManage;
import com.people2000.user.business.write.manage.UnionLoginManage;
import com.people2000.user.business.write.manage.UserAccountWriteManage;
import com.people2000.user.business.write.manage.UserRoleWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.PushMessageInputDTO;
import com.people2000.user.model.dto.UserAccountDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.CaptchasPO;
import com.people2000.user.model.po.ibatis.CaptchasPOExample;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserExample;
import com.people2000.user.model.po.ibatis.UserExample.Criteria;
import com.people2000.user.model.po.ibatis.UserExtPO;
import com.people2000.user.model.po.ibatis.userMappingPO;
import com.people2000.user.model.vo.CaptchasVO;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.model.vo.UserExtEnum;
import com.people2000.user.model.vo.UserInfoFromOldVO;

/**
 * @author xxl
 * @time 2015-8-27 下午3:10:23
 * @description <pre>
 * <p/>
 * </pre>
 */
@Service("mobileUserWriteManage")
public class MobileUserWriteManageImpl implements MobileUserWriteManage {
	@Value("${user.condition}")
	private String condition = "release";

	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;

	@Resource(name = "userRoleWriteManage")
	private UserRoleWriteManage userRoleWriteManage;

	@Resource(name = "userRoleDAOWrite")
	private UserRoleDAOWrite2 userRoleDAOWrite;

	@Resource(name = "captchasDAOWrite")
	private CaptchasDAOWrite2 captchasDAOWrite;

	@Resource(name = "captchasDAORead")
	private CaptchasDAORead2 captchasDAORead2;

	@Resource(name = "encryptInfoWriteManage")
	private EncryptInfoWriteManage encryptInfoWriteManage;

	@Resource(name = "uPlatformGroupRelationDAORead")
	private UPlatformGroupRelationDAORead uPlatformGroupRelationDAORead;
	@Resource(name = "userActionLogDAOWrite")
	private UserActionLogDAOWrite2 userActionLogDAOWrite;

	@Resource(name = "userMappingDAOWrite")
	private UserMappingDAOWrite userMappingDAOWrite;

	@Resource(name = "userExtDAOWrite")
	private UserExtDAOWrite userExtDAOWrite;

	@Resource(name = "unionLoginManage")
	private UnionLoginManage unionLoginManage;

	@Resource
	private UUnionLoginDAOWrite uUnionLoginDAOWrite;

	@Resource
	private PushMessageWriteManage pushMessageWriteManage;

	@Resource
	private UserAccountWriteManage userAccountWriteManage;
	@Autowired
	private SmsManage smsManage;

	private Logger logger = LogUtils.getLogger(MobileUserWriteManageImpl.class);

	private static final String SHOP_TYPE_NAME = "承诺店";

	private static final String SUCCESS_CODE = "0";

	public static final String SHOP_ACTION = "A";// 店铺行动字段

	private static final Logger log = LoggerFactory
			.getLogger(MobileUserWriteManageImpl.class);

	@Override
	public HashMap<String, Object> updatePassWordWithTx(MobileUserVO userDto)
			throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		String oldPassword = userDto.getPassword();
		User encryptUser = new User();// 1 用于查询用户密码加密信息
		if (userDto.getType() != null) {
			encryptUser.setType(userDto.getType());
		}
		if (null != userDto.getId()) {
			encryptUser.setId(userDto.getId());
		} else {
			if (StringUtils.isNotEmpty(userDto.getMobile())) {
				encryptUser.setMobile(userDto.getMobile());
			} else {
				resultMap.put("code", "3");
				resultMap.put("message", "参数信息异常.");
				return resultMap;
			}
		}
		User user = new User();
		// 2 查询用户的密码加密信息
		EncryptEntity encryptEntity = encryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);
		// 如果有有旧密码
		if (!StringUtils.isBlank(oldPassword)) {
			userDto.setPassword(oldPassword);
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			// criteria.andIdEqualTo(userDto.getId()).andPasswordEqualTo(SecurityUtils.getMD5Str(userDto.getPassword()));
			criteria.andIdEqualTo(userDto.getId());

			if (StringUtils.isEmpty(encryptEntity.getOldBSalt())) {// 没有old盐值,是修改之前的用户,原密码md5加密一次
				criteria.andPasswordEqualTo(PasswordUtil.getSecretPassWord(
						userDto.getPassword(), "", 1));
			} else {// 有old盐值,就用old盐值跟old算法加密
				if (null != encryptEntity.getOldBVersion()) {
					criteria.andPasswordEqualTo(PasswordUtil.getSecretPassWord(
							userDto.getPassword(), encryptEntity.getOldBSalt(),
							encryptEntity.getOldBVersion()));
				}
			}

			// -----------------------criteria.andPasswordEqualTo(PassWordUtils.getSecretPassWord(userDto.getPassword()));

			List list = userDAOWrite.selectByExample(example, false);
			if (CollectionUtils.isEmpty(list)) {
				resultMap.put("code", 4);
				resultMap.put("message", "旧密码不正确");
				return resultMap;
			}
		}
		String password1 = userDto.getPassword1();
		String password2 = userDto.getPassword2();

		if (StringUtils.isBlank(password1)) {
			resultMap.put("code", 3);
			resultMap.put("message", "新密码不能空");
			return resultMap;
		}
		if (password1.length() < 6) {
			resultMap.put("code", 5);
			resultMap.put("message", "密码不能少于6位");
			return resultMap;
		}

		if (!password1.equals(password2)) {
			resultMap.put("code", 2);
			resultMap.put("message", "输入密码不一致");
			return resultMap;
		}

		// 没有旧密码使用手机号码校验
		if (StringUtils.isBlank(oldPassword)) {
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andMobileEqualTo(userDto.getMobile());
			// 用户必需是没有被删除的.
			criteria.andIsDeletedEqualTo(0);
			if (StringUtils.isEmpty(encryptEntity.getOldBSalt())) {// 没有old盐值,是修改之前的用户,原密码md5加密一次
				criteria.andPasswordEqualTo(PasswordUtil.getSecretPassWord(
						password1, "", 1));
			} else {// 有old盐值,就用old盐值跟old算法加密
				if (null != encryptEntity.getOldBVersion()) {
					criteria.andPasswordEqualTo(PasswordUtil.getSecretPassWord(
							password1, encryptEntity.getOldBSalt(),
							encryptEntity.getOldBVersion()));
				}
			}

			// -----------------------criteria.andPasswordEqualTo(PassWordUtils.getSecretPassWord(userDto.getPassword()));
			/*List userList = userDAOWrite.selectByExample(example, false);

			if (!CollectionUtils.isEmpty(userList)) {
				resultMap.put("code", 7);
				resultMap.put("message", "新密码不能与旧密码相同");
				return resultMap;
			}*/
			CaptchasPOExample captchasPOExample = new CaptchasPOExample();
			CaptchasPOExample.Criteria createCriteria = captchasPOExample
					.createCriteria();
			captchasPOExample.setOrderByClause("expire_time desc");
			createCriteria.andMobileEqualTo(userDto.getMobile())
			/* .andSuccessIsEqualTo(1) */
			.andExpireTimeGreaterThan(new Date());
			List list = captchasDAOWrite.selectByExample(captchasPOExample,
					false);
			if (CollectionUtils.isNotEmpty(list)) {
				CaptchasPO captchas1 = (CaptchasPO) list.get(0);
				if (null == captchas1.getSuccessIs()) {
					Calendar timeout = Calendar.getInstance();
					timeout.setTime(new Date());
					timeout.add(Calendar.MINUTE,
							TimeUtils.getMinFromNowTOToday() - 1);// 当天的最后两分钟内
					if (captchas1.getExpireTime().compareTo(timeout.getTime()) <= 0) {
						// 未验证
						resultMap.put("code", 1);
						resultMap.put("message", "手机号码未校验");
						return resultMap;
					}
				}
			} else {
				resultMap.put("code", 1);
				resultMap.put("message", "手机号码未校验");
				return resultMap;
			}
		}

		String password = "";// 新密码加密之后密文
		// user.setPassword(SecurityUtils.getMD5Str(password1));

		Date now = new Date();

		// 3 如果用户有新的盐值或者新的算法version,用新的加密保存然后更新到数据库
		if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())
				|| null != encryptEntity.getNewBVersion()) {
			String salt = StringUtils.isEmpty(encryptEntity.getNewBSalt()) ? encryptEntity
					.getOldBSalt() : encryptEntity.getNewBSalt();
			Integer version = (null == encryptEntity.getNewBVersion()) ? encryptEntity
					.getOldBVersion() : encryptEntity.getNewBVersion();

			password = PasswordUtil.getSecretPassWord(password1, salt, version);

			if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
				user.setbSalt(encryptEntity.getNewBSalt());
				user.setbSaltUpdateTime(now);
			}
			if (null != encryptEntity.getNewBVersion()) {
				user.setbAlgorithmVersion(encryptEntity.getNewBVersion());
			}
		} else {// 没有返回新的盐值和算法,肯定返回了旧的算法和盐值
			String salt = encryptEntity.getOldBSalt();
			Integer version = encryptEntity.getOldBVersion();
			password = PasswordUtil.getSecretPassWord(password1, salt, version);
		}

		// ---------------------user.setPassword(PassWordUtils.getSecretPassWord(password1));
		user.setPassword(password);

		// 更新前端加密算法信息
		if (StringUtils.isNotEmpty(userDto.getNewFSalt())) {
			user.setfSalt(userDto.getNewFSalt());
			user.setfSaltUpdateTime(now);
		}
		if (null != userDto.getNewFVersion()) {
			user.setfAlgorithmVersion(userDto.getNewFVersion());
		}

		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();

		if (StringUtils.isBlank(oldPassword)) {
			user.setId(userDto.getId());
			criteria.andMobileEqualTo(userDto.getMobile());

		} else {
			criteria.andIdEqualTo(userDto.getId());
			user.setId(userDto.getId());
		}
		criteria.andIsDeletedEqualTo(0);
		criteria.andTypeEqualTo(userDto.getType());
		int result = userDAOWrite.updateByExampleSelective(user, example);
		if (result == 1) {
			resultMap.put("code", 0);
			resultMap.put("message", "修改成功");
		} else {
			throw new OuserMangeException("用户信息不正确");
		}
		try {
			userActionLogDAOWrite.insertUserActionLog(user,
					UserActionLogEnum.changePassword.getType());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return resultMap;
	}

	// 根据用户信息查询用户
	public User getUserByCon(User user) {
		final UserExample example = new UserExample();
		final Criteria criteria = example.createCriteria();
		if (user.getMobile() != null) {
			criteria.andMobileEqualTo(user.getMobile());
		}
		if (user.getCompanyId() != null) {
			criteria.andCompanyIdEqualTo(user.getCompanyId());
		}
		if (user.getId() != null) {
			criteria.andIdEqualTo(user.getId());
		}
		try {
			final List<User> list = userDAOWrite
					.selectByExample(example, false);
			if (!CollectionUtils.isEmpty(list)) {
				return list.get(0);
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		return null;
	}

	private static Map<Long, String> companyMap = new HashMap<Long, String>();

	static {
		companyMap.put(1l, "yhRegisterTemplete");// yh
		companyMap.put(2l, "zsRegisterTemplete");// zs
		companyMap.put(3l, "dyRegisterTemplete");// dy
		companyMap.put(4l, "blblRegisterTemplete");// bl
		companyMap.put(5l, "zgRegisterTemplete");// zg
		companyMap.put(7l, "ddpgRegisterTemplete");// ddpg
		companyMap.put(8l, "zpptRegisterTemplete");// zppt
	}

	@Override
	public HashMap<String, Object> registerWithTx(MobileUserVO userDto)
			throws Exception {
		logger.info("注册开始，入参： ");
		logger.info("用户名： " + userDto.getUsername());
		logger.info("手机： " + userDto.getMobile());

		if (userDto.getType() == null) {
			userDto.setType(ConstantUser.user_type.member);
		}

		if (userDto.getPassword() == null || userDto.getPassword().length() < 6) {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("code", -1);
			resultMap.put("message", "密码不能少于6位");
			return resultMap;
		}

		// 校验手机号是否存在
		if (userDto.getMobile() != null) {
			int count = userDAOWrite.selectCountByMobileAndCompanyId(
					userDto.getMobile(), userDto.getCompanyId());
			if (count > 0) {
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("code", -1);
				resultMap.put("message", "对不起，手机号已存在");
				return resultMap;
			}
		}

		// 校验用户名是否存在
		if (userDto.getUsername() != null) {
			int count = userDAOWrite.selectCountByUserNameAndCompanyId(
					userDto.getUsername(), userDto.getCompanyId());
			if (count > 0) {
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("code", -1);
				resultMap.put("message", "对不起，用户名已存在");
				return resultMap;
			}
		}

		User user = new User();

		// 1 用于查询密码加密信息
		User encryptUser = null;
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = encryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);
		userDto.setPassword(PasswordUtil.getSecretPassWord(
				userDto.getPassword(), encryptEntity.getNewBSalt(),
				encryptEntity.getNewBVersion()));

		// 前端算法盐值信息
		Date now = new Date();
		userDto.setfAlgorithmVersion(userDto.getNewFVersion());
		userDto.setfSalt(userDto.getNewFSalt());
		if (StringUtils.isNotEmpty(userDto.getNewFSalt())) {
			userDto.setfSaltUpdateTime(now);
		}
		BeanUtils.copyProperties(userDto, user);
		user.setParentId(0L);
		user.setIsDeleted(ConstantUser.isDelete.normal);

		// 如果类型为4会员，无需审核
		if (user.getType() == ConstantUser.user_type.member) {
			user.setStatus(ConstantUser.user_status.success);
		} else {
			user.setStatus(ConstantUser.user_status.wait);
		}

		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
			user.setbSaltUpdateTime(now);
		}
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());
		if (StringUtils.isEmpty(user.getUsername())) {
			user.setUsername("");
		}
		user.setPlatformId(userDto.getUserPlatformId());

		userDAOWrite.insert(user);

		// 维护用户动作日志信息
		try {
			userActionLogDAOWrite.insertUserActionLog(user,
					UserActionLogEnum.register.getType());
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error("注册日志插入错误." + e.getMessage(),
					e);
		}

		Long userId = user.getId();
		// 添加客户端消息推送信息
		try {
			if (userDto.getDeviceId() != null && userDto.getAppType() != null) {
				PushMessageInputDTO pm = new PushMessageInputDTO();
				pm.setUserId(userId);
				pm.setDeviceId(userDto.getDeviceId());
				pm.setAppType(userDto.getAppType());
				pushMessageWriteManage.addPushMessageWithTx(pm);
			}
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(
					"注册消息推送设备信息." + e.getMessage(), e);
		}

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", 20);
		resultMap.put("id", userId);
		resultMap.put("message", "注册成功!");
		return resultMap;
	}

	@Override
	public HashMap<String, Object> sendCodeWithTx(MobileUserVO captchas)
			throws Exception {
		String mobileN = captchas.getMobile();
		final Long companyId = captchas.getCompanyId();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(mobileN)) {
			resultMap.put("code", 5);
			resultMap.put("message", "手机号码不能为空!");
			return resultMap;
		}
		if (!CacheKeyUtils.checkTimes(mobileN, companyId)) {
			resultMap.put("code", 6);
			resultMap.put("message", "请以最后一次验证码为准");
			return resultMap;
		}

		CaptchasPOExample captchasPOExample = new CaptchasPOExample();
		captchasPOExample.setOrderByClause("expire_time desc");
		CaptchasPOExample.Criteria criteria = captchasPOExample
				.createCriteria();
		criteria.andMobileEqualTo(captchas.getMobile())
				.andExpireTimeGreaterThanOrEqualTo(new Date())
				.andSuccessIsIsNull();
		List list = captchasDAOWrite.selectByExample(captchasPOExample, false);
		if (CollectionUtils.isNotEmpty(list)) {
			CaptchasPO captchas1 = (CaptchasPO) list.get(0);
			Calendar timeout = Calendar.getInstance();
			timeout.setTime(new Date());
			timeout.add(Calendar.MINUTE, 9);
			if (captchas1.getExpireTime().compareTo(timeout.getTime()) >= 0) {
				resultMap.put("code", 6);
				resultMap.put("message", "一分钟内至多发送一次");
				return resultMap;
			}
		}

		String charValue = "";
		charValue = getString(6);
		CaptchasVO captchasVO = new CaptchasVO();
		captchasVO.setCompanyId(companyId);
		;
		captchasVO.setMobile(mobileN);
		captchasVO.setSmsCode(charValue);
		String env = System.getProperty("env");
		int resultCode = 0;
		if (!"development".equalsIgnoreCase(env)) {// 开发环境不发送短信验证码
			resultCode = smsManage.sendSmsVerifyCode(mobileN, charValue);
		}
		if (resultCode == 0) {
			// 缓存中put+1
			CacheKeyUtils.addOne(mobileN, companyId);
			resultMap.put("code", 0);
			resultMap.put("message", "校验码发送成功!");

			// 生成失效时间
			Calendar timeout = Calendar.getInstance();
			timeout.setTime(new Date());
			timeout.add(Calendar.MINUTE, 10);
			// 如果为第十次发送，验证码有效期设置为当天有效
			if (10 == CacheKeyUtils.getTimes(mobileN, companyId)) {
				timeout.add(Calendar.MINUTE,
						TimeUtils.getMinFromNowTOToday() - 10);
			}
			// 保存手机号。验证码。失效时间
			CaptchasPO captchas2 = new CaptchasPO();
			captchas2.setMobile(mobileN);
			captchas2.setCaptchas(charValue);
			captchas2.setExpireTime(timeout.getTime());
			if (captchas.getCompanyId() != null) {
				captchas2.setCompanyId(captchas.getCompanyId());
			}
			captchasDAOWrite.insert(captchas2);
		} else {
			resultMap.put("code", -1);
			resultMap.put("message", "error ");
		}
		return resultMap;
	}

	/**
	 * 校验手机号码 发送验证码
	 *
	 * @param captchas
	 * @throws Exception
	 */

	@Override
	public HashMap<String, Object> sendCaptchasWithTx(MobileUserVO captchas,
			HttpServletRequest request) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String mobileN = captchas.getMobile();
		String checkImageCode = captchas.getCheckImageCode();
		String imgeKey = captchas.getImgeKey();
		final Long companyId = captchas.getCompanyId();
		if (StringUtils.isEmpty(mobileN)) {
			resultMap.put("code", 5);
			resultMap.put("message", "手机号码不能为空!");
			return resultMap;
		}
		if (StringUtils.isEmpty(imgeKey)) {
			resultMap.put("code", 5);
			resultMap.put("message", "验证码不合法!");
			return resultMap;
		}
		if (StringUtils.isEmpty(checkImageCode)) {
			resultMap.put("code", 5);
			resultMap.put("message", "验证码不能为空!");
			return resultMap;
		}
		// 校验验证码
		if (checkImageCode == null
				|| CacheCommUtils.getCache().get(imgeKey) == null
				|| !checkImageCode.equalsIgnoreCase(CacheCommUtils.getCache()
						.get(imgeKey).toString())) {
			LogUtils.getLogger(this.getClass()).debug("验证码输入有误");
			resultMap.put("code", "-2");
			resultMap.put("message", "验证码输入有误");
			return resultMap;
		} else {
			CacheCommUtils.getCache().remove(imgeKey);
		}
		String ip = CommonUtils.getLocalIp(request);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", mobileN);
		params.put("ip", ip);
		int sendCount = captchasDAOWrite.selectCount(params);
		if (sendCount >= 10) {
			resultMap.put("code", "-3");
			resultMap.put("message", "验证码发送次数过多");
			return resultMap;
		}
		CaptchasPOExample captchasPOExample = new CaptchasPOExample();
		captchasPOExample.setOrderByClause("expire_time desc");
		CaptchasPOExample.Criteria criteria = captchasPOExample
				.createCriteria();
		criteria.andMobileEqualTo(captchas.getMobile())
				.andExpireTimeGreaterThanOrEqualTo(new Date())
				.andSuccessIsIsNull();
		List list = captchasDAOWrite.selectByExample(captchasPOExample, false);
		if (CollectionUtils.isNotEmpty(list)) {
			CaptchasPO captchas1 = (CaptchasPO) list.get(0);
			Calendar timeout = Calendar.getInstance();
			timeout.setTime(new Date());
			timeout.add(Calendar.MINUTE, 9);
			if (captchas1.getExpireTime().compareTo(timeout.getTime()) >= 0) {
				resultMap.put("code", 6);
				resultMap.put("message", "一分钟内至多发送一次");
				return resultMap;
			}
		}

		String charValue = "";
		charValue = getString(6);
		CaptchasVO captchasVO = new CaptchasVO();
		captchasVO.setCompanyId(companyId);
		;
		captchasVO.setMobile(mobileN);
		captchasVO.setSmsCode(charValue);
		String env = System.getProperty("env");
		int resultCode = 0;
		if (!"development".equalsIgnoreCase(env)) {// 开发环境不发送短信验证码
			resultCode = smsManage.sendSmsVerifyCode(mobileN, charValue);
		}
		if (resultCode == 0) {
			resultMap.put("code", 0);
			resultMap.put("message", "校验码发送成功!");
			// 生成失效时间
			Calendar timeout = Calendar.getInstance();
			timeout.setTime(new Date());
			timeout.add(Calendar.MINUTE, 10);
			// 如果为第十次发送，验证码有效期设置为当天有效
			if (10 == sendCount) {
				timeout.add(Calendar.MINUTE,
						TimeUtils.getMinFromNowTOToday() - 10);
			}
			// 保存手机号。验证码。失效时间
			CaptchasPO captchas2 = new CaptchasPO();
			captchas2.setMobile(mobileN);
			captchas2.setCaptchas(charValue);
			captchas2.setExpireTime(timeout.getTime());
			if (captchas.getCompanyId() != null) {
				captchas2.setCompanyId(captchas.getCompanyId());
			}
			captchas2.setSendip(ip);
			captchasDAOWrite.insert(captchas2);
		} else {
			resultMap.put("code", -1);
			resultMap.put("message", "校验码发送失败!");
		}
		return resultMap;
	}

	private String getString(int n) {
		// 生成6位验证码
		String charValue = "";
		for (int i = 0; i < n; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	public int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	@Override
	public HashMap<String, Object> checkCodeWithTx(MobileUserVO captchas)
			throws Exception {
		LogUtils.getLogger(getClass())
				.info("checkCodeWithTx----------------------------------------------");
		LogUtils.getLogger(getClass()).info(
				"captchas=" + captchas.getCaptchas() + "|" + "mobile="
						+ captchas.getMobile());
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ("development".equals(condition)) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("code", 0);
			resultMap.put("message", "该手机号还未注册!");
			return resultMap;
		}
		if (captchas.getMobile() == null) {
			resultMap.put("code", 2);
			resultMap.put("message", "手机号码不能为空!");
			return resultMap;
		}
		if (captchas.getCaptchas() == null) {
			resultMap.put("code", 3);
			resultMap.put("message", "验证码不能为空!");
			return resultMap;
		}
		// 根据手机号码和验证码查询验证码是否正确
		CaptchasPOExample captchasPOExample = new CaptchasPOExample();
		captchasPOExample.setOrderByClause("expire_time desc");
		CaptchasPOExample.Criteria criteria = captchasPOExample
				.createCriteria();
		criteria.andMobileEqualTo(captchas.getMobile())
				// .andCaptchasEqualTo(captchas.getCaptchas())
				.andExpireTimeGreaterThanOrEqualTo(new Date())
				.andSuccessIsIsNull();
		List list = captchasDAOWrite.selectByExample(captchasPOExample, false);
		if (null == list || list.size() == 0) {
			// 验证码错误
			resultMap.put("code", 1);
			resultMap.put("message", "验证码错误或失效!");
			return resultMap;
		}
		if (!captchas.getCaptchas().equals(
				((CaptchasPO) list.get(0)).getCaptchas())) {
			// 验证码错误
			resultMap.put("code", 1);
			resultMap.put("message", "验证码错误或失效!");
			return resultMap;
		}
		// 验证成功
		Calendar timeout = Calendar.getInstance();
		timeout.setTime(new Date());
		timeout.add(Calendar.MINUTE, TimeUtils.getMinFromNowTOToday() - 1);
		CaptchasPO captchas2 = (CaptchasPO) list.get(0);
		if (captchas2.getExpireTime().compareTo(timeout.getTime()) < 0) {
			captchas2.setSuccessIs(1);
		}
		captchasDAOWrite.updateByPrimaryKeySelective(captchas2);
		for (int i = 1; i < list.size(); i++) {
			CaptchasPO captchas1 = (CaptchasPO) list.get(i);
			if (captchas1.getExpireTime().compareTo(timeout.getTime()) < 0) {
				captchas1.setSuccessIs(0);
			}
			captchasDAOWrite.updateByPrimaryKeySelective(captchas1);
		}
		resultMap.put("code", 0);
		resultMap.put("message", "校验成功!");
		return resultMap;
	}

	@Override
	public HashMap<String, Object> checkUsernameAndMobile(MobileUserVO userVO)
			throws SQLException {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String temp;
		{
			temp = userVO.getUsername();
			if (StringUtils.isBlank(temp)) {
				resultMap.put("code", 1);
				resultMap.put("message", "用户名不能为空!");
				return resultMap;
			}
			ParamCheck.trimTemp(temp);
			userVO.setUsername(temp);
		}
		{
			temp = userVO.getMobile();
			if (StringUtils.isBlank(temp)) {
				resultMap.put("code", 2);
				resultMap.put("message", "手机号码不能为空!");
				return resultMap;
			}
			ParamCheck.trimTemp(temp);
			userVO.setMobile(temp);
		}
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userVO.getUsername())
				.andMobileEqualTo(userVO.getMobile())
				.andCompanyIdEqualTo(userVO.getCompanyId());
		List list = userDAOWrite.selectByExample(example, false);
		if (list != null && list.size() > 0) {
			resultMap.put("code", 0);
			resultMap.put("message", "用户名和号码匹配成功!");
			return resultMap;
		}
		resultMap.put("code", 3);
		resultMap.put("message", "用户名和号码不匹配!");
		return resultMap;
	}

	@Override
	public HashMap<String, Object> updateByIdWithTx(User user)
			throws SQLException {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		final MobileUserVO mobileUserVO = new MobileUserVO();
		BeanUtils.copyProperties(user, mobileUserVO);
		int i = userDAOWrite.updateByPrimaryKeySelective(user);
		if (i > 0) {// 维护修改用户信息动作日志
			userActionLogDAOWrite.insertUserActionLog(user,
					UserActionLogEnum.completeUser.getType());
		}

		resultMap.put("code", 0);
		resultMap.put("message", "更新成功!");
		return resultMap;
	}

	/**
	 * 查询手机号码是否被注册 TODO lws:
	 * isRepeatPhone又有不同的实现，那为什么isRepeatPhone不成为1个接口，实现不同的子类，工厂来获取实现
	 *
	 * @param userAndCaptchas
	 * @return
	 */
	@Override
	public HashMap<String, Object> isRepeatPhone(MobileUserVO userAndCaptchas) {
		logger.info("查询手机号码是否被注册,参数：");
		logger.info("手机号码：" + userAndCaptchas.getMobile());
		logger.info("用户名：" + userAndCaptchas.getUsername());
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", 0);
		if ("development".equals(condition)) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("code", 0);
			resultMap.put("message", "该手机号还未注册!");
			return resultMap;
		}
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(userAndCaptchas.getMobile());
		// 过滤掉 已注销的用户
		criteria.andIsDeletedEqualTo(0);
		try {
			logger.info("查询新系统是否存在该用户： ");
			List list = userDAOWrite.selectByExample(example, false);
			logger.info("新系统-查询结果大小： " + list.size());
			if (list.size() > 0) {
				resultMap.put("code", -1);
				resultMap.put("message", "手机号码已经被其他用户注册!");
			}

			return resultMap;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			resultMap.put("code", -2);
			resultMap.put("message", "系统错误!");
			return resultMap;
		}
	}

	/**
	 * 查询手机号码是否被注册
	 *
	 * @param userAndCaptchas
	 * @return
	 */
	@Override
	public HashMap<String, Object> isRepeatPhoneFormLocal(
			MobileUserVO userAndCaptchas) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(userAndCaptchas.getMobile());
		criteria.andCompanyIdEqualTo(userAndCaptchas.getCompanyId());
		// 注销的用户 去除掉
		criteria.andIsDeletedEqualTo(0);
		try {
			List list = userDAOWrite.selectByExample(example, false);
			if (CollectionUtils.isEmpty(list)) {
				resultMap.put("code", 0);
				resultMap.put("message", "该手机号还未注册!");
				return resultMap;
			}
			resultMap.put("code", -1);
			resultMap.put("message", "手机号码已经被注册!");
			return resultMap;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			resultMap.put("code", -2);
			resultMap.put("message", "系统错误!");
			return resultMap;
		}
	}

	/**
	 * 根据id查询改用户是否绑定手机，绑定返回true
	 *
	 * @param mobileUserVO
	 * @return
	 */
	@Override
	public boolean hasMobile(MobileUserVO mobileUserVO) throws SQLException {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(mobileUserVO.getId()).andMobileEqualTo(
				mobileUserVO.getMobile());
		List list = userDAOWrite.selectByExample(example, false);
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}
		return true;

	}

	/**
	 * 根据id查询用户是否已经绑定手机号码
	 *
	 * @param mobileUserVO
	 * @return
	 */
	@Override
	public boolean hasAMobile(MobileUserVO mobileUserVO) throws SQLException {

		User user = userDAOWrite
				.selectByPrimaryKey(mobileUserVO.getId(), false);
		if (StringUtil.isBlank(user.getMobile())) {
			return false;
		}
		return true;

	}

	/**
	 * 根据手机号码和公司id查询
	 *
	 * @param mobile
	 * @param companyId
	 * @return
	 */
	@Override
	public boolean isRepeatMobile(String mobile, Long companyId)
			throws SQLException {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(mobile).andCompanyIdEqualTo(companyId);
		List list = userDAOWrite.selectByExample(example, false);
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}
		return true;
	}

	/**
	 * 根据密码查询是否正确
	 *
	 * @param userAndCaptchas
	 * @return
	 */
	@Override
	public boolean checkPasswordByUt(MobileUserVO userAndCaptchas)
			throws SQLException {
		final UserExample example = new UserExample();
		final Criteria criteria = example.createCriteria();
		String password = "";
		// ---------------------------password=PassWordUtils.getSecretPassWord(userAndCaptchas.getPassword());
		User encryptUser = new User();// 1 用于查询用户密码加密信息
		encryptUser.setId(userAndCaptchas.getId());

		// 2 查询用户的密码加密信息
		EncryptEntity encryptEntity = encryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);
		if (StringUtils.isEmpty(encryptEntity.getOldBSalt())) {// 没有old盐值,是修改之前的用户,原密码md5加密一次
			// c.andPasswordEqualTo(PassWordUtils.getSecretPassWord(user.getPassword(),"",1));
			password = PasswordUtil.getSecretPassWord(
					userAndCaptchas.getPassword(), "", 1);
		} else {// 有old盐值,就用old盐值跟old算法加密
			if (null != encryptEntity.getOldBVersion()) {
				// c.andPasswordEqualTo(PassWordUtils.getSecretPassWord(user.getPassword(),encryptEntity.getOldBSalt(),encryptEntity.getOldBVersion()));
				password = PasswordUtil.getSecretPassWord(
						userAndCaptchas.getPassword(),
						encryptEntity.getOldBSalt(),
						encryptEntity.getOldBVersion());
			}
		}

		criteria.andIdEqualTo(userAndCaptchas.getId());
		criteria.andPasswordEqualTo(password);

		final List list = userDAOWrite.selectByExample(example, false);
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}
		if (list.size() > 1) {
			return false;
		}
		return true;
	}

	/**
	 * 根据用户id修改手机号码
	 *
	 * @param userAndCaptchas
	 */
	@Override
	public void modifyMobileWithTx(MobileUserVO userAndCaptchas) {
		String oldMobile = null;
		User userPO = null;

		final User user = new User();
		user.setMobile(userAndCaptchas.getMobile());
		user.setId(userAndCaptchas.getId());

		try {
			userPO = userDAOWrite.selectByPrimaryKey(user.getId(), false);
			if (null != userPO) {
				oldMobile = userPO.getMobile();
			}

		} catch (SQLException e) {
			logger.error(
					String.format("select user by id error. userid:%s",
							user.getId()), e);
		}

		try {
			userDAOWrite.updateByPrimaryKeySelective(user);
		} catch (SQLException e) {
			logger.error(String.format("update user error. user:%s", user), e);
		}

	}

	/**
	 * 根据手机号码查询，是否有校验过的验证码
	 *
	 * @param mobileUserVO
	 * @return
	 */
	@Override
	public boolean hasAvailableCode(MobileUserVO mobileUserVO) {
		if (mobileUserVO.getMobile() == null) {
			return false;
		}
		final CaptchasPOExample example = new CaptchasPOExample();
		final CaptchasPOExample.Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(mobileUserVO.getMobile());
		if (mobileUserVO.getCompanyId() != null) {
			criteria.andCompanyIdEqualTo(mobileUserVO.getCompanyId());
		}
		criteria.andSuccessIsEqualTo(1);
		criteria.andExpireTimeGreaterThan(new Date());
		try {
			final List list = captchasDAOWrite.selectByExample(example, false);
			if (CollectionUtils.isEmpty(list)) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			return false;
		}
	}

	public Map<String, Object> getCaptchasByMobile(MobileUserVO userDto)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if (null == userDto || null == userDto.getMobile()) {
			return result;
		}
		CaptchasPOExample example = new CaptchasPOExample();
		CaptchasPOExample.Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(userDto.getMobile());
		example.setOrderByClause("id desc");
		List<CaptchasPO> listData = captchasDAOWrite.selectByExample(example,
				false);
		if (null != listData && !listData.isEmpty()) {
			CaptchasPO captchas = listData.get(0);
			result.put("captchas", captchas.getCaptchas());
		}
		return result;
	}

	private static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null)
			return null;

		Map<String, Object> map = new HashMap<String, Object>();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter != null ? getter.invoke(obj) : null;
			map.put(key, value);
		}

		return map;
	}

	@Override
	public User addUserWhereInOldWithTx(UserInfoFromOldVO userInfoFromOld)
			throws SQLException {
		User user = new User();
		user.setUsername(userInfoFromOld.getUsername());// 用户名
		user.setNickname(userInfoFromOld.getNickname());// 昵称
		user.setMobile(userInfoFromOld.getMobile());// 手机
		user.setIdentityCardName(userInfoFromOld.getIdentityCardName());// 真实姓名
		if (StringUtils.isNotBlank(userInfoFromOld.getPassword())) {
			user.setPassword(SecurityUtils.getMD5Str(userInfoFromOld
					.getPassword()));// md5加密
		}
		user.setHeadPicUrl(userInfoFromOld.getHeadPicUrl());// 头像
		user.setEmail(userInfoFromOld.getEmail());// 邮箱
		user.setAddress(userInfoFromOld.getAddress());// 地址
		user.setUserCity(userInfoFromOld.getCity());// 城市
		user.setUserProvince(userInfoFromOld.getProvince());// 省
		user.setSex(userInfoFromOld.getSex());// 性别
		user.setStatus(userInfoFromOld.getStatus());
		// if(null!=userInfoFromOld.getQq() &&
		// !StringUtils.isEmpty(userInfoFromOld.getQq())){
		// user.setQq(userInfoFromOld.getQq());//qq
		// }
		// user.setBirthday(userInfoFromOld.getBirthday());//生日
		user.setParentId(0L);
		user.setType(4);
		Long userId = userDAOWrite.insert(user);
		user.setId(userId);

		// 扩展的用户信息-老系统获取
		Map<UserExtEnum, String> map = userInfoFromOld.getMap();
		// 遍历map,插入记录
		Iterator<Map.Entry<UserExtEnum, String>> entries = map.entrySet()
				.iterator();
		List<UserExtPO> list = new ArrayList<UserExtPO>();
		while (entries.hasNext()) {
			UserExtPO userExt = new UserExtPO();
			Map.Entry<UserExtEnum, String> entry = entries.next();
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());
			String showName = entry.getKey().getShowName();
			userExt.setUserId(userId);
			userExt.setExtKey(entry.getKey().toString());
			userExt.setExtValue(entry.getValue());
			userExt.setShowName(showName);
			list.add(userExt);
		}
		// 批量插入
		userExtDAOWrite.batchInsert(list);
		userMappingPO userMapping = new userMappingPO();
		userMapping.setUserId(userId);// 新系统id
		userMapping.setOldSystemId(userInfoFromOld.getOldSystemId());// 老系统ID
		userMapping.setRemark(userInfoFromOld.getJsonStringInfo());// json字符串信息
		userMappingDAOWrite.insert(userMapping);

		return user;
	}

	/**
	 * 绑定、换绑发送MQ消息
	 */
	@Override
	public void sendMsg(String mobile, String userId) {
		String updateType = "1";// 发送消息类型为更新用户手机号码

		if (StringUtils.isBlank(mobile)) {
			logger.error("手机绑定中手机号码为空");
			return;
		}
		if (StringUtils.isBlank(userId)) {
			logger.error("手机绑定用户id为空");
			return;
		}

		Map<String, String> messageObject = new HashMap<>();
		messageObject.put("mobile", mobile);
		messageObject.put("userId", userId);
		messageObject.put("updateType", updateType);

		logger.info("用户绑定手机发送MQ成功");
	}

	@Override
	public HashMap<String, Object> updateBussinessPassWord(MobileUserVO userDto)
			throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		String oldPassword = userDto.getPassword();
		User encryptUser = new User();// 1 用于查询用户密码加密信息
		if (userDto.getType() != null) {
			encryptUser.setType(userDto.getType());
		}
		if (null != userDto.getId()) {
			encryptUser.setId(userDto.getId());
		} else {
			if (StringUtils.isNotEmpty(userDto.getMobile())) {
				encryptUser.setMobile(userDto.getMobile());
			} else {
				resultMap.put("code", "3");
				resultMap.put("message", "参数信息异常.");
				return resultMap;
			}
		}
		User user = new User();
		// 2 查询用户的密码加密信息
		EncryptEntity encryptEntity = encryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);

		String password1 = userDto.getPassword1();

		if (StringUtils.isBlank(password1)) {
			resultMap.put("code", 3);
			resultMap.put("message", "密码不能空");
			return resultMap;
		}
		if (password1.length() < 6) {
			resultMap.put("code", 5);
			resultMap.put("message", "密码不能少于6位");
			return resultMap;
		}

		String password = "";// 新密码加密之后密文
		Date now = new Date();

		// 3 如果用户有新的盐值或者新的算法version,用新的加密保存然后更新到数据库
		if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())
				|| null != encryptEntity.getNewBVersion()) {
			String salt = StringUtils.isEmpty(encryptEntity.getNewBSalt()) ? encryptEntity
					.getOldBSalt() : encryptEntity.getNewBSalt();
			Integer version = (null == encryptEntity.getNewBVersion()) ? encryptEntity
					.getOldBVersion() : encryptEntity.getNewBVersion();

			password = PasswordUtil.getSecretPassWord(password1, salt, version);

			if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
				user.setbSalt(encryptEntity.getNewBSalt());
				user.setbSaltUpdateTime(now);
			}
			if (null != encryptEntity.getNewBVersion()) {
				user.setbAlgorithmVersion(encryptEntity.getNewBVersion());
			}
		} else {// 没有返回新的盐值和算法,肯定返回了旧的算法和盐值
			String salt = encryptEntity.getOldBSalt();
			Integer version = encryptEntity.getOldBVersion();
			password = PasswordUtil.getSecretPassWord(password1, salt, version);
		}

		// ---------------------user.setPassword(PassWordUtils.getSecretPassWord(password1));
		user.setPassword(password);

		// 更新前端加密算法信息
		if (StringUtils.isNotEmpty(userDto.getNewFSalt())) {
			user.setfSalt(userDto.getNewFSalt());
			user.setfSaltUpdateTime(now);
		}
		if (null != userDto.getNewFVersion()) {
			user.setfAlgorithmVersion(userDto.getNewFVersion());
		}

		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();

		if (StringUtils.isBlank(oldPassword)) {
			user.setId(userDto.getId());
			criteria.andMobileEqualTo(userDto.getMobile());

		} else {
			criteria.andIdEqualTo(userDto.getId());
			user.setId(userDto.getId());
		}
		criteria.andIsDeletedEqualTo(0);

		int result = userDAOWrite.updateByExampleSelective(user, example);
		if (result == 1) {
			resultMap.put("code", 0);
			resultMap.put("message", "修改成功");
		} else {
			throw new OuserMangeException("用户信息不正确");
		}
		try {
			userActionLogDAOWrite.insertUserActionLog(user,
					UserActionLogEnum.changePassword.getType());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return resultMap;
	}

}
