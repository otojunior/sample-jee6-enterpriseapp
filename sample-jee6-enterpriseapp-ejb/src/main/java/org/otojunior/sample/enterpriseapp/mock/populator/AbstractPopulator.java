/**
 * 
 */
package org.otojunior.sample.enterpriseapp.mock.populator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Abstract AbstractPopulator class.</p>
 *
 * @author 01456231650
 * @version $Id: $Id
 */
public abstract class AbstractPopulator implements IPopulator {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(AbstractPopulator.class);
	
	/**
	 * Injected Entity Manger.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/** {@inheritDoc} */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
