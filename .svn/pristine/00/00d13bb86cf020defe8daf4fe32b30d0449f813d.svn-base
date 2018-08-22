package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.dao.UserRoleDAOWriteImpl;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.po.ibatis.UserRole;
import com.people2000.user.model.vo.UserRoleVo;

@Repository("userRoleDAOWrite")
public class UserRoleDAOWriteImpl2 extends UserRoleDAOWriteImpl implements
		UserRoleDAOWrite2 {
	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	@Autowired
	public UserRoleDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	@Override
	public List<HashMap<String, Object>> getUserRole(Long id)
			throws SQLException {
		return sqlMapClient.queryForList(
				"u_user_role2.getUserRoleListByUserId", id);
	}

	@Override
	public List<Long> getUserRoleListByPlatformId(Map params)
			throws SQLException {
		return sqlMapClient.queryForList(
				"u_user_role2.getUserRoleListByPlatformId", params);
	}

	@Override
	public List<Role> getUserRoleByUserId(Long id) throws SQLException {
		return sqlMapClient
				.queryForList("u_user_role2.getUserRoleByUserId", id);
	}

	@Override
	public List<Role> getUserRoleByParams(Map params) throws SQLException {
		return sqlMapClient.queryForList("u_user_role2.getUserRoleByParams",
				params);
	}

	@Override
	public Integer addRolesToUser(List<UserRole> list) throws SQLException {
		return (Integer) sqlMapClient.insert("u_user_role2.addRolesToUser",
				list);
	}

	@Override
	public Integer batchDeleteUserRole(UserRoleVo vo) throws SQLException {
		return sqlMapClient.update("u_user_role2.batchDeleteUserRole", vo);
	}

}