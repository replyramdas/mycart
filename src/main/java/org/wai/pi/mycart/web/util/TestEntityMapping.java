package org.wai.pi.mycart.web.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.wai.pi.mycart.web.model.Role;
import org.wai.pi.mycart.web.model.UserLogin;
import org.wai.pi.mycart.web.model.UserProfile;

public class TestEntityMapping {
	public static void main(String[] args) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("mycart-entity-mapping");
	    EntityManager em = emFactory.createEntityManager();
	    
	    UserLogin adminLogin = new UserLogin();
	    adminLogin.setUsername("rsawant");
	    adminLogin.setPassword("$2a$10$3587PkU.ZYPiSF95JlJL6uXVY/6L1RVCu7tq7Yu0IlzwVpyecXHAS");
	    adminLogin.setEnabled(true);

	    
	    UserLogin userLogin1 = new UserLogin();
	    userLogin1.setUsername("sashi");
	    userLogin1.setPassword("$2a$10$vt33gtp.OF0aKpzdL.MVG.8GLHsb2kn8w.QG8Hm5WogxHh9N.O/oy");
	    userLogin1.setEnabled(true);

	    UserLogin userLogin2 = new UserLogin();
	    userLogin2.setUsername("feroz");
	    userLogin2.setPassword("$2a$10$3nR5rFhhHeRd2Lc1x35.N.3IifYEpw047H/nJxNUUFWw.1M9dxfVy");
	    userLogin2.setEnabled(false);

	    
	    
	    Role adminRole = new Role();
	    adminRole.setName("ROLE_ADMIN");
	    adminRole.setDescription("For Admin tasks");

	    
	    Role authorRole = new Role();
	    authorRole.setName("ROLE_AUTHOR");
	    authorRole.setDescription("Creates Content");

	    Role userRole = new Role();
	    userRole.setName("ROLE_USER");
	    userRole.setDescription("User of the application");
	    
	    
	    UserProfile adminProfile = new UserProfile();
	    adminProfile.setUserLogin(adminLogin);;
	    adminProfile.setFirstName("Ramdas");
	    adminProfile.setLastName("Sawant");
	    adminProfile.setEmail("replyramdas@gmail.com");
	    adminProfile.setAccountName("mycomp");
	    adminProfile.setRole(adminRole);
	    
	    UserProfile authorProfile = new UserProfile();
	    authorProfile.setUserLogin(userLogin1);;
	    authorProfile.setFirstName("Sashi");
	    authorProfile.setLastName("LastName");
	    authorProfile.setEmail("sashi@gmail.com");
	    authorProfile.setAccountName("mycomp");
	    authorProfile.setRole(authorRole);

	    UserProfile userProfile = new UserProfile();
	    userProfile.setUserLogin(userLogin2);
	    userProfile.setRole(userRole);
	    
	    em.getTransaction().begin();

	    em.persist(authorRole);
	    em.persist(adminRole);
	    em.persist(userRole);

	    
	    
	    em.persist(adminProfile);
	    em.persist(authorProfile);
	    em.persist(userProfile);
	    
	    em.getTransaction().commit();
	    em.close();
	    emFactory.close();
	    
	}
}
