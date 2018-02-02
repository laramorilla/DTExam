
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Explorer;
import domain.Finder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ExplorerServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private ExplorerService	explorerService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private FinderService	finderService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Explorer explorer = null;

		explorer = this.explorerService.create();

		Assert.notNull(explorer.getApplications());
		Assert.notNull(explorer.getStories());
		Assert.notNull(explorer.getFinder());
		Assert.notNull(explorer.getFolders());
		Assert.notNull(explorer.getSocialIdentities());
		Assert.notNull(explorer.getUserAccount());
		Assert.isNull(explorer.getName());
		Assert.isNull(explorer.getSurname());
		Assert.isNull(explorer.getEmail());
		Assert.isNull(explorer.getAddress());
		Assert.isNull(explorer.getPhone());
	}

	@Test
	public void testSave() {

		Explorer explorer = null;
		Explorer saved = null;
		Actor actor = null;
		Finder finder = null;

		explorer = this.explorerService.create();

		explorer.setName("Explorer 1");
		explorer.setSurname("Explorer 1");
		explorer.setEmail("explorer1@acmeexplorer.com");
		explorer.setAddress("Explorer 1 Street");
		explorer.setPhone("111111111");
		explorer.getUserAccount().setUsername("explorer11");
		explorer.getUserAccount().setPassword("explorer11");

		saved = this.explorerService.save(explorer);
		actor = this.actorService.findOne(saved.getId());
		finder = this.finderService.findOne(explorer.getFinder().getId());

		Assert.isTrue(actor.equals(saved));
		Assert.isTrue(actor.getFolders().size() == 5);
		Assert.notNull(finder);
	}

	@Test
	public void testFindByPrincipal() {

		super.authenticate("explorer1");

		Explorer explorer = null;

		explorer = this.explorerService.findByPrincipal();
		Assert.isTrue(explorer.getUserAccount().getUsername().equals("explorer1"));

		super.unauthenticate();
	}

}
