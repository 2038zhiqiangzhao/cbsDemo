package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.people2000.user.business.write.dao.UserLoginDAOWrite;
import com.people2000.user.model.dto.UserInputDTO;

/**
 * Created with IntelliJ IDEA.
 *
 * @time: 2016/10/21 20:06
 * @author: Wang Pengfei
 * @description:
 * @version:
 */
public interface UserLoginDAOWrite2 extends UserLoginDAOWrite {
	/**
	 * 通过userId查询2次登录记录
	 * 
	 * @return
	 */
	List<Map<String, Object>> getLoginLimit2(UserInputDTO userInputDTO)
			throws SQLException;
}
