/**
 * 
 */
package org.otojunior.sample.enterpriseapp.dao.support;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Populate a initial data in database to demonstrate the application.
 * @author 01456231650
 */
@Singleton
@Startup
public class PetOwnerPopulator {
	private static final Logger LOG = LoggerFactory.getLogger(PetOwnerPopulator.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void start() {
		LOG.info("PetOwnerPopulator:start");
	}
}
