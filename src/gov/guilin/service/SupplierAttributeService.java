package gov.guilin.service;

import gov.guilin.entity.SupplierAttribute;

import java.util.List;

public interface SupplierAttributeService  extends BaseService<SupplierAttribute, Long> {
	/**
	 * 查找商家注册项
	 * 
	 * @return 商家注册项，仅包含已启用商家注册项
	 */
	List<SupplierAttribute> findList();
	/**
	 * 查找未使用的对象属性序号
	 * 
	 * @return 未使用的对象属性序号，若无可用序号则返回null
	 */
	Integer findUnusedPropertyIndex();
}
