package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.utils.UpdateCacheUtil;
import com.people2000.user.business.write.dao.RoleDAOWrite;
import com.people2000.user.business.write.dao.ext.FunctionDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserRoleDAOWrite2;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.business.write.manage.UserRoleWriteManage;
import com.people2000.user.business.write.manage.UserWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.constant.ErrorCode;
import com.people2000.user.model.dto.DefaultRoleDTO;
import com.people2000.user.model.dto.RoleUpdateDTO;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.po.ibatis.RoleExample;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.po.ibatis.UserRole;
import com.people2000.user.model.po.ibatis.UserRoleExample;
import com.people2000.user.model.po.ibatis.UserRoleExample.Criteria;
import com.people2000.user.model.vo.UserRoleVo;

/**
 * @author crc
 * @time 2015-5-17 下午1:26:32
 * @description <pre>
 * <p/>
 * </pre>
 */
@Service("userRoleWriteManage")
public class UserRoleWriteManageImpl implements UserRoleWriteManage {

	@Resource(name = "userRoleDAOWrite")
	private UserRoleDAOWrite2 userRoleDAOWrite;

	@Resource(name = "userWriteManage")
	private UserWriteManage userWriteManage;

	@Resource(name = "userCookieWriteManage")
	private UserCookieWriteManage userCookieWriteManage;

	@Resource(name = "functionDAOWrite")
	private FunctionDAOWrite2 functionDAOWrite;

	@Resource(name = "roleDAOWrite")
	private RoleDAOWrite roleDAOWrite;

	@Override
	public List<HashMap<String, Object>> getUserRole(Long id) throws Exception {
		return userRoleDAOWrite.getUserRole(id);
	}

	@Override
	public List<Role> getUserRoleByUserId(Long id) throws Exception {
		return userRoleDAOWrite.getUserRoleByUserId(id);
	}

