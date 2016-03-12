package org.wai.pi.mycart.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping(value = MCURIConstants.profile)
public class ProfileController {

	Logger logger = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@RequestMapping(params = "changepassword", method = RequestMethod.GET)
	public String createChangePasswordForm(@AuthenticationPrincipal MCUser currentUser, Model uiModel) {

		logger.debug("currentUser: {}", currentUser);
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(currentUser.getUsername());
		userLogin.setCompanyCode(currentUser.getCompanyCode());
		uiModel.addAttribute("userLogin", userLogin);
		return "user/changepassword";
	}

	@RequestMapping(params = "changepassword", method = RequestMethod.POST)
	public String processChangePassword(@AuthenticationPrincipal MCUser currentUser,
			@ModelAttribute UserLogin userLogin, Model model) {

		UserProfile profile = userService.getUserProfile(userLogin.getUsername(), currentUser.getCompanyCode());
		profile.getUserLogin().setPassword(userLogin.getPassword());
		profile.getUserLogin().setFirstTimeLogin(false);
		userService.updateUserProfile(profile);
		return "redirect:" + MCURIConstants.userCompleteProfile;
	}

	@RequestMapping(params = "completeprofile", method = RequestMethod.GET)
	public String createCompleteProfileForm(@AuthenticationPrincipal MCUser currentUser, Model uiModel) {

		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(currentUser.getUsername());
		userLogin.setCompanyCode(currentUser.getCompanyCode());
		UserProfile profile = userService.getUserProfile(currentUser.getUsername(), currentUser.getCompanyCode());
		uiModel.addAttribute("profile", profile);
		uiModel.addAttribute("secQuestions", userService.getSecurityQuestion());
		return "user/completeprofile";
	}

	@RequestMapping(params = "completeprofile", method = RequestMethod.POST)
	public String updateProfileForm(@AuthenticationPrincipal MCUser currentUser, @ModelAttribute UserProfile profile,
			Model model) {

		UserProfile pUserProfile = userService.getUserProfile(currentUser.getUsername(), currentUser.getCompanyCode());
		pUserProfile.setFirstName(profile.getFirstName());
		pUserProfile.setLastName(profile.getLastName());
		pUserProfile.setSecurityQuestion(profile.getSecurityQuestion());
		pUserProfile.setSecurityAnswer(profile.getSecurityAnswer());
		userService.updateUserProfile(pUserProfile);
		return "redirect:" + MCURIConstants.mycartAppUrl;
	}
}
