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
package org.otojunior.sample.enterpriseapp.entity.petowner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.h2.engine.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.otojunior.sample.enterpriseapp.entity.common.Address;
import org.powermock.reflect.Whitebox;

/**
 * Pet Owner entity test class.
 * @author Oto Junior (otojunior@gmail.com)
 */
public class PetOwnerTest {
	/**
	 * Entity to be tested.
	 */
	private PetOwner petOwner;

	/**
	 * Test setup;
	 * @throws Exception Generic Exception.
	 */
	@Before
	public void setUp() throws Exception {
		petOwner = new PetOwner();
	}

	/**
	 * Test tear down.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		petOwner = null;
	}

	/**
	 * Test the accessor methods.
	 */
	@Test
	public void testAccessorMethods() {
		/*
		 * Setting the entity state for tests the accessor methods.
		 */
		Whitebox.setInternalState(petOwner, "id", Long.valueOf(1L));
		Whitebox.setInternalState(petOwner, "version", Long.valueOf(1L));
		petOwner.setName("Test name");
		Address address = new Address();
		petOwner.setAddress(address );
		
		/*
		 * Testing the accessor methods.
		 */
		assertEquals(Long.valueOf(1L), petOwner.getId());
		assertEquals(Long.valueOf(1L), petOwner.getVersion());
		assertEquals("Test name", petOwner.getName());
		assertEquals(address, petOwner.getAddress());
	}
	
	/**
	 * Test equals.
	 */
	@Test
	public void testEquals() {
		PetOwner other = new PetOwner();
		Whitebox.setInternalState(petOwner, "id", Long.valueOf(1L));
		Whitebox.setInternalState(other, "id", Long.valueOf(1L));
		assertTrue(petOwner.equals(other));
	}
	
	/**
	 * Test equals with same object.
	 */
	@Test
	public void testEqualsOtherClass() {
		String other = "otherClass";
		assertFalse(petOwner.equals(other));
	}
	
	/**
	 * Test equals with same object.
	 */
	@Test
	public void testEqualsSameObject() {
		PetOwner other = petOwner;
		assertTrue(petOwner.equals(other));
	}
	
	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		Whitebox.setInternalState(petOwner, "id", Long.valueOf(1L));
		assertEquals(32, petOwner.hashCode());
	}
	
	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCodeWithIdNull() {
		assertEquals(31, petOwner.hashCode());
	}
	
	/**
	 * Test equals.
	 */
	@Test
	public void testNotEquals() {
		PetOwner other = new PetOwner();
		Whitebox.setInternalState(petOwner, "id", Long.valueOf(1L));
		Whitebox.setInternalState(other, "id", Long.valueOf(2L));
		assertFalse(petOwner.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsOtherWithIdNull() {
		PetOwner other = new PetOwner();
		Whitebox.setInternalState(petOwner, "id", Long.valueOf(1L));
		assertFalse(petOwner.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsOtherWithIdNotNull() {
		PetOwner other = new PetOwner();
		Whitebox.setInternalState(other, "id", Long.valueOf(1L));
		assertFalse(petOwner.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsOtherWithBothIdNull() {
		PetOwner other = new PetOwner();
		assertTrue(petOwner.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsWithIdNull() {
		User other = null;
		Whitebox.setInternalState(petOwner, "id", Long.valueOf(1L));
		assertFalse(petOwner.equals(other));
	}
	
	/**
	 * Test the toString method.
	 */
	@Test
	public void testToString() {
		petOwner.setName("Name Test");
		petOwner.setAddress(new Address());
		Whitebox.setInternalState(petOwner, "id", Long.valueOf(1L));
		Whitebox.setInternalState(petOwner, "version", Long.valueOf(0L));
		assertEquals(
			"PetOwner[name=Name Test,photo=<null>,address=Address[address=<null>,number=<null>,zipCode=<null>,city=<null>,state=<null>],id=1,version=0]",
			petOwner.toString());
	}
}
