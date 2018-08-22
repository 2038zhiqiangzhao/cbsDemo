package com.people2000.user.business.read.dao;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.po.ibatis.UPlatformGroupRelationPO;
import com.people2000.user.model.po.ibatis.UPlatformGroupRelationPOExample;

public interface UPlatformGroupRelationDAORead {
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group_relation
	 *
	 * @abatorgenerated Tue Jul 05 14:20:15 CST 2016
	 */
	Long insert(UPlatformGroupRelationPO record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group_relation
	 *
	 * @abatorgenerated Tue Jul 05 14:20:15 CST 2016
	 */
	int updateByPrimaryKeySelective(UPlatformGroupRelationPO record)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group_relation
	 *
	 * @abatorgenerated Tue Jul 05 14:20:15 CST 2016
	 */
	List selectByExample(UPlatformGroupRelationPOExample example,
			boolean fromCache) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group_relation
	 *
	 * @abatorgenerated Tue Jul 05 14:20:15 CST 2016
	 */
	UPlatformGroupRelationPO selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group_relation
	 *
	 * @abatorgenerated Tue Jul 05 14:20:15 CST 2016
	 */
	int countByExample(UPlatformGroupRelationPOExample example,
			boolean fromCache) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group_relation
	 *
	 * @abatorgenerated Tue Jul 05 14:20:15 CST 2016
	 */
	int updateByExampleSelective(UPlatformGroupRelationPO record,
			UPlatformGroupRelationPOExample example) throws SQLException;
}