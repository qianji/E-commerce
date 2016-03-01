/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.controller.admin;

import java.util.ArrayList;
import java.util.Set;

import gov.guilin.Message;
import gov.guilin.Page;
import gov.guilin.Pageable;
import gov.guilin.entity.Consultation;
import gov.guilin.entity.Review;
import gov.guilin.entity.Supplier;
import gov.guilin.entity.Review.Type;
import gov.guilin.service.AdminService;
import gov.guilin.service.ReviewService;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 评论
 * 
 * @author guilin
 * @version
 */
@Controller("adminReviewController")
@RequestMapping("/admin/review")
public class ReviewController extends BaseController {

	@Resource(name = "reviewServiceImpl")
	private ReviewService reviewService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("review", reviewService.find(id));
		return "/admin/review/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Long id, @RequestParam(defaultValue = "false") Boolean isShow, RedirectAttributes redirectAttributes) {
		Review review = reviewService.find(id);
		if (review == null) {
			return ERROR_VIEW;
		}
		review.setIsShow(isShow);
		reviewService.update(review);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Type type, Pageable pageable, ModelMap model) {
		model.addAttribute("type", type);
		model.addAttribute("types", Type.values());
		//Add by DanielChen 20140503
		Set<Supplier> suppliers = adminService.getCurrent().getSuppliers();
		if((suppliers !=null) && (!suppliers.isEmpty())){
			model.addAttribute("page", reviewService.findPage(null, null, type, null, pageable,suppliers));
		}else{
			model.addAttribute("page",  new Page<Review>(new ArrayList<Review>(),0,new Pageable(0,100)));
		}
		return "/admin/review/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		reviewService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}