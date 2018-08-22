package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.RoleFunctionDAOWriteImpl;
import com.people2000.user.model.po.ibatis.RoleFunction;

@Repository("roleFunctionDAOWrite")
public class RoleFunctionDAOWriteImpl2 extends RoleFunctionDAOWriteImpl
		implements RoleFunctionDAOWrite2 {

	@Autowired
	public RoleFunctionDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	@Override
	public int updateRoleFunction(Map params) throws SQLException {
		return sqlMapClient.update("u_role_function2.updateRoleFunction",
				params);
	}

	@Override
	public void insertBatch(List<RoleFunction> list) throws SQLException {
		sqlMapClient.insert("u_role_function2.insertBatch", list);
	}

}
