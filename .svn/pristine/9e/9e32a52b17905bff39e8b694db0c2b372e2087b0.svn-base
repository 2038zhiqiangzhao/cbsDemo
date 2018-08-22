package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UOrganizationUserDAOWriteImpl;
import com.people2000.user.model.po.ibatis.UOrganizationUserPO;

@Repository("uOrganizationUserDAOWrite")
public class UOrganizationUserDAOWriteImpl2 extends
		UOrganizationUserDAOWriteImpl implements UOrganizationUserDAOWrite2 {

	@Resource(name = "sqlMapClientWrite")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	// @Resource(name = "cache")
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	@Override
	public void addOrgUserBatch(List<UOrganizationUserPO> listPo)
			throws SQLException {
		// TODO Auto-generated method stub
		this.sqlMapClient.startBatch();
		for (UOrganizationUserPO po : listPo) {
			insert(po);
		}
		this.sqlMapClient.executeBatch();
	}

}
