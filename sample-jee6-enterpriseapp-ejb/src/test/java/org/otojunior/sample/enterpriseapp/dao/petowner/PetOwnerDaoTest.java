package org.otojunior.sample.enterpriseapp.dao.petowner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.otojunior.sample.enterpriseapp.dao.AbstractDaoTest;
import org.otojunior.sample.enterpriseapp.entity.common.Address;
import org.otojunior.sample.enterpriseapp.entity.petowner.PetOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User DAO Test.
 * @author [Author name]
 */
public class PetOwnerDaoTest extends AbstractDaoTest<PetOwnerDao> {
	private static final Logger LOG = LoggerFactory.getLogger(PetOwnerDaoTest.class);
	
	/**
	 * Default constructor.
	 */
	public PetOwnerDaoTest() {
		setDao(new PetOwnerDao());
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
		
		PetOwner p = new PetOwner();
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
		
		p = new PetOwner();
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
		
		p = new PetOwner();
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
		List<PetOwner> all = getDao().findAll();
		assertEquals(3, all.size());
	}

	/**
	 * Find by ID test.
	 */
	@Test
	public void testFindById() {
		TypedQuery<Long> q = getEntityManager().createQuery("select min(p.id) from PetOwner p", Long.class);
		Long minId = q.getSingleResult();
		
		PetOwner p = getDao().findById(Long.valueOf(minId));
		assertNotNull(p);
		assertEquals("John Smith", p.getName());
	}
	
	/**
	 * Test find for all parameters.
	 */
	@Test
	public void testFindAllParameters() {
		List<PetOwner> result = getDao().find("john", "ot", "oo");
		assertEquals(1, result.size());
		
		PetOwner petOwner = result.get(0);
		assertEquals("Mary Johnson", petOwner.getName());
		assertEquals("Potato Av.", petOwner.getAddress().getAddress());
		assertEquals("Roots", petOwner.getAddress().getCity());
	}
	
	/**
	 * Find for parameter name.
	 */
	@Test
	public void testFindParameterName() {
		List<PetOwner> result = getDao().find("john", null, null);
		assertEquals(2, result.size());
		
		Collections.sort(result, new Comparator<PetOwner>() {
			@Override
			public int compare(PetOwner o1, PetOwner o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		
		PetOwner p = result.get(0);
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
		List<PetOwner> result = getDao().find(null, "ouv", null);
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterCity() {
		List<PetOwner> result = getDao().find(null, null, "fruti");
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterNameAndAddress() {
		List<PetOwner> result = getDao().find("SMI", "st", null);
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterNameAndCity() {
		List<PetOwner> result = getDao().find("john", null, "frut");
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterAddressAndCity() {
		List<PetOwner> result = getDao().find(null, "Couves", "Hortifruti");
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
}
