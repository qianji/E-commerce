/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.controller.admin;

import gov.guilin.Template;
import gov.guilin.service.StaticService;
import gov.guilin.service.TemplateService;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - Sitemap
 * 
 * @author guilin
 * @version
 */
@Controller("adminSitemapController")
@RequestMapping("/admin/sitemap")
public class SitemapController extends BaseController {

	@Resource(name = "templateServiceImpl")
	private TemplateService templateService;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;

	/**
	 * 生成Sitemap
	 */
	@RequestMapping(value = "/build", method = RequestMethod.GET)
	public String build(ModelMap model) {
		Template sitemapIndexTemplate = templateService.get("sitemapIndex");
		model.addAttribute("sitemapIndexPath", sitemapIndexTemplate.getStaticPath());
		return "/admin/sitemap/build";
	}

	/**
	 * 生成Sitemap
	 */
	@RequestMapping(value = "/build", method = RequestMethod.POST)
	public String build(RedirectAttributes redirectAttributes) {
		staticService.buildSitemap();
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:build.jhtml";
	}

}