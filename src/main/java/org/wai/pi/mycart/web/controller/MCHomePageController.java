package org.wai.pi.mycart.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/")
public class MCHomePageController {


	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showIndex() {
        return new ModelAndView("home");
    }	
	
	@RequestMapping(value="/pu/about")
	@ResponseBody
	public String getAboutPage(){
		return "<h1>About page for safe logic</h1>";
	}
	

	@RequestMapping(value="/pu/contact")
	@ResponseBody
	public String getContactPage(){
		return "<h1>Address of safe logic</h1>";
	}


	@RequestMapping(value="/pu/403")
	public ModelAndView getAccessDeniedPage(Principal user){
		ModelAndView model = new ModelAndView("accessdenied");
		String mesg = "Hi " + user.getName() + ", you dont have access to this page!!";
		model.addObject("message", mesg);
		return model;
	}

}
