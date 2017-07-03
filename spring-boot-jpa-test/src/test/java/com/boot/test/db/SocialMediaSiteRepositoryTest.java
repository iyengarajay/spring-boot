package com.boot.test.db;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.boot.entity.EmailAddress;
import com.boot.entity.SocialMediaSite;
import com.boot.entity.User;
import com.boot.repository.SocialMediaSiteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SocialMediaSiteRepositoryTest {

	@Autowired
	private SocialMediaSiteRepository socialMediaSiteRepository;

	private SocialMediaSite facebook;

	@Before
	public void setUp() {
		facebook = new SocialMediaSite("Facebook", "Online Social Media and Networking Service");

	}

	@Test
	public void saveFacebookAndFindById() {
		facebook = socialMediaSiteRepository.save(facebook);
		assertThat(socialMediaSiteRepository.findOne(facebook.getId())).isEqualTo((facebook));

	}

	@Test
	public void saveFacebookAndFindBySocialMediaSiteName() {
		facebook = socialMediaSiteRepository.save(facebook);
		assertThat(socialMediaSiteRepository.findByName("Facebook")).isEqualTo((facebook));
	}

	@Test
	public void saveFacebookUsers() {

		User firstUser = new User("Mar", "Zuckerber", new EmailAddress("mark@mark.com"));
		facebook.addUser(firstUser);

		facebook = socialMediaSiteRepository.save(facebook);

		// user added is not null
		assertThat(socialMediaSiteRepository.findOne(facebook.getId()).getUsers()).isNotNull();

		// check the one user we added.
		assertThat(socialMediaSiteRepository.findOne(facebook.getId()).getUsers().size()).isEqualTo(1);

	}

}
