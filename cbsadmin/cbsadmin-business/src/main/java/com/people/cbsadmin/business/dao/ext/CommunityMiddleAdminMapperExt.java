package com.people.cbsadmin.business.dao.ext;

import java.util.List;

import com.people.cbsadmin.model.po.CommunityMiddleAdmin;

public interface CommunityMiddleAdminMapperExt {

	CommunityMiddleAdmin queryCommutniyId(CommunityMiddleAdmin record);
	List<CommunityMiddleAdmin> queryCommutniyIdList(CommunityMiddleAdmin record);
}
