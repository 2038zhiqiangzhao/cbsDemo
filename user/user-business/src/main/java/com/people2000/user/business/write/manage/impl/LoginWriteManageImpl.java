package com.people2000.user.business.write.manage.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.CalcEncryInfoUtils;
import com.people2000.user.business.utils.CommonUtils;
import com.people2000.user.business.utils.PasswordUtil;
import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.business.write.dao.UserCookieDAOWrite;
import com.people2000.user.business.write.dao.UserGradeDAOWrite;
import com.people2000.user.business.write.dao.UserLoginDAOWrite;
import com.people2000.user.business.write.dao.ext.FunctionDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.manage.LoginWriteManage;
import com.people2000.user.business.write.manage.PasswordEncryptInfoWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.po.ibatis.UserGradePO;
import com.people2000.user.model.po.ibatis.UserLogin;

/**
 * @author crc
 * @time 2015-4-29 下午5:29:40
 * @description <pre>
 * 登陆逻辑处理类
 * </pre>
 */
@Service("loginWriteManageImpl")
public class LoginWriteManageImpl implements LoginWriteManage {

	private Logger logger = Logger.getLogger(LoginWriteManageImpl.class);

	@Resource(name = "passwordEncryptInfoWriteManage")
	private PasswordEncryptInfoWriteManage passwordEncryptInfoWriteManage;

	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;

	@Resource(name = "userLoginDAOWrite")
	private UserLoginDAOWrite userLoginDAOWrite;

	@Resource(name = "userCookieDAOWrite")
	private UserCookieDAOWrite userCookieDAOWrite;

	@Resource(name = "functionDAOWrite")
	private FunctionDAOWrite2 functionDAOWrite;

