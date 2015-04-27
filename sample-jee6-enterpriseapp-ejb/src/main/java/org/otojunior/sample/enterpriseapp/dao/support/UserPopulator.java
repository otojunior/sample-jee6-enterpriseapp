/**
 * 
 */
package org.otojunior.sample.enterpriseapp.dao.support;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.otojunior.sample.enterpriseapp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Populate a initial data in database to demonstrate the application.
 * @author 01456231650
 */
@Singleton
@Startup
public class UserPopulator {
	private static final Logger LOG = LoggerFactory.getLogger(UserPopulator.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void start() {
		LOG.info("Populating User Records");
		for (int i = 1; i <= 9; i++) {
			User user = new User();
			user.setLogin("userlogin"+i);
			user.setName("User Example " + i);
			user.setPassword("passwd0"+i);
			em.persist(user);
			LOG.info("Saved record " + user.getLogin());
		}
	}
}
