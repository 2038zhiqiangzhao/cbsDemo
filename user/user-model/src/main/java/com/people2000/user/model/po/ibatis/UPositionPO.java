package com.people2000.user.model.po.ibatis;

import java.io.Serializable;
import java.util.Date;

public class UPositionPO implements Serializable {
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.id
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Long id;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.position_code
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String positionCode;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.position_name
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String positionName;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.position_desc
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String positionDesc;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.is_available
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Integer isAvailable;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.is_deleted
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Integer isDeleted;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.version_no
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Integer versionNo;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.create_userid
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Long createUserid;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.create_username
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String createUsername;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.create_userip
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String createUserip;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.create_usermac
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String createUsermac;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.create_time
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Date createTime;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.create_time_db
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Date createTimeDb;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.server_ip
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String serverIp;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.update_userid
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Long updateUserid;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.update_username
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String updateUsername;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.update_userip
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String updateUserip;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.update_usermac
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String updateUsermac;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.update_time
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Date updateTime;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.update_time_db
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Date updateTimeDb;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.client_versionno
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private String clientVersionno;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_position.company_id
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	private Long companyId;

	private static long serialVersionUID = 1L;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.id
	 *
	 * @return the value of u_position.id
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.id
	 *
	 * @param id
	 *            the value for u_position.id
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.position_code
	 *
	 * @return the value of u_position.position_code
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getPositionCode() {
		return positionCode;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.position_code
	 *
	 * @param positionCode
	 *            the value for u_position.position_code
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.position_name
	 *
	 * @return the value of u_position.position_name
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.position_name
	 *
	 * @param positionName
	 *            the value for u_position.position_name
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.position_desc
	 *
	 * @return the value of u_position.position_desc
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getPositionDesc() {
		return positionDesc;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.position_desc
	 *
	 * @param positionDesc
	 *            the value for u_position.position_desc
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.is_available
	 *
	 * @return the value of u_position.is_available
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Integer getIsAvailable() {
		return isAvailable;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.is_available
	 *
	 * @param isAvailable
	 *            the value for u_position.is_available
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.is_deleted
	 *
	 * @return the value of u_position.is_deleted
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.is_deleted
	 *
	 * @param isDeleted
	 *            the value for u_position.is_deleted
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.version_no
	 *
	 * @return the value of u_position.version_no
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Integer getVersionNo() {
		return versionNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.version_no
	 *
	 * @param versionNo
	 *            the value for u_position.version_no
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.create_userid
	 *
	 * @return the value of u_position.create_userid
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.create_userid
	 *
	 * @param createUserid
	 *            the value for u_position.create_userid
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.create_username
	 *
	 * @return the value of u_position.create_username
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getCreateUsername() {
		return createUsername;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.create_username
	 *
	 * @param createUsername
	 *            the value for u_position.create_username
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.create_userip
	 *
	 * @return the value of u_position.create_userip
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getCreateUserip() {
		return createUserip;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.create_userip
	 *
	 * @param createUserip
	 *            the value for u_position.create_userip
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.create_usermac
	 *
	 * @return the value of u_position.create_usermac
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getCreateUsermac() {
		return createUsermac;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.create_usermac
	 *
	 * @param createUsermac
	 *            the value for u_position.create_usermac
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.create_time
	 *
	 * @return the value of u_position.create_time
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.create_time
	 *
	 * @param createTime
	 *            the value for u_position.create_time
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.create_time_db
	 *
	 * @return the value of u_position.create_time_db
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Date getCreateTimeDb() {
		return createTimeDb;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.create_time_db
	 *
	 * @param createTimeDb
	 *            the value for u_position.create_time_db
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setCreateTimeDb(Date createTimeDb) {
		this.createTimeDb = createTimeDb;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.server_ip
	 *
	 * @return the value of u_position.server_ip
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getServerIp() {
		return serverIp;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.server_ip
	 *
	 * @param serverIp
	 *            the value for u_position.server_ip
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.update_userid
	 *
	 * @return the value of u_position.update_userid
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Long getUpdateUserid() {
		return updateUserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.update_userid
	 *
	 * @param updateUserid
	 *            the value for u_position.update_userid
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.update_username
	 *
	 * @return the value of u_position.update_username
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getUpdateUsername() {
		return updateUsername;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.update_username
	 *
	 * @param updateUsername
	 *            the value for u_position.update_username
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.update_userip
	 *
	 * @return the value of u_position.update_userip
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getUpdateUserip() {
		return updateUserip;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.update_userip
	 *
	 * @param updateUserip
	 *            the value for u_position.update_userip
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setUpdateUserip(String updateUserip) {
		this.updateUserip = updateUserip;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.update_usermac
	 *
	 * @return the value of u_position.update_usermac
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getUpdateUsermac() {
		return updateUsermac;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.update_usermac
	 *
	 * @param updateUsermac
	 *            the value for u_position.update_usermac
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setUpdateUsermac(String updateUsermac) {
		this.updateUsermac = updateUsermac;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.update_time
	 *
	 * @return the value of u_position.update_time
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.update_time
	 *
	 * @param updateTime
	 *            the value for u_position.update_time
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.update_time_db
	 *
	 * @return the value of u_position.update_time_db
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Date getUpdateTimeDb() {
		return updateTimeDb;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.update_time_db
	 *
	 * @param updateTimeDb
	 *            the value for u_position.update_time_db
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setUpdateTimeDb(Date updateTimeDb) {
		this.updateTimeDb = updateTimeDb;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.client_versionno
	 *
	 * @return the value of u_position.client_versionno
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public String getClientVersionno() {
		return clientVersionno;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.client_versionno
	 *
	 * @param clientVersionno
	 *            the value for u_position.client_versionno
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setClientVersionno(String clientVersionno) {
		this.clientVersionno = clientVersionno;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_position.company_id
	 *
	 * @return the value of u_position.company_id
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_position.company_id
	 *
	 * @param companyId
	 *            the value for u_position.company_id
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}