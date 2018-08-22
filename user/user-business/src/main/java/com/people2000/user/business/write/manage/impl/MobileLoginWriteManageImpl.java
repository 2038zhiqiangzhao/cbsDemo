package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.CalcEncryInfoUtils;
import com.people2000.user.business.utils.ParamCheck;
import com.people2000.user.business.utils.PasswordUtil;
import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.business.write.dao.UserCookieDAOWrite;
import com.people2000.user.business.write.dao.UserGradeDAOWrite;
import com.people2000.user.business.write.dao.UserLoginDAOWrite;
import com.people2000.user.business.write.dao.ext.FunctionDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.manage.AppMessageManage;
import com.people2000.user.business.write.manage.MobileLoginWriteManage;
import com.people2000.user.business.write.manage.MobileUserWriteManage;
import com.people2000.user.business.write.manage.PasswordEncryptInfoWriteManage;
import com.people2000.user.business.write.manage.PushMessageWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.PushMessageInputDTO;
import com.people2000.user.model.dto.PushMessageOutputDTO;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.po.ibatis.UserGradePO;
import com.people2000.user.model.po.ibatis.UserLogin;
import com.people2000.user.model.vo.MobileUserVO;

/**
 * @author xxl
 * @time 2015-9-8 下午5:29:40
 * @description <pre>
 * 登录逻辑处理类
 * </pre>
 */
@Service("mobileLoginWriteManageImpl")
public class MobileLoginWriteManageImpl implements MobileLoginWriteManage {

	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;

	@Resource(name = "userLoginDAOWrite")
	private UserLoginDAOWrite userLoginDAOWrite;

	@Resource(name = "userCookieDAOWrite")
	private UserCookieDAOWrite userCookieDAOWrite;

	@Resource(name = "functionDAOWrite")
	private FunctionDAOWrite2 functionDAOWrite;

	@Resource(name = "mobileUserWriteManage")
	private MobileUserWriteManage mobileUserWriteManage;

