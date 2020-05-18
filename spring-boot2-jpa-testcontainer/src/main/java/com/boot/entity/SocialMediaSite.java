package com.boot.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.util.Assert;

@Entity
@Table(name = "social_media_site")
public class SocialMediaSite {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "social_media_site_id_seq", sequenceName = "social_media_site_id_seq")
	private Long id;

	@Column(unique = true)
	private String name;

	private String description;

	@OneToMany(mappedBy = "socialMediaSite", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> users = new ArrayList<>();

	protected SocialMediaSite() {

	}

	public SocialMediaSite(String name, String description) {
		Assert.hasLength(name, "Social Media Site Name should not be empty");
		Assert.hasLength(description, "Social Media Site Description should not be empty");
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return this.id;
	}

	public String getDescription() {
		return description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void addUser(User user) {
		users.add(user);
		user.setSocialMediaSite(this);
	}

	public void removeUser(User user) {
		users.remove(user);
		user.setSocialMediaSite(null);
	}

	@Override
	public String toString() {
		return String.format("Social Media Site[name = '%s' , description  = '%s']", name, description);
	}

}
