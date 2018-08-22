package com.people2000.user.web.vo;

import java.io.Serializable;
import java.util.Date;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.user.model.po.ibatis.User;

/**
 * @author crc
 * @time 2015-5-17 上午10:40:10
 * @description <pre>
 * 前台展示用户
 * </pre>
 */
public class UserVo extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer type;
	private Long parentId;
	private String username;
	private String nickname;
	private String companyName;
	private String contactPerson;
	private String mobile;
	private String email;
	private Integer status;
	private String remarks;
	private Date createTime;
	private Date updateTime;
	private Date checkTime;
	private Date deleteTime;
	private Long createUserId;
	private Long checkUserId;
	private Long updateUserId;
	private Long deleteUserId;
	private Integer deleteIs;
	private Integer sex;
	private String headPicUrl;
	private Date birthday;
	private String userProvince;
	private String userCity;
	private String userRegion;
	private String userAddress;
	private Long orgId;// 部门id
	private Long positionId;// 岗位id
	private Date lastLoginTime;// 最后登录时间
	private String lastLoginIp;// 最后登录ip
	private String createUserip;// 注册ip
	private String mobile2;// 手机号*好隐藏

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getHeadPicUrl() {
		return headPicUrl;
	}

	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUserProvince() {
		return userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserRegion() {
		return userRegion;
	}

	public void setUserRegion(String userRegion) {
		this.userRegion = userRegion;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public UserVo(User user) {
		BeanUtils.copyProperties(user, this);
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Long getDeleteUserId() {
		return deleteUserId;
	}

	public void setDeleteUserId(Long deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public Integer getDeleteIs() {
		return deleteIs;
	}

	public void setDeleteIs(Integer deleteIs) {
		this.deleteIs = deleteIs;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getCreateUserip() {
		return createUserip;
	}

	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}

}
