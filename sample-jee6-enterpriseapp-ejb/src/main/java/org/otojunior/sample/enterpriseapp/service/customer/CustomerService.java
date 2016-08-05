/**
 * 
 */
package org.otojunior.sample.enterpriseapp.service.customer;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.otojunior.sample.enterpriseapp.dao.customer.CustomerDao;
import org.otojunior.sample.enterpriseapp.entity.customer.Customer;
import org.otojunior.sample.enterpriseapp.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Service for Customer.
 *
 * @author Oto Junior (otojunior@gmail.com)
 * @version $Id: $Id
 */
@Stateless
public class CustomerService extends AbstractService {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);
	
	@EJB
	private CustomerDao customerDao;

	/**
	 * Find all Customers.
	 *
	 * @return The customers list.
	 */
	public List<Customer> findAll() {
		LOG.trace("CustomerService::findAll");
		return customerDao.findAll();
	}
	
	/**
	 * Find a customer by id.
	 *
	 * @param id the Customer Id.
	 * @return the Customer found.
	 */
	public Customer findById(Long id) {
		LOG.trace("CustomerService::findById");
		return customerDao.findById(id);
	}
	
	/**
	 * Find a customer by name, address or city.
	 *
	 * @param name Name to find.
	 * @param address Address to find.
	 * @param city City to find.
	 * @return List of Customers.
	 */
	public List<Customer> find(String name, String address, String city) {
		LOG.trace("CustomerService::find");
		return customerDao.find(name, address, city);
	}
}
