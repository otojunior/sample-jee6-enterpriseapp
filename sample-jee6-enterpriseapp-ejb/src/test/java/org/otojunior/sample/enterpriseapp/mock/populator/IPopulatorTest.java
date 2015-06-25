/**
 * 
 */
package org.otojunior.sample.enterpriseapp.mock.populator;

/**
 * @author 01456231650
 *
 */
public interface IPopulatorTest {
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
	 * Test method.
	 */
	public void testStart();
}
