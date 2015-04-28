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

		User user = new User();
		user.setLogin("mahoney");
		user.setName("Carey Mahoney");
		user.setPassword("passwd01");
		em.persist(user);
		LOG.info("Saved record " + user.getLogin());
		
		user = new User();
		user.setLogin("jones");
		user.setName("Larvelle Jones");
		user.setPassword("passwd02");
		em.persist(user);
		LOG.info("Saved record " + user.getLogin());
		
		user = new User();
		user.setLogin("hightower");
		user.setName("Moses Hightower");
		user.setPassword("passwd03");
		em.persist(user);
		LOG.info("Saved record " + user.getLogin());
		
		user = new User();
		user.setLogin("tackleberry");
		user.setName("Eugene Tackleberry");
		user.setPassword("passwd04");
		em.persist(user);
		LOG.info("Saved record " + user.getLogin());
		
		user = new User();
		user.setLogin("house");
		user.setName("Thomas House Conklin");
		user.setPassword("passwd05");
		em.persist(user);
		LOG.info("Saved record " + user.getLogin());
		
		user = new User();
		user.setLogin("hooks");
		user.setName("Laverne Hooks");
		user.setPassword("passwd06");
		em.persist(user);
		LOG.info("Saved record " + user.getLogin());
		
		user = new User();
		user.setLogin("callahan");
		user.setName("Debbie Callahan");
		user.setPassword("passwd07");
		em.persist(user);
		LOG.info("Saved record " + user.getLogin());
	}
}
