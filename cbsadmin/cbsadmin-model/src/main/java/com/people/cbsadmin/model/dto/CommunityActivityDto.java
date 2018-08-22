package com.people.cbsadmin.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.people2000.common.base.page.Pagination;






public class CommunityActivityDto extends Pagination implements Serializable {

	/**
	 * 
	 */
	  private String authCode;
	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
    private Long adminId;
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	private static final long serialVersionUID = 1L;
	private List<Long> ids;
	private Long id;
    private Date activityTimeStartTime;//活动开始时间整点半
    private Date applyEndTimeStartTime;//报名截止时间整点半
    public Date getActivityTimeStartTime() {
		return activityTimeStartTime;
	}

	public void setActivityTimeStartTime(Date activityTimeStartTime) {
		this.activityTimeStartTime = activityTimeStartTime;
	}

	public Date getApplyEndTimeStartTime() {
		return applyEndTimeStartTime;
	}

	public void setApplyEndTimeStartTime(Date applyEndTimeStartTime) {
		this.applyEndTimeStartTime = applyEndTimeStartTime;
	}

	public Date getTimeIntervalStartTime() {
		return timeIntervalStartTime;
	}

	public void setTimeIntervalStartTime(Date timeIntervalStartTime) {
		this.timeIntervalStartTime = timeIntervalStartTime;
	}

	private Date timeIntervalStartTime;//发布时间整点半
	
	

	private String activityName;

	private Date activityTime;
	private Date activityEndTime;

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	private String richTextArea;

	public String getRichTextArea() {
		return richTextArea;
	}

	public void setRichTextArea(String richTextArea) {
		this.richTextArea = richTextArea;
	}

	private String activityPlace;

	private BigDecimal registrationFee;

	private Date timeInterval;

	private Integer publishState;

	private Integer activityState;

	private Integer isAvailable;

	private Integer isDeleted;

	private String createUserName;

	private String createUserPhone;

	private String surfacePicture;

	private Integer isTimePublish;

	public Integer getIsTimePublish() {
		return isTimePublish;
	}

	public void setIsTimePublish(Integer isTimePublish) {
		this.isTimePublish = isTimePublish;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	private Date applyEndTime;

	private Date updateTime;

	private Date createTime;

	private Long updateUserId;

	private Long createUserId;

	private Long userId;

	private String qrExt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName == null ? null : activityName.trim();
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public String getActivityPlace() {
		return activityPlace;
	}

	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace == null ? null : activityPlace
				.trim();
	}

	public BigDecimal getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(BigDecimal registrationFee) {
		this.registrationFee = registrationFee;
	}

	public Date getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Date timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Integer getPublishState() {
		return publishState;
	}

	public void setPublishState(Integer publishState) {
		this.publishState = publishState;
	}

	public Integer getActivityState() {
		return activityState;
	}

	public void setActivityState(Integer activityState) {
		this.activityState = activityState;
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

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName == null ? null : createUserName
				.trim();
	}

	public String getCreateUserPhone() {
		return createUserPhone;
	}

	public void setCreateUserPhone(String createUserPhone) {
		this.createUserPhone = createUserPhone == null ? null : createUserPhone
				.trim();
	}

	public String getSurfacePicture() {
		return surfacePicture;
	}

	public void setSurfacePicture(String surfacePicture) {
		this.surfacePicture = surfacePicture;
	}

	public Date getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(Date applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getQrExt() {
		return qrExt;
	}

	public void setQrExt(String qrExt) {
		this.qrExt = qrExt == null ? null : qrExt.trim();
	}
}
