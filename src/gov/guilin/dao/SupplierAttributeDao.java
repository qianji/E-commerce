package gov.guilin.dao;

import java.util.List;

import gov.guilin.entity.MemberAttribute;
import gov.guilin.entity.SupplierAttribute;
/**
 * Copyright: 	www.xc.com
 * Author:		chenyibin@xc.com
 * Created:		2014-4-29 下午02:02:02
 * Vesion:		1.0
 * Last EditTime:	
 *				2014-4-29 下午02:02:02
 * Update Logs:
 *				DanielChen@2014-4-29 下午02:02:02 添加
 * 项目名称：		guilin
 * 说明: 供应商注册持久层
 */
public interface SupplierAttributeDao extends BaseDao<SupplierAttribute, Long>{
	/**
	 * 查找未使用的对象属性序号
	 * 
	 * @return 未使用的对象属性序号，若无可用序号则返回null
	 */
	Integer findUnusedPropertyIndex();

	/**
	 * 查找已启用商家注册项
	 * 
	 * @return 已启用商家注册项
	 */
	List<SupplierAttribute> findList();
}
