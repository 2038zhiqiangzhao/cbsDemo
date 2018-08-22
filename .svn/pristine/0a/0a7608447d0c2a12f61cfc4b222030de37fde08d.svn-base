/**
 *
 */
package com.people2000.user.business.write.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.people2000.user.model.dto.RoleUpdateDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.po.ibatis.UserRole;
import com.people2000.user.model.vo.UserRoleVo;

/**
 * @author crc
 * @time 2015-5-17 下午1:26:18
 * @description <pre>
 * <p/>
 * </pre>
 */
public interface UserRoleWriteManage {

	/**
	 * @param id
	 * @return
	 * @description <pre>
	 * 根据用户id获取用户角色
	 * </pre>
	 */
	List<HashMap<String, Object>> getUserRole(Long id) throws Exception;

	/**
	 * @param id
	 * @return
	 * @description <pre>
	 * 根据用户id获取用户角色
	 * </pre>
	 */
	List<Role> getUserRoleByUserId(Long id) throws Exception;

	/**
	 * @param
	 * @param userId
	 * @throws Exception
	 * @description <pre>
	 * 给用户添加角色（修改）
	 * </pre>
	 */
	void UpdateUserRoleWithTx(Long userId, List<Long> roleIds) throws Exception;

	/**
	 * @param ids
	 * @throws Exception
	 * @description <pre>
	 * 删除用户对于的角色
	 * </pre>
	 */
	void deleteUserRoleWithTx(List<Long> ids) throws Exception;

	/**
	 * @param roleId
	 * @param userId
	 * @throws Exception
	 * @description <pre>
	 * 给用户删除角色
	 * </pre>
	 */
	void deleteUserRoleWithTx(Long roleId, Long userId) throws Exception;

	/**
	 * @param roleId
	 * @param userId
	 * @throws Exception
	 * @description <pre>
	 * 给用户添加角色
	 * </pre>
	 */
	void addUserRoleWithTx(Long roleId, Long userId) throws Exception;

	/**
	 * @param userId
	 * @throws Exception
	 * @description <pre>
	 * 给商家用户加载角色
	 * </pre>
	 */
	void getUserRoleMerchantWithTx(Long userId, String ut) throws Exception;

	/**
	 * @param userId
	 * @throws Exception
	 * @description <pre>
	 * 给技师用户加载角色
	 * </pre>
	 */
	void getUserRoleServiceWithTx(Long userId, String ut) throws Exception;

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 根据用户id获取用户拥有的角色id
	 * <p/>
	 * </pre>
	 */
	List<Long> getUserRoleList(Long userId) throws Exception;

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 根据用户id和平台id获取用户拥有的角色id
	 * <p/>
	 * </pre>
	 */
	List<Long> getUserRoleList(Long userId, Long platformId, Long companyId)
			throws Exception;

	/**
	 * 根据用户ID和角色ID查询关联关系
	 *
	 * @param userId
	 * @param roleId
	 * @param isAvailable
	 * @return
	 * @throws Exception
	 */
	List<UserRole> getUserRoleListByUserIdAndRoleId(Long userId, Long roleId,
			Integer isAvailable, Long companyId) throws Exception;

	/**
	 * 更新用户角色信息
	 *
	 * @param userId
	 * @param roleId
	 * @param isAvailable
	 * @throws Exception
	 */
	void updateUserRoleRelationWithTx(Long userId, Long roleId,
			Integer isAvailable, Long companyId) throws Exception;

	/**
	 * 更新用户角色
	 *
	 * @param commonInputDTO
	 */
	void updateRolesWithTx(RoleUpdateDTO commonInputDTO)
			throws OuserMangeException;

	/**
	 * 获取用户角色
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Role> getUserRoles(Map params) throws Exception;

	/**
	 * 给用户赋予角色
	 * 
	 * @param listUserRole
	 * @return
	 * @throws Exception
	 */
	Integer addRolesToUserWithTx(List<UserRole> listUserRole) throws Exception;

	Integer updateOneUserRoleWithTx(UserRole userRole) throws Exception;

	Integer batchDeleteUserRoleWithTx(UserRoleVo vo) throws Exception;

}
