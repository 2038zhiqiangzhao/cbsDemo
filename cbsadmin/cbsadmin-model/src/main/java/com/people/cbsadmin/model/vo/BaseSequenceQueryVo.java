package com.people.cbsadmin.model.vo;

import java.io.Serializable;
import java.util.List;

import com.people2000.common.base.page.Pagination;




public class BaseSequenceQueryVo extends Pagination implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private List<Long> ids;

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
	

}
