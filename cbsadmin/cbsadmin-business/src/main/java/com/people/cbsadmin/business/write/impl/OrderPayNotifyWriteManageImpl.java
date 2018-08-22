package com.people.cbsadmin.business.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people.cbsadmin.business.dao.OrderPayNotifyMapper;
import com.people.cbsadmin.business.dao.ext.OrderPayNotifyMapperExt;
import com.people.cbsadmin.business.write.OrderPayNotifyWriteManage;
import com.people.cbsadmin.model.dto.OrderPayNotifyDTO;
import com.people.cbsadmin.model.po.OrderPayNotify;
import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.exception.BusinessException;


/**
 * 
 * 订单支付成功 第三方支付平台 异步回调 返回结果处理 业务类
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:dusai,date:2017年11月2日 下午5:43:03,content: </p>
 * @author dusai
 * @date 2017年11月2日 下午5:43:03
 * @since
 * @version
 */
@Service("orderPayNotifyWriteManage")
public class OrderPayNotifyWriteManageImpl implements OrderPayNotifyWriteManage {

	@Autowired
	private OrderPayNotifyMapper orderPayNotifyMapper;
	@Autowired
	private OrderPayNotifyMapperExt orderPayNotifyMapperExt;
	
	@Override
	public void addOrderPayNotifyWithTx(OrderPayNotifyDTO orderPayNotifyDTO)
			throws BusinessException {
		OrderPayNotify record = new OrderPayNotify();
		BeanUtils.copyProperties(orderPayNotifyDTO, record);
		orderPayNotifyMapper.insertSelective(record);
	}

	@Override
	public void updateOrderPayNotifyWithTx(OrderPayNotifyDTO orderPayNotifyDTO)
			throws BusinessException {
		OrderPayNotify record = new OrderPayNotify();
		BeanUtils.copyProperties(orderPayNotifyDTO, record);
		orderPayNotifyMapper.updateByPrimaryKeySelective(record);
	}

}
