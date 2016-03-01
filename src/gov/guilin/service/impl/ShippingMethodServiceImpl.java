/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.ShippingMethodDao;
import gov.guilin.entity.ShippingMethod;
import gov.guilin.service.ShippingMethodService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 配送方式
 * 
 * @author guilin
 * @version
 */
@Service("shippingMethodServiceImpl")
public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethod, Long> implements ShippingMethodService {

	@Resource(name = "shippingMethodDaoImpl")
	public void setBaseDao(ShippingMethodDao shippingMethodDao) {
		super.setBaseDao(shippingMethodDao);
	}

}