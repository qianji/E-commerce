/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.OrderItemDao;
import gov.guilin.entity.OrderItem;
import gov.guilin.service.OrderItemService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 订单项
 * 
 * @author guilin
 * @version
 */
@Service("orderItemServiceImpl")
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem, Long> implements OrderItemService {

	@Resource(name = "orderItemDaoImpl")
	public void setBaseDao(OrderItemDao orderItemDao) {
		super.setBaseDao(orderItemDao);
	}

}