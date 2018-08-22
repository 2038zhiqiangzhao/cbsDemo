package com.people2000.user.business.write.dao;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.po.ibatis.UOrganizationPO;
import com.people2000.user.model.po.ibatis.UOrganizationPOExample;

public interface UOrganizationDAOWrite {
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_organization
	 *
	 * @abatorgenerated Mon Jun 27 14:20:32 CST 2016
	 */
	Long insert(UOrganizationPO record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_organization
	 *
	 * @abatorgenerated Mon Jun 27 14:20:32 CST 2016
	 */
	int updateByPrimaryKeySelective(UOrganizationPO record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_organization
	 *
	 * @abatorgenerated Mon Jun 27 14:20:32 CST 2016
	 */
	List selectByExample(UOrganizationPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_organization
	 *
	 * @abatorgenerated Mon Jun 27 14:20:32 CST 2016
	 */
	UOrganizationPO selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_organization
	 *
	 * @abatorgenerated Mon Jun 27 14:20:32 CST 2016
	 */
	int countByExample(UOrganizationPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_organization
	 *
	 * @abatorgenerated Mon Jun 27 14:20:32 CST 2016
	 */
	int updateByExampleSelective(UOrganizationPO record,
			UOrganizationPOExample example) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_organization
	 *
	 * @abatorgenerated Mon Jun 27 14:20:32 CST 2016
	 */
	UOrganizationPO selectOneByExample(UOrganizationPOExample example,
			boolean fromCache) throws SQLException;
}