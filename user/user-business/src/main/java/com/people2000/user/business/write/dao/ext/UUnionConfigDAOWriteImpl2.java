package com.people2000.user.business.write.dao.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UUnionConfigDAOWriteImpl;

@Repository("uUnionConfigDAOWrite")
public class UUnionConfigDAOWriteImpl2 extends UUnionConfigDAOWriteImpl
		implements UUnionConfigDAOWrite2 {

	@Autowired
	public UUnionConfigDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

}