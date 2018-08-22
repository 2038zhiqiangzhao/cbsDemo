package com.people2000.user.business.utils.uionlogin;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.ConstantUser;
import com.people2000.user.business.write.dao.UUnionConfigDAOWrite;
import com.people2000.user.business.write.dao.UUnionLoginDAOWrite;
import com.people2000.user.business.write.dao.UserDAOWrite;
import com.people2000.user.business.write.dao.UserRoleDAOWrite;
import com.people2000.user.model.dto.UserUnionLoginDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.UUnionConfigPO;
import com.people2000.user.model.po.ibatis.UUnionConfigPOExample;
import com.people2000.user.model.po.ibatis.UUnionLoginPO;
import com.people2000.user.model.po.ibatis.UUnionLoginPOExample;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserExample;
import com.people2000.user.model.po.ibatis.UserRole;

/**
 * 联合登录的一些模板 Created by dailf on 2016/8/5.
 */
public abstract class UnionLoginTemplate {

	@Resource
	private UUnionConfigDAOWrite uUnionConfigDAOWrite;
	@Resource
	private UserDAOWrite userDAOWrite;
	@Resource
	private UserRoleDAOWrite userRoleDAOWrite;
	@Resource
	private UUnionLoginDAOWrite uUnionLoginDAOWrite;

	private Logger logger = LogUtils.getLogger(UnionLoginTemplate.class);

	@PostConstruct
	public abstract void registerService();

	/**
	 * 联合登录
	 * 
	 * @param userUnionLoginDTO
	 */
	public UserUnionLoginDTO savaUnionUserInfoWithTx(
			UserUnionLoginDTO userUnionLoginDTO) throws Exception {

		logger.error("联合登录参数：" + JSON.toJSONString(userUnionLoginDTO));

		// 获取回调相关参数
		userUnionLoginDTO = getCompanyUnionInfo(userUnionLoginDTO);
		logger.error("获取回调相关参数：" + JSON.toJSONString(userUnionLoginDTO));
		// 由于第三方联合登录提供了多种方式
		// 此种方式用来判断返回值为code的情况
		if (StringUtil.isBlank(userUnionLoginDTO.getToken())
				&& StringUtil.isBlank(userUnionLoginDTO.getOpenid())) {
			logger.error("token和openid入参都没有传，获取token、openId");
			// 获取token(抽象)
			userUnionLoginDTO = getAccessToken(userUnionLoginDTO);
			// 获取openid(抽象)
			userUnionLoginDTO = getOpenId(userUnionLoginDTO);
		}
		// 获取第三方用户信息(抽象)
		userUnionLoginDTO = getUserInfo(userUnionLoginDTO);
		logger.error("获取第三方用户信息：" + JSON.toJSONString(userUnionLoginDTO));
		// 查询联合登录表
		userUnionLoginDTO = saveOrUpdateUnionUserWithTx(userUnionLoginDTO);
		// 判断是否存在和是否过期
		if (userUnionLoginDTO.isUnionExists()) {
			// 查询更新用户信息
			updateUserInfoWithTx(userUnionLoginDTO);
			// 更新联合登录表数据
			udpateUnionInfoWithTx(userUnionLoginDTO);
		}
		return userUnionLoginDTO;
	}

	/**
	 * 更新联合登录表
	 * 
	 * @param userUnionLoginDTO
	 * @throws SQLException
	 */
	private void udpateUnionInfoWithTx(UserUnionLoginDTO userUnionLoginDTO)
			throws SQLException {
		UUnionLoginPOExample example = new UUnionLoginPOExample();
		example.createCriteria()
				.andUserIdEqualTo(userUnionLoginDTO.getUserId())
				.andOauthOpenIdEqualTo(userUnionLoginDTO.getOpenid());
		List<UUnionLoginPO> unionList = uUnionLoginDAOWrite.selectByExample(
				example, false);
		UUnionLoginPO uUnionLoginPO = null;
		if (unionList.size() > 0) {
			uUnionLoginPO = unionList.get(0);
		} else {
			return;
		}

		uUnionLoginPO.setOauthAccessToken(userUnionLoginDTO.getToken());
		uUnionLoginPO.setOauthExpiresIn(userUnionLoginDTO.getExpire_in());

		if (null != userUnionLoginDTO.getRefreshToken()) {
			uUnionLoginPO.setOauthRefreshToken(userUnionLoginDTO
					.getRefreshToken());
		}
		if (StringUtils.isNotBlank(userUnionLoginDTO.getUnionId())
				&& StringUtils.isBlank(uUnionLoginPO.getOauthUnionId())) {
			uUnionLoginPO.setOauthUnionId(userUnionLoginDTO.getUnionId());
		}
		uUnionLoginDAOWrite.updateByPrimaryKeySelective(uUnionLoginPO);
	}