	@Resource(name = "userGradeDAOWrite")
	private UserGradeDAOWrite userGradeDAOWrite;

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> login(User user) {// added by cash，这里暂时没有增加
														// 前端加密的修改,入参不对,没有加密算法信息

		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		if (StringUtils.isBlank(user.getUsername())) {
			resultMap.put("code", 1);
			LogUtils.getLogger(this.getClass()).warn("用户名称为空，登陆失败！");
			return resultMap;
		}
		if (StringUtils.isBlank(user.getPassword())) {
			resultMap.put("code", 2);
			LogUtils.getLogger(this.getClass()).warn("密码为空，登陆失败！");
			return resultMap;
		}

		boolean validateFlag = false;// ouser这边验证通过标志

		HashMap<String, Object> userparams = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(user.getUsername())) {
			userparams.put("username", user.getUsername());
		}
		if (user.getCompanyId() != null) {// 如果有companyId
			userparams.put("companyId", user.getCompanyId());
		}
		List<User> listUser = new ArrayList<User>();
		try {
			listUser = userDAOWrite.selectByUserNameAndPlatformId(userparams);
		} catch (Exception e) {
			logger.info("查询出错", e);
			resultMap.put("code", 5);
			resultMap.put("message", "查询出错，登录失败！");
			return resultMap;
		}
		if (!CollectionUtils.isEmpty(listUser)) {
			User euser = listUser.get(0);
			EncryptEntity encryptEntity = new EncryptEntity();
			encryptEntity = CalcEncryInfoUtils.calc(euser);

			// 没有盐值,是修改之前的用户,原密码md5加密一次
			String passWordNullSalt = PasswordUtil.getSecretPassWord(
					user.getPassword(), "", 1);
			// 有old盐值,就用old盐值跟old算法加密
			String passWordWithSalt = PasswordUtil.getSecretPassWord(user
					.getPassword(), (null == encryptEntity.getOldBSalt()) ? ""
					: encryptEntity.getOldBSalt(), (null == encryptEntity
					.getOldBVersion()) ? 1 : encryptEntity.getOldBVersion());

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
			validateFlag = true;
			if (true == validateFlag) {
				try {
					UserCache cacheUser = this.getCacheUser(euser);
					resultMap.put("data", cacheUser);
					resultMap.put("code", 0);
					resultMap.put("message", "登录成功！");

					try {// 更新加密信息的操作不能影响正常的登录
						Long userId = listUser.get(0).getId();

						if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())
								|| null != encryptEntity.getNewBVersion()) {
							String salt = StringUtils.isEmpty(encryptEntity
									.getNewBSalt()) ? encryptEntity
									.getOldBSalt() : encryptEntity
									.getNewBSalt();
							Integer version = (null == encryptEntity
									.getNewBVersion()) ? encryptEntity
									.getOldBVersion() : encryptEntity
									.getNewBVersion();

							User updUser = new User();

							updUser.setId(userId);

							String password = PasswordUtil.getSecretPassWord(
									user.getPassword(), salt, version);
							Date now = new Date();
							if (StringUtils.isNotEmpty(encryptEntity
									.getNewBSalt())) {
								updUser.setbSalt(encryptEntity.getNewBSalt());
								updUser.setbSaltUpdateTime(now);
							}
							if (null != encryptEntity.getNewBVersion()) {
								updUser.setbAlgorithmVersion(encryptEntity
										.getNewBVersion());
							}
							updUser.setPassword(password);

							int cnt = passwordEncryptInfoWriteManage
									.updateEncryptInfoByUserWithTx(updUser,
											null);

							if (0 == cnt) {
								LogUtils.getLogger(this.getClass()).error(
										"更新用户加密密码失败");
							}

						}
					} catch (Exception e1) {
						LogUtils.getLogger(this.getClass()).error("更新用户加密密码失败",
								e1);
					}

					return resultMap;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					LogUtils.getLogger(this.getClass())
							.error(e.getMessage(), e);
					LogUtils.getLogger(getClass()).error(e.getMessage(), e);
					;
					resultMap.put("code", 5);
					return resultMap;
				}
			} else {
				resultMap.put("code", 3);
				resultMap.put("message", "用户名或密码错误，登录失败！");
				return resultMap;
			}
		} else {
			resultMap.put("code", 3);
			resultMap.put("message", "用户名或密码错误，登录失败！");
			return resultMap;
		}

	}

	public UserCache getCacheUser(User user) throws Exception {
		UserCache userCache = new UserCache();
		userCache.setId(user.getId());
		userCache.setParentId(user.getParentId());
		userCache.setUsername(user.getUsername());
		userCache.setMobile(user.getMobile());
		userCache.setType(user.getType());
		userCache.setCompanyId(user.getCompanyId());

		userCache.setNickname(user.getNickname());
		userCache.setHeadPicUrl(user.getHeadPicUrl());
		if (user.getGradeId() != null) {
			userCache.setGradeId(user.getGradeId());
			UserGradePO gradePO = userGradeDAOWrite.selectByPrimaryKey(
					user.getGradeId(), false);
			userCache.setGradeType(gradePO.getGradeType());
		}
		// 开发阶段使用，会员无权限可登录
		if (user.getType() == ConstantUser.user_type.member) {
			return userCache;
		}

		Set<String> set = new HashSet<String>();
		String functionCodes = ",";

		List<HashMap<String, String>> list = functionDAOWrite
				.getAllFunctionByUserId(user.getId());
		if (list == null || list.size() == 0) {
			LogUtils.getLogger(this.getClass()).debug("该用户无权限登陆系统！");
			throw new Exception("获取用户权限出错");
		}
		for (HashMap<String, String> hashMap : list) {
			String path = hashMap.get("path");
			String code = hashMap.get("code");
			if (path != null && !"".equals(path)) {
				set.add(path);
			}
			if (code != null && !"".equals(code)) {
				functionCodes = functionCodes + code + ",";
			}
		}
		userCache.setFunctionCodes(functionCodes);
		userCache.setFunctionPahts(set);

		return userCache;
	}

	@Override
	public Long insertLoginLogWithTx(HashMap<String, Object> map,
			HttpServletRequest req) throws Exception {
		UserLogin userLogin = new UserLogin();
		int code = Integer.parseInt(map.get("code").toString());// code 等于
																// 0表示登陆成功
		UserCache cacheUser = (UserCache) map.get("data");
		userLogin.setLoginResult(code);
		userLogin.setUsername(cacheUser.getUsername());
		userLogin.setUserId(cacheUser.getId());
		userLogin.setIp(CommonUtils.getUserIp(req));
		userLogin.setBrowser(req.getHeader("User-Agent"));
		long id = userLoginDAOWrite.insert(userLogin);
		return id;
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
			HttpServletRequest req) throws Exception {
		UserLogin userLogin = new UserLogin();
		userLogin.setLoginResult(code);
		userLogin.setUsername(userName);
		userLogin.setIp(CommonUtils.getUserIp(req));
		userLogin.setBrowser(req.getHeader("User-Agent"));
		userLoginDAOWrite.insert(userLogin);
	}

}
