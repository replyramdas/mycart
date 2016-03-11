package org.wai.pi.mycart.web;

public final class MCURIConstants {

	public static final String mycartHome= "/";
	
	public static final String mycartAbout = "/pu/about";
	
	public static final String accessDenied = "/pu/403";
	
	public static final String mycartContact = "/pu/contact";
			
	/* accessed by users */
	public static final String mycartAppUrl = "/app";



	/* accessed by admins */
	public static final String  userBase = "/user";
	public static final String  userChangePassword = userBase+"?changepassword";
	
	public static final String  userCompleteProfile = userBase+"?completeprofile";	
	
	public static final String  userCreate = userBase+"?form";
	
	

	public static final String userNameParamName = "username";
	
	public static final String passwordParamName = "password";
	
	public static final String companyCodeParamName = "companycode";

	
	public static final String loginUrl = "/au/l";
	
	public static final String loginFailureUrl = "/au/l?error=true";
	
	public static final String loginProcessingUrl = "/j_spring_security_check";
	
	public static final String logoutSuccessUrl = "/au/l?logout";
	
	
	
	public static final String accessDeniedUrl = "/pu/403";
	
}
