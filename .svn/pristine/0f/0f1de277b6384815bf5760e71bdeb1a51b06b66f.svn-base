package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.dao.UserPositionDAOWrite;
import com.people2000.user.business.write.dao.ext.UOrganizationDAOWrite2;
import com.people2000.user.business.write.dao.ext.UOrganizationUserDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserRoleDAOWrite2;
import com.people2000.user.business.write.manage.OrganizationWriteManage;
import com.people2000.user.business.write.manage.UserAccountWriteManage;
import com.people2000.user.business.write.manage.UserWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.UserAccountDTO;
import com.people2000.user.model.dto.UserDto;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.OrganizationQuery;
import com.people2000.user.model.po.ibatis.UOrganization;
import com.people2000.user.model.po.ibatis.UOrganizationPO;
import com.people2000.user.model.po.ibatis.UOrganizationPOExample;
import com.people2000.user.model.po.ibatis.UOrganizationPOExample.Criteria;
import com.people2000.user.model.po.ibatis.UOrganizationUserPO;
import com.people2000.user.model.po.ibatis.UOrganizationUserPOExample;
import com.people2000.user.model.po.ibatis.UPosition;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserPositionPO;
import com.people2000.user.model.po.ibatis.UserRole;
import com.people2000.user.model.vo.UserOrgVo;

/**
 * 
 * @author xuc
 * @time 2016年6月28日 上午10:28:01
 * @description 组织机构service
 */

@Service("organizationWriteManage")
public class OrganizationWriteManageImpl implements OrganizationWriteManage {

	@Resource(name = "userWriteManage")
	private UserWriteManage userWriteManage;

	@Resource(name = "userRoleDAOWrite")
	private UserRoleDAOWrite2 userRoleDAOWrite;

	@Resource(name = "uOrganizationDAOWrite")
	private UOrganizationDAOWrite2 uOrganizationDAOWrite;

	@Resource(name = "uOrganizationUserDAOWrite")
	private UOrganizationUserDAOWrite2 uOrganizationUserDAOWrite;

	@Resource(name = "userPositionDAOWrite")
	private UserPositionDAOWrite userPositionDAOWrite;
	@Resource
	private UserAccountWriteManage userAccountWriteManage;

	private static final Logger logger = LogUtils
			.getLogger(OrganizationWriteManageImpl.class);

	@Override
	public List<UOrganizationPO> getOrgInfo(UOrganization org)
			throws SQLException {
		// TODO Auto-generated method stub
		UOrganizationPOExample example = new UOrganizationPOExample();
		Criteria criteria = example.createCriteria();
		if (null != org.getCompanyId()) {
			criteria.andCompanyIdEqualTo(org.getCompanyId());
		}
		if (StringUtils.isNotEmpty(org.getCode())) {
			criteria.andCodeEqualTo(org.getCode());
		}
		if (StringUtils.isNotEmpty(org.getParentCode())) {
			criteria.andParentCodeEqualTo(org.getParentCode());
		}
		if (StringUtils.isNotEmpty(org.getOrgName())) {
			criteria.andOrgNameEqualTo(org.getOrgName());
		}
		if (null != org.getId()) {
			criteria.andIdEqualTo(org.getId());
		}
		if (null != org.getPlatformId()) {
			criteria.andPlatformIdEqualTo(org.getPlatformId());
		}

		List<UOrganizationPO> list = uOrganizationDAOWrite.selectByExample(
				example, false);

		return list;
	}

	@Override
	public Long addOneOrgWithTx(UOrganization org) throws SQLException {
		// TODO Auto-generated method stub
		UOrganizationPO po = new UOrganizationPO();
		BeanUtils.copyProperties(org, po);
		Long l = uOrganizationDAOWrite.insert(po);
		return l;
	}

	@Override
	public int updOneOrgWithTx(UOrganization org) throws SQLException {
		// TODO Auto-generated method stub
		UOrganizationPO po = new UOrganizationPO();
		BeanUtils.copyProperties(org, po);
		int cnt = uOrganizationDAOWrite.updateByPrimaryKeySelective(po);
		return cnt;
	}

	@Override
	public Map<String, Object> queryOrgUser(OrganizationQuery query)
			throws Exception {
		return queryOrgUser(query, true);
	}

