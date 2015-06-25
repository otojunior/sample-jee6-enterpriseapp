package org.otojunior.sample.enterpriseapp.dao.customer;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.otojunior.sample.enterpriseapp.dao.AbstractDao;
import org.otojunior.sample.enterpriseapp.entity.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Customer Data Access Object pattern.
 * 
 * Obs.:This DAO class may have an ancestor generic DAO class with parametrized entity.
 * (not showed here in this example).
 * 
 * @author [Author name]
 */
@Stateless
public class CustomerDao extends AbstractDao<Customer> {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(CustomerDao.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Customer> findAll() {
		return getEntityManager().
			createNamedQuery(Customer.QUERY_FIND_ALL, Customer.class).
			getResultList();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer findById(Long id) {
		return getEntityManager().find(Customer.class, id);
	}

	/**
	 * Find a list of {@link Customer}
	 * @param name Customer's name.
	 * @param address Customer's address.
	 * @param city Customer's city.
	 * @return List of {@link Customer}
	 */
	public List<Customer> find(String name, String address, String city) {
		List<Customer> result = Collections.emptyList();
		
		if (StringUtils.isBlank(name) && StringUtils.isBlank(address) && StringUtils.isBlank(city)) {
			result = findAll();
		} else {
			boolean first = true;
			StringBuilder jpql = new StringBuilder("select p from Customer p where ");
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
			
			TypedQuery<Customer> query = getEntityManager().createQuery(jpql.toString(), Customer.class);
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
