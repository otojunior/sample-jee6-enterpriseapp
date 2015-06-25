package org.otojunior.sample.enterpriseapp.mock.populator.customer;

import org.otojunior.sample.enterpriseapp.mock.populator.AbstractPopulatorTest;
import org.otojunior.sample.enterpriseapp.mock.populator.customer.CustomerPopulator;

/**
 * 
 * @author 01456231650
 *
 */
public class CustomerPopulatorTest extends AbstractPopulatorTest<CustomerPopulator> {
	/**
	 * Default constructor.
	 */
	public CustomerPopulatorTest() {
		setPopulator(new CustomerPopulator());
	}
}
