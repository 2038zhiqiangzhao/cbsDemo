package com.people.cbsadmin.business.write;

import com.people.cbsadmin.model.dto.OrderPayNotifyDTO;
import com.people2000.common.exception.BusinessException;

public interface OrderPayNotifyWriteManage {
	
	void addOrderPayNotifyWithTx(OrderPayNotifyDTO orderPayNotifyDTO) throws BusinessException;
	
	void updateOrderPayNotifyWithTx(OrderPayNotifyDTO orderPayNotifyDTO) throws BusinessException;
}
