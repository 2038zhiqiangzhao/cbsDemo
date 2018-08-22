package com.people.cbsadmin.business.read.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people.cbsadmin.business.dao.CommunityActivityAdminMapper;
import com.people.cbsadmin.business.dao.CommunityActivityMapper;
import com.people.cbsadmin.business.dao.CommunityActivityOrderMapper;
import com.people.cbsadmin.business.dao.CommunityMiddleAdminMapper;
import com.people.cbsadmin.business.dao.ext.CommunityActivityAdminMapperExt;
import com.people.cbsadmin.business.dao.ext.CommunityActivityMapperExt;
import com.people.cbsadmin.business.dao.ext.CommunityActivityOrderMapperExt;
import com.people.cbsadmin.business.dao.ext.CommunityMiddleAdminMapperExt;
import com.people.cbsadmin.business.read.CommunityActivityReadOrderManage;
import com.people.cbsadmin.business.write.CommunityActivityWriterManager;
import com.people.cbsadmin.model.constant.AllConstant;
import com.people.cbsadmin.model.po.CommunityActivity;
import com.people.cbsadmin.model.po.CommunityActivityAdmin;
import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.po.CommunityMiddleAdmin;
import com.people.cbsadmin.model.vo.CommunityActivityOrderVo;
import com.people2000.common.base.date.DateUtil;
import com.people2000.common.base.page.PageResult;



@Service("communityActivityReadOrderManage")
public class CommunityActivityReadOrderManageImpl implements CommunityActivityReadOrderManage {
	@Autowired
	private CommunityActivityMapper communityMapper;
	@Autowired
	private CommunityActivityWriterManager communityActivityWriterManager;
	@Autowired
	private CommunityActivityMapperExt communityMapperExt;
	@Autowired
	private CommunityActivityOrderMapper mCommunityActivityOrderMapper;
	@Autowired
	private CommunityActivityOrderMapperExt mCommunityActivityOrderMapperExt;
	@Autowired
	private CommunityActivityAdminMapper communityActivityAdminMapper;
	@Autowired
	private CommunityActivityAdminMapperExt communityActivityAdminMapperExt;
	@Autowired
	private CommunityMiddleAdminMapperExt communityMiddleAdminMapperExt;
	@Override
	public CommunityActivityOrder initCreateActivityOrder(
			CommunityActivityOrderVo mCommunityActivityOrderVo) {
	

		// 初始化活动主表
		CommunityActivityOrder mCommunityActivityOrder = new CommunityActivityOrder();
		/**支付状态为了和订单状态对应上使用关系如下：
		 *   订单状态进行中1013014
		 *   订单状态已结束1013015
		 */
		mCommunityActivityOrder.setPayState(AllConstant.OrderPayState.ORDER_PAY_STATE_NO);//待支付
		//得到活动编号查询数据库信息
		List<CommunityActivity> coa = communityMapper.selectByPrimaryKey(
				mCommunityActivityOrderVo.getCommunityActivityId());
		
		Date dataActivityStartTime=null;//数据库中的活动开始时间
		Date dataActivityendTime=null;//数据库中的活动结束时间
		BigDecimal dataRegistrationFee=null;
		for (CommunityActivity co : coa) {
			dataActivityStartTime = co.getActivityTime();
			dataActivityendTime=co.getActivityEndTime();
			//实际金额必须要和活动中的数据库中校验通过活动id查询
			 dataRegistrationFee = co.getRegistrationFee();
		}
        if(dataActivityStartTime!=null&&dataActivityendTime!=null){
        	 //判断活动开始时间和现在时间对比，来定义订单状态
   		 Date nowDate = new Date();
   		 Long dfStart = DateUtil.getDiffSeconds(dataActivityStartTime,nowDate);
   		 Long dfEnd = DateUtil.getDiffSeconds(dataActivityendTime,nowDate);

   		 //订单状态未开始
   		 if(dfStart<0){
   			 mCommunityActivityOrder.setStatus(AllConstant.CommunityActivityStates.ACTIVITY_NOUSER);
   		  }else if(dfStart>0&&dfEnd<0){ //订单状态进行中
   			  mCommunityActivityOrder.setStatus(AllConstant.CommunityActivityStates.ACTIVITY_ING);
   		  }else if(dfEnd>0){//订单状态已结束
   			 mCommunityActivityOrder.setStatus(AllConstant.CommunityActivityStates.ACTIVITY_OVER);
   		 }	 
		 }
		 
		  
		// 生成订单编号
		String orderCode = communityActivityWriterManager.createOrderCode();
		mCommunityActivityOrderVo.setOrderCode(orderCode);
		//获取活动编号id查到活动订单主键并设置信息
		Long communityActivityId = mCommunityActivityOrderVo.getCommunityActivityId();
		mCommunityActivityOrder.setOrderCode(mCommunityActivityOrderVo.getOrderCode());
		mCommunityActivityOrder.setCommunityActivityId(communityActivityId);
		mCommunityActivityOrder.setActivityName(mCommunityActivityOrderVo.getActivityName());
		mCommunityActivityOrder.setActivityTime(mCommunityActivityOrderVo.getActivityTime());
		mCommunityActivityOrder.setReserveName(mCommunityActivityOrderVo.getReserveName());
		mCommunityActivityOrder.setReservePhone(mCommunityActivityOrderVo.getReservePhone());
		mCommunityActivityOrder.setPayWay(mCommunityActivityOrderVo.getPayWay());
		mCommunityActivityOrder.setSurfacePicture(mCommunityActivityOrderVo.getSurfacePicture());
		mCommunityActivityOrder.setTotalAmount(dataRegistrationFee);
		mCommunityActivityOrder.setOrderSource(mCommunityActivityOrderVo.getOrderSource());
		//设置订单创建时间
		mCommunityActivityOrder.setCreateTime(new Date());
		
		return mCommunityActivityOrder;
	}
	@Override
	public List<CommunityActivityOrder> queryActivityOrderInfo(Long createUserId) {
		
		return  mCommunityActivityOrderMapperExt.selectOrderByUserId(createUserId);
	}
	@Override
	public CommunityActivityOrder queryCommnityOrderDetail(String orderCode) {
		return mCommunityActivityOrderMapperExt.queryOrderMainByOrderCode(orderCode);
	}
	