	@Resource(name = "passwordEncryptInfoWriteManage")
	private PasswordEncryptInfoWriteManage passwordEncryptInfoWriteManage;
	@Resource(name = "userGradeDAOWrite")
	private UserGradeDAOWrite userGradeDAOWrite;
	@Resource
	private PushMessageWriteManage pushMessageWriteManage;
	@Resource
	private AppMessageManage appMessageManage;

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> login(MobileUserVO user,
			HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		String tempUsername = ParamCheck.trimTemp(user.getUsername());
		if (StringUtils.isBlank(user.getUsername())) {
			resultMap.put("code", 1);
			resultMap.put("message", "用户名称为空，登录失败！");
			LogUtils.getLogger(this.getClass()).debug("用户名称为空，登录失败！");
			return resultMap;
		}
		user.setUsername(tempUsername);

		String tempPassword = ParamCheck.trimTemp(user.getPassword());
		if (StringUtils.isBlank(user.getPassword())) {
			resultMap.put("code", 2);
			resultMap.put("message", "密码为空，登录失败！");
			LogUtils.getLogger(this.getClass()).debug("密码为空，登录失败！");
			return resultMap;
		}
		user.setPassword(tempPassword);

		boolean validateFlag = false;// Douser这边验证通过标志
		// 性能测试优化,先查询手机号
		HashMap<String, Object> userMobileparams = new HashMap<String, Object>();
		userMobileparams.put("username", user.getUsername());
		if (user.getCompanyId() != null) {
			userMobileparams.put("companyId", user.getCompanyId());
		}
		userMobileparams.put("platformId", user.getUserPlatformId());

		try {
			// 表示从后台登录
			if (user.getType() != null && user.getType() == 1) {
				userMobileparams.put("type", 1);
			}
			List<User> listUser = userDAOWrite
					.selectByMobilePassAndPlatformId(userMobileparams);
			if (CollectionUtils.isEmpty(listUser)) {
				HashMap<String, Object> userparams = new HashMap<String, Object>();
				userparams.put("username", user.getUsername());
				if (user.getCompanyId() != null) {// 如果有companyId
					userparams.put("companyId", user.getCompanyId());
				}
				userparams.put("platformId", user.getUserPlatformId());
				// 表示从后台登录
				if (user.getType() != null && user.getType() == 1) {
					userparams.put("type", 1);
				}
				listUser = userDAOWrite
						.selectByUserNameAndPlatformId(userparams);
			}
			// 没有查到记录
			if (CollectionUtils.isEmpty(listUser)) {
				resultMap.put("code", 1);
				resultMap.put("message", "该用户未注册！");
				return resultMap;
			}

			EncryptEntity encryptEntity = new EncryptEntity();
			if (!CollectionUtils.isEmpty(listUser)) {// 查询到记录,验证密码正确性

				User euser = listUser.get(0);

				encryptEntity = CalcEncryInfoUtils.calc(euser);

				// 没有盐值,是修改之前的用户,原密码md5加密一次
				String passWordNullSalt = PasswordUtil.getSecretPassWord(
						user.getPassword(), "", 1);
				// 有old盐值,就用old盐值跟old算法加密
				String passWordWithSalt = PasswordUtil.getSecretPassWord(user
						.getPassword(),
						(null == encryptEntity.getOldBSalt()) ? ""
								: encryptEntity.getOldBSalt(),
						(null == encryptEntity.getOldBVersion()) ? 1
								: encryptEntity.getOldBVersion());

				if (StringUtils.isEmpty(encryptEntity.getOldBSalt())) {// 没有old盐值,是修改之前的用户,原密码md5加密一次
					if (euser.getPassword().equals(passWordNullSalt)) {
						validateFlag = true;
					}
				} else {// 有old盐值,就用old盐值跟old算法加密
					if (null != encryptEntity.getOldBVersion()) {
						if (euser.getPassword().equals(passWordWithSalt)) {
							validateFlag = true;
						}
					}
				}

			}

			if (false == validateFlag) {// ouser密码验证没通过或者ouser不存在用户
				listUser.clear();
			}

			if (listUser.size() == 0) {
				resultMap.put("code", 3);
				resultMap.put("message", "用户名或密码错误，登录失败！");
				return resultMap;
			}

			User user2 = listUser.get(0);
			if (user2.getIsAvailable() != null && user2.getIsAvailable() == 0) {
				resultMap.put("code", 6);
				resultMap.put("message", "该用户已被停用！");
				return resultMap;
			}
			// todo 审核逻辑暂不使用
			// if (ConstantUser.user_status.wait == user2.getStatus()) {
			// resultMap.put("code", 4);
			// resultMap.put("message", "未通过审核，登录失败");
			// }

			UserCache cacheUser = this.getCacheUser(user2, req);

			cacheUser.setAutoLogin(user.isAutoLogin());
			LogUtils.getLogger(getClass()).debug(
					"cacheUser======================="
							+ JSON.toJSONString(cacheUser));

			resultMap.put("data", cacheUser);
			resultMap.put("code", 0);
			resultMap.put("message", "登录成功！");

			if (true == validateFlag) {
				try {// 更新加密信息的操作不能影响正常的登录
					Long userId = listUser.get(0).getId();

					// 前端传入了新的加密密码,登录成功之后置为当前密码
					if (StringUtils.isNotEmpty(user.getNewEncryptPassword())) {
						user.setPassword(user.getNewEncryptPassword());
					}

					if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())
							|| null != encryptEntity.getNewBVersion()
							|| StringUtils.isNotEmpty(user
									.getNewEncryptPassword())) {
						String salt = StringUtils.isEmpty(encryptEntity
								.getNewBSalt()) ? encryptEntity.getOldBSalt()
								: encryptEntity.getNewBSalt();
						Integer version = (null == encryptEntity
								.getNewBVersion()) ? encryptEntity
								.getOldBVersion() : encryptEntity
								.getNewBVersion();

						User updUser = new User();

						updUser.setId(userId);

						String password = PasswordUtil.getSecretPassWord(
								user.getPassword(), salt, version);
						Date now = new Date();
						if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
							updUser.setbSalt(encryptEntity.getNewBSalt());
							updUser.setbSaltUpdateTime(now);
						}
						if (null != encryptEntity.getNewBVersion()) {
							updUser.setbAlgorithmVersion(encryptEntity
									.getNewBVersion());
						}

						if (StringUtils.isNotEmpty(user.getNewFSalt())) {
							updUser.setfSalt(user.getNewFSalt());
							updUser.setfSaltUpdateTime(now);
						}

						if (null != user.getNewFVersion()) {
							updUser.setfAlgorithmVersion(user.getNewFVersion());
						}

						updUser.setPassword(password);

						int cnt = passwordEncryptInfoWriteManage
								.updateEncryptInfoByUserWithTx(updUser, null);

						if (0 == cnt) {
							LogUtils.getLogger(this.getClass()).error(
									"更新用户加密密码失败");
						}
					}

					// 添加app客户端信息
					if (StringUtils.isNotEmpty(user.getDeviceId())
							&& user.getAppType() != null) {
						PushMessageInputDTO pm = new PushMessageInputDTO();
						pm.setUserId(userId);
						pm.setDeviceId(user.getDeviceId());
						pm.setAppType(user.getAppType());
						List<PushMessageOutputDTO> pmList = pushMessageWriteManage
								.findByConditions(pm);
						if (pmList == null || pmList.isEmpty()) {
							pushMessageWriteManage.addPushMessageWithTx(pm);
						}
					}

				} catch (Exception e1) {
					LogUtils.getLogger(this.getClass()).error("更新用户加密密码失败", e1);
				}

			}
			return resultMap;

		} catch (Exception e) {
			LogUtils.getLogger(this.getClass()).error(
					"登录出现异常，username:" + user.getUsername(), e);
			resultMap.put("code", 1);
			resultMap.put("message", "查询出错，登录失败！");
			return resultMap;
		}
	}

	public HashMap<String, Object> loginWithPhoneAndPwd(MobileUserVO user,
			HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<User> list = userDAOWrite.selectByMobileAndPlatformId(
					user.getMobile(), user.getCompanyId(),
					user.getUserPlatformId());
			if (CollectionUtils.isEmpty(list)) {
				resultMap.put("code", "-4");
				resultMap.put("message", "未查询到该用户");
				return resultMap;
			}
			if (list.size() != 1) {
				resultMap.put("code", "-5");
				resultMap.put("message", "查询到多条记录");
				return resultMap;
			}
			User user2 = list.get(0);

			if (user2.getIsAvailable() != null && user2.getIsAvailable() == 0) {
				resultMap.put("code", 6);
				resultMap.put("message", "该用户已被冻结！");
				return resultMap;
			}

			UserCache cacheUser = getCacheUser(user2, req);
			resultMap.put("data", cacheUser);
			resultMap.put("code", 0);
			resultMap.put("message", "登录成功！");
			return resultMap;
		} catch (Exception e) {
			LogUtils.getLogger(this.getClass()).error(
					"查询用户异常，mobile=" + user.getMobile(), e);
			resultMap.put("code", "-3");
			resultMap.put("message", "查询用户出错");
			return resultMap;
		}
	}

	// 手机号码验证码登录逻辑
	public HashMap<String, Object> loginWithPhone(MobileUserVO user,
			HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 校验手机号码和验证码是否匹配
			resultMap = mobileUserWriteManage.checkCodeWithTx(user);// TODO
																	// 验证码为什么要存数据库
			String code = resultMap.get("code").toString();
			if (code != null && !code.equals("0")) {
				return resultMap;
			}
		} catch (Exception e) {
			LogUtils.getLogger(this.getClass()).error(
					"手机验证码校验异常，mobile=" + user.getMobile(), e);
			resultMap.put("code", "-1");
			resultMap.put("message", "校验手机号码错误");
			return resultMap;
		}
		// 校验通过，根据手机号码和公司id查询用户
		/*
		 * if (user.getCompanyId() == null) {
		 * LogUtils.getLogger(this.getClass())
		 * .debug("公司id不能为空，mobile="+user.getMobile()); resultMap.put("code",
		 * "-2"); resultMap.put("message", "公司id不能为空"); return resultMap; }
		 */
		try {
			List<User> list = userDAOWrite.selectByMobileAndPlatformId(
					user.getMobile(), user.getCompanyId(),
					user.getUserPlatformId());
			if (CollectionUtils.isEmpty(list)) {
				resultMap.put("code", "-4");
				resultMap.put("message", "未查询到该用户");
				return resultMap;
			}
			if (list.size() != 1) {
				resultMap.put("code", "-5");
				resultMap.put("message", "查询到多条记录");
				return resultMap;
			}
			User user2 = list.get(0);

			if (user2.getIsAvailable() != null && user2.getIsAvailable() == 0) {
				resultMap.put("code", 6);
				resultMap.put("message", "该用户已被冻结！");
				return resultMap;
			}
			UserCache cacheUser = getCacheUser(user2, req);
			resultMap.put("data", cacheUser);
			resultMap.put("code", 0);
			resultMap.put("message", "登录成功！");
			return resultMap;
		} catch (Exception e) {
			LogUtils.getLogger(this.getClass()).error(
					"查询用户异常，mobile=" + user.getMobile(), e);
			resultMap.put("code", "-3");
			resultMap.put("message", "查询用户出错");
			return resultMap;
		}
	}

	public UserCache getCacheUser(User user) throws Exception {
		UserCache userCache = new UserCache();
		userCache.setOriginalId(user.getOriginalId());
		userCache.setId(user.getId());
		userCache.setParentId(user.getParentId());
		userCache.setUsername(user.getUsername());
		userCache.setMobile(user.getMobile());
		userCache.setType(user.getType());
		userCache.setCompanyId(user.getCompanyId());
		userCache.setIdentityType(user.getIdentityType());

		userCache.setNickname(user.getNickname());
		userCache.setHeadPicUrl(user.getHeadPicUrl());

		if (user.getType() == ConstantUser.user_type.member /*
															 * || user.getType()
															 * == ConstantUser.
															 * user_type.main
															 */
				|| user.getType() == ConstantUser.user_type.business /*
																	 * ||
																	 * ConstantUser
																	 * .
																	 * user_type
																	 * .service
																	 * ==
																	 * user.getType
																	 * ()
																	 */) // todo
																			// 现阶段会员免权限
		{
			return userCache;
		}

		Set<String> set = new HashSet<String>();
		StringBuilder sb = new StringBuilder(",");

		List<HashMap<String, String>> list = functionDAOWrite
				.getAllFunctionByUserId(user.getId());

		if (list == null || list.size() == 0) {
			LogUtils.getLogger(this.getClass()).debug(
					"该用户无权限登录系统！username=" + user.getUsername() + ",mobile="
							+ user.getMobile());
			throw new Exception("该用户无权限登录系统！");
		}
		for (HashMap<String, String> hashMap : list) {
			String path = hashMap.get("path");
			String code = hashMap.get("code");
			if (path != null && !"".equals(path)) {
				set.add(path);
			}
			if (code != null && !"".equals(code)) {
				// FIXME 字符串拼接
				sb.append(code).append(",");
			}
		}
		userCache.setFunctionCodes(sb.toString());
		userCache.setFunctionPahts(set);
		return userCache;
	}

	public UserCache getCacheUser(User user, HttpServletRequest req)
			throws Exception {
		UserCache userCache = new UserCache();
		userCache.setOriginalId(user.getOriginalId());
		userCache.setId(user.getId());
		userCache.setParentId(user.getParentId());
		userCache.setUsername(user.getUsername());
		userCache.setMobile(user.getMobile());
		userCache.setType(user.getType());
		userCache.setCompanyId(user.getCompanyId());
		userCache.setIdentityType(user.getIdentityType());
		userCache.setCompanyName(user.getCompanyName());
		userCache.setIdentityCardName(user.getIdentityCardName());
		userCache.setNickname(user.getNickname());
		userCache.setHeadPicUrl(user.getHeadPicUrl());
		if (user.getGradeId() != null) {
			userCache.setGradeId(user.getGradeId());
			UserGradePO gradePO = userGradeDAOWrite.selectByPrimaryKey(
					user.getGradeId(), false);
			userCache.setGradeType(gradePO.getGradeType());
		}

		Set<String> set = new HashSet<String>();
		StringBuilder sb = new StringBuilder(",");

		Integer platformId = 1;// 默认 1运营平台 异常也运营平台

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getId());
		map.put("platformId", platformId);

		// List<HashMap<String, String>> list =
		// functionDAOWrite.getAllFunctionByUserId(user.getId());
		List<HashMap<String, String>> list = functionDAOWrite
				.getFunctionMapByUserIdAndPlatformId(map);

		/*
		 * if (list == null || list.size() == 0) {
		 * LogUtils.getLogger(this.getClass
		 * ()).debug("该用户无权限登录系统！username="+user.
		 * getUsername()+",mobile="+user.getMobile()); throw new
		 * Exception("该用户无权限登录系统！"); }
		 */
		if (list != null) {
			for (HashMap<String, String> hashMap : list) {
				String path = hashMap.get("path");
				String code = hashMap.get("code");
				if (path != null && !"".equals(path)) {
					set.add(path);
				}
				if (code != null && !"".equals(code)) {
					// FIXME 字符串拼接
					sb.append(code).append(",");
				}
			}
		}

		userCache.setFunctionCodes(sb.toString());
		userCache.setFunctionPahts(set);
		return userCache;
	}

	@Override
	public Long insertLoginLogWithTx(HashMap<String, Object> map)
			throws Exception {
		UserLogin userLogin = new UserLogin();
		int code = Integer.parseInt(map.get("code").toString());// code 等于
																// 0表示登录成功
		UserCache cacheUser = (UserCache) map.get("data");
		userLogin.setLoginResult(code);
		userLogin.setUsername(cacheUser.getUsername());
		userLogin.setUserId(cacheUser.getId());

		userLogin.setIp(ObjectUtils.toString(map.get("ip")));
		userLogin.setBrowser(ObjectUtils.toString(map.get("userAgent")));

		userLoginDAOWrite.insert(userLogin);
		return userLogin.getId();
	}

	@Override
	public UserCookie inserUserCookieWithTx(Long id, Long userLoginId)
			throws Exception {
		UserCookie userCookie = new UserCookie();
		userCookie.setUserId(id);
		userCookie.setUserLoginId(userLoginId);
		userCookie.setCookieName(ConstantUser.COOKIE_NAME);
		userCookie
				.setCookieValue(UUID.randomUUID().toString().replace("-", ""));
		userCookie.setPath("/");
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		now.set(Calendar.DATE, now.get(Calendar.DATE)
				+ ConstantUser.COOKIE_EXPIRATION_TIME);
		userCookie.setExpirationTime(now.getTime());
		userCookieDAOWrite.insert(userCookie);
		return userCookie;
	}

	@Override
	public void insertLoginExceptionLogWithTx(int code, String userName,
			String ip, String userAgent) throws Exception {
		UserLogin userLogin = new UserLogin();
		userLogin.setLoginResult(code);
		userLogin.setUsername(userName);
		userLogin.setIp(ip);
		userLogin.setBrowser(userAgent);
		userLoginDAOWrite.insert(userLogin);
	}

	@Override
	public void sendLoginInfoToMQ(Map<String, Object> user) {

	}

	public static void main(String[] args) {
		String s = "222222";
		s = s.substring(1, 3);
		System.out.println(s);
	}

	@Override
	public void updateOpenIdWithTx(MobileUserVO user) {
		com.people2000.user.model.po.ibatis.User record = new com.people2000.user.model.po.ibatis.User();
		record.setId(user.getId());
		record.setOpenId(user.getOpenId());
		int cnt = userDAOWrite.updateOpenIdByPrimaryKey(record);
		System.out.print("count======>>>"+cnt);
	}

}
