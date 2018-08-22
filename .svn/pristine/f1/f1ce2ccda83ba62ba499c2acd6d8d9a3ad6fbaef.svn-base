package com.people.cbsadmin.business.job;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.people.cbsadmin.business.read.CommunityActivityReadManage;
import com.people.cbsadmin.business.read.CommunityActivityReadOrderManage;
import com.people.cbsadmin.business.write.CommunityActivityOrderWriterManager;
import com.people.cbsadmin.business.write.CommunityActivityWriterManager;
import com.people.cbsadmin.model.constant.AllConstant;
import com.people.cbsadmin.model.dto.CommunityActivityDto;
import com.people.cbsadmin.model.po.CommunityActivity;
import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.vo.CommunityActivityOrderVo;
import com.people2000.common.base.date.DateUtil;
import com.people2000.common.base.page.PageResult;
import com.people2000.common.exception.BusinessException;


@Component("orderJob")
public class OrderJob {

	/**
	 * 空间订单支付有效时间 15分钟
	 */
	private static final long PAY_VALID_TIME = 15 * 60 * 1000L;

	private static final int HANLDER_DATA_SIZE = 1000;

	/**
	 * 距离订单完成30分钟
	 */
	private static final long ORDER_COMPLETE_LAST_30M = 30;
	/**
	 * 距离订单完成10分钟
	 */
	private static final long ORDER_COMPLETE_LAST_10M = 10;
	// 距离定时发布还有一分半钟
	private static final long ORDER_COMPLETE_LAST_1M = 1;

	private static Logger logger = LoggerFactory.getLogger(OrderJob.class);


	@Resource
	private CommunityActivityReadManage communityActivityReadManage;
	@Resource
	private CommunityActivityWriterManager communityActivityWriterManager;
	@Resource
	private CommunityActivityOrderWriterManager communityActivityOrderWriterManager;
	@Resource
	private CommunityActivityReadOrderManage communityActivityReadOrderManage;

	
	



	

	
	/**
	 * 活动状态报名中，报名截止，活动截止定时切换
	 * 
	 */
	public void CommunityActivityOrderTask() {
		logger.debug("系统定时任务[活动状态报名中通知], start run...");
		try {
			Calendar nowCal = Calendar.getInstance();
			nowCal.set(Calendar.MILLISECOND, 0);

			// 处理待使用的订单
			CommunityActivityDto querys = new CommunityActivityDto();
			querys.setCurrentPage(1);
			querys.setItemsPerPage(HANLDER_DATA_SIZE);
			PageResult<CommunityActivity> pageResult = communityActivityReadManage
					.queryCommunityActivityPage(querys);
			if (null != pageResult.getListObj()
					&& pageResult.getListObj().size() > 0) {
				for (CommunityActivity communityActivity : pageResult
						.getListObj()) {
					if (communityActivity.getId() == null) {
						continue;
					}
					if (communityActivity.getApplyEndTime() != null
							&& communityActivity.getActivityEndTime() != null) {
						Calendar endTimeCal = Calendar.getInstance();
						endTimeCal.setTime(communityActivity.getApplyEndTime());// 报名截止时间
						endTimeCal.set(Calendar.MILLISECOND, 0);

						Calendar endTimeCal2 = Calendar.getInstance();
						endTimeCal2.setTime(communityActivity
								.getActivityEndTime());// 活动截止时间
						endTimeCal2.set(Calendar.MILLISECOND, 0);

						long diffstart = endTimeCal.getTimeInMillis()//
								- nowCal.getTimeInMillis();
						long diffsend = endTimeCal2.getTimeInMillis()// 当前时间和活动截止时间对比
								- nowCal.getTimeInMillis();

						CommunityActivityDto mCommunityActivityDto = new CommunityActivityDto();
						if (diffstart <= (ORDER_COMPLETE_LAST_1M) * 60 * 1000L
								&& diffstart >= 0) {// 当前时间和报名截止时间，考虑到定时器一分钟一次执行到截止时间：状态-报名截止
							mCommunityActivityDto.setId(communityActivity
									.getId());
							communityActivityWriterManager
									.completeOrderWithTx(mCommunityActivityDto);
							logger.debug("系统定时任务待[活动状态为报名截止]]");
						}
						if (diffstart <= 0
								&& diffstart > -(ORDER_COMPLETE_LAST_1M) * 60 * 1000L) {
							mCommunityActivityDto.setId(communityActivity
									.getId());
							communityActivityWriterManager
									.completeOrderWithTx(mCommunityActivityDto);
							logger.debug("系统定时任务待[活动状态为报名截止]]");
						}

						if (diffsend <= (ORDER_COMPLETE_LAST_1M) * 60 * 1000L
								&& diffsend >= 0) {// 当前时间和报名截止时间，考虑到定时器一分钟一次执行到截止时间：状态-报名截止
							mCommunityActivityDto.setId(communityActivity
									.getId());
							communityActivityWriterManager
									.completeOrderWithTxTwo(mCommunityActivityDto);
							logger.debug("系统定时任务待[活动状态为活动截止]]");
						}
						if (diffsend <= 0
								&& diffsend > -(ORDER_COMPLETE_LAST_1M) * 60 * 1000L) {
							mCommunityActivityDto.setId(communityActivity
									.getId());
							communityActivityWriterManager
									.completeOrderWithTxTwo(mCommunityActivityDto);
							logger.debug("系统定时任务待[活动状态为活动截止]]");
						}

					}
				}

			}
		} catch (Exception e) {
			logger.error("系统定时任务待[活动状态异常] ", e);
		}
		logger.debug("系统定时任务待[活动状态开始], end run.");

	}