	@Override
	public PageResult<CommunityActivityOrderVo> quryListOder(
			CommunityActivityOrderVo communityActivityOrderVo) {
		try {
			PageResult<CommunityActivityOrderVo> page = new PageResult<CommunityActivityOrderVo>();
			 Integer totalSize = mCommunityActivityOrderMapperExt.countOrderMain(communityActivityOrderVo);
			if (totalSize > 0) {
				page.setTotal(totalSize);
				List<CommunityActivityOrderVo> dataList = mCommunityActivityOrderMapperExt.selectAll(communityActivityOrderVo);
				page.setListObj(dataList);
			}				
			return page;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public CommunityActivityOrder quryListOderDetails(
			CommunityActivityOrder communityActivityOrder) {
		return mCommunityActivityOrderMapper.selectByPrimaryKey(communityActivityOrder.getId());
	}
	@Override
	public PageResult<CommunityActivityOrder> queryCommunityActivityPage(
			CommunityActivityOrderVo querys) {
		PageResult<CommunityActivityOrder> page = new PageResult<CommunityActivityOrder>();
		int totalSize = mCommunityActivityOrderMapperExt.countOrderMain(querys);
		if (totalSize > 0) {
			page.setTotal(totalSize);
			List<CommunityActivityOrder> dataList = mCommunityActivityOrderMapperExt
					.queryOrderMainPage(querys);
			page.setListObj(dataList);
		}
		return page;
	}
	@Override
	public int quryUserIdAndActivityId(
			CommunityActivityOrderVo mCommunityActivityOrderVo) {
		return mCommunityActivityOrderMapperExt.queryUserIdAndActivityId(mCommunityActivityOrderVo);
	}
	@Override
	public Object queryCommnityOrderAdmin(String oderCode) {
		//通过odercode查询活动id和用户id
		CommunityActivityOrder communityActivityOrder = mCommunityActivityOrderMapperExt.queryOrderMainByOrderCode(oderCode);
		if(communityActivityOrder!=null){
			Long communityActivityId = communityActivityOrder.getCommunityActivityId();
			//通过活动id查询管理员表中是否有该信息
			CommunityActivityAdmin communityActivityAdmin=new CommunityActivityAdmin();
			communityActivityAdmin.setUserId(communityActivityOrder.getCreateUserId());
			//通过查询用户id得到活动id然后再去查询中间表
			List<CommunityActivityAdmin> queryUserIdAndActivityId = communityActivityAdminMapperExt.queryUserIdAndActivityId(communityActivityAdmin);
			
			
		
			if(queryUserIdAndActivityId!=null){
				int communityid=-1;
				for (CommunityActivityAdmin communityActivityAdmin2 : queryUserIdAndActivityId) {
					Long activityId = communityActivityAdmin2.getActivityId();
					CommunityMiddleAdmin cm=new CommunityMiddleAdmin();
					cm.setActivityId(activityId);
					CommunityMiddleAdmin queryCommutniyId = communityMiddleAdminMapperExt.queryCommutniyId(cm);
					if(queryCommutniyId!=null){
						communityid=0;
					}
				}
				return communityid;
			}
			return -1;
		}
		return -1;
		
	}
	@Override
	public Object isSignCommunityCode(String oderCode) {
		//通过odercode查询活动id和用户id
		CommunityActivityOrder communityActivityOrder = mCommunityActivityOrderMapperExt.queryOrderMainByOrderCode(oderCode);
		if(communityActivityOrder!=null){
			//管理员进行签到活动订单
			if(communityActivityOrder.getIsSign().equals(0)){//未签到进行签到
				CommunityActivityOrder record=new CommunityActivityOrder();
				record.setIsSign(1);
				record.setId(communityActivityOrder.getId());
				record.setSignTime(new Date());
				mCommunityActivityOrderMapper.updateByPrimaryKeySelective(record);
				return 0;
			}else{
				//已经签到过无需再次签到
				return 1;
			}		
		}
	
		return -1;
		
		
	}
	@Override
	public List<CommunityActivityOrder> quryListSign(
			CommunityActivityOrder communityActivityOrder) {
		List<CommunityActivityOrder> qurySignList = mCommunityActivityOrderMapperExt.qurySignList(communityActivityOrder);
		
		
		return qurySignList;
	}
	@Override
	public List<CommunityActivityOrder> quryListReport(
			CommunityActivityOrder communityActivityOrder) {
        List<CommunityActivityOrder> qurySignList = mCommunityActivityOrderMapperExt.quryReportList(communityActivityOrder);
		
		
		return qurySignList;
	}


	

}
