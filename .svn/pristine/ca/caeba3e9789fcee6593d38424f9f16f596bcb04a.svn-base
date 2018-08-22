package com.people2000.user.business.write.dao.ext;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.people2000.user.business.write.dao.UserAccountDAOWriteImpl;

@Repository("userAccountDAOWrite")
public class UserAccountDAOWriteImpl2 extends UserAccountDAOWriteImpl {

	@Resource(name = "sqlMapClientWrite")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/*
	 * @Resource(name = "cache") public void setCache(CacheProxy cache) {
	 * this.cache = cache; }
	 */

}
