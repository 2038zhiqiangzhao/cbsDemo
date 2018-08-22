package com.people.cbsadmin.business.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people.cbsadmin.business.dao.OrderPayNotifyMapper;
import com.people.cbsadmin.business.dao.ext.OrderPayNotifyMapperExt;
import com.people.cbsadmin.business.read.OrderPayNotifyReadManage;
import com.people.cbsadmin.model.po.OrderPayNotify;
import com.people.cbsadmin.model.vo.OrderPayNotifyQueryVo;
import com.people2000.common.base.page.PageResult;


@Service("orderPayNotifyReadManage")
public class OrderPayNotifyReadManageImpl implements OrderPayNotifyReadManage {

	@Autowired
	private OrderPayNotifyMapper orderPayNotifyMapper;
	@Autowired
	private OrderPayNotifyMapperExt orderPayNotifyMapperExt;
	
	@Override
	public List<OrderPayNotify> queryOrderPayNotifyList(
			OrderPayNotifyQueryVo querys) {
		return orderPayNotifyMapperExt.queryOrderPayNotifyList(querys);
	}

	@Override
	public PageResult<OrderPayNotify> queryOrderPayNotifyPage(
			OrderPayNotifyQueryVo querys) {
		PageResult<OrderPayNotify> page = new PageResult<OrderPayNotify>();
		int totalSize = orderPayNotifyMapperExt.countOrderPayNotify(querys);
		if (totalSize > 0) {
			page.setTotal(totalSize);
			List<OrderPayNotify> dataList = orderPayNotifyMapperExt
					.queryOrderPayNotifyPage(querys);
			page.setListObj(dataList);
		}
		return page;
	}

	@Override
	public OrderPayNotify queryByOrderCode(String orderCode) {
		return orderPayNotifyMapperExt.queryByOrderCode(orderCode);
	}

}
