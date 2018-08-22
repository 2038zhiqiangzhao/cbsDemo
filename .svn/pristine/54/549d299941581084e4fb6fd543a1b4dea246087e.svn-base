package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.people2000.user.business.write.dao.UserRoleDAOWrite;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.po.ibatis.UserRole;
import com.people2000.user.model.vo.UserRoleVo;

public interface UserRoleDAOWrite2 extends UserRoleDAOWrite {

	List<HashMap<String, Object>> getUserRole(Long id) throws SQLException;

	List<Long> getUserRoleListByPlatformId(Map params) throws SQLException;

	List<Role> getUserRoleByUserId(Long id) throws SQLException;

	List<Role> getUserRoleByParams(Map params) throws SQLException;

	Integer addRolesToUser(List<UserRole> list) throws SQLException;

	Integer batchDeleteUserRole(UserRoleVo vo) throws SQLException;

}