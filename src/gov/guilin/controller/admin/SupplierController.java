package gov.guilin.controller.admin;

import gov.guilin.CommonAttributes;
import gov.guilin.Message;
import gov.guilin.Page;
import gov.guilin.Pageable;
import gov.guilin.Setting;
import gov.guilin.entity.Admin;
import gov.guilin.entity.BaseEntity.Save;
import gov.guilin.entity.Member;
import gov.guilin.entity.MemberRank;
import gov.guilin.entity.Supplier;
import gov.guilin.entity.Supplier.Supplie;
import gov.guilin.entity.SupplierAttribute;
import gov.guilin.entity.SupplierAttribute.Type;
import gov.guilin.entity.SupplierCompanySale;
import gov.guilin.service.AdminService;
import gov.guilin.service.MemberRankService;
import gov.guilin.service.MemberService;
import gov.guilin.service.SupplierAttributeService;
import gov.guilin.service.SupplierCompanySaleService;
import gov.guilin.service.SupplierService;
import gov.guilin.util.SettingUtils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("adminSupplierController")
@RequestMapping("/admin/supplier")
public class SupplierController extends BaseController {
	@Resource(name = "supplierServiceImpl")
	private SupplierService supplierService;
	@Resource(name = "supplierAttributeServiceImpl")
	private SupplierAttributeService supplierAttributeService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "memberRankServiceImpl")
	private MemberRankService memberRankService;
	@Resource(name = "supplierCompanySaleServiceImpl")
	private SupplierCompanySaleService supplierCompanySaleService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		Page<Supplier> supplier = supplierService.findPage(pageable);
		model.addAttribute("page", supplier);
		return "/admin/supplier/list";
	}
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("supplierType", Supplie.values());
		List<SupplierAttribute> attrs = supplierAttributeService.findList() ;
		model.addAttribute("supplierAttributes", supplierAttributeService.findList());
		return "/admin/supplier/add";
	}
	/**
	 * 检查用户名是否被禁用或已存在
	 */
	@RequestMapping(value = "/check_username", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkUsername(String supplierCode) {
		if (StringUtils.isEmpty(supplierCode)) {
			return false;
		}
		if (supplierService.usernameDisabled(supplierCode) || supplierService.usernameExists(supplierCode)) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 检查E-mail是否唯一
	 */
	@RequestMapping(value = "/check_email", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkEmail(String previousEmail, String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		if (supplierService.emailUnique(previousEmail, email)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 方法说明: 验证保存的商家信息是否正确
	 * @param supplier 商家信息
	 * @return
	 */
	private boolean validateSave(Supplier supplier){
		boolean flag = true ;
		if (!isValid(supplier, Save.class)) {
			return false ;
		}
		Setting setting = SettingUtils.get();
		if (supplier.getSupplierCode().length() < setting.getUsernameMinLength() || supplier.getSupplierCode().length() > setting.getUsernameMaxLength()) {
			return false ;
		}

		if (supplierService.usernameDisabled(supplier.getSupplierCode()) || supplierService.usernameExists(supplier.getSupplierCode())) {
			return false ;
		}
		if (!setting.getIsDuplicateEmail() && supplierService.emailExists(supplier.getEmail())) {
			return false ;
		}
		return flag ;
	}
	/**
	 * 方法说明:
	 * @param supplier 封装要保存的商家信息
	 * @param request 请求
	 * @param operator 操作人
	 * @return 操作信息
	 */
	private boolean convertSupplier(Supplier supplier , HttpServletRequest request ,String operator){
		boolean flag = true ;
		
		//清空商家注册字段并重新赋值
		supplier.removeAttributeValue();
		for (SupplierAttribute supplierAttribute : supplierAttributeService.findList()) {
			String parameter = request.getParameter("supplierAttribute_" + supplierAttribute.getId());
			if (supplierAttribute.getType() == Type.fax || supplierAttribute.getType() == Type.zipCode ||supplierAttribute.getType() == Type.bankDeposit ||supplierAttribute.getType() == Type.remark  
				|| supplierAttribute.getType() == Type.bankName || supplierAttribute.getType() == Type.bankAccount || supplierAttribute.getType() == Type.text || supplierAttribute.getType() == Type.select) {
				if (supplierAttribute.getIsRequired() && StringUtils.isEmpty(parameter)) {
					return false;
				}
				supplier.setAttributeValue(supplierAttribute, parameter);
			} else if (supplierAttribute.getType() == Type.supplie) {
				Supplie supplie = StringUtils.isNotEmpty(parameter) ? Supplie.valueOf(parameter) : null;
				if (supplierAttribute.getIsRequired() && supplie == null) {
					return false;
				}
				supplier.setSupplie(supplie);
			} else if (supplierAttribute.getType() == Type.activeStartDate ) {
				try {
					Date activeStartDate = StringUtils.isNotEmpty(parameter) ? DateUtils.parseDate(parameter, CommonAttributes.DATE_PATTERNS) : null;
					if (supplierAttribute.getIsRequired() && activeStartDate == null) {
						return false;
					}
					supplier.setActiveStartDate(activeStartDate);
				} catch (ParseException e) {
					return false;
				}
			}else if (supplierAttribute.getType() == Type.activeEndDate ) {
				try {
					Date activeEndDate = StringUtils.isNotEmpty(parameter) ? DateUtils.parseDate(parameter, CommonAttributes.DATE_PATTERNS) : null;
					if (supplierAttribute.getIsRequired() && activeEndDate == null) {
						return false;
					}
					supplier.setActiveEndDate(activeEndDate);
				} catch (ParseException e) {
					return false;
				}
			} else if (supplierAttribute.getType() == Type.checkbox) {
				String[] parameterValues = request.getParameterValues("supplierAttribute_" + supplierAttribute.getId());
				List<String> options = parameterValues != null ? Arrays.asList(parameterValues) : null;
				if (supplierAttribute.getIsRequired() && (options == null || options.isEmpty())) {
					return false;
				}
				supplier.setAttributeValue(supplierAttribute, options);
			}
		}
		
		return flag ;
		
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Supplier supplier, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Admin admin = adminService.getCurrent();
		//1:验证保持数据
		if(!validateSave(supplier)){
			return ERROR_VIEW;
		}
		//2:验证并构造商家信息
		if(!convertSupplier(supplier, request, admin.getName())){
			return ERROR_VIEW;
		}
		supplier.setSupplierCode(supplier.getSupplierCode().toLowerCase());
		supplier.setIsLocked(false);
		supplier.setLockedDate(null);
		supplier.setLoginIp(request.getRemoteAddr());
		supplier.setCreateDate(new Date());
		supplier.setCreateBy(admin.getName());
		
		//3:保存商家信息
		supplierService.save(supplier, admin.getName());
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		return "redirect:list.jhtml";
	}
	/**
	 * 查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("supplierType", Supplie.values());
		model.addAttribute("supplierAttributes", supplierAttributeService.findList());
		model.addAttribute("supplier", supplierService.find(id));
		
		return "/admin/supplier/view";
	}
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("supplierType", Supplie.values());
		model.addAttribute("supplierAttributes", supplierAttributeService.findList());
		model.addAttribute("supplier", supplierService.find(id));
		return "/admin/supplier/edit";
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Supplier supplier, Integer modifyPoint, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Admin admin = adminService.getCurrent();
		Supplier pSupplier = supplierService.find(supplier.getId());
		//1:校验更新信息是否正确
		if (!isValid(supplier)) {
			return ERROR_VIEW;
		}
		Setting setting = SettingUtils.get();
		if (!setting.getIsDuplicateEmail() && !supplierService.emailUnique(pSupplier.getEmail(), supplier.getEmail())) {
			return ERROR_VIEW;
		}

		//2：验证并构造商家信息
		if(!convertSupplier(supplier, request, admin.getName())){
			return ERROR_VIEW;
		}
		if (pSupplier.getIsLocked() && !supplier.getIsLocked()) {
			supplier.setLockedDate(null);
		} else {
			supplier.setIsLocked(pSupplier.getIsLocked());
			supplier.setLockedDate(pSupplier.getLockedDate());
		}
		
		//3：更新供应商
		supplier.setLoginIp(request.getRemoteAddr());
		supplier.setModifyDate(new Date());
		supplier.setModifyBy(admin.getName());
		BeanUtils.copyProperties(supplier, pSupplier, new String[] { "supplierCode", "department", "createBy", "createDate" });
		supplierService.update(supplier, admin.getName());
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids != null) {
//			for (Long id : ids) {
//				Supplier supplier = supplierService.find(id);
//				if (supplier != null && member.getBalance().compareTo(new BigDecimal(0)) > 0) {
//					return Message.error("admin.member.deleteExistDepositNotAllowed", member.getUsername());
//				}
//			}
			supplierService.delete(ids);
		}
		return SUCCESS_MESSAGE;
	}
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list_company_sales", method = RequestMethod.GET)
	public String listCompanySales(Pageable pageable, ModelMap model) {
		Page<Supplier> supplier = supplierService.findPage(pageable);
//		for(Supplier s : supplier.getContent()){
//			Set<SupplierCompanySale> t = s.getSupplierCompanySale();
//			System.out.println(s.getSupplierCode()+"----"+t.size());
//			for(SupplierCompanySale y : t){
//				System.out.println(y.getUsername()+"---"+y.getMemberRank().getName()+"----"+y.getSupplier().getSupplierCode());
//			}
//		}
		model.addAttribute("page", supplier);
		return "/admin/supplier_company/list";
	}
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit_supplier_company", method = RequestMethod.GET)
	public String editSupplierCompany(Long id, ModelMap model) {
		model.addAttribute("supplier", supplierService.find(id));
		model.addAttribute("memberRanks", memberRankService.findAll());
		return "/admin/supplier_company/edit";
	}
	/**
	 * 查看
	 */
	@RequestMapping(value = "/view_supplier_company", method = RequestMethod.GET)
	public String viewSupplierCompany(Long id, ModelMap model) {
		model.addAttribute("supplierType", Supplie.values());
		model.addAttribute("supplierAttributes", supplierAttributeService.findList());
		model.addAttribute("supplier", supplierService.find(id));
		model.addAttribute("memberRanks", memberRankService.findAll());
		return "/admin/supplier_company/view";
	}
	/**
	 * 保存
	 */
	@RequestMapping(value = "/update_supplier_company", method = RequestMethod.POST)
	public String updateSupplierCompany(Supplier supplier, Long[] memberRankId, String[] userName ,RedirectAttributes redirectAttributes) {
		//1:校验数据是否合法
		if(supplier==null || (memberRankId==null) || (userName==null) ){
			return ERROR_VIEW ;
		}
		Supplier pSupplier = supplierService.find(supplier.getId());
		if(pSupplier==null){
			return ERROR_VIEW ;
		}
		if( (memberRankId.length != userName.length) ){
			return ERROR_VIEW ;
		}
		if(hasRepeat(userName)){	//同一商家下配置相同的企销用户
			addFlashMessage(redirectAttributes, Message.warn("admin.supplierCompany.exits", supplier.getSupplierName()));
			return ERROR_VIEW ;
		}
		
		//2.1: 保存前将原先的全部删除
		supplierCompanySaleService.deleteScsBySupplier(supplier);
		//2.2: 构造保存对象
		for(int i = 0 ; i< memberRankId.length; i++){
			SupplierCompanySale scs = new SupplierCompanySale();
			Member me = memberService.findByUsername(userName[i]);
			MemberRank mr = memberRankService.find(memberRankId[i]);
			if( (me==null) || (mr==null)){
				continue; //任何一个不存在跳过
			}
			boolean exits = supplierCompanySaleService.existScsByUserNameAndMemberRank(me, mr);
			if(!exits){
				scs.setSupplier(pSupplier);
				scs.setMemberRank(mr);
				scs.setMember(me);
				//3: 保存数据
				supplierCompanySaleService.save(scs);
			}
		}
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list_company_sales.jhtml";
	}
	@RequestMapping(value = "/list_admin", method = RequestMethod.GET)
	public String listAdmin(Pageable pageable, ModelMap model) {

		model.addAttribute("page",  adminService.findPage(pageable));
		return "/admin/supplier_admin/list";
	}
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit_supplier_admin", method = RequestMethod.GET)
	public String editSupplierAdmin(Long id, ModelMap model) {
		model.addAttribute("admin", adminService.find(id));
		return "/admin/supplier_admin/edit";
	}
	/**
	 * 查看
	 */
	@RequestMapping(value = "/view_supplier_admin", method = RequestMethod.GET)
	public String viewSupplierAdmin(Long id, ModelMap model) {
		model.addAttribute("supplierType", Supplie.values());
		model.addAttribute("supplierAttributes", supplierAttributeService.findList());
		model.addAttribute("admin", adminService.find(id));
		return "/admin/supplier_admin/view";
	}
	/**
	 * 保存
	 */
	@RequestMapping(value = "/update_supplier_admin", method = RequestMethod.POST)
	public String updateSupplierAdmin(Admin admin, String[] supplierCode ,RedirectAttributes redirectAttributes) {
		//1:判断管理员是否存在
		if(null == admin ){
			return ERROR_VIEW;
		}
		Admin pAdmin = adminService.find(admin.getId());
		if( null == pAdmin){
			return ERROR_VIEW;
		}
		
		//2：根据商家编码获取商家列表
		if( (null!=supplierCode) && supplierCode.length > 0 ){
			Set<Supplier> suppliers = new HashSet<Supplier>();
			for(String code : supplierCode){
				Supplier supplier = supplierService.findSupplierByCode(code);
				if( null!= supplier){
					suppliers.add(supplier);
				}
			}
			//3: 如果配置则保存管理员与商家关系
			if(!suppliers.isEmpty()){
				pAdmin.setSuppliers(suppliers);
				adminService.update(pAdmin);
			}
		}
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list_admin.jhtml";
	}
	/** 
	 * 判断数组内有无重复元素 
	 * @param args 
	 * @return true 有重复 | false 无重复 
	 */  
	private boolean hasRepeat(Object[] args){  
	    Set<Object> tempSet = new HashSet<Object>();  
	    for (int i = 0; i < args.length; i++) {  
	        tempSet.add(args[i]);  
	    }  
	    if(args.length == tempSet.size()){  
	        return false;  
	    }else{  
	        return true;  
	    }  
	} 
	
}
