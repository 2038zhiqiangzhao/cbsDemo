package com.people2000.user.business.write.dao.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UPlatformGroupDAOWriteImpl;

@Repository("uPlatformGroupDAOWrite")
public class UPlatformGroupDAOWriteImpl2 extends UPlatformGroupDAOWriteImpl {

	@Autowired
	public UPlatformGroupDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

}
