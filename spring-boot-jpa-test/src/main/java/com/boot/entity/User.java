package com.boot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	@ManyToOne
	private SocialMediaSite socialMediaSite;
	
	
	protected User(){
		
	}
	
	public User(String firstName, String lastName , String email){
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SocialMediaSite getSocialMediaSite() {
		return socialMediaSite;
	}

	public void setSocialMediaSite(SocialMediaSite socialMediaSite) {
		this.socialMediaSite = socialMediaSite;
	}

}
