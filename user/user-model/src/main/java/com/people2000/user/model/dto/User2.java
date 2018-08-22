package com.people2000.user.model.dto;

import java.util.List;

import com.people2000.user.model.po.ibatis.User;

public class User2 extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8863949574078234471L;
	private Long inviteUserId;// 邀请人id
	private String inviteUserName;// 邀请人姓名
	private String job;// 职业

	private List<Long> roleIds;
	private Long orgId;
	private String orgName;

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Long getInviteUserId() {
		return inviteUserId;
	}

	public void setInviteUserId(Long inviteUserId) {
		this.inviteUserId = inviteUserId;
	}

	public String getInviteUserName() {
		return inviteUserName;
	}

	public void setInviteUserName(String inviteUserName) {
		this.inviteUserName = inviteUserName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}