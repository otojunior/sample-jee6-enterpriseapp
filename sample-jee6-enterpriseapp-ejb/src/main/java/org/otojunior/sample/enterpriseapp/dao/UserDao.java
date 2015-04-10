package org.otojunior.sample.enterpriseapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.otojunior.sample.enterpriseapp.entity.User;



/**
 * User Data Access Object pattern.
 * 
 * Obs.:This DAO class may have an ancestor generic DAO class with parametrized entity.
 * (not showed here in this example).
 * 
 * @author [Author name]
 */
public class UserDao {
	/**
	 * Injected Entity Manger.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * find all Users.
	 * @return All users.
	 */
	public List<User> findAll() {
		return entityManager.
			createNamedQuery(User.QUERY_FIND_ALL, User.class).
			getResultList();
	}
	
	/**
	 * find all Users with login specified.
	 * @param login Login filter condition.
4	 * @return The Users found by condition.
	 */
	public List<User> findByLogin(String login) {
		TypedQuery<User> query = entityManager.createNamedQuery(User.QUERY_FIND_BY_LOGIN, User.class);
		query.setParameter("_login", login);
		return query.getResultList();
	}
	
	/**
	 * Find a Entity by id.
	 * @param id Entity ID.
	 * @return The User found (if any).
	 */
	public User findById(Long id) {
		return entityManager.find(User.class, id);
	}
}
