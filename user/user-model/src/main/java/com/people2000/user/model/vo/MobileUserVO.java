package com.people2000.user.model.vo;

import java.util.Date;

import com.people2000.user.model.po.ibatis.User;

/**
 * Created by xiaole on 2015/10/8.
 */
public class MobileUserVO extends User {

	private static final long serialVersionUID = 1L;
	/**
	 * 是否自动登录
	 */
	private boolean autoLogin = false;
	/**
	 * 手机校验码 *
	 */
	private String captchas;
	/**
	 * 手机校验码类型 1:注册 2:找回密码
	 */
	private String captchasType;
	/**
	 * 密码1 *
	 */
	private String password1;
	/**
	 * 密码2 *
	 */
	private String password2;
	/**
	 * 确认密码 *
	 */
	private String confirmPassword;
	/**
	 * 图形验证码长度 *
	 */
	private String codeNmInSession;
	/**
	 * 验证码 *
	 */
	private String checkImageCode;
	/**
	 * 验证码对应key值
	 */
	private String imgeKey;
	/**
	 * 是否有图形验证码
	 * 
	 * @deprecated 是否要验证码应该开cache，而不是入参
	 */
	private boolean hasCheckImage;
	/**
	 * 是否实名认证 *
	 */
	private boolean isCertified;
	/**
	 * token *
	 */
	private String ut;

	private String orgianlMobile;

	private int something;

	private String oldFSalt;// 原前端盐
	private String newFSalt;// 新前端盐
	private Integer oldFVersion;// 原前端算法版本
	private Integer newFVersion;// 新前端算法版本
	private String newEncryptPassword;// 新的加密密码

	private Long userPlatformId;// 防止冲突更换

	private String inviteMobile;// 邀请人手机号

	private Date businessStart;

	private Date businessEnd;

	/**
	 * 解绑所需新手机号
	 */
	private String newMobile;

	/**
	 * 操作者类型 1：运营
	 */
	private String operatorType;

	private Integer platformType;
	private String inviteCode; // 邀请码
	private String deviceId;
	private Integer appType;
	private Integer accountType;// 账户类型
	private String headPicUrl;

	/**
	 * 注册获取用户unionId
	 */
	private String oauthCode;
	private String oauthState;
	private String unionId;
	private String openId;

	/*********************/

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getOauthState() {
		return oauthState;
	}

	public void setOauthState(String oauthState) {
		this.oauthState = oauthState;
	}

	public String getOauthCode() {
		return oauthCode;
	}

	public void setOauthCode(String oauthCode) {
		this.oauthCode = oauthCode;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getOldFSalt() {
		return oldFSalt;
	}

	public void setOldFSalt(String oldFSalt) {
		this.oldFSalt = oldFSalt;
	}

	public String getNewFSalt() {
		return newFSalt;
	}

	public void setNewFSalt(String newFSalt) {
		this.newFSalt = newFSalt;
	}

	public Integer getOldFVersion() {
		return oldFVersion;
	}

	public void setOldFVersion(Integer oldFVersion) {
		this.oldFVersion = oldFVersion;
	}

	public Integer getNewFVersion() {
		return newFVersion;
	}

	public void setNewFVersion(Integer newFVersion) {
		this.newFVersion = newFVersion;
	}

	public String getNewEncryptPassword() {
		return newEncryptPassword;
	}

	public void setNewEncryptPassword(String newEncryptPassword) {
		this.newEncryptPassword = newEncryptPassword;
	}

	public Integer getPlatformType() {
		return platformType;
	}

	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}

	public String getOrgianlMobile() {
		return orgianlMobile;
	}

	public void setOrgianlMobile(String orgianlMobile) {
		this.orgianlMobile = orgianlMobile;
	}

	public String getUt() {
		return ut;
	}

	public void setUt(String ut) {
		this.ut = ut;
	}

	public int getSomething() {
		return something;
	}

	public void setSomething(int something) {
		this.something = something;
	}

	public String getCheckImageCode() {
		return checkImageCode;
	}

	public void setCheckImageCode(String checkImageCode) {
		this.checkImageCode = checkImageCode;
	}

	/**
	 * @deprecated
	 */
	public boolean isHasCheckImage() {
		return hasCheckImage;
	}

	/**
	 * @deprecated
	 * @param hasCheckImage
	 */
	public void setHasCheckImage(boolean hasCheckImage) {
		this.hasCheckImage = hasCheckImage;
	}

	public String getCodeNmInSession() {
		return codeNmInSession;
	}

	public void setCodeNmInSession(String codeNmInSession) {
		this.codeNmInSession = codeNmInSession;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isCertified() {
		return isCertified;
	}

	public void setIsCertified(boolean isCertified) {
		this.isCertified = isCertified;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getCaptchas() {
		return captchas;
	}

	public void setCaptchas(String captchas) {
		this.captchas = captchas;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getCaptchasType() {
		return captchasType;
	}

	public void setCaptchasType(String captchasType) {
		this.captchasType = captchasType;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public String getNewMobile() {
		return newMobile;
	}

	public void setNewMobile(String newMobile) {
		this.newMobile = newMobile;
	}

	public Long getUserPlatformId() {
		return userPlatformId;
	}

	public void setUserPlatformId(Long userPlatformId) {
		this.userPlatformId = userPlatformId;
	}

	public String getImgeKey() {
		return imgeKey;
	}

	public void setImgeKey(String imgeKey) {
		this.imgeKey = imgeKey;
	}

	public Date getBusinessStart() {
		return businessStart;
	}

	public void setBusinessStart(Date businessStart) {
		this.businessStart = businessStart;
	}

	public Date getBusinessEnd() {
		return businessEnd;
	}

	public void setBusinessEnd(Date businessEnd) {
		this.businessEnd = businessEnd;
	}

	public String getInviteMobile() {
		return inviteMobile;
	}

	public void setInviteMobile(String inviteMobile) {
		this.inviteMobile = inviteMobile;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getHeadPicUrl() {
		return headPicUrl;
	}

	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
