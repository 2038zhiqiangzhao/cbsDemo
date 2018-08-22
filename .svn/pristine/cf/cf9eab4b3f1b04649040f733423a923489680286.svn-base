package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.business.write.dao.UserExtDAOWrite;
import com.people2000.user.model.dto.UserExtInDTO;
import com.people2000.user.model.po.ibatis.UserExtPO;

public interface UserExtDAOWrite2 extends UserExtDAOWrite {
	/**
	 * 批量更新
	 * 
	 * @param list
	 * @throws SQLException
	 */
	void batchUpdate(List<UserExtPO> list) throws SQLException;

	/**
	 * 查询用户扩展信息
	 * 
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	List<UserExtPO> selectUserExtList(UserExtInDTO dto) throws SQLException;
}
