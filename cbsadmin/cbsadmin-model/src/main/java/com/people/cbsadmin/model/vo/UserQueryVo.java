package com.people.cbsadmin.model.vo;

import java.io.Serializable;
import java.util.List;

import com.people2000.common.base.page.Pagination;

public class UserQueryVo extends Pagination implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 8558440614436977394L;

	private Long id;
	private List<Long> ids;
	private String username;
	private String mobile;
	private Integer type;
	private String email;
	private String identityCardName;
	private String employeNum;// 工号
	private Long gradeId;// 等级id
	private Long companyId;// 公司id
	private String companyName;// 公司名
	private String refereeMobile;// 推荐人手机
	private Integer identityType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentityCardName() {
		return identityCardName;
	}

	public void setIdentityCardName(String identityCardName) {
		this.identityCardName = identityCardName;
	}

	public String getEmployeNum() {
		return employeNum;
	}

	public void setEmployeNum(String employeNum) {
		this.employeNum = employeNum;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRefereeMobile() {
		return refereeMobile;
	}

	public void setRefereeMobile(String refereeMobile) {
		this.refereeMobile = refereeMobile;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

}
