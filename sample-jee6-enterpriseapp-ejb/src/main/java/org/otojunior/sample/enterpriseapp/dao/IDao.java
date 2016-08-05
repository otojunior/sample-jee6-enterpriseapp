package org.otojunior.sample.enterpriseapp.dao;

import java.io.Serializable;
import java.util.List;

/**
 * DAO generic interface.
 *
 * @author Oto Junior (otojunior@gmail.com)
 * @param <T> Entity to manage.
 * @version $Id: $Id
 */
public interface IDao<T, K> extends IPersistenceManager, Serializable {
	/**
	 * Return a list of <T>
	 *
	 * @return List of <T>
	 */
	public List<T> findAll();
	
	/**
	 * Find a T entity based on K primary key.
	 *
	 * @param id Entity Id.
	 * @return The Entity <T>
	 */
	public T findById(K id);
}
