package org.wai.pi.mycart.web.configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wai.pi.mycart.web.model.Role;
import org.wai.pi.mycart.web.model.SecurityQuestion;
/**
 * 
 * @author Ramdas Sawant
 * Description :- Temperory component to load data for testing purpose
 *
 */
@Component
@Transactional(readOnly = true)
public class MCLoadDataForTesting implements ApplicationListener<ContextRefreshedEvent>{
    
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("Invoked load Data !!");
	    Role adminRole = new Role();
	    adminRole.setName("ROLE_ADMIN");
	    adminRole.setDescription("For Admin tasks");

	    
	    Role authorRole = new Role();
	    authorRole.setName("ROLE_AUTHOR");
	    authorRole.setDescription("Creates Content");

	    Role userRole = new Role();
	    userRole.setName("ROLE_USER");
	    userRole.setDescription("User of the application");
	    
	    Role contributorRole = new Role();
	    contributorRole.setName("ROLE_CONTRIBUTOR");
	    contributorRole.setDescription("Contributor to the application");

	    em.persist(adminRole);
	    em.persist(authorRole);
	    em.persist(userRole);
	    em.persist(contributorRole);
	    
	    SecurityQuestion question1 = new SecurityQuestion();
	    question1.setQuestion("Which city you were born?");
	    
	    SecurityQuestion question2 = new SecurityQuestion();
	    question2.setQuestion("Name of first Employer?");
	    
	    
	    
	    em.persist(question1);
	    em.persist(question2);
	    
	}
}
