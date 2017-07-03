package com.boot.test.db;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.boot.entity.EmailAddress;
import com.boot.entity.SocialMediaSite;
import com.boot.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SocialMediaSiteEntityTest {

	@Autowired
	private TestEntityManager entityManager;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private SocialMediaSite facebook;

	private User firstUser;

	private User secondUser;

	@Before
	public void setUp() {
		facebook = new SocialMediaSite("Facebook", "Online Social Media and Networking Service");
		firstUser = new User("Mar", "Zuckerber", new EmailAddress("mark@mark.com"));
		secondUser = new User("Chri", "Hughe", new EmailAddress("chris@chris.com"));

	}

	@Test
	public void saveSocialMediaSiteFacebook() {
		SocialMediaSite savedFacebookData = this.entityManager.persistAndFlush(facebook);
		assertThat(savedFacebookData.getName()).isEqualTo("Facebook");

	}

	@Test
	public void createSocialMediaSiteFacebookNullNameException() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Social Media Site Name should not be empty");
		new SocialMediaSite("", "Online Social Media and Networking Service");

	}

	@Test
	public void saveFacebookUsers() {

		facebook.addUser(firstUser);

		facebook.addUser(secondUser);

		SocialMediaSite savedFaceBook = this.entityManager.persistFlushFind(facebook);

		assertThat(savedFaceBook.getId()).isNotNull();
		assertThat(savedFaceBook.getName()).isEqualTo("Facebook");
		assertThat(savedFaceBook.getUsers().size()).isEqualTo(2);

	}

	@Test
	public void removeFacebookUserChri() {

		facebook.addUser(firstUser);
		facebook.addUser(secondUser);

		SocialMediaSite savedFaceBook = this.entityManager.persistFlushFind(facebook);

		savedFaceBook.removeUser(secondUser);

		SocialMediaSite updatedData = this.entityManager.persistAndFlush(savedFaceBook);
		assertThat(updatedData.getUsers().size()).isEqualTo(1);
		assertThat(updatedData.getUsers().get(0).getFirstName()).isEqualTo("Mar");

	}

}
