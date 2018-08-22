package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.dao.UOrganizationDAOWriteImpl;
import com.people2000.user.model.po.ibatis.OrganizationQuery;
import com.people2000.user.model.po.ibatis.UOrganization;
import com.people2000.user.model.po.ibatis.UPosition;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.vo.UserOrgVo;

@Repository("uOrganizationDAOWrite")
public class UOrganizationDAOWriteImpl2 extends UOrganizationDAOWriteImpl
		implements UOrganizationDAOWrite2 {

	@Resource(name = "sqlMapClientWrite")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	// @Resource(name = "cache")
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	public int selectUserCntByParam(OrganizationQuery query)
			throws SQLException {
		int cnt = (Integer) sqlMapClient.queryForObject(
				"u_organization2.selectUserCntByParam", query);
		return cnt;
	}

	public List<User> selectUserPageByParam(OrganizationQuery query)
			throws SQLException {
		List<User> listUser = sqlMapClient.queryForList(
				"u_organization2.selectUserPageByParam", query);
		return listUser;
	}

	@Override
	public int deleteOrgUser(OrganizationQuery query) throws Exception {
		// TODO Auto-generated method stub
		int cnt = sqlMapClient.update("u_organization2.deleteOrgUser", query);
		return cnt;
	}

	@Override
	public int selectUserCntByOrgInfo(OrganizationQuery query)
			throws SQLException {
		// TODO Auto-generated method stub
		int cnt = (Integer) sqlMapClient.queryForObject(
				"u_organization2.selectUserCntByOrgInfo", query);
		return cnt;
	}

	@Override
	public List<UserOrgVo> selectUserPageByOrgInfo(OrganizationQuery query)
			throws SQLException {
		List<UserOrgVo> listUser = sqlMapClient.queryForList(
				"u_organization2.selectUserPageByOrgInfo", query);
		return listUser;
	}

	@Override
	public List<UOrganization> getAllOrgInfo(Map<String, Object> map)
			throws SQLException {
		List<UOrganization> list = sqlMapClient.queryForList(
				"u_organization2.getAllOrgInfo", map);
		return list;
	}

	@Override
	public List<UPosition> getAllPostitionInfo(Map<String, Object> map)
			throws SQLException {
		List<UPosition> list = sqlMapClient.queryForList(
				"u_organization2.getAllPostitionInfo", map);
		return list;
	}

	@Override
	public int userOrgChange(OrganizationQuery query) throws SQLException {
		// TODO Auto-generated method stub
		int cnt = sqlMapClient.update("u_organization2.userOrgChange", query);
		return cnt;
	}

	@Override
	public Long updateOrgParentCode(UOrganization org) throws SQLException {
		// TODO Auto-generated method stub
		int cnt = sqlMapClient.update("u_organization2.updateOrgParentCode",
				org);
		return (long) cnt;
	}

}
