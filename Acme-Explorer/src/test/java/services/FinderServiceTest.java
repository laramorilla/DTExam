
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Explorer;
import domain.Finder;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private FinderService	finderService;

	@Autowired
	private ExplorerService	explorerService;

	@Autowired
	private TripService		tripService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Finder finder;

		super.authenticate("explorer1");

		finder = this.finderService.create();
		Assert.isNull(finder.getKeyword());
		Assert.isNull(finder.getPriceMin());
		Assert.isNull(finder.getPriceMax());
		Assert.isNull(finder.getStartDateTripMin());
		Assert.isNull(finder.getStartDateTripMax());
		Assert.notNull(finder.getLastUpdate());

		super.unauthenticate();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSave() {

		Finder finder, saved;

		super.authenticate("explorer1");

		finder = this.finderService.findByPrincipal();
		finder.setKeyword("Keyword test");
		final Double priceMin, priceMax;
		priceMin = 50.00;
		priceMax = 100.00;

		final Date dateMin = new Date(2017, 10, 10, 12, 00);
		final Date dateMax = new Date(2017, 11, 10, 12, 00);

		finder.setPriceMin(priceMin);
		finder.setPriceMax(priceMax);
		finder.setStartDateTripMin(dateMin);
		finder.setStartDateTripMax(dateMax);

		final Date lastUpdate = finder.getLastUpdate();
		saved = this.finderService.save(finder);

		Assert.isTrue(saved.getKeyword() == "Keyword test");
		Assert.isTrue(saved.getPriceMin() == priceMin);
		Assert.isTrue(saved.getPriceMax() == priceMax);
		Assert.isTrue(saved.getStartDateTripMin() == dateMin);
		Assert.isTrue(saved.getStartDateTripMax() == dateMax);
		Assert.isTrue(saved.getLastUpdate() != lastUpdate);

		super.authenticate(null);
	}

	@Test
	public void testFindByPrincipal() {

		Finder finder;

		super.authenticate("explorer1");

		finder = this.finderService.findByPrincipal();
		this.finderService.checkByPrincipal(finder);

		super.unauthenticate();
	}

	@Test
	public void testAssignTripsToFinder() {
		Finder finder = null;
		Explorer explorer = null;
		Trip trip1 = null;
		Trip trip2 = null;

		final Collection<Trip> trips = new ArrayList<>();

		super.authenticate("explorer2");

		explorer = this.explorerService.findByPrincipal();
		finder = explorer.getFinder();
		trip1 = this.tripService.findOne(7278);
		trip2 = this.tripService.findOne(7279);

		trips.add(trip1);
		trips.add(trip2);
		Assert.isTrue(!finder.getTrips().contains(trip1));

		this.finderService.assignTripsToFinder(finder, trips);

		Assert.isTrue(finder.getTrips().contains(trip1));

		super.unauthenticate();
	}
}
