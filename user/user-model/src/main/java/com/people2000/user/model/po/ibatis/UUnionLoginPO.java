package com.people2000.user.model.po.ibatis;

import java.io.Serializable;
import java.util.Date;

public class UUnionLoginPO implements Serializable {
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Long id;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Long userId;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.oauth_access_token
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private String oauthAccessToken;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.oauth_refresh_token
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private String oauthRefreshToken;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.oauth_open_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private String oauthOpenId;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.oauth_source_system
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Integer oauthSourceSystem;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.oauth_expires_in
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Long oauthExpiresIn;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.create_time
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Date createTime;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.update_time
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Date updateTime;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.delete_time
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Date deleteTime;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.create_user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Long createUserId;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.update_user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Long updateUserId;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.delete_user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Long deleteUserId;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.is_deleted
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Integer isDeleted;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.checktime
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private Date checktime;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database column u_union_login.oauth_union_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	private String oauthUnionId;

	private static long serialVersionUID = 1L;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.id
	 *
	 * @return the value of u_union_login.id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.id
	 *
	 * @param id
	 *            the value for u_union_login.id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.user_id
	 *
	 * @return the value of u_union_login.user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.user_id
	 *
	 * @param userId
	 *            the value for u_union_login.user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.oauth_access_token
	 *
	 * @return the value of u_union_login.oauth_access_token
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public String getOauthAccessToken() {
		return oauthAccessToken;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.oauth_access_token
	 *
	 * @param oauthAccessToken
	 *            the value for u_union_login.oauth_access_token
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setOauthAccessToken(String oauthAccessToken) {
		this.oauthAccessToken = oauthAccessToken;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.oauth_refresh_token
	 *
	 * @return the value of u_union_login.oauth_refresh_token
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public String getOauthRefreshToken() {
		return oauthRefreshToken;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.oauth_refresh_token
	 *
	 * @param oauthRefreshToken
	 *            the value for u_union_login.oauth_refresh_token
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setOauthRefreshToken(String oauthRefreshToken) {
		this.oauthRefreshToken = oauthRefreshToken;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.oauth_open_id
	 *
	 * @return the value of u_union_login.oauth_open_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public String getOauthOpenId() {
		return oauthOpenId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.oauth_open_id
	 *
	 * @param oauthOpenId
	 *            the value for u_union_login.oauth_open_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setOauthOpenId(String oauthOpenId) {
		this.oauthOpenId = oauthOpenId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.oauth_source_system
	 *
	 * @return the value of u_union_login.oauth_source_system
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Integer getOauthSourceSystem() {
		return oauthSourceSystem;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.oauth_source_system
	 *
	 * @param oauthSourceSystem
	 *            the value for u_union_login.oauth_source_system
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setOauthSourceSystem(Integer oauthSourceSystem) {
		this.oauthSourceSystem = oauthSourceSystem;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.oauth_expires_in
	 *
	 * @return the value of u_union_login.oauth_expires_in
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Long getOauthExpiresIn() {
		return oauthExpiresIn;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.oauth_expires_in
	 *
	 * @param oauthExpiresIn
	 *            the value for u_union_login.oauth_expires_in
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setOauthExpiresIn(Long oauthExpiresIn) {
		this.oauthExpiresIn = oauthExpiresIn;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.create_time
	 *
	 * @return the value of u_union_login.create_time
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.create_time
	 *
	 * @param createTime
	 *            the value for u_union_login.create_time
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.update_time
	 *
	 * @return the value of u_union_login.update_time
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.update_time
	 *
	 * @param updateTime
	 *            the value for u_union_login.update_time
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.delete_time
	 *
	 * @return the value of u_union_login.delete_time
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Date getDeleteTime() {
		return deleteTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.delete_time
	 *
	 * @param deleteTime
	 *            the value for u_union_login.delete_time
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.create_user_id
	 *
	 * @return the value of u_union_login.create_user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.create_user_id
	 *
	 * @param createUserId
	 *            the value for u_union_login.create_user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.update_user_id
	 *
	 * @return the value of u_union_login.update_user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.update_user_id
	 *
	 * @param updateUserId
	 *            the value for u_union_login.update_user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.delete_user_id
	 *
	 * @return the value of u_union_login.delete_user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Long getDeleteUserId() {
		return deleteUserId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.delete_user_id
	 *
	 * @param deleteUserId
	 *            the value for u_union_login.delete_user_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setDeleteUserId(Long deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.is_deleted
	 *
	 * @return the value of u_union_login.is_deleted
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.is_deleted
	 *
	 * @param isDeleted
	 *            the value for u_union_login.is_deleted
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.checktime
	 *
	 * @return the value of u_union_login.checktime
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public Date getChecktime() {
		return checktime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.checktime
	 *
	 * @param checktime
	 *            the value for u_union_login.checktime
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the
	 * value of the database column u_union_login.oauth_union_id
	 *
	 * @return the value of u_union_login.oauth_union_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public String getOauthUnionId() {
		return oauthUnionId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the
	 * value of the database column u_union_login.oauth_union_id
	 *
	 * @param oauthUnionId
	 *            the value for u_union_login.oauth_union_id
	 *
	 * @abatorgenerated Thu Oct 13 20:49:35 CST 2016
	 */
	public void setOauthUnionId(String oauthUnionId) {
		this.oauthUnionId = oauthUnionId;
	}
}