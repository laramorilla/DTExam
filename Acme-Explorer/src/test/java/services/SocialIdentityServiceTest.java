
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.SocialIdentity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SocialIdentityServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private SocialIdentityService	socialIdentityService;

	@Autowired
	private ActorService			actorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		super.authenticate("admin1");

		SocialIdentity socialIdentity;
		socialIdentity = this.socialIdentityService.create();
		Assert.isNull(socialIdentity.getNick());
		Assert.isNull(socialIdentity.getNameSocialNetwork());
		Assert.isNull(socialIdentity.getLink());
		Assert.isNull(socialIdentity.getPhoto());

		super.unauthenticate();
	}

	@Test
	public void testSave() {

		super.authenticate("admin1");

		SocialIdentity socialIdentity = null;
		SocialIdentity saved = null;
		Collection<SocialIdentity> socialIdentities = null;

		socialIdentity = this.socialIdentityService.create();
		socialIdentity.setNick("Social identity nick");
		socialIdentity.setNameSocialNetwork("Social identity name social network");
		socialIdentity.setLink("http://www.link.com/");
		socialIdentity.setPhoto("http://www.photo.com/");

		saved = this.socialIdentityService.save(socialIdentity);

		socialIdentities = this.socialIdentityService.findByPrincipal();

		Assert.isTrue(socialIdentities.contains(saved));

		super.unauthenticate();
	}

	@Test
	public void testDelete() {

		super.authenticate("admin1");

		SocialIdentity socialIdentity = null;
		SocialIdentity saved = null;
		Collection<SocialIdentity> socialIdentities = null;

		socialIdentity = this.socialIdentityService.create();
		socialIdentity.setNick("Social identity nick");
		socialIdentity.setNameSocialNetwork("Social identity name social network");
		socialIdentity.setLink("http://www.link.com/");
		socialIdentity.setPhoto("http://www.photo.com/");

		saved = this.socialIdentityService.save(socialIdentity);

		this.socialIdentityService.delete(saved);

		socialIdentities = this.socialIdentityService.findByPrincipal();

		Assert.isTrue(!socialIdentities.contains(saved));

		super.unauthenticate();
	}

	@Test
	public void testFindOneToEditPositive() {

		super.authenticate("admin1");

		SocialIdentity socialIdentity = null;
		SocialIdentity saved = null;
		SocialIdentity socialIdentityRetrieved = null;

		socialIdentity = this.socialIdentityService.create();
		socialIdentity.setNick("Social identity nick");
		socialIdentity.setNameSocialNetwork("Social identity name social network");
		socialIdentity.setLink("http://www.link.com/");
		socialIdentity.setPhoto("http://www.photo.com/");

		saved = this.socialIdentityService.save(socialIdentity);
		socialIdentityRetrieved = this.socialIdentityService.findOneToEdit(saved.getId());
		Assert.isTrue(saved.equals(socialIdentityRetrieved));

		super.unauthenticate();
	}

	@Test
	public void testFindOneToEditNegative() {

		super.authenticate("admin1");

		SocialIdentity socialIdentity1 = null;

		socialIdentity1 = this.socialIdentityService.findOneToEdit(this.socialIdentityService.findByPrincipal().iterator().next().getId());

		super.unauthenticate();

		super.authenticate("auditor1");

		try {
			this.socialIdentityService.findOneToEdit(socialIdentity1.getId());
		} catch (final Exception e) {
			System.out.println(e);
		}

		super.unauthenticate();
	}

	@Test
	public void testFindByPrincipal() {

		super.authenticate("admin1");

		Actor actor = null;
		Collection<SocialIdentity> socialIdentities = null;

		actor = this.actorService.findByPrincipal();
		socialIdentities = this.socialIdentityService.findByPrincipal();

		Assert.isTrue(actor.getSocialIdentities().containsAll(socialIdentities));

		super.unauthenticate();
	}
}
