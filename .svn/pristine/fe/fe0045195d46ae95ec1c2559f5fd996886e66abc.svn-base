package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.people2000.user.business.write.dao.UserDAOWrite;
import com.people2000.user.model.dto.User2;
import com.people2000.user.model.dto.User3;
import com.people2000.user.model.dto.UserDto;
import com.people2000.user.model.dto.UserOutDTO;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserExample;

public interface UserDAOWrite2 extends UserDAOWrite {

	List<User2> selectByExample2(UserExample example) throws SQLException;

	/**
	 * 根据username和companyId查询用户
	 * 
	 * @param username
	 * @param companyId
	 * @return User
	 * @throws SQLException
	 * @author lijinkui
	 */
	User selectByUsernameAndCompanyId(String username, Long companyId)
			throws SQLException;

	List<User> selectByMobileAndPlatformId(String mobile, Long companyId,
			Long platformId) throws SQLException;

	List<User> selectByUserNameAndPlatformId(Map<String, Object> params)
			throws SQLException;

	List<User> selectByMobilePassAndPlatformId(Map<String, Object> params)
			throws SQLException;

	int selectCountByMobileAndPlatformId(String mobile, Long platformId)
			throws SQLException;

	int selectCountByUserNameAndPlatformId(String userName, Long platformId)
			throws SQLException;

	int selectCountByMobileAndCompanyId(String mobile, Long companyId)
			throws SQLException;

	int selectCountByUserNameAndCompanyId(String userName, Long companyId)
			throws SQLException;

	/* 通过用户信息条件查询用户信息 */
	List<User> selectListUserByuser(User user) throws SQLException;

	// 用户列表分页
	List<User> getUserListByConditionWithPage(Map<String, Object> map)
			throws SQLException;

	// 用户列表 条数
	int getUserCntByConditionWithPage(Map<String, Object> map)
			throws SQLException;

	int activeOrInactiveUser(UserDto userDto) throws SQLException;

	int validateRepeatOrNotByParams(UserDto userDto) throws SQLException;

	int batchDeleteUser(UserDto userDto) throws SQLException;

	List<Map<String, Object>> getUnionAccounts(List<UserOutDTO> param)
			throws SQLException;

	User3 selectDetailByUserId2(Long userId) throws SQLException;

	int updateOpenIdByPrimaryKey(User record);
}