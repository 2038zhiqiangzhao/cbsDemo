package com.people.cbsadmin.business.dao.ext;

import java.util.List;

import com.people.cbsadmin.model.dto.CommunityActivityDto;
import com.people.cbsadmin.model.po.CommunityActivity;
import com.people.cbsadmin.model.vo.CommunityActivityVo;




public interface CommunityActivityMapperExt {

	List<CommunityActivity> selectAll();

	CommunityActivity  SelectByCid(Long communityActivityId);

	int countOrderMain(CommunityActivityDto querys);

	List<CommunityActivity> queryOrderMainPage(CommunityActivityDto querys);

	CommunityActivity queryActivityState(Long id);

	void updateByPrimaryKeySelective(CommunityActivity mCommunityActivity);

	List<CommunityActivityDto> selectAll(
			CommunityActivityDto communityActivityDto);

	List<CommunityActivityVo> queryActivityListAll();

	void updateByPrimaryKeyTimeInterval(CommunityActivity record);





	

}
