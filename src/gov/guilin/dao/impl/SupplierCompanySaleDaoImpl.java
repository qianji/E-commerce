package gov.guilin.dao.impl;

import gov.guilin.dao.SupplierCompanySaleDao;
import gov.guilin.entity.Member;
import gov.guilin.entity.MemberRank;
import gov.guilin.entity.Supplier;
import gov.guilin.entity.SupplierCompanySale;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
@Repository("supplierCompanySaleDaoImpl")
public class SupplierCompanySaleDaoImpl extends BaseDaoImpl<SupplierCompanySale, Long> implements SupplierCompanySaleDao {
	/**
	 * 执行本地SQL插入数据
	 * @param SupplierCompanySale
	 *           关联关系对象
	 */
	public int insertSupplierCompanySale(SupplierCompanySale scs) {
		if (scs != null ) {
//			String jpql = "update Supplier suppliers set suppliers." + propertyName + " = null";
//			entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
			Date date = new Date();
			String insertSQL = "insert into xc_supplier_member_reank_b(supplier , user_name , member_rank,id,create_date,modify_date) values (2,'zhangsan',1,33, '2014-04-27 00:03:43' , '2014-04-27 00:03:43')";
			Query query =  entityManager.createNativeQuery(insertSQL) ;
			query.executeUpdate();
//			super.remove(supplierAttribute);
		}
		return 1 ;
	}
	/**
	 * 
	 * 方法说明: 判断相同用户和级别是否存在
	 * @param member 用户
	 * @param memberRank 用户级别
	 * @return
	 */
	public boolean existScsByUserNameAndMemberRank(Member member ,MemberRank memberRank){
		if ((member == null) || (memberRank==null)) {
			return false;
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SupplierCompanySale> criteriaQuery = criteriaBuilder.createQuery(SupplierCompanySale.class);
		Root<SupplierCompanySale> root = criteriaQuery.from(SupplierCompanySale.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (member != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
		}
		if (memberRank != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("memberRank"), memberRank));
		}
		criteriaQuery.where(restrictions);
		List<SupplierCompanySale> scs = super.findList(criteriaQuery, null, null, null,null);
		if( (scs==null) || (scs.isEmpty())){
			return false ;
		}
		
		return scs.size() > 0;
	}
	/**
	 * 方法说明: 删除该供应商下面的全部配置
	 * @param supplier供应商
	 * @return
	 */
	public int deleteScsBySupplier(Supplier supplier){
		Assert.notNull(supplier);

		String jpql = "delete from SupplierCompanySale scs where scs.supplier = :supplier";
		int result = entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("supplier", supplier).executeUpdate();
		return result ;
	}

}
