
package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curriculum;
import domain.ProfessionalRecord;
import domain.Ranger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ProfessionalRecordServiceTest extends AbstractTest {

	// Service under test --------------------------------------------------
	@Autowired
	private ProfessionalRecordService	professionalRecordService;

	@Autowired
	private CurriculumService			curriculumService;

	@Autowired
	private RangerService				rangerService;


	// Tests ---------------------------------------------------------------

	@Test
	public void testCreate() {

		super.authenticate("ranger1");

		ProfessionalRecord professionalRecord = null;

		professionalRecord = this.professionalRecordService.create();
		Assert.isNull(professionalRecord.getComments());

		super.unauthenticate();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSave() {

		super.authenticate("ranger1");

		Ranger ranger = null;
		ProfessionalRecord professionalRecord = null;
		ProfessionalRecord saved = null;
		ProfessionalRecord professionalRecordRetrieved = null;
		Curriculum curriculum = null;

		ranger = this.rangerService.findByPrincipal();

		professionalRecord = this.professionalRecordService.create();
		professionalRecord.setRole("Test");
		professionalRecord.setStartDate(new Date(2010, 01, 12, 15, 00));
		professionalRecord.setEndDate(new Date(2011, 01, 12, 15, 00));
		professionalRecord.setCompany("test");

		curriculum = this.curriculumService.findOne(ranger.getCurriculum().getId());
		saved = this.professionalRecordService.save(professionalRecord);
		professionalRecordRetrieved = this.professionalRecordService.findOne(saved.getId());

		Assert.notNull(professionalRecordRetrieved);
		Assert.isTrue(curriculum.getProfessionalRecords().contains(professionalRecordRetrieved));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDelete() {

		super.authenticate("ranger1");

		ProfessionalRecord professionalRecord = null;
		ProfessionalRecord saved = null;

		professionalRecord = this.professionalRecordService.create();
		professionalRecord.setRole("Test");
		professionalRecord.setStartDate(new Date(2010, 01, 11, 15, 00));
		professionalRecord.setEndDate(new Date(2011, 01, 11, 15, 00));
		professionalRecord.setCompany("test");

		saved = this.professionalRecordService.save(professionalRecord);
		this.professionalRecordService.delete(saved);

		Assert.isNull(this.professionalRecordService.findOne(saved.getId()));
	}
}
