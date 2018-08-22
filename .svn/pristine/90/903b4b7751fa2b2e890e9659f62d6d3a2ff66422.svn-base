package com.people2000.user.model.dto;

import java.io.Serializable;

/**
 * Created by xiaole on 2016/3/4.
 */
public class PushMessageOutputDTO implements Serializable {
	private Long id;

	private Long userId;

	private String alias;

	private String tags;
	private String[] arrTags;
	private String deviceId;

	private Integer clientType;

	private Integer appType;

	private Long companyId;

	public String[] getArrTags() {
		if (tags != null) {
			return tags.split(",");
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
