package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.write.dao.UserLoginDAOWriteImpl;
import com.people2000.user.model.dto.UserInputDTO;

@Repository("userLoginDAOWrite")
public class UserLoginDAOWriteImpl2 extends UserLoginDAOWriteImpl implements
		UserLoginDAOWrite2 {

	@Autowired
	public UserLoginDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	@Override
	public List<Map<String, Object>> getLoginLimit2(UserInputDTO userInputDTO)
			throws SQLException {
		return sqlMapClient.queryForList("u_user_login2.getLoginLimit2",
				userInputDTO);
	}

}
