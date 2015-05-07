package org.otojunior.sample.enterpriseapp.dao;

public interface IDaoTest {
	/**
	 * Before class.
	 * @throws Exception
	 */
	public void before() throws Exception;
	
	/**
	 * After class.
	 * @throws Exception
	 */
	public void after() throws Exception;
	
	/**
	 * Populate the base.
	 */
	public void populate();
}
