package com.people2000.user.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.people2000.common.base.page.Pagination;

/**
 * Created by xiaole on 2016/1/15.
 */
public class UserAreaDTO extends Pagination implements Serializable {
	// 用户id
	private Long userId;
	// 注册时间范围开始时间
	private Date startTime;
	// 注册时间范围结束时间
	private Date endTime;
	// 手机号码
	private String mobile;
	// 收货人名字
	private String userName;
	// //省id
	// private Long provinceId;
	// //市id
	// private Long cityId;
	// //区id
	// private Long regionId;
	// 随机id
	private Long unknowId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	// public Long getProvinceId() {
	// return provinceId;
	// }
	//
	// public void setProvinceId(Long provinceId) {
	// this.provinceId = provinceId;
	// }
	//
	// public Long getCityId() {
	// return cityId;
	// }
	//
	// public void setCityId(Long cityId) {
	// this.cityId = cityId;
	// }
	//
	// public Long getRegionId() {
	// return regionId;
	// }
	//
	// public void setRegionId(Long regionId) {
	// this.regionId = regionId;
	// }

	public Long getUnknowId() {
		return unknowId;
	}

	public void setUnknowId(Long unknowId) {
		this.unknowId = unknowId;
	}
}
