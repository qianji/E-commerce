package gov.guilin.entity;

import gov.guilin.entity.SupplierAttribute.Type;
import gov.guilin.util.JsonUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * Entity - 供应商
 * 
 * @author guilin
 * @version
 */
@Entity
@Table(name = "xc_supplier_b")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xc_supplier_sequence")
public class Supplier extends BaseEntity{

	private static final long serialVersionUID = 4550624791438865313L;
	/**
	 * 供应商类型
	 */
	public enum Supplie {
		/** 自营 */
		self,
		/** 第三方 */
		pop
	}
	/**
	 * 获取性别
	 * 
	 * @return 性别
	 */



	/** 会员注册项值属性个数 */
	public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 10;

	/** 会员注册项值属性名称前缀 */
	public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";
	/** 供应商类型 */
	private Supplie supplie;
	/** 供应商编码 */
	private String supplierCode;
	/** 供应商名称 */
	private String supplierName;	
	
	/** 备注 */
	private String remark;		
	/** 地址 */
	private String address;		
	/** 电话 */
	private String phone;		
	/** 传真 */
	private String fax;	
	/** 邮件 */
	private String email;	
	/** 邮编 */
	private String zipCode;	
	/** 联系人 */
	private String constactPerson;		
	/** 部门 */
	private String department;	
	/** 是否启用 */
	private Boolean isEnabled;

	/** 是否锁定 */
	private Boolean isLocked;
	/** 锁定日期 */
	private Date lockedDate;
	/** 生效日期 */
	private Date activeStartDate;
	/** 失效日期 */
	private Date activeEndDate;
	/** 父供应商 */
	private Supplier parent;
	/** 下级供应商 */
	private Set<Supplier> children = new HashSet<Supplier>();
	/** 供应商与用户等级 */
	private Set<SupplierCompanySale> supplierCompanySale = new HashSet<SupplierCompanySale>();
//	/** 用户 */
//	private Set<Member> members = new HashSet<Member>();
//	/** 等级 */
//	private Set<MemberRank> memberRanks = new HashSet<MemberRank>();
	
	
	/** 银行账号 */
	private String bankAccount;	
	/** 开户行 */
	private String bankDeposit;	
	/** 开户名 */
	private String bankName;		
	
	/** 最后登录IP */
	private String loginIp;
	/** 创建人 */
	private String createBy;
	/** 最后修改人 */
	private String modifyBy;
	
	/** 会员注册项值类型 */
	private String attributeValueCategory ;
	/** 会员注册项值0 */
	private String attributeValue0;

	/** 会员注册项值1 */
	private String attributeValue1;

	/** 会员注册项值2 */
	private String attributeValue2;

	/** 会员注册项值3 */
	private String attributeValue3;

	/** 会员注册项值4 */
	private String attributeValue4;

	/** 会员注册项值5 */
	private String attributeValue5;

	/** 会员注册项值6 */
	private String attributeValue6;

	/** 会员注册项值7 */
	private String attributeValue7;

	/** 会员注册项值8 */
	private String attributeValue8;

	/** 会员注册项值9 */
	private String attributeValue9;

	/**
	 * 获取供应商编号
	 * @return 供应商编号
	 */
	@NotEmpty(groups = Save.class)
	@Column(nullable = false, updatable = false, unique = true, length = 35)
	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	/**
	 * 获取供应商名称
	 * @return 供应商名称
	 */
	@Column(nullable = false,unique = true, length = 255)
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 获取备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 获取供应商类别
	 * @return 供应商类别
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Supplie getSupplie() {
		return supplie;
	}

	public void setSupplie(Supplie supplie) {
		this.supplie = supplie;
	}

