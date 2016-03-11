package org.wai.pi.mycart.web.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wai.pi.mycart.web.model.UserProfile;
import org.wai.pi.mycart.web.service.UserService;

@Service
@Qualifier("mycartUserDetailsService")
public class MCUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(MCUserDetailsService.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	public MCUserDetailsService() {
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String combinedUserName) throws UsernameNotFoundException {

		try{
			logger.debug("User Name in UserDetails Service: {}",combinedUserName);
			String userName = combinedUserName.split(":")[0];
			String accountName = combinedUserName.split(":")[1];			
			UserProfile profile = userService.getUserProfile(userName,accountName);
			if(!profile.getUserLogin().isEnabled()){
				throw new UsernameNotFoundException(messageSourceAccessor.getMessage("MCUserDetailsService.userIdDisabled",new Object[]{userName}));
			}
			GrantedAuthority authority = new SimpleGrantedAuthority(profile.getRole().getName());
			User userDetails = new User(profile.getUserLogin().getUsername(), profile.getUserLogin().getPassword(), Arrays.asList(authority));
			return userDetails;
		}catch(EmptyResultDataAccessException exp){
			throw new UsernameNotFoundException(messageSourceAccessor.getMessage("MCUserDetailsService.userIdInvalid"));
		}
	}
	
}
