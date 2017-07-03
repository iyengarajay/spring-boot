package com.boot.test.db;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

import com.boot.entity.EmailAddress;

public class EmailAddressTest {

	private static final String testEmail = "test@test.com";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testShoulReturnValidEmail() {
		EmailAddress email = new EmailAddress(testEmail);
		assertThat(email.toString()).isEqualTo(testEmail);

	}

	@Test
	public void testShoulReturnExceptionForInvalidEmailAddress() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Email Address is Invalid !");
		EmailAddress email = new EmailAddress("test@.com");
		assertThat(email.toString()).isEqualTo(testEmail);

	}

	@Test
	public void testShoulReturnExceptionForBlankEmailAddress() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Email Address is Invalid !");
		EmailAddress email = new EmailAddress(" ");
		assertThat(email.toString()).isEqualTo(testEmail);

	}

	@Test
	public void testShoulReturnExceptionForNuullEmailAddress() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Email Address is Invalid !");
		EmailAddress email = new EmailAddress(null);
		assertThat(email.toString()).isEqualTo(testEmail);

	}

}
