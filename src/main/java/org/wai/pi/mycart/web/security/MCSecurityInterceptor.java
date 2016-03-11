package org.wai.pi.mycart.web.security;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.wai.pi.mycart.web.MCURIConstants;
import org.wai.pi.mycart.web.model.UserProfile;
import org.wai.pi.mycart.web.service.UserService;

@Component
@Qualifier("mcSecurityInterceptor")
public class MCSecurityInterceptor implements HandlerInterceptor {

	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof MCUser){
			MCUser userDetails = (MCUser)principal;
			UserProfile profile = userService.getUserProfile(userDetails.getUsername(), userDetails.getCompanyCode());
			if(profile.getUserLogin().isFirstTimeLogin()){
				//RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(MCURIConstants.userChangePassword);
				new DefaultRedirectStrategy().sendRedirect(request, response, MCURIConstants.userChangePassword);
			}else{
				System.out.println("---------------> logging in after change password");
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
