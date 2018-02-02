
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalClassRepository;
import domain.Explorer;
import domain.Manager;
import domain.SurvivalClass;
import domain.Trip;

@Service
@Transactional
public class SurvivalClassService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SurvivalClassRepository	survivalClassRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ExplorerService			explorerService;

	@Autowired
	private ManagerService			managerService;


	// Constructors -----------------------------------------------------------

	public SurvivalClassService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SurvivalClass create() {

		SurvivalClass result = null;

		result = new SurvivalClass();

		result.setExplorers(new ArrayList<Explorer>());

		return result;
	}

	public SurvivalClass save(final SurvivalClass survivalClass) {

		Assert.notNull(survivalClass);

		SurvivalClass result = null;
		Explorer explorer = null;
		Manager manager = null;
		Boolean isExplorer = null;
		Boolean isManager = null;

		isManager = this.checkByPrincipalManager(survivalClass);
		isExplorer = this.checkByPrincipalExplorer();

		Assert.isTrue(isManager || isExplorer);

		if (isManager) {
			manager = this.managerService.findByPrincipal();
			result = this.survivalClassRepository.save(survivalClass);
			manager.getSurvivalClasses().add(result);
		} else if (isExplorer) {

			explorer = this.explorerService.findByPrincipal();
			Assert.isTrue(this.checkEnrolAnExplorerToSurvivalClass(explorer, survivalClass));

			explorer.getSurvivalClasses().add(survivalClass);
			survivalClass.getExplorers().add(explorer);

			this.explorerService.save(explorer);
			result = this.survivalClassRepository.save(survivalClass);
		}

		return result;
	}

	public void delete(final SurvivalClass survivalClass) {

		Assert.notNull(survivalClass);
		Assert.isTrue(this.checkByPrincipalManager(survivalClass));
		Manager manager = null;

		manager = this.managerService.findByPrincipal();
		manager.getSurvivalClasses().remove(survivalClass);
		this.managerService.save(manager);

		this.survivalClassRepository.delete(survivalClass);
	}

	public SurvivalClass findOne(final int id) {
		return this.survivalClassRepository.findOne(id);
	}

	public Collection<SurvivalClass> findAll() {
		return this.survivalClassRepository.findAll();
	}

	// Other business methods -------------------------------------------------

	public void deleteSurvivalClasses(final Trip trip) {
		final Collection<SurvivalClass> survivalClasses = trip.getSurvivalClasses();
		for (final SurvivalClass s : survivalClasses)
			this.delete(s);
	}

	public Collection<SurvivalClass> findByManager(final Manager manager) {

		Collection<SurvivalClass> result = null;
		result = this.survivalClassRepository.findByManager(manager.getId());
		return result;
	}

	public Collection<SurvivalClass> findByExplorer(final Explorer explorer) {

		Collection<SurvivalClass> result = null;
		result = this.survivalClassRepository.findByExplorer(explorer.getId());
		return result;
	}

	public SurvivalClass findOneToEdit(final int id) {

		Assert.notNull(id);

		SurvivalClass result = null;

		result = this.findOne(id);
		this.checkByPrincipalManager(result);
		Assert.isTrue(this.checkByPrincipalManager(result) == true);

		return result;
	}

	public Boolean checkByPrincipalManager(final SurvivalClass survivalClass) {

		Manager manager = null;
		Boolean result = false;

		manager = this.managerService.findByPrincipal();

		if (manager != null && survivalClass.getId() == 0)
			result = true;
		else if (manager != null && this.managerService.findSurvivalClassesByManagerId(manager.getId()).contains(survivalClass))
			result = true;

		return result;
	}
	public Boolean checkByPrincipalExplorer() {

		Explorer explorer = null;
		Boolean result = false;

		explorer = this.explorerService.findByPrincipal();

		if (explorer != null && explorer instanceof Explorer)
			result = true;

		return result;
	}

	public SurvivalClass enrolAnExplorerToSurvivalClass(final Explorer explorer, final SurvivalClass survivalClass) {
		Assert.notNull(explorer);
		Assert.notNull(survivalClass);
		Assert.isTrue(this.checkEnrolAnExplorerToSurvivalClass(explorer, survivalClass));

		SurvivalClass result = null;

		explorer.getSurvivalClasses().add(survivalClass);
		survivalClass.getExplorers().add(explorer);

		this.explorerService.save(explorer);
		result = this.survivalClassRepository.save(survivalClass);

		return result;

	}

	public SurvivalClass disenrolAnExplorerToSurvivalClass(final Explorer explorer, final SurvivalClass survivalClass) {
		Assert.notNull(explorer);
		Assert.notNull(survivalClass);
		Assert.isTrue(this.checkDisenrolAnExplorerToSurvivalClass(explorer, survivalClass));

		SurvivalClass result = null;

		explorer.getSurvivalClasses().remove(survivalClass);
		survivalClass.getExplorers().remove(explorer);

		this.explorerService.save(explorer);
		result = this.survivalClassRepository.save(survivalClass);

		return result;
	}

	private boolean checkEnrolAnExplorerToSurvivalClass(final Explorer explorer, final SurvivalClass survivalClass) {

		Assert.notNull(explorer);
		Assert.notNull(survivalClass);

		Date moment = null;
		Boolean isTripFinished = null;
		Boolean isSurvivalClassFinished = null;
		Boolean isExplorerAssignedOnSurvivalClass = null;
		Boolean canExplorerEnrolOnSurvivalClass = null;

		moment = new Date(System.currentTimeMillis());
		isTripFinished = moment.after(survivalClass.getTrip().getStartDateTrip());
		isSurvivalClassFinished = moment.after(survivalClass.getMoment());
		isExplorerAssignedOnSurvivalClass = this.survivalClassRepository.isEnrolExplorerToSurvivalClass(survivalClass.getId(), explorer.getId());
		canExplorerEnrolOnSurvivalClass = this.survivalClassRepository.checkEnrolExplorerToSurvivalClass(survivalClass.getId(), explorer.getId());

		return !isTripFinished && !isSurvivalClassFinished && !isExplorerAssignedOnSurvivalClass && canExplorerEnrolOnSurvivalClass;
	}

	private boolean checkDisenrolAnExplorerToSurvivalClass(final Explorer explorer, final SurvivalClass survivalClass) {

		Assert.notNull(explorer);
		Assert.notNull(survivalClass);

		Date moment = null;
		Boolean tripFinished = null;
		Boolean isSurvivalClassFinished = null;
		Boolean isExplorerAssignedOnSurvivalClass = null;

		moment = new Date(System.currentTimeMillis());
		tripFinished = moment.after(survivalClass.getTrip().getStartDateTrip());
		isSurvivalClassFinished = moment.after(survivalClass.getMoment());
		isExplorerAssignedOnSurvivalClass = this.survivalClassRepository.isEnrolExplorerToSurvivalClass(survivalClass.getId(), explorer.getId());

		return !tripFinished && !isSurvivalClassFinished && isExplorerAssignedOnSurvivalClass;
	}

	public SurvivalClass findByTrip(final int tripID) {
		return this.survivalClassRepository.findByTrip(tripID);
	}

	public Collection<SurvivalClass> findByTripId(final int tripID) {
		return this.survivalClassRepository.findByTripId(tripID);
	}
}
