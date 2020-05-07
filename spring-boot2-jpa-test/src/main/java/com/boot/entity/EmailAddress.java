package com.boot.entity;

import java.util.regex.Pattern;
import org.springframework.util.Assert;

public record EmailAddress(String emailAddress) {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public EmailAddress {
		Assert.isTrue(isValid(emailAddress), "Email Address is Invalid !");
	}

	private static boolean isValid(String emailAddress) {
		return emailAddress != null ? VALID_EMAIL_ADDRESS_REGEX.matcher(emailAddress).matches() : false;
	}

}
