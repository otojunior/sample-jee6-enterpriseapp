package org.otojunior.sample.enterpriseapp.dao.petowner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.otojunior.sample.enterpriseapp.entity.common.Address;
import org.otojunior.sample.enterpriseapp.entity.petowner.PetOwner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User DAO Test.
 * @author [Author name]
 */
public class PetOwnerDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(PetOwnerDaoTest.class);
	
	private static EntityManagerFactory factory;
	private EntityManager entityManager;
	
	/**
	 * DAO to be tested.
	 */
	private PetOwnerDao petOwnerDao;
	
	@BeforeClass
	public static void beforeClass() {
		factory = Persistence.createEntityManagerFactory("test");
	}
	
	@AfterClass
	public static void afterClass() {
		factory.close();
	}
	
	/**
	 * DAO Setup.
	 * @throws Exception Generic exception.
	 */
	@Before
	public void setUp() throws Exception {
		petOwnerDao = new PetOwnerDao();
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Whitebox.setInternalState(petOwnerDao, "entityManager", entityManager);

		populate();
	}
	
	/**
	 * Populate the database for the tests.
	 */
	private void populate() {
		// Entity 1
		Address a = new Address();
		a.setAddress("Couves St.");
		a.setCity("Hortifruti");
		a.setNumber("69");
		a.setState("TS");
		
		PetOwner p = new PetOwner();
		p.setName("John Smith");
		p.setAddress(a);
		entityManager.persist(p);
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
		entityManager.persist(p);
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
		entityManager.persist(p);
		LOG.info(p.toString());
	}

	/**
	 * DAO Tear down.
	 * @throws Exception Generic exception.
	 */
	@After
	public void tearDown() throws Exception {
		petOwnerDao = null;
		entityManager.getTransaction().rollback();
	}

	/**
	 * Find all test.
	 */
	@Test
	public void testFindAll() {
		List<PetOwner> all = petOwnerDao.findAll();
		assertEquals(3, all.size());
	}

	/**
	 * Find by ID test.
	 */
	@Test
	public void testFindById() {
		TypedQuery<Long> q = entityManager.createQuery("select min(p.id) from PetOwner p", Long.class);
		Long minId = q.getSingleResult();
		
		PetOwner p = petOwnerDao.findById(Long.valueOf(minId));
		assertNotNull(p);
		assertEquals("John Smith", p.getName());
	}
	
	@Test
	public void testFindAllParameters() {
		List<PetOwner> result = petOwnerDao.find("john", "ot", "oo");
		assertEquals(1, result.size());
		
		PetOwner petOwner = result.get(0);
		assertEquals("Mary Johnson", petOwner.getName());
		assertEquals("Potato Av.", petOwner.getAddress().getAddress());
		assertEquals("Roots", petOwner.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterName() {
		List<PetOwner> result = petOwnerDao.find("john", null, null);
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
		List<PetOwner> result = petOwnerDao.find(null, "ouv", null);
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterCity() {
		List<PetOwner> result = petOwnerDao.find(null, null, "fruti");
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterNameAndAddress() {
		List<PetOwner> result = petOwnerDao.find("SMI", "st", null);
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterNameAndCity() {
		List<PetOwner> result = petOwnerDao.find("john", null, "frut");
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
	
	@Test
	public void testFindParameterAddressAndCity() {
		List<PetOwner> result = petOwnerDao.find(null, "Couves", "Hortifruti");
		assertEquals(1, result.size());
		
		PetOwner p = result.get(0);
		assertEquals("John Smith", p.getName());
		assertEquals("Couves St.", p.getAddress().getAddress());
		assertEquals("Hortifruti", p.getAddress().getCity());
	}
}
