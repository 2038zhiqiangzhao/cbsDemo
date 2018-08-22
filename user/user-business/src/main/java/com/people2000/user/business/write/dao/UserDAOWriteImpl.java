package com.people2000.user.business.write.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.common.encrypt.MD5Support;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserExample;

public class UserDAOWriteImpl implements UserDAOWrite {
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	protected SqlMapClient sqlMapClient;

	protected CacheProxy cache;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	public UserDAOWriteImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	public Long insert(User record) throws SQLException {
		Object newKey = sqlMapClient.insert("u_user.abatorgenerated_insert",
				record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	public int updateByPrimaryKeySelective(User record) throws SQLException {
		int rows = sqlMapClient.update(
				"u_user.abatorgenerated_updateByPrimaryKeySelective", record);
		return rows;
	}
	
	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	public int updateOpenIdByPrimaryKey(User record) throws SQLException {
		int rows = sqlMapClient.update(
				"u_user.abatorgenerated_updateOpenIdByPrimaryKey", record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	public List selectByExample(UserExample example, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_SelectByExampleWithoutBLOBs_"
					+ MD5Support.MD5(example.allValue2String());
			List list_cache = (List) cache.get(key_cache);
			if (list_cache == null) {
				List list = sqlMapClient.queryForList(
						"u_user.abatorgenerated_selectByExample", example);
				list_cache = list;
				if (list != null && list.size() > 0) {
					cache.put(key_cache, list_cache);
				}
			}
			return list_cache;
		} else {
			List list = sqlMapClient.queryForList(
					"u_user.abatorgenerated_selectByExample", example);
			return list;
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	public User selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_selectByPrimaryKey_" + id;
			User record_cache = (User) cache.get(key_cache);
			if (record_cache == null) {
				User key = new User();
				key.setId(id);
				User record = (User) sqlMapClient.queryForObject(
						"u_user.abatorgenerated_selectByPrimaryKey", key);
				record_cache = record;
				if (record_cache != null) {
					cache.put(key_cache, record_cache);
				}
			}
			return record_cache;
		} else {
			User key = new User();
			key.setId(id);
			User record = (User) sqlMapClient.queryForObject(
					"u_user.abatorgenerated_selectByPrimaryKey", key);
			return record;
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	public int countByExample(UserExample example, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_countByExample_"
					+ MD5Support.MD5(example.allValue2String());
			Integer count_cache = (Integer) cache.get(key_cache);
			if (count_cache == null) {
				Integer count = (Integer) sqlMapClient.queryForObject(
						"u_user.abatorgenerated_countByExample", example);
				count_cache = count;
				if (count > 0) {
					cache.put(key_cache, count_cache);
				}
			}
			return count_cache;
		} else {
			Integer count = (Integer) sqlMapClient.queryForObject(
					"u_user.abatorgenerated_countByExample", example);
			return count.intValue();
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	public int updateByExampleSelective(User record, UserExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update(
				"u_user.abatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to
	 * the database table u_user
	 *
	 * @abatorgenerated Wed May 18 11:42:19 CST 2016
	 */
	protected static class UpdateByExampleParms extends UserExample {
		private Object record;

		public UpdateByExampleParms(Object record, UserExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}