package org.otojunior.sample.enterpriseapp.dao.petowner;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.otojunior.sample.enterpriseapp.dao.AbstractDao;
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
public class PetOwnerDao extends AbstractDao<PetOwner> {
	private static final Logger LOG = LoggerFactory.getLogger(PetOwnerDao.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PetOwner> findAll() {
		return getEntityManager().
			createNamedQuery(PetOwner.QUERY_FIND_ALL, PetOwner.class).
			getResultList();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PetOwner findById(Long id) {
		return getEntityManager().find(PetOwner.class, id);
	}

	/**
	 * Find a list of {@link PetOwner}
	 * @param name PetOwner's name.
	 * @param address PetOwner's address.
	 * @param city PetOwner's city.
	 * @return List of {@link PetOwner}
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
			
			TypedQuery<PetOwner> query = getEntityManager().createQuery(jpql.toString(), PetOwner.class);
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
