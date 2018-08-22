package com.people2000.user.business.write.dao.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UserAddressDAOWriteImpl;

@Repository("userAddressDAOWrite")
public class UserAddressDAOWriteImpl2 extends UserAddressDAOWriteImpl {

	@Autowired
	public UserAddressDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

}
