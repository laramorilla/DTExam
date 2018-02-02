
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.PersonalRecord;
import domain.Ranger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PersonalRecordServiceTest extends AbstractTest {

	// Service under test ---------------------------------------

	@Autowired
	private PersonalRecordService	personalRecordService;

	@Autowired
	private RangerService			rangerService;


	// Tests ----------------------------------------------------

	@Test
	public void TestCreate() {
		PersonalRecord personalRecord = null;

		super.authenticate("ranger1");
		personalRecord = this.personalRecordService.create();

		Assert.notNull(personalRecord);

	}

	@Test
	public void TestSave() {
		Ranger ranger1 = null;
		PersonalRecord personalRecord = null;
		PersonalRecord saved = null;

		super.authenticate("ranger1");
		ranger1 = this.rangerService.findByPrincipal();
		personalRecord = this.personalRecordService.create();
		personalRecord.setEmail("test@gmail.com");
		personalRecord.setFullName("Test save");
		personalRecord.setLink("https://www.link.com");
		personalRecord.setPhone("11111111");
		personalRecord.setPhoto("http://www.photo.com");

		saved = this.personalRecordService.save(personalRecord);

		Assert.isTrue(ranger1.getCurriculum().getPersonalRecord().equals(saved));

	}

	@Test
	public void TestDelete() {
		Ranger ranger1 = null;
		PersonalRecord personalRecord = null;
		PersonalRecord saved = null;

		super.authenticate("ranger1");
		ranger1 = this.rangerService.findByPrincipal();
		personalRecord = this.personalRecordService.create();
		personalRecord.setEmail("test@gmail.com");
		personalRecord.setFullName("Test save");
		personalRecord.setLink("https://www.link.com");
		personalRecord.setPhone("11111111");
		personalRecord.setPhoto("http://www.photo.com");

		saved = this.personalRecordService.save(personalRecord);

		this.personalRecordService.delete(saved);
		Assert.isNull(ranger1.getCurriculum().getPersonalRecord());

	}

}
