package gov.guilin.service.impl;

import gov.guilin.Setting;
import gov.guilin.dao.SupplierDao;
import gov.guilin.entity.Supplier;
import gov.guilin.service.SupplierService;
import gov.guilin.util.SettingUtils;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
@Service("supplierServiceImpl")
public class SupplierServiceImpl extends BaseServiceImpl<Supplier, Long> implements SupplierService {
	@Resource(name = "supplierDaoImpl")
	private SupplierDao supplierDao;
	
	@Resource(name = "supplierDaoImpl")
	public void setSupplierDao(SupplierDao supplierDao) {
		super.setBaseDao(supplierDao);
	}

	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return supplierDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public boolean usernameDisabled(String username) {
		Assert.hasText(username);
		//该类从工程根目录guilin.xml加载[setting节点信息]
		Setting setting = SettingUtils.get();
		if (setting.getDisabledUsernames() != null) {
			for (String disabledUsername : setting.getDisabledUsernames()) {
				if (StringUtils.containsIgnoreCase(username, disabledUsername)) {
					return true;
				}
			}
		}
		return false;
	}
	@Transactional(readOnly = true)
	public boolean emailUnique(String previousEmail, String currentEmail) {
		if (StringUtils.equalsIgnoreCase(previousEmail, currentEmail)) {
			return true;
		} else {
			if (supplierDao.emailExists(currentEmail)) {
				return false;
			} else {
				return true;
			}
		}
	}
	@Transactional(readOnly = true)
	public boolean emailExists(String email) {
		return supplierDao.emailExists(email);
	}
	
	public void save(Supplier supplier, String operator){
		Assert.notNull(supplier);
		supplierDao.persist(supplier);
		
	}
	public void update(Supplier supplier, String operator) {
		Assert.notNull(supplier);
		//lock
//		supplierDao.lock(supplier, LockModeType.PESSIMISTIC_WRITE);
		//merge: 新new一个对象，如果该对象设置了ID，则这个对象就当作游离态处理
		//当ID在数据库中不能找到时，用update的话肯定会报异常，然而用merge的话，就会insert。
        //当ID在数据库中能找到的时候，update与merge的执行效果都是更新数据，发出update语句；
		supplierDao.merge(supplier);
	}
	/**
	 * 
	 * 方法说明: 根据供应商编码查找供应商
	 * @param supplier 商家编码
	 * @return 供应商
	 */
	public Supplier findSupplierByCode(String supplierCode){
		return supplierDao.querySupplierByCode(supplierCode);
	}
	
}
