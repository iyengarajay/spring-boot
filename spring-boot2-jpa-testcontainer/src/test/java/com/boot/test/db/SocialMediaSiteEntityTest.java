
package com.boot.test.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.boot.entity.EmailAddress;
import com.boot.entity.SocialMediaSite;
import com.boot.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = { SocialMediaSiteEntityTest.PropertiesInitializer.class })
public class SocialMediaSiteEntityTest extends AbstractContainerBaseTest {

	@Autowired
	private TestEntityManager entityManager;

	private SocialMediaSite facebook;

	private User firstUser;

	private User secondUser;

	@BeforeEach
	public void create() {
		facebook = new SocialMediaSite("Facebook", "Online Social Media and Networking Service");
		firstUser = new User("Mar", "Zuckerber", new EmailAddress("mark@mark.com"));
		secondUser = new User("Chri", "Hughe", new EmailAddress("chris@chris.com"));
	}

	@Test
	public void saveSocialMediaSiteFacebook() {

		var savedFacebookData = this.entityManager.persistAndFlush(facebook);
		assertThat(savedFacebookData.getName()).isEqualTo("Facebook");
	}

	@Test
	public void createSocialMediaSiteFacebookNullNameException() throws Exception {
		var exception = assertThrows(IllegalArgumentException.class,
				() -> new SocialMediaSite("", "Online Social Media and Networking Service"));
		assertEquals(exception.getMessage(), "Social Media Site Name should not be empty");
	}

	@Test
	public void saveFacebookUsers() {

		facebook.addUser(firstUser);

		facebook.addUser(secondUser);

		var savedFaceBook = this.entityManager.persistAndFlush(facebook);

		assertThat(savedFaceBook.getId()).isNotNull();
		assertThat(savedFaceBook.getName()).isEqualTo("Facebook");
		assertThat(savedFaceBook.getUsers().size()).isEqualTo(2);
	}

	@Test
	public void removeFacebookUserChri() {

		facebook.addUser(firstUser);
		facebook.addUser(secondUser);

		var savedFaceBook = this.entityManager.persistFlushFind(facebook);

		savedFaceBook.removeUser(secondUser);

		var updatedData = this.entityManager.persistAndFlush(savedFaceBook);

		assertThat(updatedData.getUsers()).hasSize(1);
		assertThat(updatedData.getUsers().get(0).getFirstName()).isEqualTo("Mar");

	}

}
