package org.wai.pi.mycart.web.dao;

import java.util.List;

import org.wai.pi.mycart.web.model.Role;
import org.wai.pi.mycart.web.model.SecurityQuestion;
import org.wai.pi.mycart.web.model.UserProfile;

public interface UserDAO {
	public UserProfile createUserProfile(UserProfile profile);
	public UserProfile updateUserProfile(UserProfile profile);
	public UserProfile getUserProfile(String userName, String companyCode);
	public List<SecurityQuestion> getListOfQuestions();
	public List<Role> getAllRoles();
	public Role getRoleOfUser(String userName);
}
