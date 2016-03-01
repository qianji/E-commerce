/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import gov.guilin.entity.MemberAttribute;
import gov.guilin.service.MemberAttributeService;

/**
 * 模板指令 - 会员注册项列表
 * 
 * @author guilin
 * @version
 */
@Component("memberAttributeListDirective")
public class MemberAttributeListDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "memberAttributes";

	@Resource(name = "memberAttributeServiceImpl")
	private MemberAttributeService memberAttributeService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<MemberAttribute> memberAttributes;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		if (useCache) {
			memberAttributes = memberAttributeService.findList(cacheRegion);
		} else {
			memberAttributes = memberAttributeService.findList();
		}
		setLocalVariable(VARIABLE_NAME, memberAttributes, env, body);
	}

}