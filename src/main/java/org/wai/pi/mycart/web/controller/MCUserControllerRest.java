package org.wai.pi.mycart.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wai.pi.mycart.web.MCURIConstants;
import org.wai.pi.mycart.web.service.UserService;

@RestController
@RequestMapping(value=MCURIConstants.userBase)
public class MCUserControllerRest {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/{companyCode}/{userName}", params={"isUnique"}, method=RequestMethod.GET)
	public String isUniqueName(@PathVariable String userName, @PathVariable String companyCode){
		
		return userService.isUserNameUnique(userName,companyCode)?"true":"false";
	}
}
