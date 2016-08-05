package org.otojunior.sample.enterpriseapp.dao;

import javax.persistence.EntityManager;

/**
 * Persistence Manager Interface.
 *
 * @author 01456231650
 * @version $Id: $Id
 */
public interface IPersistenceManager {
	/**
	 * Get Entity Manager.
	 *
	 * @return {@link javax.persistence.EntityManager}
	 */
	public EntityManager getEntityManager();
}
