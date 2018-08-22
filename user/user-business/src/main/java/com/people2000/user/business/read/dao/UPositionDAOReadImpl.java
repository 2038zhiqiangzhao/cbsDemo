package com.people2000.user.business.read.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.common.encrypt.MD5Support;
import com.people2000.user.model.po.ibatis.UPositionPO;
import com.people2000.user.model.po.ibatis.UPositionPOExample;

public class UPositionDAOReadImpl implements UPositionDAORead {
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_position
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	protected SqlMapClient sqlMapClient;

	protected CacheProxy cache;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_position
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public UPositionDAOReadImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_position
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public Long insert(UPositionPO record) throws SQLException {
		Object newKey = sqlMapClient.insert(
				"u_position.abatorgenerated_insert", record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_position
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public int updateByPrimaryKeySelective(UPositionPO record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"u_position.abatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_position
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public List selectByExample(UPositionPOExample example, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_SelectByExampleWithoutBLOBs_"
					+ MD5Support.MD5(example.allValue2String());
			List list_cache = (List) cache.get(key_cache);
			if (list_cache == null) {
				List list = sqlMapClient.queryForList(
						"u_position.abatorgenerated_selectByExample", example);
				list_cache = list;
				if (list != null && list.size() > 0) {
					cache.put(key_cache, list_cache);
				}
			}
			return list_cache;
		} else {
			List list = sqlMapClient.queryForList(
					"u_position.abatorgenerated_selectByExample", example);
			return list;
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_position
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public UPositionPO selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_selectByPrimaryKey_" + id;
			UPositionPO record_cache = (UPositionPO) cache.get(key_cache);
			if (record_cache == null) {
				UPositionPO key = new UPositionPO();
				key.setId(id);
				UPositionPO record = (UPositionPO) sqlMapClient.queryForObject(
						"u_position.abatorgenerated_selectByPrimaryKey", key);
				record_cache = record;
				if (record_cache != null) {
					cache.put(key_cache, record_cache);
				}
			}
			return record_cache;
		} else {
			UPositionPO key = new UPositionPO();
			key.setId(id);
			UPositionPO record = (UPositionPO) sqlMapClient.queryForObject(
					"u_position.abatorgenerated_selectByPrimaryKey", key);
			return record;
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_position
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public int countByExample(UPositionPOExample example, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_countByExample_"
					+ MD5Support.MD5(example.allValue2String());
			Integer count_cache = (Integer) cache.get(key_cache);
			if (count_cache == null) {
				Integer count = (Integer) sqlMapClient.queryForObject(
						"u_position.abatorgenerated_countByExample", example);
				count_cache = count;
				if (count > 0) {
					cache.put(key_cache, count_cache);
				}
			}
			return count_cache;
		} else {
			Integer count = (Integer) sqlMapClient.queryForObject(
					"u_position.abatorgenerated_countByExample", example);
			return count.intValue();
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_position
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	public int updateByExampleSelective(UPositionPO record,
			UPositionPOExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update(
				"u_position.abatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to
	 * the database table u_position
	 *
	 * @abatorgenerated Tue Aug 23 13:33:33 CST 2016
	 */
	protected static class UpdateByExampleParms extends UPositionPOExample {
		private Object record;

		public UpdateByExampleParms(Object record, UPositionPOExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}