package com.people2000.user.model.vo;

/**
 * Created by Administrator on 2016/9/19.
 */

import java.util.List;

import com.people2000.user.model.po.ibatis.UserRole;

/**
 * @author tangkai
 * @send tangkai@odianyun.com
 * @company ODY
 * @date date()
 * @description <pre>
 * </pre>
 */
public class UserRoleVo extends UserRole {
	private List<Long> ids;// ids

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
}
