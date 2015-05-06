package org.otojunior.sample.enterpriseapp.dao.support;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

/**
 * 
 * @author 01456231650
 *
 */
public class PetOwnerPopulatorTest {
	private static EntityManagerFactory factory;
	private EntityManager entityManager;
	
	private PetOwnerPopulator petOwnerPopulator;

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
		petOwnerPopulator = new PetOwnerPopulator();
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Whitebox.setInternalState(petOwnerPopulator, "entityManager", entityManager);
	}
	
	/**
	 * DAO Tear down.
	 * @throws Exception Generic exception.
	 */
	@After
	public void tearDown() throws Exception {
		petOwnerPopulator = null;
		entityManager.getTransaction().rollback();
	}
	
	/**
	 * 
	 */
	@Test
	public void testStart() {
		petOwnerPopulator.start();
	}
}
