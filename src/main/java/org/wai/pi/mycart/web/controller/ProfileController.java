package org.wai.pi.mycart.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wai.pi.mycart.web.MCURIConstants;
import org.wai.pi.mycart.web.model.UserLogin;
import org.wai.pi.mycart.web.model.UserProfile;
import org.wai.pi.mycart.web.security.MCUser;
import org.wai.pi.mycart.web.service.UserService;

@Controller
@RequestMapping(value=MCURIConstants.profile)
public class ProfileController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@RequestMapping(params="changepassword", method=RequestMethod.GET)
	public String createChangePasswordForm(Model uiModel){
		MCUser currentUser = null;
		UserLogin userLogin = new UserLogin();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		if(principal instanceof MCUser){
			currentUser = (MCUser)principal;
			userLogin.setUsername(currentUser.getUsername());
			userLogin.setCompanyCode(currentUser.getCompanyCode());
		}
		uiModel.addAttribute("userLogin", userLogin);
		return "user/changepassword";
	}
	
	
	@RequestMapping(params="changepassword", method=RequestMethod.POST)
	public String processChangePassword(@ModelAttribute UserLogin userLogin, Model model){
		MCUser currentUser = null;
	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof MCUser){
			currentUser = (MCUser)principal;
		}
		if(currentUser != null){
			UserProfile profile = userService.getUserProfile(userLogin.getUsername(), currentUser.getCompanyCode());
			profile.getUserLogin().setPassword(userLogin.getPassword());
			profile.getUserLogin().setFirstTimeLogin(false);
			userService.updateUserProfile(profile);
		}		
		return "redirect:"+MCURIConstants.userCompleteProfile;
	}
	
	@RequestMapping(params="completeprofile", method=RequestMethod.GET)
	public String createCompleteProfileForm(Model uiModel){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		if(principal instanceof MCUser){
			MCUser  currentUser = (MCUser)principal;
			UserLogin userLogin = new UserLogin();
			userLogin.setUsername(currentUser.getUsername());
			userLogin.setCompanyCode(currentUser.getCompanyCode());
			UserProfile profile = userService.getUserProfile(currentUser.getUsername(), currentUser.getCompanyCode());
			uiModel.addAttribute("profile", profile);	
			uiModel.addAttribute("secQuestions", userService.getSecurityQuestion());
			return "user/completeprofile";
		}
		return "user/error";
	}
	
	@RequestMapping(params="completeprofile", method=RequestMethod.POST)
	public String updateProfileForm(@ModelAttribute UserProfile profile, Model model){
		
		System.out.println("Updating Profile: "+profile.getId()+" "+profile.getUserLogin().getUsername());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		if(principal instanceof MCUser){
			MCUser  currentUser = (MCUser)principal;
			UserProfile pUserProfile = userService.getUserProfile(currentUser.getUsername(), currentUser.getCompanyCode());
			pUserProfile.setFirstName(profile.getFirstName());
			pUserProfile.setLastName(profile.getLastName());
			pUserProfile.setSecurityQuestion(profile.getSecurityQuestion());
			pUserProfile.setSecurityAnswer(profile.getSecurityAnswer());
			userService.updateUserProfile(pUserProfile);
			return "redirect:"+MCURIConstants.mycartAppUrl;
		}
		return "user/error";
	}
}
