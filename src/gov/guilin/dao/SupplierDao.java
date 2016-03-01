package gov.guilin.dao;

import gov.guilin.entity.Supplier;

public interface SupplierDao extends BaseDao<Supplier, Long> {
	/**
	 * 判断商家名是否存在
	 * 
	 * @param username
	 *            商家名(忽略大小写)
	 * @return 商家名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);
	/**
	 * 
	 * 方法说明: 根据供应商编码查找供应商
	 * @param supplier 商家编码
	 * @return 供应商
	 */
	Supplier querySupplierByCode(String supplierCode);
	
}
