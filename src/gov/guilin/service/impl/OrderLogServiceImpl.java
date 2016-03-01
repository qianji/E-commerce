/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.OrderLogDao;
import gov.guilin.entity.OrderLog;
import gov.guilin.service.OrderLogService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 订单日志
 * 
 * @author guilin
 * @version
 */
@Service("orderLogServiceImpl")
public class OrderLogServiceImpl extends BaseServiceImpl<OrderLog, Long> implements OrderLogService {

	@Resource(name = "orderLogDaoImpl")
	public void setBaseDao(OrderLogDao orderLogDao) {
		super.setBaseDao(orderLogDao);
	}

}