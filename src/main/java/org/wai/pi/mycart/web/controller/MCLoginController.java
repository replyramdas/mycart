package org.wai.pi.mycart.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wai.pi.mycart.web.model.UserLogin;

@Controller
@RequestMapping(value="/au")
public class MCLoginController {

	private static final Logger logger = LoggerFactory.getLogger(MCLoginController.class);
	
	@RequestMapping(value="/l", method = RequestMethod.GET)
	public ModelAndView  loginPage() {		
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject(new UserLogin());
		return modelAndView;
	}

	
	@RequestMapping(value="/s", method = RequestMethod.GET)
	public ModelAndView  signupPage() {
		ModelAndView modelAndView = new ModelAndView("signup");
		return modelAndView;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/au/l?logout";
	}

}
