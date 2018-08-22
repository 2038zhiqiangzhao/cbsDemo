package com.people2000.user.model.vo;

import java.io.Serializable;
import java.util.List;

public class RoleFunctionVO implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;
	private Long roleId;
	private Long platformId;
	private List<Long> functionIds;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public List<Long> getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(List<Long> functionIds) {
		this.functionIds = functionIds;
	}
}
