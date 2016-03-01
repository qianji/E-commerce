package gov.guilin.service.impl;

import gov.guilin.dao.SupplierCompanySaleDao;
import gov.guilin.entity.Member;
import gov.guilin.entity.MemberRank;
import gov.guilin.entity.Supplier;
import gov.guilin.entity.SupplierCompanySale;
import gov.guilin.service.SupplierCompanySaleService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
@Service("supplierCompanySaleServiceImpl")
public class SupplierCompanySaleServiceImpl extends BaseServiceImpl<SupplierCompanySale, Long>  implements
		SupplierCompanySaleService {
	@Resource(name = "supplierCompanySaleDaoImpl")
	private SupplierCompanySaleDao supplierCompanySaleDao;
	
	@Resource(name = "supplierCompanySaleDaoImpl")
	public void setSupplierCompanySaleDao(SupplierCompanySaleDao supplierCompanySaleDao) {
		super.setBaseDao(supplierCompanySaleDao);
	}
	/**
	 * 
	 * 方法说明: 判断相同用户和级别是否存在
	 * @param member 用户
	 * @param memberRank 用户级别
	 * @return
	 */
	public boolean existScsByUserNameAndMemberRank(Member member ,MemberRank memberRank){
		return this.supplierCompanySaleDao.existScsByUserNameAndMemberRank(member, memberRank);
	}
	/**
	 * 方法说明: 删除该供应商下面的全部配置
	 * @param supplier供应商
	 * @return
	 */
	public int deleteScsBySupplier(Supplier supplier){
		return this.supplierCompanySaleDao.deleteScsBySupplier(supplier);
	}
}
