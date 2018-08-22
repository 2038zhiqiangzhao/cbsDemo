package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.CacheCommUtils;
import com.people2000.user.business.utils.CalcEncryInfoUtils;
import com.people2000.user.business.utils.ConstantUser;
import com.people2000.user.business.utils.PasswordUtil;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.utils.UserActionLogEnum;
import com.people2000.user.business.utils.UserUtil;
import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.business.utils.uionlogin.UnionLoginGate;
import com.people2000.user.business.utils.uionlogin.WXLoginService;
import com.people2000.user.business.write.dao.UUnionConfigDAOWrite;
import com.people2000.user.business.write.dao.UUnionLoginDAOWrite;
import com.people2000.user.business.write.dao.UserLoginDAOWrite;
import com.people2000.user.business.write.dao.UserRoleDAOWrite;
import com.people2000.user.business.write.dao.ext.UserActionLogDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.manage.EncryptInfoWriteManage;
import com.people2000.user.business.write.manage.LoginWriteManage;
import com.people2000.user.business.write.manage.UnionLoginManage;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.model.constant.BindMobileErrorCode;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.dto.UserUnionLoginDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.UUnionConfigPO;
import com.people2000.user.model.po.ibatis.UUnionConfigPOExample;
import com.people2000.user.model.po.ibatis.UUnionLoginPO;
import com.people2000.user.model.po.ibatis.UUnionLoginPOExample;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.po.ibatis.UserExample;
import com.people2000.user.model.po.ibatis.UserLogin;
import com.people2000.user.model.vo.MobileUserVO;

/**
 * Created by dailf on 2016/7/28.
 */
@Service("unionLoginManage")
public class UnionLoginManageImpl implements UnionLoginManage {

	@Resource
	private LoginWriteManage loginWriteManage;
	@Resource
	private UserLoginDAOWrite userLoginDAOWrite;
	@Resource
	private UserCookieWriteManage userCookieWriteManage;
	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;
	@Resource
	private UUnionConfigDAOWrite uUnionConfigDAOWrite;
	@Resource
	private EncryptInfoWriteManage encryptInfoWriteManage;
	@Resource
	private UserRoleDAOWrite userRoleDAOWrite;

	@Resource(name = "userActionLogDAOWrite")
	private UserActionLogDAOWrite2 userActionLogDAOWrite;

	@Resource
	private UUnionLoginDAOWrite uUnionLoginDAOWrite;

	@Resource(name = "wxLoginService")
	private WXLoginService wxLoginService;

	private static final int ut_timeout = 60; // 分钟

	private Logger logger = LogUtils.getLogger(UnionLoginManageImpl.class);

	@Override
	public Object savaUnionUserInfoWithTx(UserUnionLoginDTO userUnionLoginDTO)
			throws Exception {
		return UnionLoginGate.savaUnionUserInfoWithTx(userUnionLoginDTO);
	}

	@Override
	public void stateValidate(UserUnionLoginDTO userUnionLoginDTO) {
		// 如果是APP调用则不验证
		if (!StringUtil.isBlank(userUnionLoginDTO.getToken())
				&& !StringUtil.isBlank(userUnionLoginDTO.getOpenid())
				&& !StringUtil.isBlank(userUnionLoginDTO.getAppid())) {
			LogUtils.getLogger(getClass()).info("APP联合登录验证通过");
		} else {
			if (StringUtil.isBlank(userUnionLoginDTO.getState())) {
				throw new RuntimeException("state参数不能为空");
			}
			String flagFromReq = userUnionLoginDTO.getState().split("_")[0];
			String flag = (String) CacheCommUtils.getCache(flagFromReq);
			if (StringUtils.isBlank(flag) || !flagFromReq.equals(flag)) {
				throw new RuntimeException("state参数无法对应");
			}
		}
	}

	@Override
	public UserCache getUserCache(Long userId) throws Exception {
		UserCache userCache = null;
		// 获取用户
		User user = userDAOWrite.selectByPrimaryKey(userId, false);
		// 添加户信息到缓存
		userCache = loginWriteManage.getCacheUser(user);
		return userCache;
	}

	@Override
	public String getRelatedParams(UserUnionLoginDTO userUnionLoginDTO)
			throws SQLException {

		return UnionLoginGate.getRelatedParams(userUnionLoginDTO);
	}

