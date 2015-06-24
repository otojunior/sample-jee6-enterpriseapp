/*
 * Copyright 2015 Oto Soares Coelho Junior
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.otojunior.sample.enterpriseapp.entity.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.h2.engine.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.otojunior.sample.enterpriseapp.entity.common.Address;
import org.otojunior.sample.enterpriseapp.entity.customer.Customer;
import org.powermock.reflect.Whitebox;

/**
 * Pet Owner entity test class.
 * @author Oto Junior (otojunior@gmail.com)
 */
public class CustomerTest {
	/**
	 * Entity to be tested.
	 */
	private Customer customer;

	/**
	 * Test setup;
	 * @throws Exception Generic Exception.
	 */
	@Before
	public void setUp() throws Exception {
		customer = new Customer();
	}

	/**
	 * Test tear down.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		customer = null;
	}

	/**
	 * Test the accessor methods.
	 */
	@Test
	public void testAccessorMethods() {
		/*
		 * Setting the entity state for tests the accessor methods.
		 */
		Whitebox.setInternalState(customer, "id", Long.valueOf(1L));
		Whitebox.setInternalState(customer, "version", Long.valueOf(1L));
		customer.setName("Test name");
		Address address = new Address();
		customer.setAddress(address );
		
		/*
		 * Testing the accessor methods.
		 */
		assertEquals(Long.valueOf(1L), customer.getId());
		assertEquals(Long.valueOf(1L), customer.getVersion());
		assertEquals("Test name", customer.getName());
		assertEquals(address, customer.getAddress());
	}
	
	/**
	 * Test equals.
	 */
	@Test
	public void testEquals() {
		Customer other = new Customer();
		Whitebox.setInternalState(customer, "id", Long.valueOf(1L));
		Whitebox.setInternalState(other, "id", Long.valueOf(1L));
		assertTrue(customer.equals(other));
	}
	
	/**
	 * Test equals with same object.
	 */
	@Test
	public void testEqualsOtherClass() {
		String other = "otherClass";
		assertFalse(customer.equals(other));
	}
	
	/**
	 * Test equals with same object.
	 */
	@Test
	public void testEqualsSameObject() {
		Customer other = customer;
		assertTrue(customer.equals(other));
	}
	
	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		Whitebox.setInternalState(customer, "id", Long.valueOf(1L));
		assertEquals(32, customer.hashCode());
	}
	
	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCodeWithIdNull() {
		assertEquals(31, customer.hashCode());
	}
	
	/**
	 * Test equals.
	 */
	@Test
	public void testNotEquals() {
		Customer other = new Customer();
		Whitebox.setInternalState(customer, "id", Long.valueOf(1L));
		Whitebox.setInternalState(other, "id", Long.valueOf(2L));
		assertFalse(customer.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsOtherWithIdNull() {
		Customer other = new Customer();
		Whitebox.setInternalState(customer, "id", Long.valueOf(1L));
		assertFalse(customer.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsOtherWithIdNotNull() {
		Customer other = new Customer();
		Whitebox.setInternalState(other, "id", Long.valueOf(1L));
		assertFalse(customer.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsOtherWithBothIdNull() {
		Customer other = new Customer();
		assertTrue(customer.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsWithIdNull() {
		User other = null;
		Whitebox.setInternalState(customer, "id", Long.valueOf(1L));
		assertFalse(customer.equals(other));
	}
	
	/**
	 * Test the toString method.
	 */
	@Test
	public void testToString() {
		customer.setName("Name Test");
		customer.setAddress(new Address());
		Whitebox.setInternalState(customer, "id", Long.valueOf(1L));
		Whitebox.setInternalState(customer, "version", Long.valueOf(0L));
		assertEquals(
			"Customer[name=Name Test,photo=<null>,address=Address[address=<null>,number=<null>,zipCode=<null>,city=<null>,state=<null>],id=1,version=0]",
			customer.toString());
	}
}
