package com.people2000.user.model.dto;

import java.io.Serializable;

/**
 * Created by dailf on 2016/8/5.
 */
public class UserUnionLoginDTO implements Serializable {
	/**
	 * 每次登录返回的标识 一般10分钟过期
	 */
	private String code;
	/**
	 * 应用唯一标识
	 */
	private String appid;
	/**
	 * 私钥
	 */
	private String appsecret;
	/**
	 * 访问令牌(不能泄露)
	 */
	private String token;
	/**
	 * 刷新令牌
	 */
	private String refreshToken;
	/**
	 * 用户唯一标识(不能泄露)
	 */
	private String openid;
	/**
	 * 防止跨域攻击标识
	 */
	private String state;
	/**
	 * token过期时间
	 */
	private Long expire_in;
	/**
	 * 跳转链接(和网站上配置的相同)
	 */
	private String redirect_uri;
	/**
	 * 登录
	 */
	private Integer gateway;
	/**
	 * sessionid
	 */
	private String sessionId;

	private Long userId;

	private String userName;
	// 用来判断是否需要更新用户名
	private String newUserName;

	private Long companyId;
	/**
	 * 访问域
	 */
	private String accessDomain;
	/**
	 * 第三方用户是否登陆过
	 */
	private boolean unionExists;
	// 跳转链接
	private String redirectUrl;

	/**
	 * 用户登录微信标识
	 */
	private String unionId;

	private String mobile;
	private String password;

	public String getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccessDomain() {
		return accessDomain;
	}

	public void setAccessDomain(String accessDomain) {
		this.accessDomain = accessDomain;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public boolean isUnionExists() {
		return unionExists;
	}

	public void setUnionExists(boolean unionExists) {
		this.unionExists = unionExists;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getGateway() {
		return gateway;
	}

	public void setGateway(Integer gateway) {
		this.gateway = gateway;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getExpire_in() {
		return expire_in;
	}

	public void setExpire_in(Long expire_in) {
		this.expire_in = expire_in;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
