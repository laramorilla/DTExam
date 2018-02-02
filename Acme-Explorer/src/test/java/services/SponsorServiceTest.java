
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
import domain.Folder;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private SponsorService	sponsorService;

	@Autowired
	private ActorService	actorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Sponsor sponsor = null;

		sponsor = this.sponsorService.create();

		Assert.notNull(sponsor.getSponsorships());
		Assert.notNull(sponsor.getFolders());
		Assert.notNull(sponsor.getSocialIdentities());
		Assert.notNull(sponsor.getUserAccount());
		Assert.isNull(sponsor.getName());
		Assert.isNull(sponsor.getSurname());
		Assert.isNull(sponsor.getEmail());
		Assert.isNull(sponsor.getAddress());
		Assert.isNull(sponsor.getPhone());
	}

	@Test
	public void testSave() {

		Sponsor sponsor = null;
		Sponsor saved = null;
		Actor actor = null;
		Collection<Folder> folders = null;

		sponsor = this.sponsorService.create();
		sponsor.setName("Sponsor 1");
		sponsor.setSurname("Sponsor 1");
		sponsor.setEmail("sponsor1@acmeexplorer.com");
		sponsor.setAddress("Sponsor 1 Street");
		sponsor.setPhone("111111111");
		sponsor.getUserAccount().setUsername("sponsor11");
		sponsor.getUserAccount().setPassword("sponsor11");

		saved = this.sponsorService.save(sponsor);
		actor = this.actorService.findOne(saved.getId());
		folders = actor.getFolders();

		Assert.isTrue(actor.equals(saved));
		Assert.isTrue(folders.size() == 5);
	}

	@Test
	public void testFindByPrincipal() {

		super.authenticate("sponsor1");

		Sponsor sponsor = null;

		sponsor = this.sponsorService.findByPrincipal();
		Assert.isTrue(sponsor.getUserAccount().getUsername().equals("sponsor1"));

		super.unauthenticate();
	}

}
