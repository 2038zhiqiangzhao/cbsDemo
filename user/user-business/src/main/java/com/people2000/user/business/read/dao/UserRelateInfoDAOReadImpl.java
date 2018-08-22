package com.people2000.user.business.read.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.common.encrypt.MD5Support;
import com.people2000.user.model.po.ibatis.UserRelateInfoPO;
import com.people2000.user.model.po.ibatis.UserRelateInfoPOExample;

public class UserRelateInfoDAOReadImpl implements UserRelateInfoDAORead {
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	protected SqlMapClient sqlMapClient;

	protected CacheProxy cache;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	public List selectByExample(UserRelateInfoPOExample example,
			boolean fromCache) throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_SelectByExampleWithoutBLOBs_"
					+ MD5Support.MD5(example.allValue2String());
			List list_cache = (List) cache.get(key_cache);
			if (list_cache == null) {
				List list = sqlMapClient.queryForList(
						"u_user_relate_info.abatorgenerated_selectByExample",
						example);
				list_cache = list;
				if (list != null && list.size() > 0) {
					cache.put(key_cache, list_cache);
				}
			}
			return list_cache;
		} else {
			List list = sqlMapClient.queryForList(
					"u_user_relate_info.abatorgenerated_selectByExample",
					example);
			return list;
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	public UserRelateInfoPO selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_selectByPrimaryKey_" + id;
			UserRelateInfoPO record_cache = (UserRelateInfoPO) cache
					.get(key_cache);
			if (record_cache == null) {
				UserRelateInfoPO key = new UserRelateInfoPO();
				key.setId(id);
				UserRelateInfoPO record = (UserRelateInfoPO) sqlMapClient
						.queryForObject(
								"u_user_relate_info.abatorgenerated_selectByPrimaryKey",
								key);
				record_cache = record;
				if (record_cache != null) {
					cache.put(key_cache, record_cache);
				}
			}
			return record_cache;
		} else {
			UserRelateInfoPO key = new UserRelateInfoPO();
			key.setId(id);
			UserRelateInfoPO record = (UserRelateInfoPO) sqlMapClient
					.queryForObject(
							"u_user_relate_info.abatorgenerated_selectByPrimaryKey",
							key);
			return record;
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	public UserRelateInfoPO selectOneByExample(UserRelateInfoPOExample example,
			boolean fromCache) throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_selectOneByExample_"
					+ MD5Support.MD5(example.allValue2String());
			UserRelateInfoPO record_cache = (UserRelateInfoPO) cache
					.get(key_cache);
			if (record_cache == null) {
				UserRelateInfoPO record = (UserRelateInfoPO) sqlMapClient
						.queryForObject(
								"u_user_relate_info.abatorgenerated_selectOneByExample",
								example);
				record_cache = record;
				if (record_cache != null) {
					cache.put(key_cache, record_cache);
				}
			}
			return record_cache;
		} else {
			UserRelateInfoPO record = (UserRelateInfoPO) sqlMapClient
					.queryForObject(
							"u_user_relate_info.abatorgenerated_selectOneByExample",
							example);
			return record;
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	public int countByExample(UserRelateInfoPOExample example, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_countByExample_"
					+ MD5Support.MD5(example.allValue2String());
			Integer count_cache = (Integer) cache.get(key_cache);
			if (count_cache == null) {
				Integer count = (Integer) sqlMapClient.queryForObject(
						"u_user_relate_info.abatorgenerated_countByExample",
						example);
				count_cache = count;
				if (count > 0) {
					cache.put(key_cache, count_cache);
				}
			}
			return count_cache;
		} else {
			Integer count = (Integer) sqlMapClient.queryForObject(
					"u_user_relate_info.abatorgenerated_countByExample",
					example);
			return count.intValue();
		}
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to
	 * the database table u_user_relate_info
	 *
	 * @abatorgenerated Sun Aug 28 14:41:27 CST 2016
	 */
	protected static class UpdateByExampleParms extends UserRelateInfoPOExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				UserRelateInfoPOExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}