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
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.people.cbsadmin.api.BaseController;
import com.people.cbsadmin.business.read.OrderPayNotifyReadManage;
import com.people.cbsadmin.business.write.CommunityActivityOrderWriterManager;
import com.people.cbsadmin.business.write.OrderPayNotifyWriteManage;
import com.people.cbsadmin.model.constant.AllConstant;
import com.people.cbsadmin.model.dto.OrderDTO;
import com.people.cbsadmin.model.dto.OrderPayNotifyDTO;
import com.people.cbsadmin.model.po.OrderPayNotify;

@Controller
@RequestMapping("/communityActivityWechat")
public class CommunityActivityWechatController extends BaseController{


	/**
	 * 活动订单相关业务处理类
	 */
	@Resource
	private CommunityActivityOrderWriterManager communityActivityOrderWriterManager;
	@Resource
	private OrderPayNotifyReadManage orderPayNotifyReadManage;
	@Resource
	private OrderPayNotifyWriteManage orderPayNotifyWriteManage;
	
	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(CommunityActivityWechatController.class);
	
	/**
	 * 
	 * 微信支付异步通知回调方法
	 * 
	 * @date 2017-12-20
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/wxPayNofity")
	public void wxPayNofity(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 获取微信POST过来反馈信息
			Map requestParams = request.getParameterMap();
			String contentType = request.getHeader("Content-Type");
			logger.debug("Content-Type:" + contentType);
			if (requestParams.isEmpty()) {
				logger.error("微信支付异步通知回调方法通知被执行，但是无参数！");
				return;
			}
			Map<String, String> params = new HashMap<String, String>();
			for (Iterator iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			final String orderCode = params.get("out_trade_no");
			final String resultCode = params.get("result_code");
			final String data = JSONObject.toJSONString(params);
			// 判断是否处理过
			OrderPayNotify payNotify = orderPayNotifyReadManage
					.queryByOrderCode(orderCode);
			if (payNotify != null
					&& payNotify.getStatus() != null
					&& payNotify.getStatus().intValue() == AllConstant.PayNotifyStatus.HANDLER_YES) {// 已经处理过
				return;
			}

			if (resultCode.equals("SUCCESS")) { // 支付成功
				try {
					OrderPayNotifyDTO payNotify1 = new OrderPayNotifyDTO();
					payNotify1.setOrderCode(orderCode);
					payNotify1.setSource(AllConstant.PayNotifySource.WECHATPAY);
					payNotify1
							.setResultCode(AllConstant.PayNotifyResultCode.SUCCESS);
					payNotify1
							.setStatus(AllConstant.PayNotifyStatus.HANDLER_YES);
					payNotify1.setResultData(data);
					orderPayNotifyWriteManage
							.addOrderPayNotifyWithTx(payNotify1);

					OrderDTO order = new OrderDTO();
					order.setOrderCode(orderCode);
					order.setPayway(AllConstant.OrderPayway.WECHAR_PAY);
					communityActivityOrderWriterManager.paySuccessWithTx(order);
				} catch (Exception e) {
					logger.error("【丢单】微信支付成功通知后业务操作异常", e);
					throw e;
				}
				response.getWriter().print("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			try {
				response.getWriter().print("fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			logger.error("微信支付异步通知发生异常", e);
		}
	}

}
