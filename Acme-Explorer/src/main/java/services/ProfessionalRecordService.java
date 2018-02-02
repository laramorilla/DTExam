
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import domain.Curriculum;
import domain.ProfessionalRecord;
import domain.Ranger;

@Service
@Transactional
public class ProfessionalRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ProfessionalRecordRepository	professionalRecordRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CurriculumService				curriculumService;

	@Autowired
	private RangerService					rangerService;


	// Constructors -----------------------------------------------------------

	public ProfessionalRecordService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ProfessionalRecord create() {
		ProfessionalRecord professionalRecord;

		professionalRecord = new ProfessionalRecord();

		return professionalRecord;
	}

	public ProfessionalRecord save(final ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);
		Assert.isTrue(professionalRecord.getStartDate().before(professionalRecord.getEndDate()));

		ProfessionalRecord saved = null;
		Ranger ranger = null;

		ranger = this.rangerService.findByPrincipal();

		if (professionalRecord.getId() != 0) {
			Assert.isTrue(ranger.getCurriculum().getProfessionalRecords().contains(professionalRecord));
			saved = this.professionalRecordRepository.save(professionalRecord);
		}

		if (professionalRecord.getId() == 0) {
			saved = this.professionalRecordRepository.save(professionalRecord);
			ranger.getCurriculum().getProfessionalRecords().add(saved);
			this.curriculumService.save(ranger.getCurriculum());
		}

		return saved;
	}

	public void delete(final ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);

		Curriculum curriculum = null;
		Ranger ranger = null;

		ranger = this.rangerService.findByPrincipal();
		curriculum = ranger.getCurriculum();

		curriculum.getProfessionalRecords().remove(professionalRecord);
		this.curriculumService.save(curriculum);
		this.professionalRecordRepository.delete(professionalRecord);
	}

	public ProfessionalRecord findOne(final int id) {
		return this.professionalRecordRepository.findOne(id);
	}

	public Collection<ProfessionalRecord> findAll() {
		return this.professionalRecordRepository.findAll();
	}

	// Other business methods -------------------------------------------------
}
