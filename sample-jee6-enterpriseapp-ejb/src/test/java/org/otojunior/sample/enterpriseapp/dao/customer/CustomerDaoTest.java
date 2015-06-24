package org.otojunior.sample.enterpriseapp.dao.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.otojunior.sample.enterpriseapp.dao.AbstractDaoTest;
import org.otojunior.sample.enterpriseapp.entity.common.Address;
import org.otojunior.sample.enterpriseapp.entity.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User DAO Test.
 * @author [Author name]
 */
public class CustomerDaoTest extends AbstractDaoTest<CustomerDao> {
	private static final Logger LOG = LoggerFactory.getLogger(CustomerDaoTest.class);
	
	/**
	 * Default constructor.
	 */
	public CustomerDaoTest() {
		setDao(new CustomerDao());
	}
	
	/**
	 * Populate the database for the tests.
	 */
	@Override
	public void populate() {
		// Entity 1
		Address a = new Address();
		a.setAddress("Couves St.");
		a.setCity("Hortifruti");
		a.setNumber("69");
		a.setState("TS");
		
		Customer p = new Customer();
		p.setName("John Smith");
		p.setAddress(a);
		getEntityManager().persist(p);
		LOG.info(p.toString());
		
		// Entity 2
		a = new Address();
		a.setAddress("Potato Av.");
		a.setCity("Roots");
		a.setNumber("123");
		a.setState("TS");
		
		p = new Customer();
		p.setName("Mary Johnson");
		p.setAddress(a);
		getEntityManager().persist(p);
		LOG.info(p.toString());
		
		// Entity 3
		a = new Address();
		a.setAddress("Green Leaf St.");
		a.setCity("Trees");
		a.setNumber("666");
		a.setState("TS");
		
		p = new Customer();
		p.setName("Robert Marley");
		p.setAddress(a);
		getEntityManager().persist(p);
		LOG.info(p.toString());
	}

	/**
	 * Find all test.
	 */
	@Test
	public void testFindAll() {
		List<Customer> all = getDao().findAll();
		assertEquals(3, all.size());
	}

	/**
	 * Find by ID test.
	 */
	@Test
	public void testFindById() {
		TypedQuery<Long> q = getEntityManager().createQuery("select min(p.id) from Customer p", Long.class);
		Long minId = q.getSingleResult();
		
		Customer p = getDao().findById(Long.valueOf(minId));
		assertNotNull(p);
		assertEquals("John Smith", p.getName());
	}
	
	/**
	 * Test find for all parameters.
	 */
	@Test
	public void testFindAllParameters() {
		List<Customer> result = getDao().find("john", "ot", "oo");
		assertEquals(1, result.size());
		
		Customer customer = result.get(0);
		assertEquals("Mary Johnson", customer.getName());
		assertEquals("Potato Av.", customer.getAddress().getAddress());
		assertEquals("Roots", customer.getAddress().getCity());
	}
	
	/**
	 * Find for parameter name.
	 */
	@Test
	public void testFindParameterName() {
		List<Customer> result = getDao().find("john", null, null);
		assertEquals(2, result.size());
		
		Collections.sort(result, new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		
		Customer p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
		
		p = result.get(1);
		assertEquals("Mary Johnson", p.getName());
		assertEquals("Potato Av.", p.getAddress().getAddress());
		assertEquals("Roots", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterAddress() {
		List<Customer> result = getDao().find(null, "ouv", null);
		assertEquals(1, result.size());
		
		Customer p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterCity() {
		List<Customer> result = getDao().find(null, null, "fruti");
		assertEquals(1, result.size());
		
		Customer p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterNameAndAddress() {
		List<Customer> result = getDao().find("SMI", "st", null);
		assertEquals(1, result.size());
		
		Customer p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterNameAndCity() {
		List<Customer> result = getDao().find("john", null, "frut");
		assertEquals(1, result.size());
		
		Customer p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterAddressAndCity() {
		List<Customer> result = getDao().find(null, "Couves", "Hortifruti");
		assertEquals(1, result.size());
		
		Customer p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
}
