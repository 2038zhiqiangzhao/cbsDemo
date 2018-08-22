package com.people2000.user.business.write.dao.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UUnionLoginDAOWriteImpl;

@Repository("uUnionLoginDAOWrite")
public class UUnionLoginDAOWriteImpl2 extends UUnionLoginDAOWriteImpl implements
		UUnionLoginDAOWrite2 {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_login
	 *
	 * @param sqlMapClientWrite
	 * @abatorgenerated Thu Jul 28 15:56:10 CST 2016
	 */
	@Autowired
	public UUnionLoginDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}
}