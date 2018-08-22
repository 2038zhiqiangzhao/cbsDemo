/**
 * 
 */
package com.people2000.user.web.vo;

import java.io.Serializable;
import java.util.Set;

import com.people2000.user.model.po.ibatis.Function;

/**
 * @author crc
 * @time 2015-5-14 下午6:37:47
 * @description <pre>
 * 
 * </pre>
 * 
 */
public class FunctionVo implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;
	private String id;
	private String pId;
	private Long functionId;
	private String name;
	private Boolean open = true;
	private Boolean checked;

	public FunctionVo(Function function, Set<Long> set) {
		this.id = function.getCode();
		this.pId = function.getParentCode();
		this.functionId = function.getId();
		this.name = function.getName();
		if (set != null && set.size() > 0) {
			if (set.contains(function.getId())) {
				this.checked = Boolean.TRUE;
			} else {
				this.checked = Boolean.FALSE;
			}
		}

	}

	public FunctionVo(Function function) {
		this.id = function.getCode();
		this.pId = function.getParentCode();
		this.functionId = function.getId();
		this.name = function.getName() != null ? function.getName() : "";
		this.checked = Boolean.FALSE;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
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

	public Long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
