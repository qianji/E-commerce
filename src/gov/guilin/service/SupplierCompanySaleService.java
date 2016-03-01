package gov.guilin.service;

import gov.guilin.entity.Member;
import gov.guilin.entity.MemberRank;
import gov.guilin.entity.Supplier;
import gov.guilin.entity.SupplierCompanySale;

public interface SupplierCompanySaleService extends BaseService<SupplierCompanySale, Long> {
	/**
	 * 
	 * 方法说明: 判断相同用户和级别是否存在
	 * @param member 用户
	 * @param memberRank 用户级别
	 * @return
	 */
	boolean existScsByUserNameAndMemberRank(Member member ,MemberRank memberRank);
	/**
	 * 方法说明: 删除该供应商下面的全部配置
	 * @param supplier供应商
	 * @return
	 */
	int deleteScsBySupplier(Supplier supplier);
}