	/**
	 * 社区活动定时发布
	 * 
	 */
	public void CommunityActivityIsTimePublishTask() {
		logger.debug("系统定时任务[活动定时发布], start run...");
		try {
			Calendar nowCal = Calendar.getInstance();
			nowCal.set(Calendar.MILLISECOND, 0);

			// 处理待使用的订单
			CommunityActivityDto querys = new CommunityActivityDto();
			querys.setCurrentPage(1);
			querys.setItemsPerPage(HANLDER_DATA_SIZE);
			PageResult<CommunityActivity> pageResult = communityActivityReadManage
					.queryCommunityActivityPage(querys);
			if (pageResult.getTotal() == 0) {// 没有需要处理的数据
				return;
			}
			for (CommunityActivity communityActivity : pageResult.getListObj()) {
				CommunityActivity queryActivity = communityActivityReadManage
						.queryActivity(communityActivity.getId());
				if (queryActivity.getIsTimePublish() == 1) {// 是定时发布
					Calendar isTimePublish = Calendar.getInstance();
					if (communityActivity.getTimeInterval() != null
							&& !communityActivity.getTimeInterval().equals("")) {
						isTimePublish.setTime(communityActivity
								.getTimeInterval());// 定时发布时间
						isTimePublish.set(Calendar.MILLISECOND, 0);
						long diff = isTimePublish.getTimeInMillis()// 当前时间和定时发布时间
								- nowCal.getTimeInMillis();
						CommunityActivityDto mCommunityActivityDto = new CommunityActivityDto();
						if (diff <= (ORDER_COMPLETE_LAST_1M) * 60 * 1000L
								&& diff >= 0) {// 定时发布时间<=当前时间才可以发布，考虑到定时器一分钟一次执行
							mCommunityActivityDto.setId(communityActivity
									.getId());
							communityActivityWriterManager
									.completeIsTimePublish(mCommunityActivityDto);
							logger.debug("系统定时任务待[活动定时发布]]");
						}
						if (diff <= 0
								&& diff > -(ORDER_COMPLETE_LAST_1M) * 60 * 1000L) {// 定时发布时间<=当前时间才可以发布，考虑到定时器一分钟一次执行
							mCommunityActivityDto.setId(communityActivity
									.getId());
							communityActivityWriterManager
									.completeIsTimePublish(mCommunityActivityDto);
							logger.debug("系统定时任务待[活动定时发布]]");
						}
					}
				}

			}
		} catch (Exception e) {
			logger.error("系统定时任务待[活动定时发布异常] ", e);
		}
		logger.debug("系统定时任务待[活动定时发布结束], end run.");

	}

