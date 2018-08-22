package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.people2000.user.business.write.dao.UOrganizationDAOWrite;
import com.people2000.user.model.po.ibatis.OrganizationQuery;
import com.people2000.user.model.po.ibatis.UOrganization;
import com.people2000.user.model.po.ibatis.UPosition;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.vo.UserOrgVo;

public interface UOrganizationDAOWrite2 extends UOrganizationDAOWrite {

	public int selectUserCntByParam(OrganizationQuery query)
			throws SQLException;

	public List<User> selectUserPageByParam(OrganizationQuery query)
			throws SQLException;

	public int deleteOrgUser(OrganizationQuery query) throws Exception;

	public int selectUserCntByOrgInfo(OrganizationQuery query)
			throws SQLException;

	public List<UserOrgVo> selectUserPageByOrgInfo(OrganizationQuery query)
			throws SQLException;

	public List<UOrganization> getAllOrgInfo(Map<String, Object> map)
			throws SQLException;

	public List<UPosition> getAllPostitionInfo(Map<String, Object> map)
			throws SQLException;

	public int userOrgChange(OrganizationQuery query) throws SQLException;

	Long updateOrgParentCode(UOrganization org) throws SQLException;

}
