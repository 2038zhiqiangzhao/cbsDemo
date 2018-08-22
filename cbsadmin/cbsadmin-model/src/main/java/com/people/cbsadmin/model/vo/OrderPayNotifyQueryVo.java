package com.people.cbsadmin.model.vo;

import java.io.Serializable;
import java.util.List;

import com.people2000.common.base.page.Pagination;

public class OrderPayNotifyQueryVo extends Pagination implements Serializable {
	
	/**
	 */
	private static final long serialVersionUID = 3770744040505331967L;

	private Long id;

	private List<Long> ids;

	private String orderCode;

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

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}
