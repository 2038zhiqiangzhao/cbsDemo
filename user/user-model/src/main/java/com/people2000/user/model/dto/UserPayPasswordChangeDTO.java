package com.people2000.user.model.dto;

import java.io.Serializable;

public class UserPayPasswordChangeDTO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -5048328343231064509L;

	private Long userId;// 用户id

	private String mobile;// 用户手机
	/**
	 * 手机校验码 *
	 */
	private String captchas;

	private String newPassword;// 新密码

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCaptchas() {
		return captchas;
	}

	public void setCaptchas(String captchas) {
		this.captchas = captchas;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
