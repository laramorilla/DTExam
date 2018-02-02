
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HasValueRepository;
import domain.HasValue;
import domain.Manager;
import domain.Tag;
import domain.Trip;

@Service
@Transactional
public class HasValueService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private HasValueRepository	hasValueRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private TripService			tripService;

	@Autowired
	private ManagerService		managerService;


	// Constructors -----------------------------------------------------------

	public HasValueService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public HasValue create(final Trip trip) {

		Assert.notNull(trip);

		HasValue result = null;
		result = new HasValue();

		result.setTrip(trip);
		this.checkPrincipal(result);

		return result;

	}

	public Collection<HasValue> findAll() {

		Collection<HasValue> result = null;

		result = this.hasValueRepository.findAll();

		return result;
	}

	public HasValue findOne(final int hasValueId) {

		HasValue result = null;

		result = this.hasValueRepository.findOne(hasValueId);

		return result;

	}

	public HasValue findOneToEdit(final int hasValueId) {

		HasValue result;

		result = this.findOne(hasValueId);
		this.checkPrincipal(result);

		return result;
	}

	public HasValue save(final HasValue hasValue) {

		Assert.notNull(hasValue);
		this.checkPrincipal(hasValue);

		HasValue result;
		Trip trip;
		Tag tag;

		result = this.hasValueRepository.save(hasValue);
		if (hasValue.getId() == 0) {
			trip = hasValue.getTrip();
			trip.getHasValues().add(result);
			tag = hasValue.getTag();
			tag.getHasValues().add(result);
		}

		return result;
	}

	public void delete(final HasValue hasValue) {

		Assert.notNull(hasValue);
		this.checkPrincipal(hasValue);

		Trip trip;
		Tag tag;

		trip = hasValue.getTrip();
		trip.getHasValues().remove(hasValue);

		tag = hasValue.getTag();
		tag.getHasValues().remove(hasValue);

		this.hasValueRepository.delete(hasValue);
	}

	// Other business methods -------------------------------------------------

	public void checkPrincipal(final HasValue hasValue) {

		final Manager manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager != null && hasValue.getTrip().getManager().equals(manager));
	}

	public void deleteByTrip(final Trip trip) {
		final Collection<HasValue> hasValues = trip.getHasValues();
		this.hasValueRepository.delete(hasValues);

	}

	public void deleteByTag(final Tag tag) {

		Collection<HasValue> hasValues;

		hasValues = tag.getHasValues();
		for (final HasValue hasValue : hasValues)
			hasValue.getTrip().getHasValues().remove(hasValue);

		this.hasValueRepository.delete(hasValues);
	}

	public Collection<HasValue> findByTripId(final int tripId) {

		Collection<HasValue> result;
		Trip trip;

		trip = this.tripService.findOne(tripId);
		result = trip.getHasValues();

		return result;
	}

}
