package com.people2000.user.web.vo;

import java.io.Serializable;

import com.people2000.user.model.po.ibatis.UOrganizationPO;

public class OrgVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -872037461766531997L;
	private String id;
	private String pId;
	private Long orgId;
	private String name;
	private Boolean open = true;
	private Boolean checked;

	public OrgVo(UOrganizationPO uOrganizationPO) {
		if (null != uOrganizationPO) {
			this.id = uOrganizationPO.getCode();
			this.pId = uOrganizationPO.getParentCode();
			this.orgId = uOrganizationPO.getId();
			this.name = uOrganizationPO.getOrgName() != null ? uOrganizationPO
					.getOrgName() : "";
			this.checked = Boolean.FALSE;
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