	/**
	 * 获取地址
	 * @return 地址
	 */
	@Length(max = 200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取电话
	 * @return 电话
	 */
	@Length(max = 25)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取传真
	 * @return 传真
	 */
	@Length(max = 25)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * 获取邮件
	 * @return 邮件
	 */
	@Length(max = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取邮编
	 * @return 邮编
	 */
	@Length(max = 100)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * 获取联系人
	 * @return 联系人
	 */
	@Length(max = 50)
	public String getConstactPerson() {
		return constactPerson;
	}
	public void setConstactPerson(String constactPerson) {
		this.constactPerson = constactPerson;
	}
	/**
	 * 获取部门
	 * @return 部门
	 */
	@Length(max = 200)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 获取是否锁定
	 * @return 是否锁定
	 */
	@Column(nullable = false)
	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	/**
	 * 获取锁定日期
	 * 
	 * @return 锁定日期
	 */
	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	/**
	 * 获取生效日期
	 * @return 生效日期
	 */
	public Date getActiveStartDate() {
		return activeStartDate;
	}

	public void setActiveStartDate(Date activeStartDate) {
		this.activeStartDate = activeStartDate;
	}
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getActiveEndDate() {
		return activeEndDate;
	}

	public void setActiveEndDate(Date activeEndDate) {
		this.activeEndDate = activeEndDate;
	}


	/**
	 * 获取父供应商
	 * @return 父供应商
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	public Supplier getParent() {
		return parent;
	}

	public void setParent(Supplier parent) {
		this.parent = parent;
	}
	/**
	 * 获取下级父供应商
	 * @return 下级父供应商
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<Supplier> getChildren() {
		return children;
	}

	public void setChildren(Set<Supplier> children) {
		this.children = children;
	}
	/**
	 * 获取开户账号
	 * @return 开户账号
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	/**
	 * 获取开户行
	 * @return 开户行
	 */
	public String getBankDeposit() {
		return bankDeposit;
	}

	public void setBankDeposit(String bankDeposit) {
		this.bankDeposit = bankDeposit;
	}
	/**
	 * 获取开户名
	 * @return 开户名
	 */
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * 获取登录IP
	 * @return 登册IP
	 */
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/**
	 * 获取创建人
	 * @return 创建人
	 */
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 获取修改人
	 * @return 修改人
	 */
	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	/**
	 * 获取属性类别
	 * @return 属性类别
	 */
	@Length(max=30)
	public String getAttributeValueCategory() {
		return attributeValueCategory;
	}

	public void setAttributeValueCategory(String attributeValueCategory) {
		this.attributeValueCategory = attributeValueCategory;
	}
	/**
	 * 获取会员注册项值0
	 * 
	 * @return 会员注册项值8
	 */
	@Length(max = 200)
	public String getAttributeValue0() {
		return attributeValue0;
	}

	public void setAttributeValue0(String attributeValue0) {
		this.attributeValue0 = attributeValue0;
	}
	/**
	 * 获取会员注册项值1
	 * @return 会员注册项值1
	 */
	@Length(max = 200)
	public String getAttributeValue1() {
		return attributeValue1;
	}

	public void setAttributeValue1(String attributeValue1) {
		this.attributeValue1 = attributeValue1;
	}
	/**
	 * 获取会员注册项值2
	 * @return 会员注册项值2
	 */
	@Length(max = 200)
	public String getAttributeValue2() {
		return attributeValue2;
	}

	public void setAttributeValue2(String attributeValue2) {
		this.attributeValue2 = attributeValue2;
	}
	/**
	 * 获取会员注册项值3
	 * @return 会员注册项值3
	 */
	@Length(max = 200)
	public String getAttributeValue3() {
		return attributeValue3;
	}

	public void setAttributeValue3(String attributeValue3) {
		this.attributeValue3 = attributeValue3;
	}	/**
	 * 获取会员注册项值4
	 * @return 会员注册项值4
	 */
	@Length(max = 200)

	public String getAttributeValue4() {
		return attributeValue4;
	}

	public void setAttributeValue4(String attributeValue4) {
		this.attributeValue4 = attributeValue4;
	}
	/**
	 * 获取会员注册项值5
	 * @return 会员注册项值5
	 */
	@Length(max = 200)
	public String getAttributeValue5() {
		return attributeValue5;
	}

	public void setAttributeValue5(String attributeValue5) {
		this.attributeValue5 = attributeValue5;
	}
	/**
	 * 获取会员注册项值6
	 * @return 会员注册项值6
	 */
	@Length(max = 200)
	public String getAttributeValue6() {
		return attributeValue6;
	}

	public void setAttributeValue6(String attributeValue6) {
		this.attributeValue6 = attributeValue6;
	}
	/**
	 * 获取会员注册项值7
	 * @return 会员注册项值7
	 */
	@Length(max = 200)
	public String getAttributeValue7() {
		return attributeValue7;
	}

	public void setAttributeValue7(String attributeValue7) {
		this.attributeValue7 = attributeValue7;
	}
	/**
	 * 获取会员注册项值8
	 * @return 会员注册项值8
	 */
	@Length(max = 200)
	public String getAttributeValue8() {
		return attributeValue8;
	}

	public void setAttributeValue8(String attributeValue8) {
		this.attributeValue8 = attributeValue8;
	}
	/**
	 * 获取会员注册项值9
	 * @return 会员注册项值9
	 */
	@Length(max = 200)
	public String getAttributeValue9() {
		return attributeValue9;
	}

	public void setAttributeValue9(String attributeValue9) {
		this.attributeValue9 = attributeValue9;
	}
	
	/**
	 * 
	 * 方法说明: 获取供应商下配置的企销用户
	 * @return 企销用户列表
	 */
	@OneToMany(mappedBy = "supplier" ,fetch=FetchType.LAZY , cascade = CascadeType.REMOVE)
	public Set<SupplierCompanySale> getSupplierCompanySale() {
		return supplierCompanySale;
	}

	public void setSupplierCompanySale(Set<SupplierCompanySale> supplierCompanySale) {
		this.supplierCompanySale = supplierCompanySale;
	}
	/**
	 * 获取用户
	 * @return 用户
	 */
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "xc_supplier_member_reank_b")
//	public Set<Member> getMembers() {
//		return members;
//	}
//
//	public void setMembers(Set<Member> members) {
//		this.members = members;
//	}
	/**
	 * 获取用户等级
	 * @return 用户等级
	 */
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "xc_supplier_member_reank_b")
//	public Set<MemberRank> getMemberRanks() {
//		return memberRanks;
//	}
//
//	public void setMemberRanks(Set<MemberRank> memberRanks) {
//		this.memberRanks = memberRanks;
//	}

