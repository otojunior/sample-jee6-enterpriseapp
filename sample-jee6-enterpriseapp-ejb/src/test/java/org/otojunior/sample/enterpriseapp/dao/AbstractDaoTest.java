/**
 * 
 */
package org.otojunior.sample.enterpriseapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.otojunior.sample.enterpriseapp.dao.IDao;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 01456231650
 *
 */
public abstract class AbstractDaoTest<T extends IDao<?,?>> implements IDaoTest {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(AbstractDaoTest.class);
	
	private static EntityManagerFactory factory;
	
	@AfterClass
	public static void afterClass() {
		factory.close();
	}
	
	@BeforeClass
	public static void beforeClass() {
		factory = Persistence.createEntityManagerFactory("test");
	}
	
	/**
	 * @return the factory
	 */
	public static EntityManagerFactory getFactory() {
		return factory;
	}
	
	/**
	 * @param factory the factory to set
	 */
	public static void setFactory(EntityManagerFactory factory) {
		AbstractDaoTest.factory = factory;
	}
	
	private EntityManager entityManager;
	
	private T dao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@After
	public void after() throws Exception {
		dao.getEntityManager().getTransaction().rollback();
		dao.getEntityManager().close();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Before
	public void before() throws Exception {
		entityManager = AbstractDaoTest.getFactory().createEntityManager();
		Whitebox.setInternalState(dao, EntityManager.class, entityManager);
		dao.getEntityManager().getTransaction().begin();
		populate();
	}
	
	/**
	 * @return the dao
	 */
	public T getDao() {
		return dao;
	}
	
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(T dao) {
		this.dao = dao;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