	@Override
	public boolean checkPassword() throws SQLException {
		Long userId = UserUtil.getCurrentMainAccountUserId();
		User user = userDAOWrite.selectByPrimaryKey(userId, false);
		if (StringUtil.isBlank(user.getPassword())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean bindUserPasswordWithTx(MobileUserVO userVo)
			throws SQLException {
		Long userId = UserUtil.getCurrentMainAccountUserId();
		if (null == userId) {
			throw new RuntimeException("无法获取到当前用户");
		}
		if (StringUtil.isBlank(userVo.getPassword())) {
			throw new RuntimeException("密码不能为空");
		}
		LogUtils.getLogger(getClass()).info("当前绑定密码的用户ID为：" + userId);
		User user = userDAOWrite.selectByPrimaryKey(userId, false);
		// 密码进行MD5加密
		// userDto.setPassword(SecurityUtils.getMD5Str(userDto.getPassword()));
		if (!StringUtil.isBlank(user.getPassword())) {
			throw new RuntimeException("用户已经绑定了密码，不能再次绑定");
		}
		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = encryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);
		String password = PasswordUtil.getSecretPassWord(userVo.getPassword(),
				encryptEntity.getNewBSalt(), encryptEntity.getNewBVersion());
		user.setPassword(password);

		// -----------------userDto.setPassword(PassWordUtils.getSecretPassWord(userDto.getPassword()));
		// 前端算法盐值信息
		Date now = new Date();
		user.setfAlgorithmVersion(userVo.getNewFVersion());
		user.setfSalt(userVo.getNewFSalt());
		if (StringUtils.isNotEmpty(userVo.getNewFSalt())) {
			user.setfSaltUpdateTime(now);
		}
		// BeanUtils.copyProperties(userVo, user);
		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
			user.setbSaltUpdateTime(now);
		}
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());
		if (StringUtils.isEmpty(user.getUsername())) {
			user.setUsername("");
		}
		userDAOWrite.updateByPrimaryKeySelective(user);
		try {
			userActionLogDAOWrite.insertUserActionLog(user,
					UserActionLogEnum.changePassword.getType());
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
		}

