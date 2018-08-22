package com.people2000.user.model.dto;

import java.util.Date;

import com.people2000.user.model.po.ibatis.User;

public class User3 extends User {

	private static final long serialVersionUID = 2916887095828573652L;

	private Long orgId;// 部门id

	private Long positionId;// 岗位id

	private Date lastLoginTime;// 最后登录时间

	private String lastLoginIp;// 最后登录ip

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

}