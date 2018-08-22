package com.people2000.user.model.vo;

import java.io.Serializable;

import com.people2000.user.model.po.ibatis.Function;

public class FunctionVO extends Function implements Serializable {

	private String parentName;// 父级名称

	private Long userId;// 用户id

	private Long roleId;// 角色id

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
