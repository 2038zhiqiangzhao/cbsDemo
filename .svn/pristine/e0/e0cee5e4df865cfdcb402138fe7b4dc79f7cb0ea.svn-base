/**
 * 
 */
package com.people2000.user.business.write.manage;

import java.util.List;
import java.util.Set;

/**
 * @author crc
 * @time 2015-5-15 上午9:48:19
 * @description <pre>
 * 
 * </pre>
 *
 */
public interface RoleFunctionWriteManage {

	/**
	 * 
	 * @description <pre>
	 * 根据角色id获取角色所有权限
	 * </pre>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Set<Long> getFunctionIdsByRoleId(Long id) throws Exception;

	/**
	 * 
	 * @description <pre>
	 * 保存角色与权限的关联关系
	 * </pre>
	 * @param roleId
	 * @param functionIds
	 * @throws Exception
	 */
	void saveRoleFunctionWithTx(Long roleId, List<Long> functionIds,
			Long platformId) throws Exception;

	/**
	 * 根据角色列表获取角色所有权限
	 * 
	 * @param idList
	 * @param isAvailable
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	Set<Long> getFunctionIdsByRoleIdList(List<Long> idList,
			Integer isAvailable, Long companyId) throws Exception;
}
