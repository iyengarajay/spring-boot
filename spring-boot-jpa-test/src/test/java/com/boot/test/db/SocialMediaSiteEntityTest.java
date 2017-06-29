package com.boot.test.db;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.boot.entity.SocialMediaSite;
import com.boot.entity.User;


@RunWith(SpringRunner.class)
@DataJpaTest
public class SocialMediaSiteEntityTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void saveSocialMediaSiteFacebook() {
		SocialMediaSite facebook = new SocialMediaSite("Facebook", "Online Social Media and Networking Service");
		SocialMediaSite savedFacebookData = this.entityManager.persistAndFlush(facebook);
		assertThat(savedFacebookData.getName()).isEqualTo("Facebook");

	}

	@Test
	public void createSocialMediaSiteFacebookNullNameException() throws Exception{
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Social Media Site Name should not be empty");
		new SocialMediaSite("", "Online Social Media and Networking Service");

	}

	@Test
	public void saveFacebookUsers() {

		SocialMediaSite facebook = new SocialMediaSite("Facebook", "Online Social Media and Networking Service");

		User firstUser = new User("Mark", "Zuckerberg", "mark@mark.com");
		facebook.addUser(firstUser);

		User secondUser = new User("Chris", "Hughes", "chris@chris.com");
		facebook.addUser(secondUser);

		SocialMediaSite savedFaceBook = this.entityManager.persistFlushFind(facebook);

		assertThat(savedFaceBook.getId()).isNotNull();
		assertThat(savedFaceBook.getName()).isEqualTo("Facebook");
		assertThat(savedFaceBook.getUsers().size()).isEqualTo(2);

	}

}
