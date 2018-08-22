package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UserExtDAOWriteImpl;
import com.people2000.user.model.dto.UserExtInDTO;
import com.people2000.user.model.po.ibatis.UserExtPO;

@Repository("userExtDAOWrite")
public class UserExtDAOWriteImpl2 extends UserExtDAOWriteImpl implements
		UserExtDAOWrite2 {

	@Resource(name = "sqlMapClientWrite")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	// @Resource(name = "cache")
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	@Override
	public void batchUpdate(List<UserExtPO> list) throws SQLException {
		sqlMapClient.startBatch();
		for (UserExtPO record : list) {
			sqlMapClient.update(
					"u_user_ext2.abatorgenerated_updateByPrimaryKeySelective",
					record);
		}
		sqlMapClient.executeBatch();
	}

	@Override
	public List<UserExtPO> selectUserExtList(UserExtInDTO dto)
			throws SQLException {
		return sqlMapClient.queryForList("u_user_ext2.selectUserExtList", dto);
	}
}
