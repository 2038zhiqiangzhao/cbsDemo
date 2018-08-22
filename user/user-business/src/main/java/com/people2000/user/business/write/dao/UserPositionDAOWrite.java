package com.people2000.user.business.write.dao;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.po.ibatis.UserPositionPO;
import com.people2000.user.model.po.ibatis.UserPositionPOExample;

public interface UserPositionDAOWrite {
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_position
	 *
	 * @abatorgenerated Fri Aug 26 10:19:28 CST 2016
	 */
	Long insert(UserPositionPO record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_position
	 *
	 * @abatorgenerated Fri Aug 26 10:19:28 CST 2016
	 */
	int updateByPrimaryKeySelective(UserPositionPO record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_position
	 *
	 * @abatorgenerated Fri Aug 26 10:19:28 CST 2016
	 */
	List selectByExample(UserPositionPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_position
	 *
	 * @abatorgenerated Fri Aug 26 10:19:28 CST 2016
	 */
	UserPositionPO selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_position
	 *
	 * @abatorgenerated Fri Aug 26 10:19:28 CST 2016
	 */
	int countByExample(UserPositionPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_position
	 *
	 * @abatorgenerated Fri Aug 26 10:19:28 CST 2016
	 */
	int updateByExampleSelective(UserPositionPO record,
			UserPositionPOExample example) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_position
	 *
	 * @abatorgenerated Fri Aug 26 10:19:28 CST 2016
	 */
	UserPositionPO selectOneByExample(UserPositionPOExample example,
			boolean fromCache) throws SQLException;
}