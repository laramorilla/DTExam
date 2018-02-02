
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.LoginService;
import security.UserAccount;
import domain.Configuration;
import domain.Explorer;
import domain.Finder;
import domain.Trip;

@Service
@Transactional
public class FinderService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FinderRepository		finderRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ExplorerService			explorerService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructors -----------------------------------------------------------

	public FinderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Finder create() {

		Finder result = null;
		result = new Finder();
		result.setLastUpdate(new Date(System.currentTimeMillis() - 1000));
		result.setTrips(new ArrayList<Trip>());
		return result;
	}

	public Finder findOne(final int finderId) {

		Finder result = null;
		result = this.finderRepository.findOne(finderId);
		return result;
	}

	public Collection<Finder> findAll() {

		Collection<Finder> result = null;
		result = this.finderRepository.findAll();
		return result;
	}

	public Finder save(final Finder finder) {

		Assert.notNull(finder);

		Finder result = null;
		finder.setLastUpdate(new Date(System.currentTimeMillis() - 1000));
		result = this.finderRepository.save(finder);

		return result;
	}

	// Other business methods -------------------------------------------------

	public Finder findByPrincipal() {

		Finder result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Finder findByUserAccountId(final int userAccountId) {

		Finder result = null;
		result = this.explorerService.findFinderByUserAccountId(userAccountId);
		return result;
	}

	public void checkByPrincipal(final Finder finder) {

		Explorer explorer = null;
		Explorer explorerFinder = null;

		explorer = this.explorerService.findByPrincipal();
		explorerFinder = this.explorerService.findExplorerByFinderId(finder.getId());

		Assert.isTrue(explorer.equals(explorerFinder));
	}

	public Finder assignTripsToFinder(final Finder finder, final Collection<Trip> trips) {
		Assert.notNull(finder);

		finder.setTrips(trips);

		return finder;

	}

	public void deleteReferenceTrip(final Trip trip) {
		final Collection<Finder> finders = this.finderRepository.findFinderByTrip(trip);

		for (final Finder f : finders) {
			f.getTrips().remove(trip);
			this.save(f);
		}

	}

	public Collection<Trip> findTripsPerFinder(final Finder finder) {

		String keyword = "Trip";
		final Configuration configuration = this.configurationService.findAll().iterator().next();

		final int maxTripDisplay = configuration.getMaxTripsDisplay();
		final Calendar startDateCalendar = Calendar.getInstance();
		startDateCalendar.set(1950, 01, 01, 00, 00);
		Date startDate = new Date();
		startDate = startDateCalendar.getTime();
		final Calendar endDateCalendar = Calendar.getInstance();
		endDateCalendar.set(2100, 01, 01, 00, 00);
		Date endDate = new Date();
		endDate = endDateCalendar.getTime();
		Double priceMin = 0.0;
		Double priceMax = 9999.0;

		if (!finder.getKeyword().isEmpty())
			keyword = finder.getKeyword();
		if (finder.getStartDateTripMax() != null)
			startDate = finder.getStartDateTripMax();
		if (finder.getStartDateTripMin() != null)
			endDate = finder.getStartDateTripMin();
		if (finder.getPriceMax() != null)
			priceMax = finder.getPriceMax();
		if (finder.getPriceMin() != null)
			priceMin = finder.getPriceMin();

		List<Trip> res = new ArrayList<Trip>();
		Collection<Trip> aux = null;
		aux = new ArrayList<Trip>();

		aux = this.finderRepository.findTripsPerFinder(keyword, startDate, endDate, priceMin, priceMax);

		if (aux.size() > maxTripDisplay) {
			res = new ArrayList<Trip>(aux);
			return res.subList(0, maxTripDisplay);
		}
		res = new ArrayList<Trip>(aux);
		return res;
	}

	public boolean dateCache(final Finder finder) {

		boolean res = false;
		final Long actualDate = new Date().getTime();
		final Long dateFinder = finder.getLastUpdate().getTime();

		final Long compareDate = Math.abs(actualDate - dateFinder);

		final Configuration configuration = this.configurationService.findAll().iterator().next();
		final Long config = (long) configuration.getFinderCache();

		final Long limit = (config * 60) * 1000;
		if (compareDate >= limit)
			res = true;
		return res;
	}

	public void delete(final Finder finder) {
		this.finderRepository.delete(finder);
	}
}
