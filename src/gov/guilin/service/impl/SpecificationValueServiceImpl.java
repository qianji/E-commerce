/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.SpecificationValueDao;
import gov.guilin.entity.SpecificationValue;
import gov.guilin.service.SpecificationValueService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 规格值
 * 
 * @author guilin
 * @version
 */
@Service("specificationValueServiceImpl")
public class SpecificationValueServiceImpl extends BaseServiceImpl<SpecificationValue, Long> implements SpecificationValueService {

	@Resource(name = "specificationValueDaoImpl")
	public void setBaseDao(SpecificationValueDao specificationValueDao) {
		super.setBaseDao(specificationValueDao);
	}

}