package org.wai.pi.mycart.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.wai.pi.mycart.web.MCURIConstants;

public class MCSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {

		if(authentication.getPrincipal() instanceof MCUser){
			MCUser userDetails = (MCUser)authentication.getPrincipal();
			if(userDetails.isFirstTimeLogin()){
				setDefaultTargetUrl(MCURIConstants.userChangePassword);
			}else {
				setDefaultTargetUrl(MCURIConstants.mycartAppUrl);
			}
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
