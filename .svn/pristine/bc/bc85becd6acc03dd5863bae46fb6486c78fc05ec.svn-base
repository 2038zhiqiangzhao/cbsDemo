package com.people.cbsadmin.business.dao.ext;

import java.util.List;

import com.people.cbsadmin.model.dto.CommunityActivityDto;
import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.vo.CommunityActivityOrderVo;



public interface CommunityActivityOrderMapperExt {

	CommunityActivityOrder updatePrimaryKeyByCommunityActivityId(
			Long communityActivityId);

	List<CommunityActivityOrder> selectOrderByUserId(Long createUserId);

	CommunityActivityOrder queryOrderMainByOrderCode(String orderCode);

	CommunityActivityOrder selectOrderByUserIdAndActivityId(
			CommunityActivityOrderVo mCommunityActivityOrderVo);

	List<CommunityActivityOrder> selectUserList(Long communityActivityId);

	List<CommunityActivityOrderVo> selectAll(CommunityActivityOrderVo communityActivityOrderVo);

	Integer countOrderMain(CommunityActivityOrderVo communityActivityOrderVo);

	List<CommunityActivityDto> selectAllPage(
			CommunityActivityDto communityActivityDto);

	Integer isExsit(CommunityActivityOrder mCommunityActivityOrder);

	List<CommunityActivityOrder> queryOrderMainPage(
			CommunityActivityOrderVo querys);

	int queryUserIdAndActivityId(CommunityActivityOrderVo mCommunityActivityOrderVo);

	List<CommunityActivityOrder> qurySignList(CommunityActivityOrder communityActivityOrder);

	List<CommunityActivityOrder> quryReportList(
			CommunityActivityOrder communityActivityOrder);


}
