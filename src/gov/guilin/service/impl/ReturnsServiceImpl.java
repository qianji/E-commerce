/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.ReturnsDao;
import gov.guilin.entity.Returns;
import gov.guilin.service.ReturnsService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 退货单
 * 
 * @author guilin
 * @version
 */
@Service("returnsServiceImpl")
public class ReturnsServiceImpl extends BaseServiceImpl<Returns, Long> implements ReturnsService {

	@Resource(name = "returnsDaoImpl")
	public void setBaseDao(ReturnsDao returnsDao) {
		super.setBaseDao(returnsDao);
	}

}