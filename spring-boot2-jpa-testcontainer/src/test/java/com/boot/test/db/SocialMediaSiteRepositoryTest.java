
package com.boot.test.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.boot.entity.EmailAddress;
import com.boot.entity.SocialMediaSite;
import com.boot.entity.User;
import com.boot.repository.SocialMediaSiteRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = { SocialMediaSiteEntityTest.PropertiesInitializer.class })
public class SocialMediaSiteRepositoryTest extends AbstractContainerBaseTest{

	@Autowired
	private SocialMediaSiteRepository socialMediaSiteRepository;

	private SocialMediaSite facebook;

	@BeforeEach
	public void setUp() {
		facebook = new SocialMediaSite("Facebook", "Online Social Media and Networking Service");

	}

	@Test
	public void saveFacebookAndFindById() {
		facebook = socialMediaSiteRepository.save(facebook);
		Optional<SocialMediaSite> socialMediaSite = socialMediaSiteRepository.findById(facebook.getId());
		socialMediaSite.ifPresent(site -> assertTrue(site.equals(facebook)));

	}

	@Test
	public void saveFacebookAndFindBySocialMediaSiteName() {
		facebook = socialMediaSiteRepository.save(facebook);
		assertThat(socialMediaSiteRepository.findByName("Facebook")).isEqualTo((facebook));
	}

	@Test
	public void saveFacebookUsers() {

		var firstUser = new User("Mar", "Zuckerber", new EmailAddress("mark@mark.com"));
		facebook.addUser(firstUser);

		facebook = socialMediaSiteRepository.save(facebook);

		var site = socialMediaSiteRepository.findById(facebook.getId());
		assertTrue(site.isPresent());

		// user added is not null 
		var users = site.get().getUsers();
		assertNotNull(users);

		// check the one user we added. 
		assertEquals(users.size(), 1);

	}

}
