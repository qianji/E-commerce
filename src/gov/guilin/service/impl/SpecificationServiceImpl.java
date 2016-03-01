/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.SpecificationDao;
import gov.guilin.entity.Specification;
import gov.guilin.service.SpecificationService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 规格
 * 
 * @author guilin
 * @version
 */
@Service("specificationServiceImpl")
public class SpecificationServiceImpl extends BaseServiceImpl<Specification, Long> implements SpecificationService {

	@Resource(name = "specificationDaoImpl")
	public void setBaseDao(SpecificationDao specificationDao) {
		super.setBaseDao(specificationDao);
	}

}