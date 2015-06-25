package org.otojunior.sample.enterpriseapp.service.customer;

import static org.junit.Assert.assertNull;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.otojunior.sample.enterpriseapp.dao.customer.CustomerDao;
import org.otojunior.sample.enterpriseapp.entity.customer.Customer;
import org.powermock.reflect.Whitebox;

/**
 * CustomerService test class.
 * @author Oto Junior (otojunior@gmail.com)
 */
public class CustomerServiceTest {
	private CustomerService customerService;
	private CustomerDao customerDao;
	
	/**
	 * Before method.
	 */
	@Before
	public void before() {
		customerService = new CustomerService();
		customerDao = EasyMock.createNiceMock(CustomerDao.class);
		EasyMock.replay(customerDao);
		Whitebox.setInternalState(customerService, CustomerDao.class, customerDao);
	}
	
	/**
	 * After method.
	 */
	@After
	public void after() {
		customerService = null;
		EasyMock.verify(customerDao);
	}
	
	/**
	 * Find all test.
	 */
	@Test
	public void testFindAll() {
		List<Customer> found = customerService.findAll();
		assertNull(found);
	}

	/**
	 * Find by id.
	 */
	@Test
	public void testFindById() {
		Customer found = customerService.findById(1L);
		assertNull(found);
	}

	/**
	 * Find by name.
	 */
	@Test
	public void testFindByName() {
		List<Customer> found = customerService.find("St", null, null);
		assertNull(found);
	}
	
	/**
	 * Find by address.
	 */
	@Test
	public void testFindByAddress() {
		List<Customer> found = customerService.find(null, "2", null);
		assertNull(found);
	}
}
