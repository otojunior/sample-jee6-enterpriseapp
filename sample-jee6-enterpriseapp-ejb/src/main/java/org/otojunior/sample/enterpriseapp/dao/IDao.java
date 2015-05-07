package org.otojunior.sample.enterpriseapp.dao;

import java.util.List;

/**
 * DAO generic interface.
 * @author 01456231650
 * 
 * @param <T> Entity to manage.
 */
public interface IDao<T, K> extends IPersistenceManager {
	/**
	 * Return a list of <T> 
	 * @return List of <T>
	 */
	public List<T> findAll();
	
	/**
	 * Find a T entity based on K primary key.
	 * @param id Entity Id.
	 * @return The Entity <T>
	 */
	public T findById(K id);
}
