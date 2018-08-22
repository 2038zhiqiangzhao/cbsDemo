package com.people2000.user.business.read.dao.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.read.dao.UPositionDAOReadImpl;

@Repository("positionDAORead")
public class PositionDAOReadImpl2 extends UPositionDAOReadImpl implements
		PositionDAORead2 {

	@Autowired
	public PositionDAOReadImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

}
