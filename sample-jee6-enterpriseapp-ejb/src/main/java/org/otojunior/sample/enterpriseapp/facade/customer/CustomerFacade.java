/**
 * 
 */
package org.otojunior.sample.enterpriseapp.facade.customer;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.otojunior.sample.enterpriseapp.entity.customer.Customer;
import org.otojunior.sample.enterpriseapp.facade.AbstractFacade;
import org.otojunior.sample.enterpriseapp.service.customer.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Customer Facade.
 * @author Oto Junior (otojunior@gmail.com)
 */
@Stateless
public class CustomerFacade extends AbstractFacade {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(CustomerFacade.class);
	
	@EJB
	private CustomerService customerService;
	
	/**
	 * Find all Customers.
	 * @return The customers list.
	 */
	public List<Customer> findAll() {
		LOG.trace("CustomerFacade::findAll");
		return customerService.findAll();
	}
	
	/**
	 * Find a customer by id.
	 * @param id the Customer Id.
	 * @return the Customer found.
	 */
	public Customer findById(Long id) {
		LOG.trace("CustomerFacade::findById");
		return customerService.findById(id);
	}
	
	/**
	 * Find a customer by name, address or city.
	 * @param name Name to find.
	 * @param address Address to find. 
	 * @param city City to find.
	 * @return List of Customers.
	 */
	public List<Customer> find(String name, String address, String city) {
		LOG.trace("CustomerFacade::find");
		return customerService.find(name, address, city);
	}
}
