/**
 * 
 */
package com.people2000.user.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author crc
 * @time 2015-5-15 上午10:25:43
 * @description <pre>
 * 
 * </pre>
 * 
 */
public class RoleFunctionDto implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;
	private Long roleId;
	private List<Long> functionIds;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<Long> getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(List<Long> functionIds) {
		this.functionIds = functionIds;
	}
}
