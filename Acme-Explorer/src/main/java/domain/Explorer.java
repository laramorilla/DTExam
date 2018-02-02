
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Explorer extends Actor {

	// Constructors -----------------------------------------------------------

	public Explorer() {
		super();
	}


	// Attributes -------------------------------------------------------------
	// Relationships ----------------------------------------------------------

	private Collection<Story>			stories;
	private Finder						finder;
	private Collection<Application>		applications;
	private Collection<SurvivalClass>	survivalClasses;
	private Collection<Contact>			contacts;


	@NotNull
	@Valid
	@OneToMany(mappedBy = "explorer")
	public Collection<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(final Collection<Contact> contacts) {
		this.contacts = contacts;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "writer")
	public Collection<Story> getStories() {
		return this.stories;
	}

	public void setStories(final Collection<Story> stories) {
		this.stories = stories;
	}

	@Valid
	@OneToOne(optional = true)
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "applicant")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

	@NotNull
	@Valid
	@ManyToMany(mappedBy = "explorers")
	public Collection<SurvivalClass> getSurvivalClasses() {
		return this.survivalClasses;
	}

	public void setSurvivalClasses(final Collection<SurvivalClass> survivalClasses) {
		this.survivalClasses = survivalClasses;
	}
}