	@Override
	public Map<String, Object> queryOrgUser(OrganizationQuery query,
			Boolean flag) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int cnt = 0;
		List<User> listUser = new ArrayList<User>();
		if (true == flag) {// 查询在组织内的用户
			query.setFlag("1");

		} else {// 查询不在组织内的用户
			query.setFlag("0");
		}
		if (null != query.getCurrentPage() && null != query.getItemsPerPage()
				&& query.getCurrentPage() > 0 && query.getItemsPerPage() >= 1) {
			query.setStartItems((query.getCurrentPage() - 1)
					* query.getItemsPerPage());
		} else {
			logger.info("CurrentPage： " + query.getCurrentPage()
					+ " ItemsPerPage: " + query.getItemsPerPage());
			throw new Exception("查询分页参数错误!");
		}
		cnt = uOrganizationDAOWrite.selectUserCntByParam(query);
		listUser = uOrganizationDAOWrite.selectUserPageByParam(query);
		map.put("total", cnt);
		map.put("list", listUser);
		return map;
	}

	@Override
	public int deleteOrgUserWithTx(OrganizationQuery query) throws Exception {
		int cnt = uOrganizationDAOWrite.deleteOrgUser(query);
		return cnt;
	}

	@Override
	public void putOrgUserWithTx(OrganizationQuery query) throws Exception {
		// TODO Auto-generated method stub
		List<UOrganizationUserPO> listPo = new ArrayList<UOrganizationUserPO>();
		List<Long> ids = query.getUserIds();
		Long companyId = query.getCompanyId();
		Long orgId = query.getOrgId();
		if (CollectionUtils.isEmpty(ids)) {
			throw new Exception("删除组织人员,组织人员id为空");
		}
		for (Long l : ids) {
			UOrganizationUserPO po = new UOrganizationUserPO();
			po.setCompanyId(companyId);
			po.setUserId(l);
			po.setOrganizationId(orgId);
			po.setIsDeleted(0);
			listPo.add(po);
		}
		uOrganizationUserDAOWrite.addOrgUserBatch(listPo);
	}

	@Override
	public Map<String, Object> queryUserByOrgInfo(OrganizationQuery query)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		int cnt = 0;
		List<UserOrgVo> listUser = new ArrayList<UserOrgVo>();
		if (null != query.getCurrentPage() && null != query.getItemsPerPage()
				&& query.getCurrentPage() >= 0 && query.getItemsPerPage() >= 1) {
			if (0 == query.getCurrentPage()) {
				query.setStartItems(0);
			} else
				query.setStartItems((query.getCurrentPage() - 1)
						* query.getItemsPerPage());
		} else {
			logger.info("CurrentPage： " + query.getCurrentPage()
					+ " ItemsPerPage: " + query.getItemsPerPage());
			throw new Exception("查询分页参数错误!");
		}
		if (StringUtils.isNotEmpty(query.getUsername())) {
			query.setUsername(query.getUsername() + "%");
		}

		cnt = uOrganizationDAOWrite.selectUserCntByOrgInfo(query);
		listUser = uOrganizationDAOWrite.selectUserPageByOrgInfo(query);
		map.put("total", cnt);
		map.put("list", listUser);
		return map;
	}

	@Override
	public List<UOrganization> getAllOrgInfo(Map map) throws Exception {
		// TODO Auto-generated method stub
		List<UOrganization> list = uOrganizationDAOWrite.getAllOrgInfo(map);
		return list;
	}

	@Override
	public List<UPosition> getAllPostitionInfo(Map map) throws Exception {
		// TODO Auto-generated method stub
		List<UPosition> list = uOrganizationDAOWrite.getAllPostitionInfo(map);
		return list;
	}

	@Override
	public int userOrgChangeWithTx(OrganizationQuery query) throws SQLException {
		// TODO Auto-generated method stub
		List<Long> ids = query.getUserIds();
		Long companyId = query.getCompanyId();
		Long orgId = query.getOrgId();
		List<UOrganizationUserPO> listPo = new ArrayList<UOrganizationUserPO>();
		if (CollectionUtils.isNotEmpty(ids)) {
			uOrganizationDAOWrite.userOrgChange(query);// 批量删除

			for (Long l : ids) {
				UOrganizationUserPO po = new UOrganizationUserPO();
				po.setCompanyId(companyId);
				po.setUserId(l);
				po.setOrganizationId(orgId);
				po.setIsDeleted(0);
				listPo.add(po);
			}
			uOrganizationUserDAOWrite.addOrgUserBatch(listPo);
		}

		return 0;
	}

	/**
	 * 增加员工
	 */
	@Override
	public Long addYonghuWithTx(UserOrgVo userOrgVo) throws SQLException {
		UserDto userDto = new UserDto();
		userDto.setUsername(userOrgVo.getUsername());
		userDto.setPassword(userOrgVo.getPassword());
		userDto.setIdentityCardName(userOrgVo.getIdentityCardName());
		userDto.setEmail(userOrgVo.getEmail());
		userDto.setMobile(userOrgVo.getMobile());
		userDto.setType(ConstantUser.user_type.backend);// 员工应该不是4吧
		userDto.setCompanyId(userOrgVo.getCompanyId());
		userDto.setUserPlatformId(1l);
		Long l = userWriteManage.addOneUserAndReturnIdWithTx(userDto);
		if (l > 0) {
			if (null != userOrgVo.getOrgId()) {
				UOrganizationUserPO po = new UOrganizationUserPO();
				po.setUserId(l);
				po.setOrganizationId(userOrgVo.getOrgId());
				if (null != userOrgVo.getCompanyId()) {
					po.setCompanyId(userOrgVo.getCompanyId());
				}
				uOrganizationUserDAOWrite.insert(po);
			}
			if (null != userOrgVo.getRoleIds()) {
				List<Long> listIds = userOrgVo.getRoleIds();
				for (Long id : listIds) {// 懒得写批量了
					UserRole userRole = new UserRole();
					userRole.setUserId(l);
					userRole.setRoleId(id);
					if (null != userOrgVo.getCompanyId()) {
						userRole.setCompanyId(userOrgVo.getCompanyId());
					}
					userRoleDAOWrite.insert(userRole);
				}
			}

			if (null != userOrgVo.getPositionId()) {// 写部门表
				// userPositionDAOWrite
				UserPositionPO po = new UserPositionPO();
				po.setUserId(l);
				po.setPositionId(userOrgVo.getPositionId());
				if (null != userOrgVo.getCompanyId()) {
					po.setCompanyId(userOrgVo.getCompanyId());
				}
				userPositionDAOWrite.insert(po);
			}

			// 添加余额账户
			UserAccountDTO accountDTO = new UserAccountDTO();
			accountDTO.setUserId(l);
			try {
				userAccountWriteManage.createUserAccountWithTx(accountDTO);
			} catch (OuserMangeException e) {
				logger.error("添加员工余额账户异常", e);
			}

			return 0l;

		} else
			return l;

	}

	@Override
	public Long countOrgUser(OrganizationQuery query) throws SQLException {
		// TODO Auto-generated method stub
		UOrganizationUserPOExample example = new UOrganizationUserPOExample();
		UOrganizationUserPOExample.Criteria criteria = example.createCriteria();
		if (null != query.getCompanyId()) {
			criteria.andCompanyIdEqualTo(query.getCompanyId());
		}
		if (null != query.getOrgId()) {
			criteria.andOrganizationIdEqualTo(query.getOrgId());
		}
		int cnt = uOrganizationUserDAOWrite.countByExample(example, false);
		return (long) cnt;
	}

	@Override
	public Long updateOrgParentCodeAndDelWithTx(UOrganization org)
			throws SQLException {
		// TODO Auto-generated method stub
		uOrganizationDAOWrite.updateOrgParentCode(org);
		UOrganizationPO po = new UOrganizationPO();
		po.setId(org.getId());
		po.setIsDeleted(1);
		po.setCompanyId(org.getCompanyId());
		int cnt = uOrganizationDAOWrite.updateByPrimaryKeySelective(po);
		return (long) cnt;

	}

	@Override
	public Long updateOrgParentCodeWithTx(UOrganization org)
			throws SQLException {
		// TODO Auto-generated method stub
		Long l = uOrganizationDAOWrite.updateOrgParentCode(org);
		return l;
	}

}
