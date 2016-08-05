package org.otojunior.sample.enterpriseapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract DAO.
 *
 * @author 01456231650
 * @version $Id: $Id
 */
public abstract class AbstractDao<T> implements IDao<T, Long> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
	
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
