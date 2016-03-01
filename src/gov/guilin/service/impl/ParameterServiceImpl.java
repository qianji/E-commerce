/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.ParameterDao;
import gov.guilin.entity.Parameter;
import gov.guilin.service.ParameterService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 参数
 * 
 * @author guilin
 * @version
 */
@Service("parameterServiceImpl")
public class ParameterServiceImpl extends BaseServiceImpl<Parameter, Long> implements ParameterService {

	@Resource(name = "parameterDaoImpl")
	public void setBaseDao(ParameterDao parameterDao) {
		super.setBaseDao(parameterDao);
	}

}