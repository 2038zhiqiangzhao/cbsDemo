package com.people2000.user.business.write.dao;

import com.people2000.user.model.po.ibatis.AccountRecordPO;
import com.people2000.user.model.po.ibatis.AccountRecordPOExample;
import java.sql.SQLException;
import java.util.List;

public interface AccountRecordDAOWrite {
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	Long insert(AccountRecordPO record) throws SQLException;

	List<Long> batchInsert(List<AccountRecordPO> recordList)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	int updateByPrimaryKeySelective(AccountRecordPO record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	List selectByExample(AccountRecordPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	List selectByExample(AccountRecordPOExample example, boolean fromCache,
			int expirMins) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	AccountRecordPO selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	AccountRecordPO selectByPrimaryKey(Long id, boolean fromCache, int expirMins)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	int countByExample(AccountRecordPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	int countByExample(AccountRecordPOExample example, boolean fromCache,
			int expirMins) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	int updateByExampleSelective(AccountRecordPO record,
			AccountRecordPOExample example) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	AccountRecordPO selectOneByExample(AccountRecordPOExample example,
			boolean fromCache) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table account_record
	 *
	 * @abatorgenerated Thu Aug 17 12:53:28 CST 2017
	 */
	AccountRecordPO selectOneByExample(AccountRecordPOExample example,
			boolean fromCache, int expirMins) throws SQLException;
}