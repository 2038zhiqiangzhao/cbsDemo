/**
 *
 */
package com.people2000.user.web.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.user.business.write.manage.UserRoleWriteManage;
import com.people2000.user.model.dto.UserRoleDTO;
import com.people2000.user.model.po.ibatis.UserRole;
import com.people2000.user.web.BaseAction;

/**
 * @author crc
 * @time 2015-4-28 下午2:30:56
 * @description <pre>
 * <p/>
 * </pre>
 */
@Controller
@RequestMapping(value = "/userRole")
public class UserRoleAction extends BaseAction {

	@Resource(name = "userRoleWriteManage")
	private UserRoleWriteManage userRoleWriteManage;

	@RequestMapping(value = "/deleteUserRole")
	@ResponseBody
	public Object deleteUserRole(@RequestBody UserRole userRole)
			throws Exception {
		userRoleWriteManage.deleteUserRoleWithTx(userRole.getRoleId(),
				userRole.getUserId());
		return success("");
	}

	@RequestMapping(value = "/addUserRole")
	@ResponseBody
	public Object addUserRole(@RequestBody UserRole userRole) throws Exception {
		userRoleWriteManage.addUserRoleWithTx(userRole.getRoleId(),
				userRole.getUserId());
		return success("");
	}

	@RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
	@ResponseBody
	public Object updateUserRole(@RequestBody UserRoleDTO userRoleDTO)
			throws Exception {
		if (userRoleDTO.getUserId() == null) {
			return fail("修改失败，参数异常");
		}
		userRoleWriteManage.UpdateUserRoleWithTx(userRoleDTO.getUserId(),
				userRoleDTO.getRoleList());
		return success("");
	}

}
