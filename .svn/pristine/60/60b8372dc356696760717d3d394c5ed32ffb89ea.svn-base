package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.people2000.user.business.write.dao.UserGradeDAOWriteImpl;
import com.people2000.user.model.dto.UserGradeInputDTO;
import com.people2000.user.model.po.ibatis.UserGradePO;

@Repository("userGradeDAOWrite")
public class UserGradeDAOWriteImpl2 extends UserGradeDAOWriteImpl implements
		UserGradeDAOWrite2 {

	@Resource(name = "sqlMapClientWrite")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/*
	 * @Resource(name = "cache") public void setCache(CacheProxy cache) {
	 * this.cache = cache; }
	 */

	@Override
	public UserGradePO selectUserGradeByPrice(UserGradeInputDTO inpuDto)
			throws SQLException {
		return (UserGradePO) sqlMapClient.queryForObject(
				"user_grade2.selectUserGradeByPrice", inpuDto);
	}

}
