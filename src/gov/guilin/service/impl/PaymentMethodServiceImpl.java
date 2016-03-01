/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.PaymentMethodDao;
import gov.guilin.entity.PaymentMethod;
import gov.guilin.service.PaymentMethodService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 支付方式
 * 
 * @author guilin
 * @version
 */
@Service("paymentMethodServiceImpl")
public class PaymentMethodServiceImpl extends BaseServiceImpl<PaymentMethod, Long> implements PaymentMethodService {

	@Resource(name = "paymentMethodDaoImpl")
	public void setBaseDao(PaymentMethodDao paymentMethodDao) {
		super.setBaseDao(paymentMethodDao);
	}

}