package com.people2000.user.business.write.dao;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.po.ibatis.UPlatformGroupPO;
import com.people2000.user.model.po.ibatis.UPlatformGroupPOExample;

public interface UPlatformGroupDAOWrite {
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group
	 *
	 * @abatorgenerated Mon May 23 14:20:46 CST 2016
	 */
	Long insert(UPlatformGroupPO record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group
	 *
	 * @abatorgenerated Mon May 23 14:20:46 CST 2016
	 */
	int updateByPrimaryKeySelective(UPlatformGroupPO record)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group
	 *
	 * @abatorgenerated Mon May 23 14:20:46 CST 2016
	 */
	List selectByExample(UPlatformGroupPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group
	 *
	 * @abatorgenerated Mon May 23 14:20:46 CST 2016
	 */
	UPlatformGroupPO selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group
	 *
	 * @abatorgenerated Mon May 23 14:20:46 CST 2016
	 */
	int countByExample(UPlatformGroupPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group
	 *
	 * @abatorgenerated Mon May 23 14:20:46 CST 2016
	 */
	int updateByExampleSelective(UPlatformGroupPO record,
			UPlatformGroupPOExample example) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_platform_group
	 *
	 * @abatorgenerated Mon May 23 14:20:46 CST 2016
	 */
	UPlatformGroupPO selectOneByExample(UPlatformGroupPOExample example,
			boolean fromCache) throws SQLException;
}