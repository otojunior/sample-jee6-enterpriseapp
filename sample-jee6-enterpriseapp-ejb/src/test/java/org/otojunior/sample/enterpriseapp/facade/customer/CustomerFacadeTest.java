package org.otojunior.sample.enterpriseapp.facade.customer;

import static org.junit.Assert.assertNull;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.otojunior.sample.enterpriseapp.entity.customer.Customer;
import org.otojunior.sample.enterpriseapp.service.customer.CustomerService;
import org.powermock.reflect.Whitebox;

public class CustomerFacadeTest {
	private CustomerFacade customerFacade;
	private CustomerService customerService;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void before() throws Exception {
		customerFacade = new CustomerFacade();
		customerService = EasyMock.createNiceMock(CustomerService.class);
		EasyMock.replay(customerService);
		Whitebox.setInternalState(customerFacade, CustomerService.class, customerService);
	}

	@After
	public void after() throws Exception {
		customerFacade = null;
		EasyMock.verify(customerService);
	}

	/**
	 * Find all test.
	 */
	@Test
	public void testFindAll() {
		List<Customer> found = customerFacade.findAll();
		assertNull(found);
	}

	/**
	 * Find by id.
	 */
	@Test
	public void testFindById() {
		Customer found = customerFacade.findById(1L);
		assertNull(found);
	}

	/**
	 * Find by name.
	 */
	@Test
	public void testFindByName() {
		List<Customer> found = customerFacade.find("St", null, null);
		assertNull(found);
	}
	
	/**
	 * Find by address.
	 */
	@Test
	public void testFindByAddress() {
		List<Customer> found = customerFacade.find(null, "2", null);
		assertNull(found);
	}
}
