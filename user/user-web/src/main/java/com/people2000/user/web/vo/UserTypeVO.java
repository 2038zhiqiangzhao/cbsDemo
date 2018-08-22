package com.people2000.user.web.vo;

/**
 * @author wangpengfei
 *
 */
public class UserTypeVO {

	private int userType; // 用户类型

	private String url; // 跳转url

	private String ut; // user ticket

	public String getUt() {
		return ut;
	}

	public void setUt(String ut) {
		this.ut = ut;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
