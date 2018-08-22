package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UPlatformGroupRelationDAOWriteImpl;
import com.people2000.user.model.po.ibatis.UPlatformGroupRelationPO;

@Repository("uPlatformGroupRelationDAOWrite")
public class UPlatformGroupRelationDAOWriteImpl2 extends
		UPlatformGroupRelationDAOWriteImpl implements
		UPlatformGroupRelationDAOWrite2 {

	@Autowired
	public UPlatformGroupRelationDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	@Override
	public int delete(UPlatformGroupRelationPO record) throws SQLException {
		return sqlMapClient
				.delete("u_platform_group_relation2.deleteUPlatformGroupRelationByGroup",
						record);
	}

}
