package com.people2000.user.business.write.manage;

import java.util.List;
import java.util.Map;

import com.people2000.user.model.dto.RolePgDto;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.vo.RoleVo;

public interface RoleWriteManage {

	/**
	 * 
	 * @description <pre>
	 * 获取所有子账户的角色
	 * </pre>
	 * @return
	 */
	List<Role> getAllChildRole() throws Exception;

	/**
	 * 
	 * @description <pre>
	 * 根据查询条件查询角色信息
	 * </pre>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Role> getListRoleData(Role role) throws Exception;

	/**
	 * 
	 * @description <pre>
	 * 新增角色
	 * </pre>
	 * @param role
	 */
	void saveRoleWithTx(RoleVo role) throws Exception;

	/**
	 * 给角色赋权
	 * 
	 * @param role
	 * @throws Exception
	 */
	void addFunctionsToRoleWithTx(RoleVo role) throws Exception;

	/**
	 * 
	 * @description <pre>
	 * 删除角色
	 * </pre>
	 * @param role
	 */
	void deleteRoleWithTx(Role role) throws Exception;

	/**
	 * @description <pre>
	 * 根据ID查询角色
	 * </pre>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Role getRoleById(Long id) throws Exception;

	/**
	 * @description <pre>
	 * 根据条件查询角色
	 * </pre>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getRoleByExamplePG(RolePgDto role) throws Exception;

	/**
	 * @description <pre>
	 * 修改角色
	 * </pre>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	void updateRoleWithTx(Role role) throws Exception;

	/**
	 * @description <pre>
	 * 查询code是否存在(新增)
	 * </pre>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List queryCodeForAdd(String code);

	/**
	 * @description <pre>
	 * 查询code是否存在修改)
	 * </pre>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List queryCodeForUpdate(String code, Long id);

	/**
	 * 查询角色名是否存在
	 * 
	 * @param name
	 * @return
	 */
	List queryRoleNameForAdd(String name);
}