	/**
	 * 获取会员注册项值
	 * 
	 * @param memberAttribute
	 *            会员注册项
	 * @return 会员注册项值
	 */
	@Transient
	public Object getAttributeValue(SupplierAttribute supplierAttribute) {
		if (supplierAttribute != null) {
			if (supplierAttribute.getType() == Type.supplie) {
				return getSupplie();
			} else if (supplierAttribute.getType() == Type.fax) {
				return getFax();
			} else if (supplierAttribute.getType() == Type.zipCode) {
				return getZipCode();
			} else if (supplierAttribute.getType() == Type.activeStartDate) {
				return getActiveStartDate();
			} else if (supplierAttribute.getType() == Type.activeEndDate) {
				return getActiveEndDate();
			} else if (supplierAttribute.getType() == Type.bankDeposit) {
				return getBankDeposit();
			} else if (supplierAttribute.getType() == Type.bankName) {
				return getBankName();
			} else if (supplierAttribute.getType() == Type.bankAccount) {
				return getBankAccount();
			}  else if (supplierAttribute.getType() == Type.remark) {
				return getRemark();
			} else if (supplierAttribute.getType() == Type.checkbox) {
				if (supplierAttribute.getPropertyIndex() != null) {
					try {
						String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + supplierAttribute.getPropertyIndex();
						String propertyValue = (String) PropertyUtils.getProperty(this, propertyName);
						if (propertyValue != null) {
							return JsonUtils.toObject(propertyValue, List.class);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				}
			} else {
				if (supplierAttribute.getPropertyIndex() != null) {
					try {
						String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + supplierAttribute.getPropertyIndex();
						return (String) PropertyUtils.getProperty(this, propertyName);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	/**
	 * 设置会员注册项值
	 * 
	 * @param memberAttribute
	 *            会员注册项
	 * @param attributeValue
	 *            会员注册项值
	 */
	@Transient
	public void setAttributeValue(SupplierAttribute supplierAttribute, Object attributeValue) {
		if (supplierAttribute != null) {
			if (attributeValue instanceof String && StringUtils.isEmpty((String) attributeValue)) {
				attributeValue = null;
			}
			
			if (supplierAttribute.getType() == Type.supplie && (attributeValue instanceof Supplie || attributeValue == null)) {
				setSupplie((Supplie) attributeValue);
			} else if (supplierAttribute.getType() == Type.fax && (attributeValue instanceof String || attributeValue == null)) {
				setFax((String) attributeValue);
			} else if (supplierAttribute.getType() == Type.zipCode && (attributeValue instanceof String || attributeValue == null)) {
				setZipCode((String) attributeValue);
			} else if (supplierAttribute.getType() == Type.activeStartDate && (attributeValue instanceof Date || attributeValue == null)) {
				setActiveStartDate((Date) attributeValue);
			} else if (supplierAttribute.getType() == Type.activeEndDate && (attributeValue instanceof Date || attributeValue == null)) {
				setActiveEndDate((Date) attributeValue);
			} else if (supplierAttribute.getType() == Type.bankDeposit && (attributeValue instanceof String || attributeValue == null)) {
				setBankDeposit((String) attributeValue);
			} else if (supplierAttribute.getType() == Type.bankName && (attributeValue instanceof String || attributeValue == null)) {
				setBankName((String) attributeValue);
			} else if (supplierAttribute.getType() == Type.bankAccount && (attributeValue instanceof String || attributeValue == null)) {
				setBankAccount((String) attributeValue);
			} else if (supplierAttribute.getType() == Type.remark && (attributeValue instanceof String || attributeValue == null)) {
				setRemark((String) attributeValue);
			} else if (supplierAttribute.getType() == Type.checkbox && (attributeValue instanceof List || attributeValue == null)) {
				if (supplierAttribute.getPropertyIndex() != null) {
					if (attributeValue == null || (supplierAttribute.getOptions() != null && supplierAttribute.getOptions().containsAll((List<?>) attributeValue))) {
						try {
							String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + supplierAttribute.getPropertyIndex();
							PropertyUtils.setProperty(this, propertyName, JsonUtils.toJson(attributeValue));
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				if (supplierAttribute.getPropertyIndex() != null) {
					try {
						String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + supplierAttribute.getPropertyIndex();
						PropertyUtils.setProperty(this, propertyName, attributeValue);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * 移除所有会员注册项值
	 */
	@Transient
	public void removeAttributeValue() {
		setSupplie(null);
		setFax(null);
		setZipCode(null);
		setActiveStartDate(null);
		setActiveEndDate(null);
		setBankDeposit(null);
		setBankName(null);
		setBankAccount(null);
		setRemark(null);
		for (int i = 0; i < ATTRIBUTE_VALUE_PROPERTY_COUNT; i++) {
			String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + i;
			try {
				PropertyUtils.setProperty(this, propertyName, null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}
	/** 系统管理员 */
	private Set<Admin> admins = new HashSet<Admin>();
	/**
	 * 获取系统管理员
	 * @return 系统管理员
	 */
	@ManyToMany(mappedBy = "suppliers", fetch = FetchType.LAZY)
	public Set<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}
	
	/** 系统商品  */
	private Set<Product> products = new HashSet<Product>();
	/**
	 * 获取供应商对应的商品
	 * @return 商品
	 */
	@OneToMany(mappedBy = "supplier" ,fetch = FetchType.LAZY )
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	/** 订单  */
	private Set<Order> order = new HashSet<Order>();
	/**
	 * 获取供应商对应的订单
	 * @return 订单
	 */
	@OneToMany(mappedBy = "supplier" ,fetch = FetchType.LAZY )
	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

}
