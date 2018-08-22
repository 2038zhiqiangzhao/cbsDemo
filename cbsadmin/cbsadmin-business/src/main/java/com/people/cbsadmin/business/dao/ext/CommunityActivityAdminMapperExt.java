package com.people.cbsadmin.business.dao.ext;

import java.util.List;

import com.people.cbsadmin.model.po.CommunityActivityAdmin;
import com.people.cbsadmin.model.vo.CommunitySecret;




public interface CommunityActivityAdminMapperExt {

    CommunityActivityAdmin queryUserId(CommunitySecret communitySecret);

    List<CommunityActivityAdmin> queryUserIdAndActivityId(CommunityActivityAdmin communityActivityAdmin);

	List<CommunityActivityAdmin> queryAdminMumber(Long activityId);
}