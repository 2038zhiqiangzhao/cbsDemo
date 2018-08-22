package com.people2000.user.business.read.dao.ext;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.read.dao.CaptchasDAOReadImpl;
import com.people2000.user.model.po.ibatis.CaptchasPOExample;

@Repository("captchasDAORead")
public class CaptchasDAOReadImpl2 extends CaptchasDAOReadImpl implements
		CaptchasDAORead2 {

	@Resource(name = "sqlMapClientRead")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	// @Resource(name = "cache")
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	public List selectByExample(CaptchasPOExample example) throws SQLException {
		return sqlMapClient.queryForList(
				"u_captchas2.abatorgenerated_selectByExample", example);
	}

}
