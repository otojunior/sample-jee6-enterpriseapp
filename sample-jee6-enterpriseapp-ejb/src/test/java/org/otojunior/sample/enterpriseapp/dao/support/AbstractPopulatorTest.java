/**
 * 
 */
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 01456231650
 *
 */
public abstract class AbstractPopulatorTest<T extends IPopulator> implements IPopulatorTest {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(AbstractPopulatorTest.class);
	
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
		AbstractPopulatorTest.factory = factory;
	}
	
	private EntityManager entityManager;
	
	private T populator;

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @return the populator
	 */
	public T getPopulator() {
		return populator;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * @param populator the populator to set
	 */
	public void setPopulator(T populator) {
		this.populator = populator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Test
	@Override
	public void testStart() {
		getPopulator().start();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@After
	public void after() throws Exception {
		populator.getEntityManager().getTransaction().rollback();
		populator.getEntityManager().close();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Before
	public void before() throws Exception {
		entityManager = AbstractPopulatorTest.getFactory().createEntityManager();
		Whitebox.setInternalState(populator, EntityManager.class, entityManager);
		populator.getEntityManager().getTransaction().begin();
	}
}
