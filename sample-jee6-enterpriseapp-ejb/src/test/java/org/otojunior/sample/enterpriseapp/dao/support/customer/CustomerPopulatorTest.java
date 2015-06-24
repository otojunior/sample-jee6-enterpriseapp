package org.otojunior.sample.enterpriseapp.dao.support.customer;

import org.otojunior.sample.enterpriseapp.dao.support.AbstractPopulatorTest;
import org.otojunior.sample.enterpriseapp.dao.support.customer.CustomerPopulator;

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
