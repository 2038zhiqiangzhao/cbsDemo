/**
 * 
 */
package com.people2000.user.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.manage.RoleWriteManage;
import com.people2000.user.business.write.manage.UserRoleWriteManage;
import com.people2000.user.model.dto.RolePgDto;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.vo.RoleVo;
import com.people2000.user.web.BaseAction;

/**
 * @author crc
 * @time 2015-4-29 下午4:46:00
 * @description <pre>
 * 
 * </pre>
 *
 */
@Controller
@RequestMapping(value = "/role")
public class RoleAction extends BaseAction {

	@Resource(name = "roleWriteManage")
	private RoleWriteManage roleWriteManage;

	@Resource(name = "userRoleWriteManage")
	private UserRoleWriteManage userRoleWriteManage;

	/**
	 * 
	 * @description <pre>
	 * 获取所有子账户角色
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllChildRole", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllChildRole() throws Exception {
		List<Role> roleList = roleWriteManage.getAllChildRole();
		return success(roleList);
	}

	/**
	 * 
	 * @description <pre>
	 * 根据条件查询角色
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryListRoleData", method = RequestMethod.POST)
	@ResponseBody
	public Object getListRoleData(@RequestBody Role role) throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("根据查询条件查询角色信息方法被调用");
		List<Role> roleList = roleWriteManage.getListRoleData(role);
		return success(roleList);
	}

	/**
	 * @param
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 所有角色分页查询
	 * </pre>
	 */

	@RequestMapping(value = "/queryAllRoleDataPage", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryAllRoleDataPage(@RequestBody RolePgDto rolePgDto)
			throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("角色管理action被调用");
		Map<String, Object> map = roleWriteManage.getRoleByExamplePG(rolePgDto);
		List<Role> list = (List<Role>) map.get("list");
		List<RoleVo> resultList = new ArrayList<RoleVo>();
		if (list != null && list.size() > 0) {
			for (Role role : list) {
				RoleVo roleVo = new RoleVo(role);
				resultList.add(roleVo);

			}
		}
		return success(resultList,
				Integer.parseInt(map.get("total").toString()));
	}

	/**
	 * 
	 * @description <pre>
	 * 根据用户查询角色
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryRoleDataByUser", method = RequestMethod.POST)
	@ResponseBody
	public Object getRoleDataByUser(@RequestBody RoleVo role) throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("根据查询条件查询角色信息方法被调用");
		List<Role> roleList = roleWriteManage.getListRoleData(role);
		List<Long> RoleIdList = new ArrayList<Long>();
		if (role.getUserId() != null) {
			RoleIdList = userRoleWriteManage.getUserRoleList(role.getUserId(),
					role.getPlatformId(), null);
		}
		List<RoleVo> returnList = new ArrayList<RoleVo>();
		if (roleList != null && roleList.size() > 0) {
			for (Role role1 : roleList) {
				RoleVo roleVo = new RoleVo();
				BeanUtils.copyProperties(role1, roleVo);
				roleVo.setChecked(false);
				if (RoleIdList != null && RoleIdList.size() > 0) {
					for (Long roleId : RoleIdList) {
						if (roleVo.getId().equals(roleId)) {
							roleVo.setChecked(true);
							break;
						}
					}
				}
				returnList.add(roleVo);
			}
		}
		return success(returnList);
	}

	/**
	 * 
	 * @description <pre>
	 * 保存角色
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	@ResponseBody
	public Object saveRole(@RequestBody RoleVo role) throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("新增角色方法被调用");
		role.setType(1l);
		role.setProductType(1);
		roleWriteManage.saveRoleWithTx(role);
		return success("");
	}

	/**
	 * 
	 * @description <pre>
	 * 删除角色
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteRole(@RequestBody Role role) throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("删除角色方法被调用");
		roleWriteManage.deleteRoleWithTx(role);
		return success("");
	}

	/**
	 * 
	 * @description <pre>
	 * 根据id查询角色
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRoleById", method = RequestMethod.POST)
	@ResponseBody
	public Object getRoleById(@RequestBody Role role) throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("根据id查询角色方法被调用");
		Role role1 = roleWriteManage.getRoleById(role.getId());
		return success(role1);
	}

	/**
	 * 
	 * @description <pre>
	 * 修改角色
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateRole", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object updateFunction(@RequestBody RoleVo role) throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("修改角色");
		roleWriteManage.updateRoleWithTx(role);
		return success("");

	}

	/**
	 * 
	 * @description <pre>
	 * 查询code是否存在(新增)
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCodeForAdd", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryRoleByCode(@RequestBody RoleVo role) throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("查询code是否存在(新增)");
		List list = roleWriteManage.queryCodeForAdd(role.getCode());
		if (list != null && list.size() > 0) {
			System.out.println("已经存在");
		}

		return success(list);

	}

	/**
	 * 
	 * @description <pre>
	 * 查询角色名是否存在(新增)
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryRoleNameForAdd", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryRoleByName(@RequestBody RoleVo role) throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("查询角色名是否存在(新增)");
		List list = roleWriteManage.queryRoleNameForAdd(role.getName());
		if (list != null && list.size() > 0) {
			System.out.println("已经存在");
		}

		return success(list);

	}

	/**
	 * 
	 * @description <pre>
	 * 查询code是否存在(修改)
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCodeForUpdate", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryCodeForUpdate(@RequestBody RoleVo role) throws Exception {
		LogUtils.getLogger(RoleAction.class).debug("查询code是否存在(修改)");
		List list = roleWriteManage.queryCodeForUpdate(role.getCode(),
				role.getId());
		if (list != null && list.size() > 0) {
			System.out.println("已经存在");
		}

		return success(list);

	}

}
