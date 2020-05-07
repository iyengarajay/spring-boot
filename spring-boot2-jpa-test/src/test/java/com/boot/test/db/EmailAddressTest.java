
package com.boot.test.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.boot.entity.EmailAddress;

public class EmailAddressTest {

	private static final String testEmail = "test@test.com";

	@Test
	public void testShoulReturnValidEmail() {
		EmailAddress email = new EmailAddress(testEmail);
		assertEquals(email.emailAddress(),testEmail);
	}

	@Test
	public void testShouldReturnExceptionForInvalidEmailAddress() {
		
		var exception = assertThrows(IllegalArgumentException.class, () -> new EmailAddress("test@.com"));
		assertEquals(exception.getMessage(), "Email Address is Invalid !");
	}

	@Test
	public void testShouldReturnExceptionForBlankEmailAddress() {
		
		var exception = assertThrows(IllegalArgumentException.class, () -> new EmailAddress(" "));
		assertEquals(exception.getMessage(), "Email Address is Invalid !");
	}

	@Test
	public void testShouldReturnExceptionForNuullEmailAddress() {
		
		var exception = assertThrows(IllegalArgumentException.class, () -> new EmailAddress(null));
		assertEquals(exception.getMessage(), "Email Address is Invalid !");
	}

}
