package com.people2000.user.business.write.dao.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UserCheckTempleteDAOWriteImpl;

@Repository("userCheckTempleteDAOWrite")
public class UserCheckTempleteDAOWriteImpl2 extends
		UserCheckTempleteDAOWriteImpl implements UserCheckTempleteDAOWrite2 {

	@Autowired
	public UserCheckTempleteDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

}