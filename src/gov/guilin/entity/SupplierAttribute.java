package gov.guilin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * Copyright: 	www.xc.com
 * Author:		chenyibin@xc.com
 * Created:		2014-4-29 下午01:35:39
 * Vesion:		1.0
 * Last EditTime:	
 *				2014-4-29 下午01:35:39
 * Update Logs:
 *				DanielChen@2014-4-29 下午01:35:39 添加
 * 项目名称：		guilin
 * 说明: 供应商注册项
 */
@Entity
@Table(name = "xc_supplier_attr_b")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_supplier_attribute_sequence")
public class SupplierAttribute extends OrderEntity {

	/**
	 * @Daniel 
	 */
	private static final long serialVersionUID = 113492064163566956L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 商家类型 */
		supplie,

		/** 传真 */
		fax,

		/** 邮编 */
		zipCode,

		/** 生效日期 */
		activeStartDate,

		/** 失效日期*/
		activeEndDate,

		/** 开户行 */
		bankDeposit,

		/** 开户名*/
		bankName,

		/** 银行账号 */
		bankAccount,		
		/** 备注 */
		remark,	
		/** 文本 */
		text,

		/** 单选项 */
		select,

		/** 多选项 */
		checkbox
	}

	/** 名称 */
	private String name;

	/** 类型 */
	private Type type;

	/** 是否启用 */
	private Boolean isEnabled;

	/** 是否必填 */
	private Boolean isRequired;

	/** 属性序号 */
	private Integer propertyIndex;

	/** 可选项 */
	private List<String> options = new ArrayList<String>();

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@NotNull(groups = Save.class)
	@Column(nullable = false, updatable = false)
	public Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 获取是否启用
	 * 
	 * @return 是否启用
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 * 
	 * @param isEnabled
	 *            是否启用
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 获取是否必填
	 * 
	 * @return 是否必填
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIsRequired() {
		return isRequired;
	}

	/**
	 * 设置是否必填
	 * 
	 * @param isRequired
	 *            是否必填
	 */
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * 获取属性序号
	 * 
	 * @return 属性序号
	 */
	@Column(updatable = false)
	public Integer getPropertyIndex() {
		return propertyIndex;
	}

	/**
	 * 设置属性序号
	 * 
	 * @param propertyIndex
	 *            属性序号
	 */
	public void setPropertyIndex(Integer propertyIndex) {
		this.propertyIndex = propertyIndex;
	}

	/**
	 * 获取可选项
	 * 
	 * @return 可选项
	 */
	@ElementCollection
	@CollectionTable(name = "xc_supplier_attr_option_b")
	public List<String> getOptions() {
		return options;
	}

	/**
	 * 设置可选项
	 * 
	 * @param options
	 *            可选项
	 */
	public void setOptions(List<String> options) {
		this.options = options;
	}
}
