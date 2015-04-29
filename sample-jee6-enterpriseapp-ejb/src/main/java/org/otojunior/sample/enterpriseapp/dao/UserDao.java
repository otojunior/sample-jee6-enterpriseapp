package org.otojunior.sample.enterpriseapp.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.otojunior.sample.enterpriseapp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * User Data Access Object pattern.
 * 
 * Obs.:This DAO class may have an ancestor generic DAO class with parametrized entity.
 * (not showed here in this example).
 * 
 * @author [Author name]
 */
@Stateless
public class UserDao {
	private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);
	
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

	/**
	 * Find a user
	 * @param login
	 * @param name
	 * @return
	 */
	public List<User> find(String login, String name) {
		List<User> result = Collections.emptyList();
		if (StringUtils.isBlank(login) && StringUtils.isBlank(name)) {
			result = findAll();
		} else {
			boolean primeiro = true;
			StringBuilder jpql = new StringBuilder("select new User(u.id, u.login, u.name) from User u where ");
			if (StringUtils.isNotBlank(login)) {
				jpql.append("u.login = :_login");
				primeiro = false;
			}
			if (StringUtils.isNotBlank(name)) {
				if (!primeiro) {
					jpql.append(" and ");
				}
				jpql.append("lower(u.name) like :_name");
			}
			
			TypedQuery<User> query = entityManager.createQuery(jpql.toString(), User.class);
			LOG.info("JPQL: " + jpql.toString());
			
			if (StringUtils.isNotBlank(login)) {
				query.setParameter("_login", login);
				LOG.info("Parameter _login: " + login);
			}
			if (StringUtils.isNotBlank(name)) {
				query.setParameter("_name", "%"+name.toLowerCase()+"%");
				LOG.info("Parameter _name: " + name);
			}
			
			result = query.getResultList();
		}
		return result;
	}
}