		return Boolean.TRUE;
	}

	@Override
	public Map<Integer, Integer> iconInvisible() throws Exception {
		// 获取域名和公司ID
		// 访问域
		String accessDomain = "";

		Map<Integer, Integer> returnMap = new HashMap<Integer, Integer>();
		UUnionConfigPOExample example = new UUnionConfigPOExample();
		example.createCriteria().andAppDomainEqualTo(accessDomain);
		List<UUnionConfigPO> configList = uUnionConfigDAOWrite.selectByExample(
				example, false);
		if (!CollectionUtils.isEmpty(configList)) {
			for (UUnionConfigPO thisObj : configList) {
				returnMap.put(thisObj.getGateway(), thisObj.getStatus());
			}
		}
		return returnMap;
	}

	@Override
	public Map<String, String> userUniqueCheckWithTx(MobileUserVO mobileUserVO)
			throws Exception {
		Map<String, String> returnMap = new HashMap<>();
		String openid = "";
		String username = "";
		// 验证手机号和ut是否传入
		if (StringUtil.isBlank(mobileUserVO.getMobile())) {
			throw new RuntimeException("手机号不能为空");
		}
		if (StringUtil.isBlank(mobileUserVO.getUt())) {
			throw new RuntimeException("ut不能为空");
		}
		// 获取openid和username
		Map<String, String> openMap = SsoClientUtil.getUnionOpenId(mobileUserVO
				.getUt());
		openid = openMap.get("openid");
		username = openMap.get("username");

		// 判断当前手机是否绑定用户
		UserExample userExample = new UserExample();
		// 查询用户
		userExample.createCriteria().andMobileEqualTo(mobileUserVO.getMobile())
				.andIsDeletedEqualTo(0)
				.andCompanyIdEqualTo(mobileUserVO.getCompanyId());
		List<User> users = null;
		try {
			users = userDAOWrite.selectByExample(userExample, false);
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage());
		}
		User user;

		// 判断是否有用户，没有则创建

		if (CollectionUtils.isEmpty(users)) {
			// 保存用户
			{
				user = new User();
				user.setUsername(username);
				user.setCompanyId(mobileUserVO.getCompanyId());
				user.setType(ConstantUser.user_type.member);
				user.setParentId(0L);
				user.setMobile(mobileUserVO.getMobile());
				user.setStatus(1);
				Long insertId = userDAOWrite.insert(user);
				user.setId(insertId);
			}
			// 有用户则绑定
		} else {
			user = users.get(0);
		}

		// 更新联合登录用户
		UUnionLoginPOExample example = new UUnionLoginPOExample();
		example.createCriteria().andOauthOpenIdEqualTo(openid)
				.andUserIdEqualTo(ConstantUser.default_union_userid)
				.andIsDeletedEqualTo(0);
		List<UUnionLoginPO> unionLoginPOs = uUnionLoginDAOWrite
				.selectByExample(example, false);
		if (CollectionUtils.isEmpty(unionLoginPOs)) {
			returnMap.put("code", BindMobileErrorCode.user_login_missparams);
			returnMap.put("msg", "无法查询到对应的登录参数,请重新进行绑定");
			return returnMap;
		} else {
			unionLoginPOs.get(0).setUserId(user.getId());
			uUnionLoginDAOWrite.updateByPrimaryKeySelective(unionLoginPOs
					.get(0));
			returnMap.put("code", "0");
			returnMap.put("msg", "绑定成功");
		}
		// userCache获取失败
		UserCache userCache = null;
		try {
			userCache = loginWriteManage.getCacheUser(user);
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error("userCache获取失败");
			returnMap.put("code", BindMobileErrorCode.user_cacheuser_geterror);
			returnMap.put("msg", "userCache获取失败");
			return returnMap;
		}
		// 刷新ut
		UserCookie userCookie = null;
		try {
			userCookie = SsoClientUtil.unionLogin(userCache,
					user.getCompanyId(), mobileUserVO.getUt());
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error("ut刷新失败");
			returnMap.put("code", BindMobileErrorCode.user_ut_refresh_error);
			returnMap.put("msg", "ut刷新失败");
			return returnMap;
		}
		// 保存登录日志和cookie
		try {
			UserLogin userLogin = new UserLogin();
			int code = Integer.parseInt(returnMap.get("code"));// code 等于
																// 0表示登陆成功
			userLogin.setLoginResult(code);
			userLogin.setUsername(userCache.getUsername());
			userLogin.setUserId(userCache.getId());
			Long userLoginId = userLoginDAOWrite.insert(userLogin);

			userCookie.setUserLoginId(userLoginId);
			userCookie.setCompanyId(userCache.getCompanyId());
			userCookieWriteManage.saveUserCookieWithTx(userCookie);
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error("保存登录日志和缓存失败");
		}
		return returnMap;
	}

	/**
	 * 通过unionId查询用户id
	 * 
	 * @author wupeng
	 * @date 2016/10/13
	 * @param
	 * @return
	 * @throws
	 */
	@Override
	public UserUnionLoginDTO getUserByUnionId(String unionId)
			throws OuserMangeException {
		try {

			logger.info("通过unionId查询用户，unionId=" + unionId);

			UUnionLoginPOExample example = new UUnionLoginPOExample();
			UUnionLoginPOExample.Criteria criteria = example.createCriteria();
			criteria.andOauthUnionIdEqualTo(unionId);
			criteria.andIsDeletedEqualTo(0);
			criteria.andUserIdNotEqualTo(-1L);// 过滤userId为-1 的union_login记录

			List<UUnionLoginPO> list = uUnionLoginDAOWrite.selectByExample(
					example, false);
			if (list.size() > 0) {
				UUnionLoginPO uUnionLoginPO = list.get(0);
				UserUnionLoginDTO userUnionLoginDTO = new UserUnionLoginDTO();

				userUnionLoginDTO.setUserId(uUnionLoginPO.getUserId());

				logger.info("通过unionId查询用户结果，unionId=" + unionId + ",userId="
						+ userUnionLoginDTO.getUserId());
				return userUnionLoginDTO;
			}
		} catch (SQLException e) {
			logger.error("通过unionId查询用户id出错", e);
			throw new OuserMangeException("通过unionId查询用户id出错");
		}

		return null;
	}

	/**
	 * 手机号码绑定unionId
	 * 
	 * @author wupeng
	 * @date 2016/10/13
	 * @param
	 * @return
	 * @throws
	 */
	@Override
	@Transactional
	public Map<String, Object> bindUnionToMobileWithTx(
			UserUnionLoginDTO unionLoginDTO) throws OuserMangeException {

		Map<String, Object> result = new HashMap<>();
		try {
			logger.info("通过手机号码+密码绑定unionId，参数：mobile="
					+ unionLoginDTO.getMobile() + ",unionId="
					+ unionLoginDTO.getUnionId());

			// 验证unionId是否已经绑定过
			UUnionLoginPOExample example2 = new UUnionLoginPOExample();
			UUnionLoginPOExample.Criteria criteria2 = example2.createCriteria();
			criteria2.andOauthUnionIdEqualTo(unionLoginDTO.getUnionId());
			criteria2.andUserIdNotEqualTo(-1L);
			criteria2.andIsDeletedEqualTo(0);

			Integer count = uUnionLoginDAOWrite.countByExample(example2, false);
			logger.info("验证unionId是否已经绑定过手机号码结果：" + count);
			if (count > 0) {
				logger.error("  验证unionId已经绑定过手机号码");
				result.put("code", "4"); // 验证unionId已经绑定过手机号码
				result.put("msg", "验证unionId已经绑定过手机号码");
				return result;
			}

			// 验证手机号、密码
			Long userId = validMobileAndPassword(unionLoginDTO);

			if (userId == null) {
				logger.error("手机号，密码验证不成功，手机号码【" + unionLoginDTO.getMobile()
						+ "】");
				result.put("code", "2"); // 手机号，密码验证不成功
				result.put("msg", "手机号，密码验证不成功");
				return result;
			}

			// 用户id绑定unionId
			UUnionLoginPOExample example = new UUnionLoginPOExample();
			UUnionLoginPOExample.Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId);
			criteria.andIsDeletedEqualTo(0);

			List<UUnionLoginPO> list = uUnionLoginDAOWrite.selectByExample(
					example, false);

			if (!CollectionUtils.isEmpty(list)) {
				for (UUnionLoginPO uul : list) {
					if (StringUtils.isNotBlank(uul.getOauthUnionId())
							&& uul.getOauthUnionId().equals(
									unionLoginDTO.getUnionId())) {
						logger.info("该用户已经绑定当前微信，不需要重复绑定：mobile="
								+ unionLoginDTO.getMobile() + ",unionId="
								+ unionLoginDTO.getUnionId());
						result.put("code", "0");// 成功
						result.put("userId", userId);
						return result;
					}
				}
			}

			unionLoginDTO.setUserId(userId);

			this.saveUnionIdWithTx(unionLoginDTO);// 走统一添加逻辑,防止漏了哪个字段

			logger.info("手机号码绑定unionId成功，参数：" + unionLoginDTO.getMobile());

			result.put("code", "0");// 成功
			result.put("userId", userId);
			return result;

		} catch (SQLException e) {

			LogUtils.getLogger(getClass()).error("手机号码绑定unionId DB 操作异常", e);
			throw new OuserMangeException("手机号码绑定unionId DB 操作异常");
		}
	}

	/**
	 * 验证手机号码、密码
	 * 
	 * @author wupeng
	 * @date 2016/10/14
	 * @param
	 * @return
	 * @throws
	 */
	private Long validMobileAndPassword(UserUnionLoginDTO unionLoginDTO) {
		Long userId = null;
		try {

			logger.info("验证手机号码+密码参数：mobile=" + unionLoginDTO.getMobile());

			// 性能测试优化,先查询手机号
			HashMap<String, Object> userMobileparams = new HashMap<String, Object>();
			userMobileparams.put("username", unionLoginDTO.getMobile());

			List<User> listUser = userDAOWrite
					.selectByMobilePassAndPlatformId(userMobileparams);

			EncryptEntity encryptEntity = new EncryptEntity();
			if (!CollectionUtils.isEmpty(listUser)) {// 查询到记录,验证密码正确性

				logger.info("手机号码存在，验证密码mobile=" + unionLoginDTO.getMobile());
				User euser = listUser.get(0);

				encryptEntity = CalcEncryInfoUtils.calc(euser);

				// 没有盐值,是修改之前的用户,原密码md5加密一次
				String passWordNullSalt = PasswordUtil.getSecretPassWord(
						unionLoginDTO.getPassword(), "", 1);
				// 有old盐值,就用old盐值跟old算法加密
				String passWordWithSalt = PasswordUtil.getSecretPassWord(
						unionLoginDTO.getPassword(),
						(null == encryptEntity.getOldBSalt()) ? ""
								: encryptEntity.getOldBSalt(),
						(null == encryptEntity.getOldBVersion()) ? 1
								: encryptEntity.getOldBVersion());

				if (StringUtils.isEmpty(encryptEntity.getOldBSalt())) {// 没有old盐值,是修改之前的用户,原密码md5加密一次
					if (euser.getPassword().equals(passWordNullSalt)) {
						userId = euser.getId();
					}
				} else {// 有old盐值,就用old盐值跟old算法加密
					if (null != encryptEntity.getOldBVersion()) {
						if (euser.getPassword().equals(passWordWithSalt)) {
							userId = euser.getId();
						}
					}
				}

			}

			logger.info("验证结束，结果userId=" + userId);

		} catch (Exception e) {
			LogUtils.getLogger(this.getClass()).error(
					"查询用户出现异常，mobile:" + unionLoginDTO.getMobile(), e);
		}
		return userId;
	}

	/**
	 * 通过oauthCode获取用户的unionId
	 * 
	 * @param oauthCode
	 * @param oauthState
	 * @return
	 */
	@Override
	public UserUnionLoginDTO getUnionIdByCode(
			UserUnionLoginDTO userUnionLoginDTO) {

		userUnionLoginDTO.setGateway(2);// 微信
		logger.info("通过oauthCode获取用户的unionId，参数："
				+ JSON.toJSONString(userUnionLoginDTO));
		/*
		 * try { wxLoginService.getCompanyUnionInfo(userUnionLoginDTO);
		 * logger.info
		 * ("获取不同公司的第三方登录数据，结果："+JSON.toJSONString(userUnionLoginDTO)); } catch
		 * (SQLException e) { e.printStackTrace(); }
		 */

		try {
			wxLoginService.getAccessToken(userUnionLoginDTO);
			logger.info("获取微信接口获取accessToken，结果："
					+ JSON.toJSONString(userUnionLoginDTO));
			wxLoginService.getUserInfo(userUnionLoginDTO);
			logger.info("获取微信用户相关信息，结果：" + JSON.toJSONString(userUnionLoginDTO));
			return userUnionLoginDTO;
		} catch (Exception e) {
			LogUtils.getLogger(this.getClass()).error("获取用户unionId出现异常", e);
		}
		return null;
	}

	/**
	 * 保存用户unionId
	 * 
	 * @param unionLoginDTO
	 */
	@Override
	@Transactional
	public void saveUnionIdWithTx(UserUnionLoginDTO unionLoginDTO)
			throws OuserMangeException {
		UUnionLoginPO record = new UUnionLoginPO();

		// fixme:如果存在就更新数据，绑定用户
		UUnionLoginPOExample example = new UUnionLoginPOExample();
		example.createCriteria()
				.andIsDeletedEqualTo(0)
				.andOauthUnionIdEqualTo(unionLoginDTO.getUnionId())
				.andUserIdEqualTo(ConstantUser.default_union_userid.longValue());

		List<UUnionLoginPO> list = null;
		try {
			list = uUnionLoginDAOWrite.selectByExample(example, false);
		} catch (SQLException e) {
			logger.error("查询unionId绑定关系出错", e);
			throw new OuserMangeException("查询unionId绑定关系出错");
		}

		if (CollectionUtils.isEmpty(list)) {
			record.setUserId(unionLoginDTO.getUserId());
			record.setOauthUnionId(unionLoginDTO.getUnionId());
			record.setOauthOpenId(unionLoginDTO.getUnionId());
			record.setOauthAccessToken(unionLoginDTO.getUnionId());
			record.setOauthRefreshToken(unionLoginDTO.getUnionId());
			record.setOauthSourceSystem(2);// 微信，之前没加，通过公司id查询用户信息会报错
			try {
				logger.info("保存用户unionId，参数：" + JSON.toJSONString(record));
				uUnionLoginDAOWrite.insert(record);
				logger.info("保存用户unionId，结束");
			} catch (SQLException e) {
				LogUtils.getLogger(this.getClass()).error("保存用户unionId异常", e);
				throw new OuserMangeException("保存用户unionId异常");
			}
		} else {
			UUnionLoginPO uUnionLoginPO = list.get(0);

			record.setUserId(uUnionLoginPO.getUserId());
			record.setId(uUnionLoginPO.getId());

			try {
				uUnionLoginDAOWrite.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				LogUtils.getLogger(this.getClass()).error("更新用户unionId异常", e);
				throw new OuserMangeException("更新用户unionId异常");
			}

		}
	}

}
