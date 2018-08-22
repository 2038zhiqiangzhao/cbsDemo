package com.people2000.user.model.po.ibatis;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xuc
 * @time 2016年6月18日 下午3:37:14
 * @description 企业加密信息
 */
public class UEncryptInfo implements Serializable {

	private static final long serialVersionUID = -504148065005685591L;

	private Long id;// id

	private Long companyId;// companyId

	private String encryptKey;// 加密算法key值

	private Integer fAlgorithmVersion;// 密码前端加密算法版本

	private Integer fSaltChangeDays;// 密码前端加密salt有效期限

	private Integer bAlgorithmVersion;// 密码后端端加密算法版本

	private Integer bSaltChangeDays;// 密码后端端加密salt有效期限

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	public Integer getfAlgorithmVersion() {
		return fAlgorithmVersion;
	}

	public void setfAlgorithmVersion(Integer fAlgorithmVersion) {
		this.fAlgorithmVersion = fAlgorithmVersion;
	}

	public Integer getfSaltChangeDays() {
		return fSaltChangeDays;
	}

	public void setfSaltChangeDays(Integer fSaltChangeDays) {
		this.fSaltChangeDays = fSaltChangeDays;
	}

	public Integer getbAlgorithmVersion() {
		return bAlgorithmVersion;
	}

	public void setbAlgorithmVersion(Integer bAlgorithmVersion) {
		this.bAlgorithmVersion = bAlgorithmVersion;
	}

	public Integer getbSaltChangeDays() {
		return bSaltChangeDays;
	}

	public void setbSaltChangeDays(Integer bSaltChangeDays) {
		this.bSaltChangeDays = bSaltChangeDays;
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

}
