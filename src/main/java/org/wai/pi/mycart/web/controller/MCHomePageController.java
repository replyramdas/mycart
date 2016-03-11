package org.wai.pi.mycart.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wai.pi.mycart.web.MCURIConstants;

@Controller
@RequestMapping(value=MCURIConstants.mycartHome)
public class MCHomePageController {


	@RequestMapping(value = MCURIConstants.mycartHome, method = RequestMethod.GET)
    public ModelAndView showIndex() {
        return new ModelAndView("home");
    }	
	
	@RequestMapping(value=MCURIConstants.mycartAbout)
	@ResponseBody
	public String getAboutPage(){
		return "<h1>About page for safe logic</h1>";
	}
	

	@RequestMapping(value=MCURIConstants.mycartContact)
	@ResponseBody
	public String getContactPage(){
		return "<h1>Address of safe logic</h1>";
	}


	@RequestMapping(value=MCURIConstants.accessDenied)
	public ModelAndView getAccessDeniedPage(Principal user){
		ModelAndView model = new ModelAndView("accessdenied");
		String mesg = "Hi " + user.getName() + ", you dont have access to this page!!";
		model.addObject("message", mesg);
		return model;
	}

}
