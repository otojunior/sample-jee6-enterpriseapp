package org.otojunior.sample.enterpriseapp.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.otojunior.sample.enterpriseapp.entity.User;
import org.powermock.reflect.Whitebox;

/**
 * User DAO Test.
 * @author [Author name]
 */
public class UserDaoTest {
	private static EntityManagerFactory factory;
	private EntityManager entityManager;
	
	/**
	 * DAO to be tested.
	 */
	private UserDao userDao;
	
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
		userDao = new UserDao();
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Whitebox.setInternalState(userDao, "entityManager", entityManager);

		populate();
	}
	
	/**
	 * Populate the database for the tests.
	 */
	private void populate() {
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setLogin("userlogin"+i);
			user.setName("User Name "+i);
			user.setPassword("userpwd"+i);
			entityManager.persist(user);
		}
	}

	/**
	 * DAO Tear down.
	 * @throws Exception Generic exception.
	 */
	@After
	public void tearDown() throws Exception {
		userDao = null;
		entityManager.getTransaction().rollback();
	}

	/**
	 * Find all test.
	 */
	@Test
	public void testFindAll() {
		List<User> all = userDao.findAll();
		assertEquals(10, all.size());
	}

	/**
	 * Find by login test.
	 */
	@Test
	public void testFindByLogin() {
		List<User> all = userDao.findByLogin("userlogin0");
		assertEquals(1, all.size());
	}

	/**
	 * Find by ID test.
	 */
	@Test
	public void testFindById() {
		User user = userDao.findById(Long.valueOf(11L));
		assertNotNull(user);
		assertEquals("userlogin0", user.getLogin());
		assertEquals("User Name 0", user.getName());
	}
}
