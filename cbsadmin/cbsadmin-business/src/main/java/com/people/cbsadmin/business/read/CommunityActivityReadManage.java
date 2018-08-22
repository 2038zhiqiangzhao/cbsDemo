package com.people.cbsadmin.business.read;

import java.util.List;

import com.people.cbsadmin.model.dto.CommunityActivityDto;
import com.people.cbsadmin.model.dto.CommunityActivityTwoDto;
import com.people.cbsadmin.model.po.CommunityActivity;
import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.vo.CommunityActivityVo;
import com.people.cbsadmin.model.vo.CommunitySecret;
import com.people2000.common.base.page.PageResult;


public interface CommunityActivityReadManage {

	PageResult<CommunityActivityDto> queryActivityList(CommunityActivityDto communityActivityDto);

	List<CommunityActivityVo> queryActivityListDetails(Long id);

	List<CommunityActivityOrder> createActivityOrder(Long id);

	PageResult<CommunityActivity> queryCommunityActivityPage(
			CommunityActivityDto querys);

	Object deleteCommunityClass();

	CommunityActivityTwoDto queryCommunityActivityById(Long id);

	CommunityActivity queryActivity(Long id);

	List<CommunityActivityVo> queryActivityListAll();


	Object queryIsActivityCodeAdmin(CommunitySecret communitySecret);


}
