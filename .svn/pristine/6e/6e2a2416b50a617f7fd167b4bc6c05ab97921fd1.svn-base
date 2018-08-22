package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.FunctionDAOWriteImpl;
import com.people2000.user.model.dto.FunctionTreeDTO;
import com.people2000.user.model.po.ibatis.Function;

@Repository("functionDAOWrite")
public class FunctionDAOWriteImpl2 extends FunctionDAOWriteImpl implements
		FunctionDAOWrite2 {
	@Autowired
	public FunctionDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, String>> getAllFunctionByUserId(Long userId)
			throws SQLException {
		return sqlMapClient.queryForList("u_function2.getAllFunctionByUserId",
				userId);
	}

	@Override
	public List<HashMap<String, String>> getFunctionMapByUserIdAndPlatformId(
			Map map) throws SQLException {
		// TODO Auto-generated method stub
		return sqlMapClient.queryForList(
				"u_function2.getFunctionMapByUserIdAndPlatformId", map);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionTreeDTO> getAllMenuFunctionByParentCode(
			List<FunctionTreeDTO> codes) throws SQLException {
		return sqlMapClient.queryForList(
				"u_function2.getAllMenuFunctionByParentCode", codes);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, String>> getAllFunctionMerchantByUserId(
			Long userId) throws SQLException {
		return sqlMapClient.queryForList(
				"u_function2.getAllFunctionMerchantByUserId", userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, String>> getAllFunctionServiceByUserId(
			Long userId) throws SQLException {
		return sqlMapClient.queryForList(
				"u_function2.getAllFunctionServiceByUserId", userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> getAllFunctionByUserIdAndPlatformId(Map map)
			throws SQLException {
		return sqlMapClient.queryForList(
				"u_function2.getAllFunctionByUserIdAndPlatformId", map);
	}

	@Override
	public List<Function> getUserFunction(Map map) throws SQLException {
		return sqlMapClient.queryForList("u_function2.getAllFunction", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionTreeDTO> getAllMenuFunctionByPlatform(
			Map<String, Object> params) throws SQLException {
		return sqlMapClient.queryForList(
				"u_function2.getAllMenuFunctionByPlatform", params);
	}

}