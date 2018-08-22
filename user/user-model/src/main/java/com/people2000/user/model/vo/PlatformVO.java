package com.people2000.user.model.vo;

import java.io.Serializable;

public class PlatformVO implements Serializable {

	private Long id;// 平台id

	private String platformName;// 平台名称

	private int isCheck;// 是否选中

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

}
