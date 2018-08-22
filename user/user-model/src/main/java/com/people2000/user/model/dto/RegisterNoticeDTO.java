package com.people2000.user.model.dto;

import java.io.Serializable;

/**
 * @author churongcheng
 * @time 2016/2/25
 * @description
 */
public class RegisterNoticeDTO implements Serializable {
	private static final long serialVersionUID = 1546796283855773092L;
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