	@Override
	public void UpdateUserRoleWithTx(Long userId, List<Long> roleIds)
			throws Exception {
		// 删除用户原有角色
		UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIsDeletedEqualTo(ConstantUser.isDelete.normal);
		UserRole record = new UserRole();
		record.setIsDeleted(ConstantUser.isDelete.delete);
		userRoleDAOWrite.updateByExampleSelective(record, example);
		if (roleIds != null && roleIds.size() > 0) {
			for (Long roleId : roleIds) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(userId);
				userRole.setIsDeleted(ConstantUser.isDelete.normal);
				userRoleDAOWrite.insert(userRole);
			}
		}
	}

	@Override
	public void deleteUserRoleWithTx(List<Long> ids) throws Exception {
		if (ids != null && ids.size() > 0) {
			for (Long id : ids) {
				UserRole record = new UserRole();
				record.setId(id);
				record.setIsDeleted(ConstantUser.isDelete.delete);
				// TODO 优化 批量删除
				userRoleDAOWrite.updateByPrimaryKeySelective(record);
			}
		}
	}

	@Override
	public void deleteUserRoleWithTx(Long roleId, Long userId) throws Exception {
		UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		criteria.andUserIdEqualTo(userId);
		criteria.andIsDeletedEqualTo(ConstantUser.isDelete.normal);
		UserRole record = new UserRole();
		record.setIsDeleted(ConstantUser.isDelete.delete);
		userRoleDAOWrite.updateByExampleSelective(record, example);
	}

	@Override
	public void addUserRoleWithTx(Long roleId, Long userId) throws Exception {
		// 判断用户是否有此角色
		UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		criteria.andUserIdEqualTo(userId);
		criteria.andIsDeletedEqualTo(ConstantUser.isDelete.normal);
		int count = userRoleDAOWrite.countByExample(example, false);
		if (count == 0) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRole.setIsDeleted(ConstantUser.isDelete.normal);
			userRoleDAOWrite.insert(userRole);
		}
	}

	@Override
	public List<Long> getUserRoleList(Long userId) throws Exception {
		List<Long> roleList = new ArrayList<Long>();
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<UserRole> userRoleList = userRoleDAOWrite.selectByExample(example,
				false);
		if (userRoleList != null && !userRoleList.isEmpty()) {
			for (UserRole userRole : userRoleList) {
				roleList.add(userRole.getRoleId());
			}
		}
		return roleList;
	}

	@Override
	public List<Long> getUserRoleList(Long userId, Long platformId,
			Long companyId) throws Exception {
		List<Long> roleList = new ArrayList<Long>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("platformId", platformId);
		map.put("companyId", companyId);
		roleList = userRoleDAOWrite.getUserRoleListByPlatformId(map);
		return roleList;
	}

	@Override
	public List<UserRole> getUserRoleListByUserIdAndRoleId(Long userId,
			Long roleId, Integer isAvailable, Long companyId) throws Exception {
		UserRoleExample ur = new UserRoleExample();
		UserRoleExample.Criteria criteria = ur.createCriteria();
		if (userId != null) {
			criteria.andUserIdEqualTo(userId);
		}
		if (roleId != null) {
			criteria.andRoleIdEqualTo(roleId);
		}
		if (isAvailable != null) {
			criteria.andIsAvailableEqualTo(isAvailable);
		}
		if (companyId != null) {
			criteria.andCompanyIdEqualTo(companyId);
		}

		return userRoleDAOWrite.selectByExample(ur, false);
	}

	@Override
	public void updateUserRoleRelationWithTx(Long userId, Long roleId,
			Integer isAvailable, Long companyId) throws Exception {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId)
				.andUserIdEqualTo(userId).andCompanyIdEqualTo(companyId);
		int count = userRoleDAOWrite.countByExample(example, false);
		if (count > 0) {
			// 如果有此条数据，根据前台产的status去更新用户角色关系
			UserRole record = new UserRole();
			if (isAvailable != null)
				record.setIsAvailable(isAvailable);
			userRoleDAOWrite.updateByExampleSelective(record, example);
		} else {
			UserRole record = new UserRole();
			record.setUserId(userId);
			record.setRoleId(roleId);
			record.setIsAvailable(isAvailable);
			record.setCompanyId(companyId);
			userRoleDAOWrite.insert(record);
		}
	}

	/**
	 * 给商家用户加载角色
	 *
	 * @param
	 * @throws SQLException
	 */
	@Override
	public void getUserRoleMerchantWithTx(Long userId, String ut)
			throws Exception {
		// 更新缓存中用户权限
		Set<String> set = new HashSet<String>();
		String functionCodes = ",";
		List<HashMap<String, String>> list = functionDAOWrite
				.getAllFunctionMerchantByUserId(userId);
		for (HashMap<String, String> hashMap : list) {
			String path = hashMap.get("path");
			String code = hashMap.get("code");
			if (path != null && !"".equals(path)) {
				set.add(path);
			}
			if (code != null && !"".equals(code)) {
				functionCodes = functionCodes + code + ",";
			}
		}
		UserCache userCache = SsoClientUtil.getUser(ut);
		if (userCache == null) {
			// 从表中取用户
			try {
				userCache = userCookieWriteManage
						.getUserByLoginCookieWithTx(ut);
			} catch (Exception e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
			}
		}
		// 刚注册的用户没有登录，userCache是null,会空指针
		if (null != userCache) {
			userCache.setFunctionCodes(functionCodes);
			userCache.setFunctionPahts(set);
			SsoClientUtil.login(userCache, ut);
		}
	}

	/**
	 * 给技师用户加载角色
	 *
	 * @param
	 * @throws SQLException
	 */
	@Override
	public void getUserRoleServiceWithTx(Long userId, String ut)
			throws Exception {
		// 更新缓存中用户权限
		Set<String> set = new HashSet<String>();
		String functionCodes = ",";
		List<HashMap<String, String>> list = functionDAOWrite
				.getAllFunctionServiceByUserId(userId);
		for (HashMap<String, String> hashMap : list) {
			String path = hashMap.get("path");
			String code = hashMap.get("code");
			if (path != null && !"".equals(path)) {
				set.add(path);
			}
			if (code != null && !"".equals(code)) {
				functionCodes = functionCodes + code + ",";
			}
		}
		UserCache userCache = SsoClientUtil.getUser(ut);
		if (userCache == null) {
			// 从表中取用户
			try {
				userCache = userCookieWriteManage
						.getUserByLoginCookieWithTx(ut);
			} catch (Exception e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
			}
		}
		// 刚注册的用户没有登录，userCache是null,会空指针
		if (null != userCache) {
			userCache.setFunctionCodes(functionCodes);
			userCache.setFunctionPahts(set);
			SsoClientUtil.login(userCache, ut);
		}
	}

	/**
	 * 更新用户角色
	 *
	 * @param commonInputDTO
	 */
	@Override
	public void updateRolesWithTx(RoleUpdateDTO roleUpdateDTO)
			throws OuserMangeException {
		final Long userId = roleUpdateDTO.getUserId();
		if (userId == null) {
			throw new OuserMangeException(ErrorCode.input_null, "input_null");
		}
		final List<Long> delRoles = roleUpdateDTO.getDelRoles();
		if (!CollectionUtils.isEmpty(delRoles)) {
			final UserRoleExample userRoleExample = new UserRoleExample();
			final Criteria criteria = userRoleExample.createCriteria();
			criteria.andUserIdEqualTo(userId);
			criteria.andRoleIdIn(delRoles);
			final UserRole userRole = new UserRole();
			userRole.setIsDeleted(1);
			try {
				userRoleDAOWrite.updateByExampleSelective(userRole,
						userRoleExample);
			} catch (SQLException e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
				throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
			}

		}
		final List<Long> addRoles = roleUpdateDTO.getAddRoles();
		if (!CollectionUtils.isEmpty(addRoles)) {
			final UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			for (int i = 0; i < addRoles.size(); i++) {
				userRole.setRoleId(addRoles.get(i));
				try {
					userRoleDAOWrite.insert(userRole);
				} catch (SQLException e) {
					LogUtils.getLogger(getClass()).error(e.getMessage(), e);
					;
					throw new OuserMangeException(ErrorCode.sql_error,
							"sql_errr");
				}

			}
		}
	}

	@Override
	public List<Role> getUserRoles(Map params) throws Exception {
		return userRoleDAOWrite.getUserRoleByParams(params);
	}

	@Override
	public Integer addRolesToUserWithTx(List<UserRole> listUserRole)
			throws Exception {
		return userRoleDAOWrite.addRolesToUser(listUserRole);
	}

	@Override
	public Integer updateOneUserRoleWithTx(UserRole userRole) throws Exception {
		return userRoleDAOWrite.updateByPrimaryKeySelective(userRole);
	}

	@Override
	public Integer batchDeleteUserRoleWithTx(UserRoleVo vo) throws Exception {
		return userRoleDAOWrite.batchDeleteUserRole(vo);
	}

}
