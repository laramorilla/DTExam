
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TripServiceTest extends AbstractTest {

	@Autowired
	private TripService		tripService;

	@Autowired
	private ManagerService	managerService;


	@Test
	public void testCreate() {

		super.authenticate("manager1");

		Trip trip = null;

		trip = this.tripService.create();

		Assert.notNull(trip.getTicker());
		Assert.isNull(trip.getTitle());
		Assert.isNull(trip.getDescription());
		Assert.isNull(trip.getRequirements());
		Assert.isNull(trip.getPublicationDate());
		Assert.isNull(trip.getStartDateTrip());
		Assert.isNull(trip.getEndDateTrip());
		Assert.isNull(trip.getCancelledReason());
		Assert.notNull(trip.getPrice());
		Assert.notNull(trip.getNotes());
		Assert.notNull(trip.getAuditRecords());
		Assert.notNull(trip.getStories());
		Assert.notNull(trip.getApplications());
		Assert.notNull(trip.getSurvivalClasses());
		Assert.notNull(trip.getSponsorships());
		Assert.notNull(trip.getStages());
		Assert.notNull(trip.getHasValues());

		super.unauthenticate();
	}

	@Test
	public void testSave() {

		super.authenticate("manager1");

		Trip trip = null;
		Trip tripSaved = null;
		Collection<Trip> trips = null;
		Calendar res = null;

		trip = this.tripService.findAll().iterator().next();

		trips = new ArrayList<Trip>();

		trip.setDescription("Description TEST");

		res = Calendar.getInstance();
		res.set(2020 + 120, 1, 1, 20, 20, 20);
		trip.setPublicationDate(res.getTime());

		res = Calendar.getInstance();
		res.set(2020 + 120, 2, 1, 20, 20, 20);
		trip.setStartDateTrip(res.getTime());

		res = Calendar.getInstance();
		res.set(2020 + 120, 3, 1, 20, 20, 20);
		trip.setEndDateTrip(res.getTime());

		tripSaved = this.tripService.save(trip);
		Assert.isTrue(tripSaved.getDescription().equals("Description TEST"));
		trips = this.tripService.findAll();

		Assert.isTrue(trips.contains(tripSaved));

		super.unauthenticate();
	}
	@Test
	public void testDelete() {

		super.authenticate("manager1");

		Trip trip = null;
		Trip tripSaved = null;
		Trip tripDelete = null;
		Calendar res = null;

		trip = this.tripService.findAll().iterator().next();

		trip.setDescription("Description TEST");

		res = Calendar.getInstance();
		res.set(1900 + 120, 1, 1, 20, 20, 20);
		trip.setPublicationDate(res.getTime());
		tripSaved = this.tripService.save(trip);

		this.tripService.delete(tripSaved);

		tripDelete = this.tripService.findOne(trip.getId());

		Assert.isNull(tripDelete);

		super.unauthenticate();
	}

	@Test
	public void testListTripByManager() {

		super.authenticate("manager1");

		final int managerId = this.managerService.findByPrincipal().getId();
		final Collection<Trip> trips = this.tripService.listTripPerManager(managerId);
		Assert.isTrue(!trips.isEmpty());

		super.unauthenticate();
	}

	@Test
	public void testAvgMinMaxDevApplicationsPerTrip() {

		Assert.isTrue(!this.tripService.avgMinMaxDevApplicationsPerTrip().isEmpty());
	}

	@Test
	public void testAvgMinMaxDevPriceOfTheTrips() {

		this.tripService.avgMinMaxDevPriceOfTheTrips();
	}

	@Test
	public void testRatioOfTripsCancelledVsTotalTripsOrganized() {

		Assert.notNull(this.tripService.ratioOfTripsCancelledVsTotalTripsOrganized());
	}

	@Test
	public void testListingTrips10PercentMoreApplicantionsThanAvg() {

		Assert.isTrue(!this.tripService.listingTrips10PercentMoreApplicantionsThanAvg().isEmpty());
	}

	@Test
	public void testMinMaxAvgDevNotesPerTrip() {
		Assert.isTrue(!this.tripService.minMaxAvgDevNotesPerTrip().isEmpty());
	}

	@Test
	public void testMinMaxAvgDevAuditRecordPerTrip() {
		Assert.isTrue(!this.tripService.minMaxAvgDevAuditRecordPerTrip().isEmpty());
	}

	@Test
	public void testRatioOfTripsWithAnyAuditRecord() {
		Assert.notNull(this.tripService.ratioOfTripsWithAnyAuditRecord());
	}

	@Test
	public void testAuditorPerTrip() {
		super.authenticate("manager1");

		final Iterator<Trip> trips = this.tripService.findAll().iterator();
		Trip trip = trips.next();
		while (this.tripService.auditorPerTrip(trip.getId()).isEmpty())
			trip = trips.next();
		Assert.isTrue(!this.tripService.auditorPerTrip(trip.getId()).isEmpty());

		super.unauthenticate();
	}

	@Test
	public void testFindAvailableTrips() {
		super.authenticate("explorer2");

		Collection<Trip> result = null;
		Date moment = null;

		moment = new Date(System.currentTimeMillis());
		result = this.tripService.findAvailableTrips(moment);

		Assert.notNull(result);

		super.unauthenticate();
	}
}
