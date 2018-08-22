package com.people2000.user.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.people2000.common.base.page.Pagination;

/**
 * Created by xiaole on 2016/2/23.
 */
public class UserInputDTO extends Pagination implements Serializable {
	// 手机号码
	private String mobile;
	// 用户名
	private String username;
	/**
	 * 用户类型
	 */
	private Integer type;

	/**
	 * 用户状态
	 */
	private Integer status;
	// 注册时间范围开始时间
	private Date startTime;
	// 注册时间范围结束时间
	private Date endTime;

	private Integer isAvailable;// 是否启用

	private String identityCardName;// 身份证

	private Long companyId;

	private Long userId;// 用户id

	private String password;// 密码

	private String password1;// 新密码

	private String password2;// 确认新密码

	private Long createUserid;

	public Long getCreateUserid() {
		return createUserid;
	}

	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getIdentityCardName() {
		return identityCardName;
	}

	public void setIdentityCardName(String identityCardName) {
		this.identityCardName = identityCardName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
