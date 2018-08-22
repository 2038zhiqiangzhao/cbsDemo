package com.people2000.user.business.write.dao;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.po.ibatis.PushMessage;
import com.people2000.user.model.po.ibatis.PushMessageExample;

public interface PushMessageDAOWrite {
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_push_message
	 *
	 * @abatorgenerated Fri Mar 04 16:01:19 CST 2016
	 */
	Long insert(PushMessage record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_push_message
	 *
	 * @abatorgenerated Fri Mar 04 16:01:19 CST 2016
	 */
	int updateByPrimaryKeySelective(PushMessage record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_push_message
	 *
	 * @abatorgenerated Fri Mar 04 16:01:19 CST 2016
	 */
	List selectByExample(PushMessageExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_push_message
	 *
	 * @abatorgenerated Fri Mar 04 16:01:19 CST 2016
	 */
	PushMessage selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_push_message
	 *
	 * @abatorgenerated Fri Mar 04 16:01:19 CST 2016
	 */
	int countByExample(PushMessageExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_push_message
	 *
	 * @abatorgenerated Fri Mar 04 16:01:19 CST 2016
	 */
	int updateByExampleSelective(PushMessage record, PushMessageExample example)
			throws SQLException;
}