	/**
	 * 更新用户基础信息
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 */
	private void updateUserInfoWithTx(UserUnionLoginDTO userUnionLoginDTO)
			throws SQLException {
		if (0 == userUnionLoginDTO.getUserId()) {
			return;
		}
		User user = userDAOWrite.selectByPrimaryKey(
				userUnionLoginDTO.getUserId(), false);
		if (!StringUtil.isBlank(userUnionLoginDTO.getNewUserName())
				&& !StringUtil.isBlank(user.getUsername())
				&& !StringUtil.isBlank(userUnionLoginDTO.getUserName())
				&& !(user.getUsername().contains(userUnionLoginDTO
						.getNewUserName()))) {
			// user.setUsername(userUnionLoginDTO.getUserName());

			user.setNickname(userUnionLoginDTO.getUserName());
			userDAOWrite.updateByPrimaryKeySelective(user);
		}
	}

	/**
	 * 获取不同公司的第三方登录数据
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 */
	public UserUnionLoginDTO getCompanyUnionInfo(
			UserUnionLoginDTO userUnionLoginDTO) throws SQLException {
		// 访问域
		String accessDomain = "";

		UUnionConfigPOExample example = new UUnionConfigPOExample();

		example.createCriteria()
				.andGatewayEqualTo(userUnionLoginDTO.getGateway())
				.andAppDomainEqualTo(accessDomain);
		List<UUnionConfigPO> configList = uUnionConfigDAOWrite.selectByExample(
				example, false);
		if (!CollectionUtils.isEmpty(configList)) {
			UUnionConfigPO uUnionConfigPO = configList.get(0);
			userUnionLoginDTO.setAppsecret(uUnionConfigPO.getAppSecret());
			userUnionLoginDTO.setAppid(uUnionConfigPO.getAppId());
			userUnionLoginDTO.setRedirect_uri(uUnionConfigPO.getRedirectUri());
		}
		return userUnionLoginDTO;
	}

	/**
	 * 添加或修改用户信息
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 */
	private UserUnionLoginDTO saveUserAndUnionWithTx(
			UserUnionLoginDTO userUnionLoginDTO) throws SQLException {
		// 保存用户
		Long userId;
		{
			User user = new User();
			user.setUsername(userUnionLoginDTO.getUserName());
			user.setCompanyId(userUnionLoginDTO.getCompanyId());
			user.setType(ConstantUser.user_type.member);
			user.setParentId(0L);
			// user.setPassword("123456");
			userId = userDAOWrite.insert(user);
		}

		userUnionLoginDTO.setUserId(userId);

		// 添加角色
		{
			UserRole userRole = new UserRole();
			userRole.setRoleId(Long.parseLong(ConstantUser.default_role));
			userRole.setUserId(userId);
			userRoleDAOWrite.insert(userRole);
		}
		// 新增联合登录用户
		{
			UUnionLoginPO uUnionLoginPO = new UUnionLoginPO();
			uUnionLoginPO.setUserId(userId);
			uUnionLoginPO.setOauthAccessToken(userUnionLoginDTO.getToken());
			uUnionLoginPO.setOauthOpenId(userUnionLoginDTO.getOpenid());
			uUnionLoginPO.setOauthSourceSystem(userUnionLoginDTO.getGateway());
			uUnionLoginPO.setOauthExpiresIn(userUnionLoginDTO.getExpire_in());
			uUnionLoginDAOWrite.insert(uUnionLoginPO);
		}

		return userUnionLoginDTO;
	}

