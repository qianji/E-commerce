package gov.guilin.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("sampleController")
@RequestMapping("/hello")
public class SampleController extends BaseController{
	
	/**
	 * eg: http://localhost:8080/guilin/hello/view.jhtml?username=test
	 * @param username
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(String username, ModelMap model) {
	    model.addAttribute("name",  username);
	    return "shop/helloworld/view";
	}
}
