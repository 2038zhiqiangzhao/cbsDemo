package com.people2000.user.model.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * @author crc
 * @time 2015-5-18 下午2:00:51
 * @description <pre>
 * 缓存中的用户
 * </pre>
 */
public class UserCache implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id; // 用户id
	private String username;// 用户名称
	private String mobile;// 用户手机
	private String originalId;// 原始id
	private Integer type;
	private Long companyId; // 公司id
	private Long parentId;// 父id，子账户父id！=0，非子账户父id=0
	private Long memberId;
	private String functionCodes;// 权限code
	private Set<String> functionPahts;// 权限 路径
	private Integer identityType;// 服务人员类型
	private boolean autoLogin = false;// 自动登录

	private String nickname;// nickname
	private String headPicUrl;// 头像
	@Deprecated
	private Long gradeId;// 会员等级id
	@Deprecated
	private Integer gradeType;// 会员等级类型
	private String companyName;// 公司名称
	private String identityCardName;// 真实姓名

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadPicUrl() {
		return headPicUrl;
	}

	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Set<String> getFunctionPahts() {
		return functionPahts;
	}

	public void setFunctionPahts(Set<String> functionPahts) {
		this.functionPahts = functionPahts;
	}

	public String getFunctionCodes() {
		return functionCodes;
	}

	public void setFunctionCodes(String functionCodes) {
		this.functionCodes = functionCodes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIdentityCardName() {
		return identityCardName;
	}

	public void setIdentityCardName(String identityCardName) {
		this.identityCardName = identityCardName;
	}

}