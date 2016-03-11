package org.wai.pi.mycart.web.service;

import java.util.List;

import org.springframework.security.access.event.PublicInvocationEvent;
import org.wai.pi.mycart.web.model.Role;
import org.wai.pi.mycart.web.model.UserProfile;

public interface UserService {
	public UserProfile createUserProfile(UserProfile profile);
	public UserProfile updateUserProfile(UserProfile profile);
	public UserProfile getUserProfile(String userName, String companyCode);
	public boolean isUserNameUnique(String userName,String companyCode);
	public List<String> getSecurityQuestion();
	public UserProfile getActiveUserProfile(String username);
	public Role getUserRole(String userName);
	public List<Role> getAllRoles();
}
