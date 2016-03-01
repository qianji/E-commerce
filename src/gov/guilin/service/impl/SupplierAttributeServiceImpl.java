package gov.guilin.service.impl;

import java.util.List;

import gov.guilin.dao.MemberAttributeDao;
import gov.guilin.dao.SupplierAttributeDao;
import gov.guilin.entity.MemberAttribute;
import gov.guilin.entity.SupplierAttribute;
import gov.guilin.service.SupplierAttributeService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Copyright: 	www.xc.com
 * Author:		chenyibin@xc.com
 * Created:		2014-4-29 下午02:00:28
 * Vesion:		1.0
 * Last EditTime:	
 *				2014-4-29 下午02:00:28
 * Update Logs:
 *				DanielChen@2014-4-29 下午02:00:28 添加
 * 项目名称：		guilin
 * 说明: 供应商注册项业务实现层
 */
@Service("supplierAttributeServiceImpl")
public class SupplierAttributeServiceImpl  extends BaseServiceImpl<SupplierAttribute, Long> implements SupplierAttributeService {
	@Resource(name = "supplierAttributeDaoImpl")
	private SupplierAttributeDao supplierAttributeDao;

	@Resource(name = "supplierAttributeDaoImpl")
	public void setBaseDao(SupplierAttributeDao supplierAttributeDao) {
		super.setBaseDao(supplierAttributeDao);
	}
	
	
	@Transactional(readOnly = true)
	public List<SupplierAttribute> findList() {
		return supplierAttributeDao.findList();
	}
	@Transactional(readOnly = true)
	public Integer findUnusedPropertyIndex() {
		return supplierAttributeDao.findUnusedPropertyIndex();
	}
	
}
