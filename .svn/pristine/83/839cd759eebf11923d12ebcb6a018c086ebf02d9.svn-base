package com.people.cbsadmin.business.read;

import java.util.List;

import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.vo.CommunityActivityOrderVo;
import com.people2000.common.base.page.PageResult;



public interface CommunityActivityReadOrderManage {

	CommunityActivityOrder initCreateActivityOrder(
			CommunityActivityOrderVo mCommunityActivityOrderVo);

	List<CommunityActivityOrder> queryActivityOrderInfo(Long createUserId);

	CommunityActivityOrder queryCommnityOrderDetail(String orderCode);

	PageResult<CommunityActivityOrderVo> quryListOder(CommunityActivityOrderVo communityActivityOrderVo);

	CommunityActivityOrder quryListOderDetails(CommunityActivityOrder communityActivityOrder);

	PageResult<CommunityActivityOrder> queryCommunityActivityPage(
			CommunityActivityOrderVo querys);

	int quryUserIdAndActivityId(
			CommunityActivityOrderVo mCommunityActivityOrderVo);

	Object queryCommnityOrderAdmin(String oderCode);

	Object isSignCommunityCode(String oderCode);
	List<CommunityActivityOrder> quryListSign(CommunityActivityOrder communityActivityOrder);

	List<CommunityActivityOrder> quryListReport(
			CommunityActivityOrder communityActivityOrder);

}
