package com.people2000.user.model.po.ibatis;

import java.io.Serializable;
import java.util.List;

public class OrganizationQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7012218950395596355L;
	private Long orgId;// 组织id
	private Long userId;// 用户id
	private Long companyId;
	private String username;// 用户名称
	private String mobile;// 手机
	private String orgName;
	private String identityCardName;// 姓名

	private Integer level;// 部门等级
	private Long positionId;// 岗位id

	private List<Long> userIds;// 用户id list

	private String flag;// 标记是在组织内还是不在组织内 1是

	private Integer currentPage;// 当前页

	private Integer startItems;// 启始条
	private Integer itemsPerPage;// 每页显示条数

	private Integer type;// 用户类型

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getStartItems() {
		return startItems;
	}

	public void setStartItems(Integer startItems) {
		this.startItems = startItems;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdentityCardName() {
		return identityCardName;
	}

	public void setIdentityCardName(String identityCardName) {
		this.identityCardName = identityCardName;
	}

}
