package gov.guilin.service;

import gov.guilin.entity.Supplier;

public  interface SupplierService extends BaseService<Supplier, Long> {
	/**
	 * 判断商家是否存在
	 * 
	 * @param username
	 *            商家(忽略大小写)
	 * @return 商家是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 判断商家是否禁用
	 * 
	 * @param username
	 *            商家(忽略大小写)
	 * @return 商家是否禁用
	 */
	boolean usernameDisabled(String username);
	/**
	 * 判断E-mail是否唯一
	 * 
	 * @param previousEmail
	 *            修改前E-mail(忽略大小写)
	 * @param currentEmail
	 *            当前E-mail(忽略大小写)
	 * @return E-mail是否唯一
	 */
	boolean emailUnique(String previousEmail, String currentEmail);
	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);
	/**
	 * 保存商家
	 * 
	 * @param member
	 *            商家
	 * @param operator
	 *            操作员
	 */
	void save(Supplier supplier, String operator);
	/**
	 * 
	 * 方法说明: 更新商家 
	 * @param supplier 商家
	 * @param operator 操作员
	 */
	void update(Supplier supplier, String operator);
	/**
	 * 
	 * 方法说明: 根据供应商编码查找供应商
	 * @param supplier 商家编码
	 * @return 供应商
	 */
	Supplier findSupplierByCode(String supplierCode);
	
}
