/**
 *
 */
package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Service;

import com.people2000.user.business.utils.PageUtils;
import com.people2000.user.business.utils.UserUtils;
import com.people2000.user.business.write.dao.RoleDAOWrite;
import com.people2000.user.business.write.dao.ext.RoleFunctionDAOWrite2;
import com.people2000.user.business.write.manage.RoleWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.RolePgDto;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.po.ibatis.RoleExample;
import com.people2000.user.model.po.ibatis.RoleExample.Criteria;
import com.people2000.user.model.po.ibatis.RoleFunction;
import com.people2000.user.model.vo.RoleVo;

/**
 * @author crc
 * @time 2015-4-29 下午4:48:31
 * @description <pre>
 * <p/>
 * </pre>
 */
@Service("roleWriteManage")
public class RoleWriteManageImpl implements RoleWriteManage {

	@Resource(name = "roleDAOWrite")
	private RoleDAOWrite roleDAOWrite;

	@Resource(name = "roleFunctionDAOWrite")
	private RoleFunctionDAOWrite2 roleFunctionDAOWrite;

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllChildRole() throws Exception {
		RoleExample example = new RoleExample();
		example.createCriteria().andTypeEqualTo(ConstantUser.roel_type.child);
		return roleDAOWrite.selectByExample(example, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getListRoleData(Role role) throws Exception {
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		Long id = role.getId();
		if (id != null) {
			criteria.andIdEqualTo(id);
		}
		String name = role.getName();
		if (name != null && !"".equals(name)) {
			criteria.andNameLike(name + "%");
		}
		Long type = role.getType();
		if (type != null) {
			criteria.andTypeEqualTo(type);
		}
		Long platformId = role.getPlatformId();
		if (platformId != null) {
			criteria.andPlatformIdEqualTo(platformId);
		}
		Long companyId = role.getCompanyId();
		if (companyId != null) {
			criteria.andCompanyIdEqualTo(companyId);
		}
		return roleDAOWrite.selectByExample(example, false);
	}

	@Override
	public void saveRoleWithTx(RoleVo role) throws Exception {
		roleDAOWrite.insert(role);
		List<Long> functionIds = role.getFunctionIds();
		if (functionIds != null && functionIds.size() > 0) {
			List<RoleFunction> list = new ArrayList<RoleFunction>();
			for (Long functionId : functionIds) {
				RoleFunction roleFunction = new RoleFunction();
				roleFunction.setRoleId(role.getId());
				roleFunction.setFunctionId(functionId);
				roleFunction.setIsDeleted(ConstantUser.isDelete.normal);
				list.add(roleFunction);
			}
			roleFunctionDAOWrite.insertBatch(list);
		}
	}

	@Override
	public void addFunctionsToRoleWithTx(RoleVo role) throws Exception {
		if (null != role.getId() && null != role.getFunctionIds()) {
			List<Long> functionIds = role.getFunctionIds();
			List<RoleFunction> list = new ArrayList<RoleFunction>();
			for (Long functionId : functionIds) {
				RoleFunction roleFunction = new RoleFunction();
				roleFunction.setFunctionId(functionId);
				roleFunction.setRoleId(role.getId());
				roleFunction.setIsDeleted(ConstantUser.isDelete.normal);
				list.add(roleFunction);
			}
			roleFunctionDAOWrite.insertBatch(list);
		}
	}

	@Override
	public void deleteRoleWithTx(Role role) throws Exception {
		role.setIsDeleted(ConstantUser.isDelete.delete);
		roleDAOWrite.updateByPrimaryKeySelective(role);
	}

	@Override
	public Role getRoleById(Long id) throws Exception {
		return roleDAOWrite.selectByPrimaryKey(id, false);
	}

	@Override
	public Map<String, Object> getRoleByExamplePG(RolePgDto role)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		RoleExample example = setCriteria4QueryPg(role);
		int total = roleDAOWrite.countByExample(example, false);
		List<Role> list = new ArrayList<Role>();
		if (total != 0) {
			// 分页
			Integer currentPage = role.getCurrentPage();
			Integer itemsPerPage = role.getItemsPerPage();
			if (currentPage != null && itemsPerPage != null) {
				example.setOrderByClause(" create_time desc limit "
						+ PageUtils.getStartItem(currentPage, itemsPerPage)
						+ "," + itemsPerPage);
			}
			list = roleDAOWrite.selectByExample(example, false);
		}
		map.put("total", total);
		map.put("list", list);
		return map;
	}

	private RoleExample setCriteria4QueryPg(RolePgDto role) throws Exception {
		RoleExample example = new RoleExample();
		RoleExample.Criteria criteria = example.createCriteria();

		if (null != role.getId()) {
			criteria.andIdEqualTo(role.getId());
		}
		String roleName = role.getName();
		if (roleName != null && !roleName.trim().equals("")) {
			criteria.andNameLike("%" + roleName + "%");
		}
		Long companyId = role.getCompanyId();
		if (companyId != null) {
			criteria.andCompanyIdEqualTo(companyId);
		}
		Integer isAvailable = role.getIsAvailable();
		if (isAvailable != null) {
			criteria.andIsAvailableEqualTo(isAvailable);
		}
		Long platformId = role.getPlatformId();
		if (platformId != null) {
			criteria.andPlatformIdEqualTo(platformId);
		}
		String code = role.getCode();
		if (code != null && !code.trim().equals("")) {
			criteria.andCodeLike("%" + code + "%");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begin = role.getBegin();
		if (!StringUtil.isBlank(begin)) {
			criteria.andCreateTimeGreaterThan(format.parse(begin));
		}
		String end = role.getEnd();
		if (!StringUtil.isBlank(end)) {
			criteria.andCreateTimeLessThan(format.parse(end));
		}

		if (null != role.getEntityId()) {
			criteria.andEntityIdEqualTo(role.getEntityId());
		}

		if (null != role.getEntityType()) {
			criteria.andEntityTypeEqualTo(role.getEntityType());
		}

		if (role.isPgFlg() != null && role.isPgFlg()) {
			example.setOrderByClause("id  limit "
					+ PageUtils.getStartItem(role.getCurrentPage(),
							role.getItemsPerPage()) + ","
					+ role.getItemsPerPage());
		}

		return example;
	}

	@Override
	public void updateRoleWithTx(Role role) throws Exception {
		Role record = new Role();
		record.setId(role.getId());
		if (StringUtils.isNotEmpty(role.getName())) {
			record.setName(role.getName());
		}
		if (StringUtils.isNotEmpty(role.getName())) {
			record.setCode(role.getCode());
		}
		if (StringUtils.isNotEmpty(role.getDesc())) {
			record.setDesc(role.getDesc());
		}
		if (null != role.getPlatformId()) {
			record.setPlatformId(role.getPlatformId());
		}
		if (null != role.getIsDeleted()) {// 删除操作
			record.setIsDeleted(role.getIsDeleted());
		}

		roleDAOWrite.updateByPrimaryKeySelective(record);
	}

	@Override
	public List queryCodeForAdd(String code) {
		try {
			return roleDAOWrite.queryCodeForAdd(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List queryCodeForUpdate(String code, Long id) {

		try {
			return roleDAOWrite.queryCodeForUpdate(code, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List queryRoleNameForAdd(String name) {
		try {
			return roleDAOWrite.queryNameForAdd(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