	/**
	 * 活动订单状态未开始，进行中，已结束
	 * 
	 */
	public void communityActivityOrderStateTask() {
		logger.debug("系统定时任务[活动订单状态通知], start run...");
		try {
			Calendar nowCal = Calendar.getInstance();
			nowCal.set(Calendar.MILLISECOND, 0);
			// 处理待使用的订单
			CommunityActivityOrderVo querys = new CommunityActivityOrderVo();
			querys.setCurrentPage(1);
			querys.setItemsPerPage(HANLDER_DATA_SIZE);
			PageResult<CommunityActivityOrder> pageResult = communityActivityReadOrderManage
					.queryCommunityActivityPage(querys);
			if (pageResult.getTotal() == 0) {// 没有需要处理的数据
				return;
			}
			for (CommunityActivityOrder communityActivityOrder : pageResult
					.getListObj()) {
				if (communityActivityOrder.getId() == null) {
					continue;
				}
				

				if (communityActivityOrder.getActivityTime() != null) {
					// 2018-04-10 02:42:59-12:30
					String time = communityActivityOrder.getActivityTime();
					String startTime = time.split(" ")[0] + " "
							+ time.split(" ")[1].split("-")[0];
					String endTime = time.split(" ")[0] + " "
							+ time.split(" ")[1].split("-")[1];
					Date dateStartTime = DateUtil.getDate(startTime,
							"yyyy-MM-dd HH:mm");
					Date dateEndTime = DateUtil.getDate(endTime,
							"yyyy-MM-dd HH:mm");
					Calendar endTimeCal = Calendar.getInstance();
					endTimeCal.setTime(dateStartTime);
					endTimeCal.set(Calendar.MILLISECOND, 0);

					Calendar endTimeCal2 = Calendar.getInstance();
					endTimeCal2.setTime(dateEndTime);
					endTimeCal2.set(Calendar.MILLISECOND, 0);

					long diffstart = endTimeCal.getTimeInMillis()
							- nowCal.getTimeInMillis();
					long diffsend = endTimeCal2.getTimeInMillis()
							- nowCal.getTimeInMillis();

					CommunityActivityOrderVo mCommunityActivityOrderVo = new CommunityActivityOrderVo();
					 if ( diffstart <= (ORDER_COMPLETE_LAST_1M ) * 60 * 1000L
					 &&diffstart >=0)
					 {//当前时间和报名截止时间，考虑到定时器一分钟一次执行到截止时间：状态-报名截止
					 mCommunityActivityOrderVo.setId(communityActivityOrder.getId());
					 mCommunityActivityOrderVo.setStatus(AllConstant.CommunityActivityStates.ACTIVITY_ING);//
					 communityActivityWriterManager.completeOrderWithTxTwo(mCommunityActivityOrderVo);
					 logger.debug("系统定时任务待[活动订单状态未开始]]");
					 }
					 if(diffstart <=0&& diffstart > -(ORDER_COMPLETE_LAST_1M )
					 * 60 * 1000L){
						 mCommunityActivityOrderVo.setId(communityActivityOrder.getId());
						 mCommunityActivityOrderVo.setStatus(AllConstant.CommunityActivityStates.ACTIVITY_ING);//
						 communityActivityWriterManager.completeOrderWithTxTwo(mCommunityActivityOrderVo);
						 logger.debug("系统定时任务待[活动订单状态未开始]]");
					 }
					
					 if ( diffsend <= (ORDER_COMPLETE_LAST_1M ) * 60 * 1000L
					 &&diffsend >=0) {//当前时间和报名截止时间，考虑到定时器一分钟一次执行到截止时间：状态-报名截止
						 mCommunityActivityOrderVo.setId(communityActivityOrder.getId());
						 mCommunityActivityOrderVo.setStatus(AllConstant.CommunityActivityStates.ACTIVITY_OVER);//
						 communityActivityWriterManager.completeOrderWithTxTwo(mCommunityActivityOrderVo);
						 logger.debug("系统定时任务待[活动订单状态未开始]]");
					 }
					 if(diffsend <=0&& diffsend > -(ORDER_COMPLETE_LAST_1M ) *
					 60 * 1000L){
						 mCommunityActivityOrderVo.setId(communityActivityOrder.getId());
						 mCommunityActivityOrderVo.setStatus(AllConstant.CommunityActivityStates.ACTIVITY_OVER);//
						 communityActivityWriterManager.completeOrderWithTxTwo(mCommunityActivityOrderVo);
						 logger.debug("系统定时任务待[活动订单状态未开始]]");
					 }
					
				}

			}
		} catch (Exception e) {
			logger.error("系统定时任务待[活动订单状态异常] ", e);
		}
		logger.debug("系统定时任务待[活动订单状态开始], end run.");

	}
}
