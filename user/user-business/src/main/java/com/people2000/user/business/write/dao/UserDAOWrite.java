package com.people2000.user.business.write.dao;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserExample;

public interface UserDAOWrite {
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	Long insert(User record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	int updateByPrimaryKeySelective(User record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	List selectByExample(UserExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	User selectByPrimaryKey(Long id, boolean fromCache) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	int countByExample(UserExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	int updateByExampleSelective(User record, UserExample example)
			throws SQLException;
}