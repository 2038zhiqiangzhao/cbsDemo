package com.people2000.user.business.read.dao;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.po.ibatis.userMappingPO;
import com.people2000.user.model.po.ibatis.userMappingPOExample;

public interface userMappingDAORead {
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_mapping
	 *
	 * @abatorgenerated Wed Sep 14 19:55:38 CST 2016
	 */
	List selectByExampleWithoutBLOBs(userMappingPOExample example,
			boolean fromCache) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_mapping
	 *
	 * @abatorgenerated Wed Sep 14 19:55:38 CST 2016
	 */
	List selectByExampleWithBLOBs(userMappingPOExample example,
			boolean fromCache) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_mapping
	 *
	 * @abatorgenerated Wed Sep 14 19:55:38 CST 2016
	 */
	int countByExample(userMappingPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_mapping
	 *
	 * @abatorgenerated Wed Sep 14 19:55:38 CST 2016
	 */
	userMappingPO selectOneByExample(userMappingPOExample example,
			boolean fromCache) throws SQLException;
}