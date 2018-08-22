package com.people.cbsadmin.business.read;


import java.util.List;

import com.people.cbsadmin.model.dto.CommunityActivityAdminDto;
import com.people.cbsadmin.model.po.CommunityActivityAdmin;
import com.people.cbsadmin.model.po.CommunityMiddleAdmin;


public interface CommunityActivityAdminReadManage {

	CommunityActivityAdmin queryIsAdmin(String  oderCode);
    List<CommunityActivityAdmin> queryAdminMumber(Long activityId);
    List<CommunityMiddleAdmin> queryAdminMiddle(Long activityId);
	CommunityActivityAdmin queryAdminnumber(CommunityActivityAdmin communityActivityAdmin);
}
