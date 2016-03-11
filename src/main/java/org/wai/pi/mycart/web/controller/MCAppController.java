package org.wai.pi.mycart.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wai.pi.mycart.web.MCURIConstants;

@Controller
@RequestMapping(value=MCURIConstants.mycartAppUrl)
public class MCAppController {

	
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAppView(Model model){
		model.addAttribute("myuser", getPrincipal());
		return "app";
	}
	
	
	
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
