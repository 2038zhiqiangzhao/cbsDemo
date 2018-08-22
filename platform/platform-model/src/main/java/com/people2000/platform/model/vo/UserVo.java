/**
 * 
 */
package com.people2000.platform.model.vo;

import java.io.Serializable;

/**
 * 
 * 用户vo
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:dusai,date:2017年3月29日 上午11:25:26,content:
 * </p>
 * 
 * @author dusai
 * @date 2017年3月29日 上午11:25:26
 * @since
 * @version
 */
public class UserVo implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -5186035279103427907L;
	private String name;
	private Integer type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
