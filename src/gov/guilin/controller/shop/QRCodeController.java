package gov.guilin.controller.shop;

import gov.guilin.util.QRCodeUtils;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("qrCodeController")
@RequestMapping("/qrcode")
public class QRCodeController  extends BaseController{
	
	@Autowired
	private ServletContext context;
	
	/**
	 * eg:http://localhost:8080/guilin/qrcode/gen/123456.jhtml
	 * @param id
	 * @param pageNumber
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gen/{code}", method = RequestMethod.GET)
	public String generate(@PathVariable String code, ModelMap model) {
		String realPath=context.getRealPath("/");
		 String qrPath=QRCodeUtils.generate(realPath,code);
		 model.addAttribute("qrPath",qrPath);
		 return "/shop/qrcode/index";
	 }

}
