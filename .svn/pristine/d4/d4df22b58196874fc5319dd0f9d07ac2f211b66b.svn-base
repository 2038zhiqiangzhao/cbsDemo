package com.people.cbsadmin.business.write;

import com.people.cbsadmin.model.dto.OrderDTO;
import com.people.cbsadmin.model.dto.OrderRefundApplyDTO;
import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.vo.CommunityActivityOrderVo;
import com.people2000.common.exception.BusinessException;



public interface CommunityActivityOrderWriterManager {

	void createActivityOrderWithTx(CommunityActivityOrder communityActivityOrder);

	void paySuccessWithTx(OrderDTO order) throws BusinessException;

	void refundApplyWithTx(OrderRefundApplyDTO refundApplyDTO);

	CommunityActivityOrder creatQRCode(CommunityActivityOrderVo mCommunityActivityOrderVo);

	void updateById(CommunityActivityOrder mCommunityActivityOrder);


}