	/**
	 * 添加或修改第三方用户
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 */
	private UserUnionLoginDTO saveOrUpdateUnionUserWithTx(
			UserUnionLoginDTO userUnionLoginDTO) throws Exception {
		UUnionLoginPOExample example = new UUnionLoginPOExample();
		// FIXME 加入超时时间查询
		// example.createCriteria().andOauthOpenIdEqualTo(userUnionLoginDTO.getOpenid()).andOauthSourceSystemEqualTo(userUnionLoginDTO.getGateway());
		// 替换原有通过openId查询联合登录记录
		example.createCriteria()
				.andOauthUnionIdEqualTo(userUnionLoginDTO.getUnionId())
				.andOauthSourceSystemEqualTo(userUnionLoginDTO.getGateway())
				.andIsDeletedEqualTo(0);
		List<UUnionLoginPO> unionList = uUnionLoginDAOWrite.selectByExample(
				example, false);
		// 判断用户是否存在
		if (!CollectionUtils.isEmpty(unionList)) {
			UUnionLoginPO uUnionLoginPO = unionList.get(0);
			if (ConstantUser.default_union_userid.longValue() == (uUnionLoginPO
					.getUserId())) {
				userUnionLoginDTO.setUnionExists(Boolean.FALSE);
			} else {

				UserExample examp = new UserExample();
				// fixme：补充逻辑，拿到userId 再验证用户信息是否有效
				examp.createCriteria()
						.andCompanyIdEqualTo(userUnionLoginDTO.getCompanyId())
						.andIdEqualTo(uUnionLoginPO.getUserId())
						.andIsDeletedEqualTo(0);
				Integer count = userDAOWrite.countByExample(examp, false);

				if (count > 0) {
					userUnionLoginDTO.setUserId(uUnionLoginPO.getUserId());
					userUnionLoginDTO.setUnionExists(Boolean.TRUE);
				} else {
					// fixme：绑定用户信息无效，更新用户id为-1，重新绑定
					UUnionLoginPO record = new UUnionLoginPO();
					record.setId(uUnionLoginPO.getId());
					record.setUserId(ConstantUser.default_union_userid
							.longValue());
					int rowCnt = uUnionLoginDAOWrite
							.updateByPrimaryKeySelective(record);
					if (rowCnt != 1) {
						logger.error("绑定用户信息无效，更新用户id为-1失败");
						throw new OuserMangeException("绑定用户信息无效，更新用户id为-1失败");
					}
					userUnionLoginDTO.setUnionExists(Boolean.FALSE);
				}
			}
		} else {
			userUnionLoginDTO.setUnionExists(Boolean.FALSE);
			UUnionLoginPO uUnionLoginPO = new UUnionLoginPO();
			// FIXME 设置一个初始的用户ID，之后修改
			uUnionLoginPO.setUserId(ConstantUser.default_union_userid
					.longValue());
			uUnionLoginPO.setOauthAccessToken(userUnionLoginDTO.getToken());
			uUnionLoginPO.setOauthRefreshToken(userUnionLoginDTO
					.getRefreshToken());
			uUnionLoginPO.setOauthOpenId(userUnionLoginDTO.getOpenid());
			uUnionLoginPO.setOauthSourceSystem(userUnionLoginDTO.getGateway());
			uUnionLoginPO.setOauthExpiresIn(userUnionLoginDTO.getExpire_in());
			uUnionLoginPO.setOauthUnionId(userUnionLoginDTO.getUnionId());

			uUnionLoginDAOWrite.insert(uUnionLoginPO);
		}
		return userUnionLoginDTO;
	}

	/**
	 * 获取联合登录配置
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 */
	public UUnionConfigPO getUnionConfigInfo(UserUnionLoginDTO userUnionLoginDTO) {
		String accessDomain = "";
		// 获取访问域，APP端需要自己传
		if (StringUtil.isBlank(userUnionLoginDTO.getAccessDomain())) {
		} else {
			accessDomain = userUnionLoginDTO.getAccessDomain();
		}
		LogUtils.getLogger(getClass()).info("accessDomain:" + accessDomain);
		UUnionConfigPOExample example = new UUnionConfigPOExample();
		example.createCriteria()
				.andGatewayEqualTo(userUnionLoginDTO.getGateway())
				.andAppDomainEqualTo(accessDomain);
		List<UUnionConfigPO> configList = null;
		try {
			configList = uUnionConfigDAOWrite.selectByExample(example, false);
		} catch (SQLException e) {
			throw new RuntimeException("SQL错误");
		}
		if (configList.size() > 0) {
			return configList.get(0);
		} else {
			throw new RuntimeException("无法查询到配置信息");
		}
	}

	/**
	 * 获取token
	 * 
	 * @return
	 */
	public abstract UserUnionLoginDTO getAccessToken(
			UserUnionLoginDTO userUnionLoginDTO);

	/**
	 * 获取openId
	 * 
	 * @return
	 */
	public abstract UserUnionLoginDTO getOpenId(
			UserUnionLoginDTO userUnionLoginDTO);

	/**
	 * 获取登录相关参数
	 * 
	 * @return
	 */
	public abstract String getRelatedParams(UserUnionLoginDTO userUnionLoginDTO);

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public abstract UserUnionLoginDTO getUserInfo(
			UserUnionLoginDTO userUnionLoginDTO) throws Exception;
}
