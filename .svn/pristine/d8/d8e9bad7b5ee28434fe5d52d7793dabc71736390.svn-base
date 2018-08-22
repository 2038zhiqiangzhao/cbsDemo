package com.people2000.user.business.write.dao.ext;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.odianyun.cache.CacheProxy;

import com.people2000.user.business.write.dao.AccountRecordDAOWriteImpl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("accountRecordDAOWrite")
public class AccountRecordDAOWriteImpl2 extends AccountRecordDAOWriteImpl {

	@Resource(name = "sqlMapClientWrite")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/*
	 * @Resource(name = "cache") public void setCache(CacheProxy cache) {
	 * this.cache = cache; }
	 */

}
