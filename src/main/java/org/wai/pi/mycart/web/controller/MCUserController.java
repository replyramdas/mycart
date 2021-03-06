package org.wai.pi.mycart.web.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wai.pi.mycart.web.MCURIConstants;
import org.wai.pi.mycart.web.model.Role;
import org.wai.pi.mycart.web.model.UserLogin;
import org.wai.pi.mycart.web.model.UserProfile;
import org.wai.pi.mycart.web.security.MCUser;
import org.wai.pi.mycart.web.service.UserService;

@Controller
@RequestMapping(value=MCURIConstants.userBase)
public class MCUserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(params="form", method=RequestMethod.GET)
	public String createUserProfileForm(Model uiModel){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof MCUser){
			MCUser initiatingUser = (MCUser)principal;
			UserProfile profile = new UserProfile();
			UserLogin login = new UserLogin();
			login.setFirstTimeLogin(true);
			login.setCompanyCode(initiatingUser.getCompanyCode());
			profile.setUserLogin(login);
			Role role = new Role();
			profile.setRole(role);
			uiModel.addAttribute("profile",profile);
			//TODO: move below to complete profile page
			Map<Long, String> rolesMap = getRolesMap();
			uiModel.addAttribute("roles", rolesMap);
			uiModel.addAttribute("secQuestions", userService.getSecurityQuestion());

			return "user/create";
		}

		return "user/error";
	}
	
	private Map<Long, String> getRolesMap() {
		return userService.getAllRoles().stream().collect(Collectors.toMap(Role::getId, Role::getName));
	}
	
	@RequestMapping(params="form", method=RequestMethod.POST)
	public String createUserProfile(@ModelAttribute UserProfile profile, Model model){
		if(!userService.isUserNameUnique(profile.getUserLogin().getUsername(),profile.getUserLogin().getCompanyCode())){
			model.addAttribute("error", "User name is already taken. Please try a different user name");
			model.addAttribute("userCreateUrl", MCURIConstants.userCreate);
			return "user/error";
		}
		UserLogin login = profile.getUserLogin();
		login.setFirstTimeLogin(true);
		String encryptedPassword = passwordEncoder.encode(login.getPassword());
		login.setPassword(encryptedPassword);

		userService.createUserProfile(profile);
		model.addAttribute("usercreated", profile);
		return "user/success";
	}
	
	
}
