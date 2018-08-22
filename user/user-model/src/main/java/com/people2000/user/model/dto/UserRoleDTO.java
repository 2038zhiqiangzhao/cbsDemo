package com.people2000.user.model.dto;

import java.util.List;

public class UserRoleDTO {

	private Long userId;// 用户id

	private List<Long> roleList;// 角色id

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Long> roleList) {
		this.roleList = roleList;
	}

}
