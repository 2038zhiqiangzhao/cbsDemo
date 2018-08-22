package com.people2000.user.business.write.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.common.encrypt.MD5Support;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.po.ibatis.RoleExample;

public class RoleDAOWriteImpl implements RoleDAOWrite {
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_role
	 *
	 * @abatorgenerated Mon Nov 16 15:23:06 CST 2015
	 */
	protected SqlMapClient sqlMapClient;

	protected CacheProxy cache;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_role
	 *
	 * @abatorgenerated Mon Nov 16 15:23:06 CST 2015
	 */
	public RoleDAOWriteImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_role
	 *
	 * @abatorgenerated Mon Nov 16 15:23:06 CST 2015
	 */
	public Long insert(Role record) throws SQLException {
		Object newKey = sqlMapClient.insert("u_role.abatorgenerated_insert",
				record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_role
	 *
	 * @abatorgenerated Mon Nov 16 15:23:06 CST 2015
	 */
	public int updateByPrimaryKeySelective(Role record) throws SQLException {
		int rows = sqlMapClient.update(
				"u_role.abatorgenerated_updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_role
	 *
	 * @abatorgenerated Mon Nov 16 15:23:06 CST 2015
	 */
	public List selectByExample(RoleExample example, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_SelectByExampleWithoutBLOBs_"
					+ MD5Support.MD5(example.allValue2String());
			List list_cache = (List) cache.get(key_cache);
			if (list_cache == null) {
				List list = sqlMapClient.queryForList(
						"u_role.abatorgenerated_selectByExample", example);
				list_cache = list;
				if (list != null && list.size() > 0) {
					cache.put(key_cache, list_cache);
				}
			}
			return list_cache;
		} else {
			List list = sqlMapClient.queryForList(
					"u_role.abatorgenerated_selectByExample", example);
			return list;
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_role
	 *
	 * @abatorgenerated Mon Nov 16 15:23:06 CST 2015
	 */
	public Role selectByPrimaryKey(Long id, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_selectByPrimaryKey_" + id;
			Role record_cache = (Role) cache.get(key_cache);
			if (record_cache == null) {
				Role key = new Role();
				key.setId(id);
				Role record = (Role) sqlMapClient.queryForObject(
						"u_role.abatorgenerated_selectByPrimaryKey", key);
				record_cache = record;
				if (record_cache != null) {
					cache.put(key_cache, record_cache);
				}
			}
			return record_cache;
		} else {
			Role key = new Role();
			key.setId(id);
			Role record = (Role) sqlMapClient.queryForObject(
					"u_role.abatorgenerated_selectByPrimaryKey", key);
			return record;
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_role
	 *
	 * @abatorgenerated Mon Nov 16 15:23:06 CST 2015
	 */
	public int countByExample(RoleExample example, boolean fromCache)
			throws SQLException {
		if (fromCache) {
			String key_cache = "DaoKey_" + this.getClass().getName()
					+ "_countByExample_"
					+ MD5Support.MD5(example.allValue2String());
			Integer count_cache = (Integer) cache.get(key_cache);
			if (count_cache == null) {
				Integer count = (Integer) sqlMapClient.queryForObject(
						"u_role.abatorgenerated_countByExample", example);
				count_cache = count;
				if (count > 0) {
					cache.put(key_cache, count_cache);
				}
			}
			return count_cache;
		} else {
			Integer count = (Integer) sqlMapClient.queryForObject(
					"u_role.abatorgenerated_countByExample", example);
			return count.intValue();
		}
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_role
	 *
	 * @abatorgenerated Mon Nov 16 15:23:06 CST 2015
	 */
	public int updateByExampleSelective(Role record, RoleExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update(
				"u_role.abatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to
	 * the database table u_role
	 *
	 * @abatorgenerated Mon Nov 16 15:23:06 CST 2015
	 */
	protected static class UpdateByExampleParms extends RoleExample {
		private Object record;

		public UpdateByExampleParms(Object record, RoleExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
	public List queryCodeForAdd(String code) throws SQLException {
		List list = null;
		list = sqlMapClient.queryForList("u_role.queryCodeForAdd", code);
		return list;
	}

	@Override
	public List queryCodeForUpdate(String code, Long id) throws SQLException {
		List list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("id", id);
		list = sqlMapClient.queryForList("u_role.queryCodeForUpdate", map);
		return list;
	}

	@Override
	public List queryNameForAdd(String name) throws SQLException {
		List list = list = sqlMapClient.queryForList("u_role.queryNameForAdd",
				name);
		return list;
	}
}