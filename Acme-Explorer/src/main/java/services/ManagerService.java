
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.Manager;
import domain.Note;
import domain.SocialIdentity;
import domain.SurvivalClass;
import domain.Trip;

@Service
@Transactional
public class ManagerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ManagerRepository	managerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private FolderService		folderService;


	// Constructors -----------------------------------------------------------

	public ManagerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Manager create() {

		Manager result = null;
		result = new Manager();
		result.setSuspicious(false);
		result.setSurvivalClasses(new ArrayList<SurvivalClass>());
		result.setTrips(new ArrayList<Trip>());
		result.setFolders(this.folderService.defaultFolders());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		result.setUserAccount(this.userAccountService.create("MANAGER"));
		return result;
	}

	public Manager findOne(final int managerId) {

		Manager result = null;
		result = this.managerRepository.findOne(managerId);
		return result;
	}

	public Collection<Manager> findAll() {

		Collection<Manager> result = null;
		result = this.managerRepository.findAll();
		return result;
	}

	public Manager save(final Manager manager) {

		Assert.notNull(manager);

		Manager result = null;

		if (manager.getId() == 0) {
			final Collection<Folder> folders = this.folderService.save(this.folderService.defaultFolders());
			manager.setFolders(folders);
			manager.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(manager.getUserAccount().getPassword(), null));
			result = this.managerRepository.save(manager);
		} else
			result = this.managerRepository.save(manager);

		return result;
	}

	// Other business methods -------------------------------------------------

	public Collection<Double> avgMinAvgMinMaxDesvTripsPerManager() {

		Collection<Double> result = null;
		result = this.managerRepository.avgMinMaxDesvTripsPerManager();
		return result;
	}

	public String ratioManagersSuspicious() {

		String result = null;
		result = this.managerRepository.ratioManagersSuspicious();
		return result;
	}

	public Manager findByPrincipal() {

		Manager result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		if (userAccount == null)
			result = null;
		else
			result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Manager findByUserAccountId(final int userAccountId) {

		Manager result = null;
		result = this.managerRepository.findByUserAccounId(userAccountId);
		return result;
	}

	public Collection<SurvivalClass> findSurvivalClassesByManagerId(final int managerId) {

		Collection<SurvivalClass> result = null;
		result = this.managerRepository.findSurvivalClassesByManagerId(managerId);
		return result;

	}

	public Collection<Note> findNotesByManager(final int managerId) {
		Collection<Note> notes = new ArrayList<>();
		notes = this.managerRepository.findNotesByManager(managerId);
		return notes;

	}
}
