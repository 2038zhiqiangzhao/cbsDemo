package com.people2000.user.model.dto;

import java.io.Serializable;
import java.util.Date;

public class UOrganizationDTO implements Serializable {

	private static final long serialVersionUID = -6339673088869964990L;

	private Long id;

	private String code;

	private String parentCode;

	private String orgName;

	private Long companyId;

	private Long platformId;

	private String remarks;

	private Integer isVirtual;

	private Integer isAvailable;

	private Integer isDeleted;

	private Integer versionNo;

	private Long createUserid;

	private String createUsername;

	private String createUserip;

	private String createUsermac;

	private Date createTime;

	private Date createTimeDb;

	private String serverIp;

	private Long updateUserid;

	private String updateUsername;

	private String updateUserip;

	private String updateUsermac;

	private Date updateTime;

	private Date updateTimeDb;

	private String clientVersionno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
	}

	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	public Long getCreateUserid() {
		return createUserid;
	}

	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public String getCreateUserip() {
		return createUserip;
	}

	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}

	public String getCreateUsermac() {
		return createUsermac;
	}

	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTimeDb() {
		return createTimeDb;
	}

	public void setCreateTimeDb(Date createTimeDb) {
		this.createTimeDb = createTimeDb;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Long getUpdateUserid() {
		return updateUserid;
	}

	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}

	public String getUpdateUsername() {
		return updateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}

	public String getUpdateUserip() {
		return updateUserip;
	}

	public void setUpdateUserip(String updateUserip) {
		this.updateUserip = updateUserip;
	}

	public String getUpdateUsermac() {
		return updateUsermac;
	}

	public void setUpdateUsermac(String updateUsermac) {
		this.updateUsermac = updateUsermac;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTimeDb() {
		return updateTimeDb;
	}

	public void setUpdateTimeDb(Date updateTimeDb) {
		this.updateTimeDb = updateTimeDb;
	}

	public String getClientVersionno() {
		return clientVersionno;
	}

	public void setClientVersionno(String clientVersionno) {
		this.clientVersionno = clientVersionno;
	}

}