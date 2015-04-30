package org.otojunior.sample.enterpriseapp.dao;

import static org.junit.Assert.*;

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
		for (int i = 0; i < 10; i++) {
			Address a = new Address();
			a.setAddress("Address " + i);
			a.setCity("City " + i);
			a.setNumber("" + i+1);
			a.setState("ST");

			PetOwner p = new PetOwner();
			p.setName("Name " + i);
			p.setAddress(a);
			entityManager.persist(p);
			LOG.info(p.toString());
		}
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
		assertEquals(10, all.size());
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
		assertEquals("Name 0", p.getName());
	}
	
	@Test
	public void testFindAllParameters() {
		List<PetOwner> result = petOwnerDao.find("Name", "Add", "City");
		assertEquals(10, result.size());
		for (int i = 0; i < 10; i++) {
			PetOwner petOwner = result.get(i);
			assertEquals("Name " + i, petOwner.getName());
			assertEquals("Address " + i, petOwner.getAddress().getAddress());
			assertEquals("City " + i, petOwner.getAddress().getCity());
		}
	}
	
	@Test
	public void testFindParameterName() {
		List<PetOwner> result = petOwnerDao.find("Nam", null, null);
		assertEquals(10, result.size());
		for (int i = 0; i < 10; i++) {
			PetOwner petOwner = result.get(i);
			assertEquals("Name " + i, petOwner.getName());
			assertEquals("Address " + i, petOwner.getAddress().getAddress());
			assertEquals("City " + i, petOwner.getAddress().getCity());
		}
	}
	
	@Test
	public void testFindParameterAddress() {
		List<PetOwner> result = petOwnerDao.find(null, "ddres", null);
		assertEquals(10, result.size());
		for (int i = 0; i < 10; i++) {
			PetOwner petOwner = result.get(i);
			assertEquals("Name " + i, petOwner.getName());
			assertEquals("Address " + i, petOwner.getAddress().getAddress());
			assertEquals("City " + i, petOwner.getAddress().getCity());
		}
	}
	
	@Test
	public void testFindParameterCity() {
		List<PetOwner> result = petOwnerDao.find(null, null, "ty");
		assertEquals(10, result.size());
		for (int i = 0; i < 10; i++) {
			PetOwner petOwner = result.get(i);
			assertEquals("Name " + i, petOwner.getName());
			assertEquals("Address " + i, petOwner.getAddress().getAddress());
			assertEquals("City " + i, petOwner.getAddress().getCity());
		}
	}
	
	@Test
	public void testFindParameterNameAndAddress() {
		List<PetOwner> result = petOwnerDao.find("Nam", "dress", null);
		assertEquals(10, result.size());
		for (int i = 0; i < 10; i++) {
			PetOwner petOwner = result.get(i);
			assertEquals("Name " + i, petOwner.getName());
			assertEquals("Address " + i, petOwner.getAddress().getAddress());
			assertEquals("City " + i, petOwner.getAddress().getCity());
		}
	}
	
	@Test
	public void testFindParameterNameAndCity() {
		List<PetOwner> result = petOwnerDao.find("Nam", null, "ty");
		assertEquals(10, result.size());
		for (int i = 0; i < 10; i++) {
			PetOwner petOwner = result.get(i);
			assertEquals("Name " + i, petOwner.getName());
			assertEquals("Address " + i, petOwner.getAddress().getAddress());
			assertEquals("City " + i, petOwner.getAddress().getCity());
		}
	}
	
	@Test
	public void testFindParameterAddressAndCity() {
		List<PetOwner> result = petOwnerDao.find(null, "ddres", "ty");
		assertEquals(10, result.size());
		for (int i = 0; i < 10; i++) {
			PetOwner petOwner = result.get(i);
			assertEquals("Name " + i, petOwner.getName());
			assertEquals("Address " + i, petOwner.getAddress().getAddress());
			assertEquals("City " + i, petOwner.getAddress().getCity());
		}
	}
}
