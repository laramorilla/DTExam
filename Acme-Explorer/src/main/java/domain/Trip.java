
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Trip extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Trip() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	ticker;
	private String	title;
	private String	description;
	private String	requirements;
	private Date	publicationDate;
	private Date	startDateTrip;
	private Date	endDateTrip;
	private String	cancelledReason;
	private double	price;


	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^(\\d{2})(\\d{2})(\\d{2})\\-([A-Z]{4})$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getRequirements() {
		return this.requirements;
	}

	public void setRequirements(final String requirements) {
		this.requirements = requirements;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getStartDateTrip() {
		return this.startDateTrip;
	}

	public void setStartDateTrip(final Date startDateTrip) {
		this.startDateTrip = startDateTrip;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getEndDateTrip() {
		return this.endDateTrip;
	}

	public void setEndDateTrip(final Date endDateTrip) {
		this.endDateTrip = endDateTrip;
	}

	public String getCancelledReason() {
		return this.cancelledReason;
	}

	public void setCancelledReason(final String cancelledReason) {
		this.cancelledReason = cancelledReason;
	}

	@NotNull
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}


	// Relationships ----------------------------------------------------------

	private Collection<Stage>			stages;
	private Category					category;
	private Collection<HasValue>		hasValues;
	private LegalText					legalText;
	private Collection<Note>			notes;
	private Collection<AuditRecord>		auditRecords;
	private Collection<Sponsorship>		sponsorships;
	private Collection<SurvivalClass>	survivalClasses;
	private Manager						manager;
	private Ranger						ranger;
	private Collection<Story>			stories;
	private Collection<Application>		applications;


	@Valid
	@NotNull
	@OneToMany(cascade = {
		CascadeType.ALL
	})
	public Collection<Stage> getStages() {
		return this.stages;
	}

	public void setStages(final Collection<Stage> stages) {
		this.stages = stages;
	}

	@Valid
	@ManyToOne(optional = true)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<HasValue> getHasValues() {
		return this.hasValues;
	}

	public void setHasValues(final Collection<HasValue> hasValues) {
		this.hasValues = hasValues;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public LegalText getLegalText() {
		return this.legalText;
	}

	public void setLegalText(final LegalText legalText) {
		this.legalText = legalText;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<AuditRecord> getAuditRecords() {
		return this.auditRecords;
	}

	public void setAuditRecords(final Collection<AuditRecord> auditRecords) {
		this.auditRecords = auditRecords;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<Sponsorship> getSponsorships() {
		return this.sponsorships;
	}

	public void setSponsorships(final Collection<Sponsorship> sponsorships) {
		this.sponsorships = sponsorships;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<SurvivalClass> getSurvivalClasses() {
		return this.survivalClasses;
	}

	public void setSurvivalClasses(final Collection<SurvivalClass> survivalClasses) {
		this.survivalClasses = survivalClasses;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Ranger getRanger() {
		return this.ranger;
	}

	public void setRanger(final Ranger ranger) {
		this.ranger = ranger;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<Story> getStories() {
		return this.stories;
	}

	public void setStories(final Collection<Story> stories) {
		this.stories = stories;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

}
