/**
 *
 */
package com.people2000.user.business.write.manage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.people2000.user.business.write.dao.ext.RoleFunctionDAOWrite2;
import com.people2000.user.business.write.manage.RoleFunctionWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.po.ibatis.RoleFunction;
import com.people2000.user.model.po.ibatis.RoleFunctionExample;

/**
 * @author crc
 * @time 2015-5-15 上午9:48:28
 * @description <pre>
 * <p/>
 * </pre>
 */
@Service("roleFunctionWriteManage")
public class RoleFunctionWriteManageImpl implements RoleFunctionWriteManage {

	@Resource(name = "roleFunctionDAOWrite")
	private RoleFunctionDAOWrite2 roleFunctionDAOWrite;

	@SuppressWarnings("unchecked")
	@Override
	public Set<Long> getFunctionIdsByRoleId(Long id) throws Exception {
		RoleFunctionExample example = new RoleFunctionExample();
		example.createCriteria().andRoleIdEqualTo(id);
		List<RoleFunction> list = roleFunctionDAOWrite.selectByExample(example,
				false);
		Set<Long> set = new HashSet<Long>();
		if (list != null && list.size() > 0) {
			for (RoleFunction roleFunction : list) {
				set.add(roleFunction.getFunctionId());
			}
		}
		return set;
	}

	@Override
	public void saveRoleFunctionWithTx(Long roleId, List<Long> functionIds,
			Long platformId) throws Exception {
		// 先删除角色之前对应的权限
		Map<String, Object> deleMap = new HashMap<String, Object>();
		deleMap.put("roleId", roleId);
		deleMap.put("platformId", platformId);
		deleMap.put("isDeleted", ConstantUser.isDelete.delete);
		roleFunctionDAOWrite.updateRoleFunction(deleMap);
		// 批量保存对应关系
		if (functionIds != null && functionIds.size() > 0) {
			List<RoleFunction> list = new ArrayList<RoleFunction>();
			for (Long functionId : functionIds) {
				RoleFunction roleFunction = new RoleFunction();
				roleFunction.setRoleId(roleId);
				roleFunction.setFunctionId(functionId);
				roleFunction.setIsDeleted(ConstantUser.isDelete.normal);
				list.add(roleFunction);
			}
			roleFunctionDAOWrite.insertBatch(list);
		}
	}

	@Override
	public Set<Long> getFunctionIdsByRoleIdList(List<Long> idList,
			Integer isAvailable, Long companyId) throws Exception {
		RoleFunctionExample example = new RoleFunctionExample();
		example.createCriteria().andRoleIdIn(idList)
				.andIsAvailableEqualTo(isAvailable)
				.andCompanyIdEqualTo(companyId);
		List<RoleFunction> list = roleFunctionDAOWrite.selectByExample(example,
				false);
		Set<Long> set = new HashSet<Long>();
		if (list != null && list.size() > 0) {
			for (RoleFunction roleFunction : list) {
				set.add(roleFunction.getFunctionId());
			}
		}
		return set;
	}

}
