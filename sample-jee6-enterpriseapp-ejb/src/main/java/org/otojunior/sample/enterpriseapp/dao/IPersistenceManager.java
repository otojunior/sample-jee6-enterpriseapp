package org.otojunior.sample.enterpriseapp.dao;

import javax.persistence.EntityManager;

/**
 * Persistence Manager Interface.
 * @author 01456231650
 *
 */
public interface IPersistenceManager {
	/**
	 * Get Entity Manager.
	 * @return {@link EntityManager}
	 */
	public EntityManager getEntityManager();
}
