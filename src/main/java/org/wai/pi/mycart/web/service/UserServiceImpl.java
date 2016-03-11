package org.wai.pi.mycart.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.wai.pi.mycart.web.dao.UserDAO;
import org.wai.pi.mycart.web.model.Role;
import org.wai.pi.mycart.web.model.UserProfile;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	public UserServiceImpl() {
		
	}
	
	@Override
	public UserProfile createUserProfile(UserProfile profile) {
		return userDao.createUserProfile(profile);
	}

	@Override
	public boolean isUserNameUnique(String userName, String accountName) {
		UserProfile profile = null;
		try{
			profile = userDao.getUserProfile(userName,accountName);
		}catch(EmptyResultDataAccessException exp){
			logger.info("userName:{} doest not exist",userName);
		}
		return profile==null?true:false;
	}

	@Override
	public List<String> getSecurityQuestion() {
		return userDao.getListOfQuestions().stream().map(securityQuestion -> securityQuestion.getQuestion()).collect(Collectors.toList());
	}

//	@Override
//	public UserProfile getUserProfile(String userName) throws EmptyResultDataAccessException{
//			return userDao.getUserProfile(userName);
//	}

	@Override
	public UserProfile getActiveUserProfile(String username) {
		UserProfile profile = null;
		profile = userDao.getActiveUserProfile(username);
		return profile;
	}

	@Override
	public Role getUserRole(String userName) {
		//TODO: implement this
		throw new RuntimeException("Not yet implemented");

	}

	@Override
	public List<Role> getAllRoles() {
		return userDao.getAllRoles();
	}

	@Override
	public UserProfile getUserProfile(String userName, String accountName) {
		return userDao.getUserProfile(userName, accountName);
	}

}
