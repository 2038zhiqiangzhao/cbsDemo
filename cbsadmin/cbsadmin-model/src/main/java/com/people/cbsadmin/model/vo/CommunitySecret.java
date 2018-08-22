package com.people.cbsadmin.model.vo;

import java.io.Serializable;

public class CommunitySecret  implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Secret;//秘钥
	private Long communityId;
	private Long activityId;
	private String alertMessage;//提示消息
	private Long userId;
	public String getSecret() {
		return Secret;
	}
	public void setSecret(String secret) {
		Secret = secret;
	}
	public Long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}


}
