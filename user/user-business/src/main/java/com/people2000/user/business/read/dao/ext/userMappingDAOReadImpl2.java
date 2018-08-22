package com.people2000.user.business.read.dao.ext;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.read.dao.userMappingDAOReadImpl;

@Repository("userMappingDAORead")
public class userMappingDAOReadImpl2 extends userMappingDAOReadImpl {

	@Resource(name = "sqlMapClientRead")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	// @Resource(name = "cache")
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

}
