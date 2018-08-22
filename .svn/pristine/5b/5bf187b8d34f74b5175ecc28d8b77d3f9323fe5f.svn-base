package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.dao.UserDAOWriteImpl;
import com.people2000.user.model.dto.User2;
import com.people2000.user.model.dto.User3;
import com.people2000.user.model.dto.UserDto;
import com.people2000.user.model.dto.UserOutDTO;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserExample;

@Repository("userDAOWrite")
public class UserDAOWriteImpl2 extends UserDAOWriteImpl implements
		UserDAOWrite2 {

	@Autowired
	public UserDAOWriteImpl2(SqlMapClient sqlMapClientWrite) {
		super(sqlMapClientWrite);
	}

	// @Autowired
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User2> selectByExample2(UserExample example)
			throws SQLException {
		return sqlMapClient.queryForList(
				"u_user2.abatorgenerated_selectByExample", example);
	}

	@Override
	public User selectByUsernameAndCompanyId(String username, Long companyId)
			throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("companyId", companyId);
		return (User) sqlMapClient.queryForObject(
				"u_user2.selectByUsernameAndCompanyId", params);
	}

	@Override
	public List<User> selectByMobileAndPlatformId(String mobile,
			Long companyId, Long platformId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", mobile);
		params.put("companyId", companyId);
		params.put("platformId", platformId);
		return sqlMapClient.queryForList("u_user2.selectByMobileAndPlatformId",
				params);
	}

	@Override
	public List<User> selectByUserNameAndPlatformId(Map<String, Object> params)
			throws SQLException {
		return sqlMapClient.queryForList(
				"u_user2.selectByUserNameAndPlatformId", params);
	}

	@Override
	public List<User> selectByMobilePassAndPlatformId(Map<String, Object> params)
			throws SQLException {
		return sqlMapClient.queryForList(
				"u_user2.selectByMobilePassAndPlatformId", params);
	}

	@Override
	public int selectCountByMobileAndPlatformId(String mobile, Long platformId)
			throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("mobile", mobile);
		params.put("platformId", platformId);
		return (Integer) sqlMapClient.queryForObject(
				"u_user2.selectCountByMobileAndPlatformId", params);
	}

	@Override
	public int selectCountByUserNameAndPlatformId(String userName,
			Long platformId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("username", userName);
		params.put("platformId", platformId);
		return (Integer) sqlMapClient.queryForObject(
				"u_user2.selectCountByUserNameAndPlatformId", params);
	}

	@Override
	public int selectCountByMobileAndCompanyId(String mobile, Long companyId)
			throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", mobile);
		params.put("companyId", companyId);
		return (Integer) sqlMapClient.queryForObject(
				"u_user2.selectCountByMobileAndCompanyId", params);
	}

	@Override
	public int selectCountByUserNameAndCompanyId(String userName, Long companyId)
			throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", userName);
		params.put("companyId", companyId);
		return (Integer) sqlMapClient.queryForObject(
				"u_user2.selectCountByUserNameAndCompanyId", params);
	}

	@Override
	public List<User> selectListUserByuser(User user) throws SQLException {
		return sqlMapClient.queryForList("u_user2.selectListUserByuser", user);
	}

	@Override
	public List<User> getUserListByConditionWithPage(Map<String, Object> map)
			throws SQLException {
		return sqlMapClient.queryForList(
				"u_user2.getUserListByConditionWithPage", map);
	}

	@Override
	public int getUserCntByConditionWithPage(Map<String, Object> map)
			throws SQLException {
		return (Integer) sqlMapClient.queryForObject(
				"u_user2.getUserCntByConditionWithPage", map);
	}

	@Override
	public int activeOrInactiveUser(UserDto userDto) throws SQLException {
		return (Integer) sqlMapClient.update("u_user2.activeOrInactiveUser",
				userDto);
	}

	@Override
	public int validateRepeatOrNotByParams(UserDto userDto) throws SQLException {
		int cnt = (Integer) sqlMapClient.queryForObject(
				"u_user2.validateRepeatOrNotByParams", userDto);
		return cnt;
	}

	@Override
	public int batchDeleteUser(UserDto userDto) throws SQLException {
		// TODO Auto-generated method stub
		int cnt = (Integer) sqlMapClient.update("u_user2.batchDeleteUser",
				userDto);
		return cnt;
	}

	public List<Map<String, Object>> getUnionAccounts(List<UserOutDTO> param)
			throws SQLException {
		return (List<Map<String, Object>>) sqlMapClient.queryForList(
				"u_user2.getUnionAccounts", param);
	}

	@Override
	public User3 selectDetailByUserId2(Long userId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return (User3) sqlMapClient.queryForObject("u_user2.getUserDetailById",
				params);
	}
	
	
	@Override
	public int updateOpenIdByPrimaryKey(User record) {
		int cnt = -1;
		try {
			cnt = (Integer) sqlMapClient.update("u_user.abatorgenerated_updateOpenIdByPrimaryKey",
					record);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}