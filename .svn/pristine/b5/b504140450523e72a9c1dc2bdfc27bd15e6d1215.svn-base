package com.people.cbsadmin.api.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.people.cbsadmin.api.BaseController;
import com.people.cbsadmin.business.read.OrderPayNotifyReadManage;
import com.people.cbsadmin.business.write.CommunityActivityOrderWriterManager;
import com.people.cbsadmin.business.write.OrderPayNotifyWriteManage;
import com.people.cbsadmin.model.constant.AllConstant;
import com.people.cbsadmin.model.dto.OrderDTO;
import com.people.cbsadmin.model.dto.OrderPayNotifyDTO;
import com.people.cbsadmin.model.dto.OrderRefundApplyDTO;
import com.people.cbsadmin.model.po.OrderPayNotify;
import com.people2000.user.client.utils.SessionContainer;

/**活动订单支付宝支付
 * 
 * @author zhq_zhao
 *
 */
@Controller
@RequestMapping("/communityActivityAliPay")
public class CommunityActivityAliPayController  extends BaseController {
	/**
	 * 活动订单相关业务处理类
	 */
	@Resource
	private CommunityActivityOrderWriterManager communityActivityOrderWriterManager;
	/**
	 * 订单支付异步通知业务处理类（读）
	 */
	@Resource
	private OrderPayNotifyReadManage orderPayNotifyReadManage;
	/**
	 * 订单支付异步通知业务处理类（写）
	 */
	@Resource
	private OrderPayNotifyWriteManage orderPayNotifyWriteManage;
	
	
	
	/**
	 * 创建支付宝logger日志
	 * 
	 */
	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(CommunityActivityAliPayController.class);
	
	/**
	 * 支付宝支付，异步通知回调方法
	 * 
	 * @author zhaozhiqiang
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/aliPayNofity")
	public void aliPayNofity(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 获取支付宝POST过来反馈信息
			Map requestParams = request.getParameterMap();
			//获取请求头信息
			String contentType = request.getHeader("Content-Type");
			logger.debug("Content-Type:" + contentType);
			if (requestParams.isEmpty()) {
				logger.error("支付宝支付异步通知回调方法通知被执行，但是无参数！");
				return;
			}
			Map<String, String> params = new HashMap<String, String>();
			for (Iterator iter = requestParams.keySet().iterator(); 
					iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			final String orderCode = params.get("out_trade_no");//商户订单号 
			/**
			 * 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
			 * TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款） 
			 * 
			 */
			final String tradeStatus = params.get("trade_status");
			final String data = JSONObject.toJSONString(params);
			// 判断是否处理过
			OrderPayNotify payNotify = orderPayNotifyReadManage
					.queryByOrderCode(orderCode);
			if (payNotify != null
					&& payNotify.getStatus() != null
					&& payNotify.getStatus().intValue() == AllConstant.PayNotifyStatus.HANDLER_YES) {// 已经处理过
				return;
			}

			if (tradeStatus.equals("TRADE_SUCCESS")) { // 支付成功
				try {
					// 支付成功操作
					OrderPayNotifyDTO payNotify1 = new OrderPayNotifyDTO();
					payNotify1.setOrderCode(orderCode);
					payNotify1.setSource(AllConstant.PayNotifySource.ALIPAY);
					payNotify1
							.setResultCode(AllConstant.PayNotifyResultCode.SUCCESS);
					payNotify1.setResultData(JSONObject.toJSONString(data));
					payNotify1
							.setStatus(AllConstant.PayNotifyStatus.HANDLER_YES);
					orderPayNotifyWriteManage
							.addOrderPayNotifyWithTx(payNotify1);

					OrderDTO order = new OrderDTO();
					order.setOrderCode(orderCode);
					order.setPayway(AllConstant.OrderPayway.ALIPAY);
					communityActivityOrderWriterManager.paySuccessWithTx(order);
				} catch (Exception e) {
					logger.error("【丢单】支付宝支付成功通知后业务操作异常", e);
					throw e;
				}
				response.getWriter().print("success");
			}
		} catch (Exception e) {
			try {
				response.getWriter().print("fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			logger.error("支付宝支付异步通知发生异常", e);
		}
	}
	/**
	 * 活动订单退款申请
	 * 
	 */
	@RequestMapping("/refundApply")
	@ResponseBody
	public Object refundApply(@RequestBody OrderRefundApplyDTO refundApplyDTO) {
		try {
			refundApplyDTO.setUserId(SessionContainer.getUserId());
			communityActivityOrderWriterManager.refundApplyWithTx(refundApplyDTO);
			return success(0);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}

}
