package com.people2000.user.business.write.manage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.people2000.user.model.po.ibatis.OrganizationQuery;
import com.people2000.user.model.po.ibatis.UOrganization;
import com.people2000.user.model.po.ibatis.UOrganizationPO;
import com.people2000.user.model.po.ibatis.UPosition;
import com.people2000.user.model.vo.UserOrgVo;

public interface OrganizationWriteManage {

	List<UOrganizationPO> getOrgInfo(UOrganization org) throws SQLException;

	Long addOneOrgWithTx(UOrganization org) throws SQLException;

	int updOneOrgWithTx(UOrganization org) throws SQLException;

	Map<String, Object> queryOrgUser(OrganizationQuery query) throws Exception;

	Map<String, Object> queryOrgUser(OrganizationQuery query, Boolean flag)
			throws Exception;

	Map<String, Object> queryUserByOrgInfo(OrganizationQuery query)
			throws Exception;

	int deleteOrgUserWithTx(OrganizationQuery query) throws Exception;

	void putOrgUserWithTx(OrganizationQuery query) throws Exception;

	List<UOrganization> getAllOrgInfo(Map map) throws Exception;

	List<UPosition> getAllPostitionInfo(Map map) throws Exception;

	int userOrgChangeWithTx(OrganizationQuery query) throws SQLException;

	Long addYonghuWithTx(UserOrgVo userOrgVo) throws SQLException;

	// 统计部门人员数
	Long countOrgUser(OrganizationQuery query) throws SQLException;

	// 更新部门上级部门编码
	Long updateOrgParentCodeAndDelWithTx(UOrganization org) throws SQLException;

	// 部门修改之后更新下级的parentCode
	Long updateOrgParentCodeWithTx(UOrganization org) throws SQLException;

}
