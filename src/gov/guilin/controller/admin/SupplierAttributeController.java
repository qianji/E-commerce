package gov.guilin.controller.admin;

import gov.guilin.Message;
import gov.guilin.Pageable;
import gov.guilin.entity.BaseEntity.Save;
import gov.guilin.entity.SupplierAttribute.Type;
import gov.guilin.entity.MemberAttribute;
import gov.guilin.entity.Supplier;
import gov.guilin.entity.SupplierAttribute;
import gov.guilin.service.SupplierAttributeService;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("adminSupplierAttributeController")
@RequestMapping("/admin/supplier_attribute")
public class SupplierAttributeController extends BaseController {
	@Resource(name = "supplierAttributeServiceImpl")
	private SupplierAttributeService supplierAttributeService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", supplierAttributeService.findPage(pageable));
		return "/admin/supplier_attribute/list";
	}
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model, RedirectAttributes redirectAttributes) {
		if (supplierAttributeService.count() - 9 >= Supplier.ATTRIBUTE_VALUE_PROPERTY_COUNT) {
			addFlashMessage(redirectAttributes, Message.warn("admin.supplierAttribute.addCountNotAllowed", Supplier.ATTRIBUTE_VALUE_PROPERTY_COUNT));
		}
		return "/admin/supplier_attribute/add";
	}
	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SupplierAttribute supplierAttribute, RedirectAttributes redirectAttributes) {
		if (!isValid(supplierAttribute, Save.class)) {
			return ERROR_VIEW;
		}
		if (supplierAttribute.getType() == Type.select || supplierAttribute.getType() == Type.checkbox) {
			List<String> options = supplierAttribute.getOptions();
			if (options != null) {
				for (Iterator<String> iterator = options.iterator(); iterator.hasNext();) {
					String option = iterator.next();
					if (StringUtils.isEmpty(option)) {
						iterator.remove();
					}
				}
			}
			if (options == null || options.isEmpty()) {
				return ERROR_VIEW;
			}
		} else if (supplierAttribute.getType() == Type.text) {
			supplierAttribute.setOptions(null);
		} else {
			return ERROR_VIEW;
		}
		Integer propertyIndex = supplierAttributeService.findUnusedPropertyIndex();
		if (propertyIndex == null) {
			return ERROR_VIEW;
		}
		supplierAttribute.setPropertyIndex(propertyIndex);
		supplierAttributeService.save(supplierAttribute);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("supplierAttribute", supplierAttributeService.find(id));
		return "/admin/supplier_attribute/edit";
	}
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SupplierAttribute supplierAttribute, RedirectAttributes redirectAttributes) {
		if (!isValid(supplierAttribute)) {
			return ERROR_VIEW;
		}
		SupplierAttribute pSupplierAttribute = supplierAttributeService.find(supplierAttribute.getId());
		if (pSupplierAttribute == null) {
			return ERROR_VIEW;
		}
		if (pSupplierAttribute.getType() == Type.select || pSupplierAttribute.getType() == Type.checkbox) {
			List<String> options = supplierAttribute.getOptions();
			if (options != null) {
				for (Iterator<String> iterator = options.iterator(); iterator.hasNext();) {
					String option = iterator.next();
					if (StringUtils.isEmpty(option)) {
						iterator.remove();
					}
				}
			}
			if (options == null || options.isEmpty()) {
				return ERROR_VIEW;
			}
		} else {
			supplierAttribute.setOptions(null);
		}
		supplierAttributeService.update(supplierAttribute, "type", "propertyIndex");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		supplierAttributeService.delete(ids);
		return SUCCESS_MESSAGE;
	}
}
