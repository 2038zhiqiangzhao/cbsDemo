package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.Map;

import com.people2000.user.business.write.dao.CaptchasDAOWrite;

public interface CaptchasDAOWrite2 extends CaptchasDAOWrite {

	/**
	 * 查询验证码数量
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int selectCount(Map<String, Object> map) throws SQLException;

}