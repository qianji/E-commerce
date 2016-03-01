/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.LogDao;
import gov.guilin.entity.Log;
import gov.guilin.service.LogService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 日志
 * 
 * @author guilin
 * @version
 */
@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements LogService {

	@Resource(name = "logDaoImpl")
	private LogDao logDao;

	@Resource(name = "logDaoImpl")
	public void setBaseDao(LogDao logDao) {
		super.setBaseDao(logDao);
	}

	public void clear() {
		logDao.removeAll();
	}

}