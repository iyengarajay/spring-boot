package com.boot.test.db;

import com.boot.entity.EmailAddress;

import spock.lang.Specification

public class EmailAddressTest extends Specification {

	private static final String testEmail = "test@test.com";
	

	def "throw exception for invalid format of email address"(String emailAddress)
	{
		
		when: 
			"create invalid email address"
			def email = new EmailAddress(emailAddress);
	
		then:
			"throw exception for invalid format of email address"
			def e = thrown(IllegalArgumentException)
			e.message == "Email Address is Invalid !"
			
		where:
			emailAddress | _
			"test@.com"  | _
			" "          | _
			null         | _	
			
	}

	def "return valid email address"()
	{
		
		when: 
			"create new email address"
			def email = new EmailAddress(testEmail);
	
		then:
			"return valid email"
			email.toString() == testEmail;
	}


}
