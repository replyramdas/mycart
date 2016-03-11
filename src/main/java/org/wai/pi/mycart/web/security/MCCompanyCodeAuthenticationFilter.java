package org.wai.pi.mycart.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.wai.pi.mycart.web.MCURIConstants;

public class MCCompanyCodeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static Logger logger = LoggerFactory.getLogger(MCCompanyCodeAuthenticationFilter.class);
	
	public MCCompanyCodeAuthenticationFilter() {
		
	}
	
    @Override
    protected String obtainUsername(HttpServletRequest request)
    {
        String userName = request.getParameter(MCURIConstants.userNameParamName);
        String companyCode = request.getParameter(MCURIConstants.companyCodeParamName);
        String combinedUsername = userName + ":" + companyCode;
        logger.info("Combined username:{} " , combinedUsername);
        return combinedUsername;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    		throws AuthenticationException {
    	// TODO Auto-generated method stub
    	return super.attemptAuthentication(request, response);
    }

}
