package org.wai.pi.mycart.web.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.wai.pi.mycart.web.MCURIConstants;
import org.wai.pi.mycart.web.security.MCCompanyCodeAuthenticationFilter;
import org.wai.pi.mycart.web.security.MCSuccessHandler;

@Configuration
@EnableWebSecurity
public class MCSecuritySetupConfig extends WebSecurityConfigurerAdapter{
	
	
	private static final Logger logger = LoggerFactory.getLogger(MCSecuritySetupConfig.class);
	
	@Autowired
	@Qualifier("mycartAuthProvider")
	private AuthenticationProvider mycartAuthProvider;
	
	@Autowired
	@Qualifier("mycartUserDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, 
								AuthenticationProvider provider) throws Exception {

	    auth.authenticationProvider(daoAuthenticationProvider());
	    auth.authenticationProvider(mycartAuthProvider);
	    
	    
	    
	}	
	private InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryConfigurer() {
		return new InMemoryUserDetailsManagerConfigurer<>();
	}	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
        .addFilterBefore(getFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(MCURIConstants.mycartAppUrl+"/**").access("hasRole('USER') OR hasRole('AUTHOR') OR hasRole('ADMIN')")
        .antMatchers(MCURIConstants.userBase+"/**").access("hasRole('ADMIN')")
        .and()
        	.formLogin()
        	.loginPage(MCURIConstants.loginUrl)
        	.loginProcessingUrl(MCURIConstants.loginProcessingUrl)
        	.defaultSuccessUrl(MCURIConstants.mycartAppUrl)
        	.failureUrl(MCURIConstants.loginFailureUrl)
        	.usernameParameter(MCURIConstants.userNameParamName)
        	.passwordParameter(MCURIConstants.passwordParamName)
        .and()
        	.logout().logoutSuccessUrl(MCURIConstants.logoutSuccessUrl)
        .and()
        	.exceptionHandling().accessDeniedPage(MCURIConstants.accessDeniedUrl);
        
        	
	}
    
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean(name="mycartAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean(){
    	try{
    		return super.authenticationManagerBean();
    	}catch(Exception exp){
    		logger.error("Problem creating auth manager!!", exp);
    		throw new RuntimeException("Problem creating auth manager!!");
    	}
    }
    
    @Bean
    public UsernamePasswordAuthenticationFilter getFilter(){
    	
    	UsernamePasswordAuthenticationFilter filter = new MCCompanyCodeAuthenticationFilter();
    	filter.setAuthenticationManager(authenticationManagerBean());
    	filter.setFilterProcessesUrl(MCURIConstants.loginProcessingUrl);
    	
    	SimpleUrlAuthenticationSuccessHandler successHandler = new MCSuccessHandler();
    	successHandler.setDefaultTargetUrl(MCURIConstants.mycartAppUrl);
    	filter.setAuthenticationSuccessHandler(successHandler);
    	
    	SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
    	failureHandler.setDefaultFailureUrl(MCURIConstants.loginFailureUrl);
    	filter.setAuthenticationFailureHandler(failureHandler);
    	return filter;
    }
}
