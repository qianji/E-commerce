/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.AttributeDao;
import gov.guilin.entity.Attribute;
import gov.guilin.service.AttributeService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 属性
 * 
 * @author guilin
 * @version
 */
@Service("attributeServiceImpl")
public class AttributeServiceImpl extends BaseServiceImpl<Attribute, Long> implements AttributeService {

	@Resource(name = "attributeDaoImpl")
	public void setBaseDao(AttributeDao attributeDao) {
		super.setBaseDao(attributeDao);
	}

}