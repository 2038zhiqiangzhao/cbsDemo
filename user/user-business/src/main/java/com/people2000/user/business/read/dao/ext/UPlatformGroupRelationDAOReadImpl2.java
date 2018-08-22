package com.people2000.user.business.read.dao.ext;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.read.dao.UPlatformGroupRelationDAOReadImpl;
import com.people2000.user.model.dto.PlatformGroupRelationDTO;

@Repository("uPlatformGroupRelationDAORead")
public class UPlatformGroupRelationDAOReadImpl2 extends
		UPlatformGroupRelationDAOReadImpl implements
		UPlatformGroupRelationDAORead2 {

	@Resource(name = "sqlMapClientRead")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	// @Resource(name = "cache")
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	public List<PlatformGroupRelationDTO> queryPlatformGroupList(
			PlatformGroupRelationDTO dto) throws SQLException {
		return sqlMapClient.queryForList(
				"u_platform_group_relation2.selectPlatformGroupList", dto);
	}

	@Override
	public List<PlatformGroupRelationDTO> queryPlatformGroupListOnly(
			PlatformGroupRelationDTO dto) throws SQLException {
		return sqlMapClient.queryForList(
				"u_platform_group_relation2.selectPlatformGroupListOnly", dto);
	}

}
