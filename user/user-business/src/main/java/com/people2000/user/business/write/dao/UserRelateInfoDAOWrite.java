package com.people2000.user.business.write.dao;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.po.ibatis.UserRelateInfoPO;
import com.people2000.user.model.po.ibatis.UserRelateInfoPOExample;

public interface UserRelateInfoDAOWrite {
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	Long insert(UserRelateInfoPO record) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	int updateByPrimaryKeySelective(UserRelateInfoPO record)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	List selectByExample(UserRelateInfoPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	UserRelateInfoPO selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	int countByExample(UserRelateInfoPOExample example, boolean fromCache)
			throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	int updateByExampleSelective(UserRelateInfoPO record,
			UserRelateInfoPOExample example) throws SQLException;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	UserRelateInfoPO selectOneByExample(UserRelateInfoPOExample example,
			boolean fromCache) throws SQLException;
}