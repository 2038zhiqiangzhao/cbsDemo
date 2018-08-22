package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.CaptchasDAOWriteImpl;

@Repository("captchasDAOWrite")
public class CaptchasDAOWriteImpl2 extends CaptchasDAOWriteImpl implements
		CaptchasDAOWrite2 {

	@Autowired
	public CaptchasDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	public int selectCount(Map<String, Object> map) throws SQLException {
		return (Integer) sqlMapClient.queryForObject(
				"u_captchas2.selectCountByMobileAndIp", map);
	}
}
