package com.people2000.user.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户扩展信息 Created by dailf on 2016/10/17.
 */
public class UserExtOutDTO implements Serializable {

	private Map<String, String> userExtMap;

	public Map<String, String> getUserExtMap() {
		return userExtMap;
	}

	public void setUserExtMap(Map<String, String> userExtMap) {
		this.userExtMap = userExtMap;
	}
}
