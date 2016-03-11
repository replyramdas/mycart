package org.wai.pi.mycart.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wai.pi.mycart.web.model.Role;
import org.wai.pi.mycart.web.model.SecurityQuestion;
import org.wai.pi.mycart.web.model.UserProfile;

@Repository
@Transactional(readOnly = true)
@Qualifier("userDao")
public class UserDAOImpl implements UserDAO {
	
    @PersistenceContext
    private EntityManager em;
	
    @Override
	public UserProfile createUserProfile(UserProfile profile) {
		em.persist(profile);
		return profile;
	}
	
//    @Override
//	public UserProfile getUserProfile(String userName) {
//			TypedQuery<UserProfile> query = em.createQuery("Select userProfile from UserProfile userProfile where userProfile.userLogin.username = :userName", UserProfile.class);
//			query.setParameter("userName", userName);
//			return query.getSingleResult();
//	}
	
	@Override
	public List<SecurityQuestion> getListOfQuestions() {
		return em.createQuery("Select secQuestion from SecurityQuestion secQuestion",SecurityQuestion.class).getResultList();
	}
	
	@Override
	public UserProfile getActiveUserProfile(String userName) throws EmptyResultDataAccessException{
		TypedQuery<UserProfile> query = em.createQuery("Select userProfile from UserProfile userProfile where userProfile.userLogin.enabled = true and userProfile.userLogin.username = :userName", UserProfile.class);
		query.setParameter("userName", userName);
		return query.getSingleResult();
	}
	
	@Override
	public List<Role> getAllRoles() {
		return em.createQuery("Select role from Role role",Role.class).getResultList();
	}
	
	@Override
	public Role getRoleOfUser(String userName) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public UserProfile getUserProfile(String userName, String accountName) {
		TypedQuery<UserProfile> query = em.createQuery("Select userProfile from UserProfile userProfile where userProfile.userLogin.username = :userName and userProfile.accountName = :accountName", UserProfile.class);
		query.setParameter("userName", userName);
		query.setParameter("accountName", accountName);
		return query.getSingleResult();
	}

}
