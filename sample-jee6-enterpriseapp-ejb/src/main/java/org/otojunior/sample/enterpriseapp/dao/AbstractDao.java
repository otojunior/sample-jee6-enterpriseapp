package org.otojunior.sample.enterpriseapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract DAO.
 * @author 01456231650
 */
public abstract class AbstractDao<T> implements IDao<T, Long> {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
	
	/**
	 * Injected Entity Manger.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @return the entityManager
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
