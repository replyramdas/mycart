package org.wai.pi.mycart.web;

public final class MCURIConstants {
	
	public static final String  userBase = "/app/u";
	
	public static final String  userCreate = userBase+"?form";
	
	public static final String loginUrl = "/au/l";
	
	public static final String loginFailureUrl = "/au/l?error=true";
	
	public static final String loginProcessingUrl = "/j_spring_security_check";
	
	public static final String logoutSuccessUrl = "/au/l?logout";
	
	public static final String mycartAppUrl = "/app";
	
	public static final String accessDeniedUrl = "/pu/403";
	
	public static final String userNameParamName = "username";
	
	public static final String passwordParamName = "password";
	
	public static final String companyCodeParamName = "companycode";
	
	
	
	
}
