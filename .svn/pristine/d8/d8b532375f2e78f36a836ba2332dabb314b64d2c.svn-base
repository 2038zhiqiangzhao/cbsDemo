package com.people2000.user.model.security;

import java.io.Serializable;

/**
 * @类名称: AuthorizingUser
 * @类描述: 权限认证的类
 * @作者: dusai
 * @作成时间: 2017年3月31日 下午12:54:51
 */
public class AuthorizingUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 管理员ID */
	private Long userId;

	/** 登录名 */
	private String loginName;

	/** 昵称 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 加密密码的盐 */
	private String salt;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	/** 证书凭证 */
	public String getCredentialsSalt() {
		return loginName + salt;
	}
}
