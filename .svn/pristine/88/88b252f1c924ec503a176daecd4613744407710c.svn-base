package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.people2000.user.business.write.dao.FunctionDAOWrite;
import com.people2000.user.model.dto.FunctionTreeDTO;
import com.people2000.user.model.po.ibatis.Function;

public interface FunctionDAOWrite2 extends FunctionDAOWrite {

	List<HashMap<String, String>> getAllFunctionByUserId(Long userId)
			throws SQLException;

	List<HashMap<String, String>> getFunctionMapByUserIdAndPlatformId(Map map)
			throws SQLException;

	List<HashMap<String, String>> getAllFunctionMerchantByUserId(Long userId)
			throws SQLException;

	List<HashMap<String, String>> getAllFunctionServiceByUserId(Long userId)
			throws SQLException;

	/**
	 * 根据code查询全部菜单节点
	 * 
	 * @param codes
	 * @return
	 * @throws SQLException
	 */
	List<FunctionTreeDTO> getAllMenuFunctionByParentCode(
			List<FunctionTreeDTO> codes) throws SQLException;

	/**
	 * 根据平台查询全部菜单节点
	 * 
	 * @param codes
	 * @return
	 * @throws SQLException
	 */
	List<FunctionTreeDTO> getAllMenuFunctionByPlatform(
			Map<String, Object> params) throws SQLException;

	List<Function> getAllFunctionByUserIdAndPlatformId(Map map)
			throws SQLException;

	List<Function> getUserFunction(Map map) throws SQLException;
}