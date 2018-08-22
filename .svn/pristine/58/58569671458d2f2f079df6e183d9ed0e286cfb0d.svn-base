package com.people.cbsadmin.business.write;

import com.people.cbsadmin.model.dto.CommunityActivityDto;
import com.people.cbsadmin.model.dto.CommunityActivityTwoDto;
import com.people.cbsadmin.model.po.CommunityActivity;
import com.people.cbsadmin.model.vo.CommunityActivityOrderVo;
import com.people.cbsadmin.model.vo.CommunityActivityVo;
import com.people.cbsadmin.model.vo.CommunitySecret;
import com.people2000.common.exception.BusinessException;



public interface CommunityActivityWriterManager {

	

	String createOrderCode();

	void updateSelective(CommunityActivity dto);

	void updateSelective(CommunityActivityVo dto);

	void completeOrderWithTx(CommunityActivityDto mCommunityActivityDto) throws BusinessException;

	void completeOrderWithTxTwo(CommunityActivityDto mCommunityActivityDto) throws BusinessException;

	void updateCommunityClass(CommunityActivityDto communityActivityDto);

	void addCommunityActivity(CommunityActivityTwoDto communityActivityDto);

	void updateProduct(CommunityActivityTwoDto communityActivityDto);

	void updateCommunityClass(CommunityActivityVo communityVo) throws BusinessException;

	void completeIsTimePublish(CommunityActivityDto mCommunityActivityDto);

	void completeOrderWithTxTwo(
			CommunityActivityOrderVo mCommunityActivityOrderVo);

	Object updateCommunitySecret(CommunityActivityVo communityActivityVo,Boolean bo);

	Object updateAuthCode(CommunitySecret communitySecret);

	

}
