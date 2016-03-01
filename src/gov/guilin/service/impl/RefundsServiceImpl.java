/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.RefundsDao;
import gov.guilin.entity.Refunds;
import gov.guilin.service.RefundsService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 退款单
 * 
 * @author guilin
 * @version
 */
@Service("refundsServiceImpl")
public class RefundsServiceImpl extends BaseServiceImpl<Refunds, Long> implements RefundsService {

	@Resource(name = "refundsDaoImpl")
	public void setBaseDao(RefundsDao refundsDao) {
		super.setBaseDao(refundsDao);
	}

}