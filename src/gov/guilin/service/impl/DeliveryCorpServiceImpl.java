/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.DeliveryCorpDao;
import gov.guilin.entity.DeliveryCorp;
import gov.guilin.service.DeliveryCorpService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 物流公司
 * 
 * @author guilin
 * @version
 */
@Service("deliveryCorpServiceImpl")
public class DeliveryCorpServiceImpl extends BaseServiceImpl<DeliveryCorp, Long> implements DeliveryCorpService {

	@Resource(name = "deliveryCorpDaoImpl")
	public void setBaseDao(DeliveryCorpDao deliveryCorpDao) {
		super.setBaseDao(deliveryCorpDao);
	}

}