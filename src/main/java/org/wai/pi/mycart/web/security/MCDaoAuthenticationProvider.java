package org.wai.pi.mycart.web.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

//@Service
//@Qualifier("mycartDaoAuthenticationProvider")
public class MCDaoAuthenticationProvider extends DaoAuthenticationProvider {
	
	public MCDaoAuthenticationProvider(){
		super();
		System.out.println("MCDaoAuthenticationProvider bean created!! ");
	}
	
//	@Override
//	@Autowired
//	@Qualifier("passwordEncoder")
//	public void setPasswordEncoder(Object passwordEncoder) {
//		super.setPasswordEncoder(passwordEncoder);
//	}
//	
	@Override
	@Autowired
	@Qualifier("mycartUserDetailsService")
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("Came in MCAuthProvider...");
		super.authenticate(authentication);
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
 
        UserDetails user = getUserDetailsService().loadUserByUsername(username);
 
        if (user == null) {
        	System.out.println("throwing username not found exception");
            throw new BadCredentialsException("Username not found.");
        }
        System.out.println("PASSWORD1: "+password);
        System.out.println("PASSWORD2: "+user.getPassword());
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
 
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
 
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}	
}
