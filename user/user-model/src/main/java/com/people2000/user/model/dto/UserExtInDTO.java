package com.people2000.user.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户扩展信息 Created by dailf on 2016/10/17.
 */
public class UserExtInDTO implements Serializable {

	private Long companyId;

	private Long userId;

	private List<String> userExtKeys;

	private Map<String, String> userExtMap;

	public Map<String, String> getUserExtMap() {
		return userExtMap;
	}

	public void setUserExtMap(Map<String, String> userExtMap) {
		this.userExtMap = userExtMap;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<String> getUserExtKeys() {
		return userExtKeys;
	}

	public void setUserExtKeys(List<String> userExtKeys) {
		this.userExtKeys = userExtKeys;
	}
}
