package com.people2000.user.model.vo;

import java.io.Serializable;

public class PlatformGroupRelationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7255781733216322267L;

	private String platformGroupName;// 平台组名称

	private Long platformGroupId;// 平台组id

	private Long companyId;// 公司id

	public String getPlatformGroupName() {
		return platformGroupName;
	}

	public void setPlatformGroupName(String platformGroupName) {
		this.platformGroupName = platformGroupName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getPlatformGroupId() {
		return platformGroupId;
	}

	public void setPlatformGroupId(Long platformGroupId) {
		this.platformGroupId = platformGroupId;
	}

}
