package gov.guilin.dao.impl;

import gov.guilin.dao.SupplierAttributeDao;
import gov.guilin.entity.Member;
import gov.guilin.entity.Supplier;
import gov.guilin.entity.SupplierAttribute;
import gov.guilin.entity.SupplierAttribute.Type;

import java.util.List;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;
/**
 * Copyright: 	www.xc.com
 * Author:		chenyibin@xc.com
 * Created:		2014-4-29 下午02:04:12
 * Vesion:		1.0
 * Last EditTime:	
 *				2014-4-29 下午02:04:12
 * Update Logs:
 *				DanielChen@2014-4-29 下午02:04:12 添加
 * 项目名称：		guilin
 * 说明: 供应商注册项持久层
 */
@Repository("supplierAttributeDaoImpl")
public class SupplierAttributeDaoImpl extends BaseDaoImpl<SupplierAttribute, Long>implements SupplierAttributeDao {
	
	public Integer findUnusedPropertyIndex() {
		for (int i = 0; i < Supplier.ATTRIBUTE_VALUE_PROPERTY_COUNT; i++) {
			String jpql = "select count(*) from SupplierAttribute supplierAttribute where supplierAttribute.propertyIndex = :propertyIndex";
			Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("propertyIndex", i).getSingleResult();
			if (count == 0) {
				return i;
			}
		}
		return null;
	}
	
	public List<SupplierAttribute> findList() {
		String jpql = "select supplierAttribute from SupplierAttribute supplierAttribute where supplierAttribute.isEnabled = true order by supplierAttribute.order asc";
		return entityManager.createQuery(jpql, SupplierAttribute.class).setFlushMode(FlushModeType.COMMIT).getResultList();
	}
	/**
	 * 清除商家注册项值
	 * 
	 * @param supplierAttribute
	 *            商家注册项
	 */
	@Override
	public void remove(SupplierAttribute supplierAttribute) {
		if (supplierAttribute != null && (supplierAttribute.getType() == Type.text || supplierAttribute.getType() == Type.select || supplierAttribute.getType() == Type.checkbox)) {
			String propertyName = Supplier.ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + supplierAttribute.getPropertyIndex();
			String jpql = "update Supplier suppliers set suppliers." + propertyName + " = null";
			entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
			super.remove(supplierAttribute);
		}
	}
	
}
