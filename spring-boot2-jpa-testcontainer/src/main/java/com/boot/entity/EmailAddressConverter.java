package com.boot.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmailAddressConverter implements AttributeConverter<EmailAddress, String> {

	@Override
	public String convertToDatabaseColumn(EmailAddress emailAddress) {
		return emailAddress.emailAddress();
	}

	@Override
	public EmailAddress convertToEntityAttribute(String email) {
		return new EmailAddress(email);
	}
}
