/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.ParameterGroupDao;
import gov.guilin.entity.ParameterGroup;
import gov.guilin.service.ParameterGroupService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 参数组
 * 
 * @author guilin
 * @version
 */
@Service("parameterGroupServiceImpl")
public class ParameterGroupServiceImpl extends BaseServiceImpl<ParameterGroup, Long> implements ParameterGroupService {

	@Resource(name = "parameterGroupDaoImpl")
	public void setBaseDao(ParameterGroupDao parameterGroupDao) {
		super.setBaseDao(parameterGroupDao);
	}

}