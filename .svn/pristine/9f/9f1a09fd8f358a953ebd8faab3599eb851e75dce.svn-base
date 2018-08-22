package com.people2000.user.model.vo;

import java.util.List;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.user.model.po.ibatis.Role;

public class RoleVo extends Role {
	/**
	 * 前台展示角色类
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * private Long id; private String code;// 角色编码 private String name; private
	 * String desc;
	 */
	/*
	 * private Date createTime;// 创建时间
	 *//*
		 * private Long platformId;// 平台id
		 */private List<Long> functionIds;// 所选权限

	private List<Long> roleIds;// 角色ids

	public RoleVo(Role role) {
		BeanUtils.copyProperties(role, this);
	}

	public RoleVo() {

	}

	private Long userId;
	private Boolean checked;

	/*
	 * public Long getPlatformId() { return platformId; }
	 * 
	 * public void setPlatformId(Long platformId) { this.platformId =
	 * platformId; }
	 * 
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getCode() { return code; }
	 * 
	 * public void setCode(String code) { this.code = code; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getDesc() { return desc; }
	 * 
	 * public void setDesc(String desc) { this.desc = desc; }
	 * 
	 * public Date getCreateTime() { return createTime; }
	 * 
	 * public void setCreateTime(Date createTime) { this.createTime =
	 * createTime; }
	 */

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<Long> getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(List<Long> functionIds) {
		this.functionIds = functionIds;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
}
