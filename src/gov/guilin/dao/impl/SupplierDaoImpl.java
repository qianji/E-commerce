package gov.guilin.dao.impl;

import gov.guilin.dao.SupplierDao;
import gov.guilin.entity.Supplier;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
@Repository("supplierDaoImpl")
public class SupplierDaoImpl extends BaseDaoImpl<Supplier, Long> implements SupplierDao {
	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from Supplier suppliers where lower(suppliers.supplierCode) = lower(:supplierCode)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("supplierCode", username).getSingleResult();
		return count > 0;
	}

	public boolean emailExists(String email) {
		if (email == null) {
			return false;
		}
		String jpql = "select count(*) from Supplier suppliers where lower(suppliers.email) = lower(:email)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getSingleResult();
		return count > 0;
	}
	
	/**
	 * 
	 * 方法说明: 根据供应商编码查找供应商
	 * @param supplier 商家编码
	 * @return 供应商
	 */
	public Supplier querySupplierByCode(String supplierCode){
		if (supplierCode == null) {
			return null;
		}
		try {
			String jpql = "select supplier from Supplier supplier where lower(supplier.supplierCode) = lower(:supplierCode)";
			return entityManager.createQuery(jpql, Supplier.class).setFlushMode(FlushModeType.COMMIT).setParameter("supplierCode", supplierCode).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
