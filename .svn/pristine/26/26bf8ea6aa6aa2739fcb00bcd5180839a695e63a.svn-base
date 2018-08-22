package com.people.cbsadmin.business.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people.cbsadmin.business.dao.CommunityActivityAdminMapper;
import com.people.cbsadmin.business.dao.CommunityActivityOrderMapper;
import com.people.cbsadmin.business.dao.ext.CommunityActivityAdminMapperExt;
import com.people.cbsadmin.business.dao.ext.CommunityActivityOrderMapperExt;
import com.people.cbsadmin.business.dao.ext.CommunityMiddleAdminMapperExt;
import com.people.cbsadmin.business.read.CommunityActivityAdminReadManage;
import com.people.cbsadmin.model.po.CommunityActivityAdmin;
import com.people.cbsadmin.model.po.CommunityMiddleAdmin;


@Service("communityActivityAdminReadManage")
public class CommunityActivityAdminReadManageImpl implements
		CommunityActivityAdminReadManage {
	private CommunityActivityOrderMapper CommunityActivityOrderMapper;
	@Autowired
	private CommunityActivityOrderMapperExt CommunityActivityOrderMapperExt;
	@Autowired
	private CommunityActivityAdminMapper communityActivityAdminMapper;
	@Autowired
	private CommunityActivityAdminMapperExt communityActivityAdminMapperExt;
	@Autowired
	private CommunityMiddleAdminMapperExt communityMiddleAdminMapperExt;
	@Override
	public CommunityActivityAdmin queryIsAdmin(String oderCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CommunityActivityAdmin> queryAdminMumber(Long activityId) {
		List<CommunityActivityAdmin> queryAdminMumber = communityActivityAdminMapperExt.queryAdminMumber(activityId);
		return queryAdminMumber;
		
	}

	@Override
	public List<CommunityMiddleAdmin> queryAdminMiddle(Long activityId) {
		CommunityMiddleAdmin communityMiddleAdmin=new CommunityMiddleAdmin();
		communityMiddleAdmin.setActivityId(activityId);
		List<CommunityMiddleAdmin> queryCommutniyIdList = communityMiddleAdminMapperExt.queryCommutniyIdList(communityMiddleAdmin);
		return queryCommutniyIdList;
	}

	@Override
	public CommunityActivityAdmin queryAdminnumber(
			CommunityActivityAdmin communityActivityAdmin) {
		CommunityActivityAdmin selectByPrimaryKey = communityActivityAdminMapper.selectByPrimaryKey(communityActivityAdmin.getId());
		return selectByPrimaryKey;
	}


}
