package org.otojunior.sample.enterpriseapp.dao.petowner;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.otojunior.sample.enterpriseapp.entity.petowner.PetOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * PetOwner Data Access Object pattern.
 * 
 * Obs.:This DAO class may have an ancestor generic DAO class with parametrized entity.
 * (not showed here in this example).
 * 
 * @author [Author name]
 */
@Stateless
public class PetOwnerDao {
	private static final Logger LOG = LoggerFactory.getLogger(PetOwnerDao.class);
	
	/**
	 * Injected Entity Manger.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * find all Users.
	 * @return All users.
	 */
	public List<PetOwner> findAll() {
		return entityManager.
			createNamedQuery(PetOwner.QUERY_FIND_ALL, PetOwner.class).
			getResultList();
	}
	
	/**
	 * Find a Entity by id.
	 * @param id Entity ID.
	 * @return The User found (if any).
	 */
	public PetOwner findById(Long id) {
		return entityManager.find(PetOwner.class, id);
	}

	/**
	 * Find a user
	 * @param login
	 * @param name
	 * @return
	 */
	public List<PetOwner> find(String name, String address, String city) {
		List<PetOwner> result = Collections.emptyList();
		
		if (StringUtils.isBlank(name) && StringUtils.isBlank(address) && StringUtils.isBlank(city)) {
			result = findAll();
		} else {
			boolean first = true;
			StringBuilder jpql = new StringBuilder("select p from PetOwner p where ");
			if (StringUtils.isNotBlank(name)) {
				jpql.append("lower(p.name) like :_name");
				first = false;
			}
			if (StringUtils.isNotBlank(address)) {
				if (!first) {
					jpql.append(" and ");
				}
				jpql.append("lower(p.address.address) like :_address");
				first = false;
			}
			if (StringUtils.isNotBlank(city)) {
				if (!first) {
					jpql.append(" and ");
				}
				jpql.append("lower(p.address.city) like :_city");
				first = false;
			}
			
			TypedQuery<PetOwner> query = entityManager.createQuery(jpql.toString(), PetOwner.class);
			LOG.info("JPQL: " + jpql.toString());
			
			if (StringUtils.isNotBlank(name)) {
				query.setParameter("_name", "%"+name.toLowerCase()+"%");
				LOG.info("Parameter _name: " + name);
			}
			if (StringUtils.isNotBlank(address)) {
				query.setParameter("_address", "%"+address.toLowerCase()+"%");
				LOG.info("Parameter _address: " + address);
			}
			if (StringUtils.isNotBlank(city)) {
				query.setParameter("_city", "%"+city.toLowerCase()+"%");
				LOG.info("Parameter _city: " + city);
			}
			
			result = query.getResultList();
		}
		return result;
	}
}
