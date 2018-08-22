package com.people.cbsadmin.business.write.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people.cbsadmin.business.dao.CommunityActivityOrderMapper;
import com.people.cbsadmin.business.dao.ext.BaseSequenceMapperExt;
import com.people.cbsadmin.business.dao.ext.BaseSystemMapperExt;
import com.people.cbsadmin.business.dao.ext.CommunityActivityMapperExt;
import com.people.cbsadmin.business.dao.ext.CommunityActivityOrderMapperExt;
import com.people.cbsadmin.business.utils.ConstantUtils;
import com.people.cbsadmin.business.write.CommunityActivityOrderWriterManager;
import com.people.cbsadmin.business.write.CommunityActivityWriterManager;
import com.people.cbsadmin.model.constant.AllConstant;
import com.people.cbsadmin.model.constant.ExceptionCodes;
import com.people.cbsadmin.model.dto.OrderDTO;
import com.people.cbsadmin.model.dto.OrderRefundApplyDTO;
import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.vo.CommunityActivityOrderVo;
import com.people2000.common.base.date.DateUtil;
import com.people2000.common.exception.BusinessException;

@Service("communityActivityOrderWriterManager")
public class CommunityActivityOrderWriterManagerImpl implements CommunityActivityOrderWriterManager {
	@Autowired
	private CommunityActivityOrderMapper mCommunityActivityOrderMapper;
	@Autowired
	private CommunityActivityOrderMapperExt mCommunityActivityOrderMapperExt;
	@Autowired
	private BaseSequenceMapperExt baseSequenceMapperExt;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	@Autowired
	private BaseSystemMapperExt baseSystemMapperExt;
	@Autowired
	private CommunityActivityWriterManager communityActivityWriterManager;
	@Resource
	private CommunityActivityMapperExt communityActivityMapperExt;
	@Override
	public void createActivityOrderWithTx(
			CommunityActivityOrder communityActivityOrder) {
		communityActivityOrder.setCreateTime(new Date());

        mCommunityActivityOrderMapper.insertSelective(communityActivityOrder);
	}

	
	
	
	/**
	 * 支付成功后
	 * 修改活动订单状态
	 * @throws BusinessException 
	 * 
	 */
	@Override
	public void paySuccessWithTx(OrderDTO orderDTO) throws BusinessException  {
		CommunityActivityOrder communityActivityOrder = 
				mCommunityActivityOrderMapperExt.queryOrderMainByOrderCode(orderDTO
				.getOrderCode());
		if (communityActivityOrder == null) {
			throw new BusinessException(ExceptionCodes.PARAMS_ERROR, "该订单不存在："
					+ orderDTO.getOrderCode());
		}
		if (communityActivityOrder.getPayState() != null
				&& communityActivityOrder.getPayState().intValue() == ConstantUtils.COMMUNITY_ORDER_PAY_STATE_YES) {
			throw new BusinessException(ExceptionCodes.PARAMS_ERROR, "该订单已经支付");
		}
		// 更新订单状态，注意活动订单和以前的订单不同，比如订单状态等
		CommunityActivityOrder mCommunityActivityOrder = new CommunityActivityOrder();
		mCommunityActivityOrder.setId(communityActivityOrder.getId());
		//活动订单状态
		mCommunityActivityOrder.setStatus(ConstantUtils.COMMUNITY_ORDER_PAY_STATE_NO);
		mCommunityActivityOrder.setPayState(AllConstant.OrderPayState.ORDER_PAY_STATE_YES);
		mCommunityActivityOrder.setPayWay(communityActivityOrder.getPayWay());
		//设置支付时间
		mCommunityActivityOrder.setPayTime(DateUtil.getDate(DateUtil.formatDateTime(new Date()),"yyyy-MM-dd HH:mm:ss"));
		//支付成功后设置入场
		mCommunityActivityOrder.setIsEntry((short) 1);
		mCommunityActivityOrderMapper.updateByPrimaryKeySelective(mCommunityActivityOrder);
		
	}
	/**
	 * 根据用户id和活动订单退款申请
	 * 
	 */
	@Override
	public void refundApplyWithTx(
			OrderRefundApplyDTO refundApplyDTO) {
		
	}
	/**
	 * 生成活动二维码
	 *   生成活动二维码注意的是：
	 *     1：用户下单后支付成功
	 *     2：把用户id，预订人信息，预订人手机号，订单编号，支付时间，活动地点，活动时间，活动结束时间等信息存进去
	 */
	@Override
	public CommunityActivityOrder creatQRCode(CommunityActivityOrderVo mCommunityActivityOrderVo) {
		
		
		CommunityActivityOrder communityActivityOrder = mCommunityActivityOrderMapperExt.selectOrderByUserIdAndActivityId(mCommunityActivityOrderVo);
		
		return communityActivityOrder;

	}
	@Override
	public void updateById(CommunityActivityOrder mCommunityActivityOrder) {

		mCommunityActivityOrderMapper.updateByPrimaryKeySelective(mCommunityActivityOrder);	
		
			
	}

	
}
