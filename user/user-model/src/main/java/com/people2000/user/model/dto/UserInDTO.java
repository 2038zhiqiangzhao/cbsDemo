package com.people2000.user.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.people2000.common.base.page.Pagination;

/**
 * Created by xiaole on 2016/3/5.
 */
public class UserInDTO extends Pagination implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6717570991946810891L;
	private String originalId;
	private Long companyId;
	/**
	 * 用户编号
	 */
	private Long id;
	/**
	 * 用户类型
	 */
	private Integer type;

	/**
	 * 用户类型list,支持多种type
	 */
	private List<Integer> typeList;

	/**
	 * 用户名称
	 */
	private String username;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 固定电话
	 */
	private String telephone;

	/**
	 * 真实姓名
	 */
	private String identityCardName;

	private Integer isAvailable;
	/**
	 * 通过user的id来筛选
	 */
	private List<Long> userIdList;
	/**
	 * 筛选-注册时间开始
	 */
	private Date createTimeBegin;
	/**
	 * 筛选-注册时间结束
	 */
	private Date createTimeEnd;

	private Long createUserid;// 创建用户id
	private String createUsername;// 创建用户名

	private List<String> excludeUserNameList;// 排除用户名列表

	private Long departId;// 部门id

	private String nickName;// 昵称

	private Long entityId;// 实体id

	private Integer entityType;// 实体type

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<Integer> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Integer> typeList) {
		this.typeList = typeList;
	}

	public Long getCreateUserid() {
		return createUserid;
	}

	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentityCardName() {
		return identityCardName;
	}

	public void setIdentityCardName(String identityCardName) {
		this.identityCardName = identityCardName;
	}

	public List<Long> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Long> userIdList) {
		this.userIdList = userIdList;
	}

	public Date getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	@Override
	public String toString() {
		return "UserInDTO{" + "originalId='" + originalId + '\''
				+ ", companyId=" + companyId + ", id=" + id + ", username='"
				+ username + '\'' + ", mobile='" + mobile + '\'' + ", email='"
				+ email + '\'' + ", telephone='" + telephone + '\''
				+ ", identityCardName='" + identityCardName + '\''
				+ ", userIdList=" + userIdList + ", createTimeBegin="
				+ createTimeBegin + ", createTimeEnd=" + createTimeEnd + '}';
	}

	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public List<String> getExcludeUserNameList() {
		return excludeUserNameList;
	}

	public void setExcludeUserNameList(List<String> excludeUserNameList) {
		this.excludeUserNameList = excludeUserNameList;
	}

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

}
