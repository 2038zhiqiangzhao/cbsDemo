package com.people2000.user.model.vo;

import java.io.Serializable;
import java.util.Date;

public class PositionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;// 岗位id

	private String positionName;// 岗位名称

	private String positionCode;// 岗位名称

	private String positionDesc;// 岗位描述-权限

	private Date createTimeStart;// 查询条件之起始时间

	private Date createTimeEnd;// 查询条件之终止时间

	private Integer currentPage;// 当前页

	private Integer startItems;// 启始条

	private Integer itemsPerPage;// 每页显示条数

	private Long companyId;// 公司id

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionDesc() {
		return positionDesc;
	}

	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getStartItems() {
		return startItems;
	}

	public void setStartItems(Integer startItems) {
		this.startItems = startItems;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
