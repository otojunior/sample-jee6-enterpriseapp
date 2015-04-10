package org.otojunior.sample.enterpriseapp.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

/**
 * User test class.
 * @author [Author name]
 */
public class UserTest {
	/**
	 * Entity to be tested.
	 */
	private User user;

	/**
	 * Test setup;
	 * @throws Exception Generic Exception.
	 */
	@Before
	public void setUp() throws Exception {
		user = new User();
	}

	/**
	 * Test tear down.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		user = null;
	}

	/**
	 * Test the accessor methods.
	 */
	@Test
	public void testAccessorMethods() {
		/*
		 * Setting the entity state for tests the accessor methods.
		 */
		Whitebox.setInternalState(user, "id", Long.valueOf(1L));
		Whitebox.setInternalState(user, "version", Long.valueOf(1L));
		user.setLogin("testlogin");
		user.setName("Test name");
		user.setPassword("testpassword");
		
		/*
		 * Testing the accessor methods.
		 */
		assertEquals(Long.valueOf(1L), user.getId());
		assertEquals(Long.valueOf(1L), user.getVersion());
		assertEquals("Test name", user.getName());
		assertEquals("testlogin", user.getLogin());
		assertEquals("testpassword", user.getPassword());
	}
	
	/**
	 * Test equals.
	 */
	@Test
	public void testEquals() {
		User other = new User();
		Whitebox.setInternalState(user, "id", Long.valueOf(1L));
		Whitebox.setInternalState(other, "id", Long.valueOf(1L));
		assertTrue(user.equals(other));
	}
	
	/**
	 * Test equals with same object.
	 */
	@Test
	public void testEqualsOtherClass() {
		String other = "otherClass";
		assertFalse(user.equals(other));
	}
	
	/**
	 * Test equals with same object.
	 */
	@Test
	public void testEqualsSameObject() {
		User other = user;
		assertTrue(user.equals(other));
	}
	
	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
		Whitebox.setInternalState(user, "id", Long.valueOf(1L));
		assertEquals(32, user.hashCode());
	}
	
	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCodeWithIdNull() {
		assertEquals(31, user.hashCode());
	}
	
	/**
	 * Test equals.
	 */
	@Test
	public void testNotEquals() {
		User other = new User();
		Whitebox.setInternalState(user, "id", Long.valueOf(1L));
		Whitebox.setInternalState(other, "id", Long.valueOf(2L));
		assertFalse(user.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsOtherWithIdNull() {
		User other = new User();
		Whitebox.setInternalState(user, "id", Long.valueOf(1L));
		assertFalse(user.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsOtherWithIdNotNull() {
		User other = new User();
		Whitebox.setInternalState(other, "id", Long.valueOf(1L));
		assertFalse(user.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsOtherWithBothIdNull() {
		User other = new User();
		assertTrue(user.equals(other));
	}
	
	/**
	 * Test equals with null value.
	 */
	@Test
	public void testNotEqualsWithIdNull() {
		User other = null;
		Whitebox.setInternalState(user, "id", Long.valueOf(1L));
		assertFalse(user.equals(other));
	}
	
	/**
	 * Test pre-persist callback.
	 */
	@Test
	public void testPrePersist() {
		user.setPassword("testpassword");
		user.prePersist();
		assertEquals("8bb6118f8fd6935ad0876a3be34a717d32708ffd", user.getSha1Password());
	}
	
	/**
	 * Test pre-persist callback with null.
	 */
	@Test
	public void testPrePersistWithNull() {
		user.setPassword(null);
		user.prePersist();
		assertNull(user.getSha1Password());
	}
	
	/**
	 * Test the toString method.
	 */
	@Test
	public void testToString() {
		user.setLogin("logintest");
		user.setName("Name Test");
		user.setPassword("testpwd");
		Whitebox.setInternalState(user, "id", Long.valueOf(1L));
		Whitebox.setInternalState(user, "version", Long.valueOf(0L));
		assertEquals(
			"User[id=1,version=0,name=Name Test,login=logintest,sha1Password=<null>,password=testpwd]",
			user.toString());
	}
}
