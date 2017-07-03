package com.boot.entity;

import java.util.regex.Pattern;

import org.springframework.util.Assert;

public final class EmailAddress {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private final String value;

	public EmailAddress(String emailAddress) {
		Assert.isTrue(isValid(emailAddress), "Email Address is Invalid !");
		this.value = emailAddress;
	}

	@Override
	public String toString() {
		return value;
	}

	private static boolean isValid(String emailAddress) {
		return emailAddress != null ? VALID_EMAIL_ADDRESS_REGEX.matcher(emailAddress).matches() : false;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailAddress other = (EmailAddress) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
