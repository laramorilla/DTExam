
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.AuditRecord;
import domain.Auditor;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditRecordServiceTest extends AbstractTest {

	// Service under tests

	@Autowired
	private AuditRecordService	auditRecordService;

	@Autowired
	private AuditorService		auditorService;

	@Autowired
	private TripService			tripService;


	// Tests ---------------------------------------------------------

	@Test
	public void testCreate() {

		AuditRecord auditRecord;

		super.authenticate("auditor1");

		auditRecord = this.auditRecordService.create();
		Assert.notNull(auditRecord.getMoment());
		Assert.isNull(auditRecord.getDescription());
		Assert.notNull(auditRecord.getFinalVersion());
		Assert.isNull(auditRecord.getTitle());

		super.unauthenticate();

	}

	@Test
	public void testSave() {

		AuditRecord auditRecord, saved;

		super.authenticate("auditor1");

		auditRecord = this.auditRecordService.create();
		final Collection<String> attachments = new ArrayList<String>();
		final Trip trip = this.tripService.findOne(this.tripService.findAll().iterator().next().getId());

		auditRecord.setAttachments(attachments);
		auditRecord.setTrip(trip);
		auditRecord.setDescription("description1");
		auditRecord.setFinalVersion(true);
		auditRecord.setTitle("title1");

		saved = this.auditRecordService.save(auditRecord);
		final Collection<AuditRecord> auditRecords = this.auditRecordService.findAll();

		Assert.isTrue(auditRecords.contains(saved));

		super.unauthenticate();
	}

	@Test
	public void testDelete() {

		AuditRecord auditRecord, saved;

		super.authenticate("auditor1");

		auditRecord = this.auditRecordService.create();
		final Collection<String> attachments = new ArrayList<String>();
		final Trip trip = this.tripService.findOne(this.tripService.findAll().iterator().next().getId());
		final String att = "http://www.attachement1.com";

		auditRecord.setAttachments(attachments);
		attachments.add(att);
		auditRecord.setTrip(trip);
		auditRecord.setDescription("description1");
		auditRecord.setFinalVersion(false);
		auditRecord.setTitle("title1");

		saved = this.auditRecordService.save(auditRecord);
		this.auditRecordService.delete(saved);
		final Collection<AuditRecord> auditRecords = this.auditRecordService.findAll();

		Assert.isTrue(!auditRecords.contains(saved));

		super.unauthenticate();

	}

	@Test
	public void testFindOneToEdit() {

		AuditRecord auditRecord, saved, auditRecordRetrieved;

		super.authenticate("auditor1");

		final Collection<String> attachments = new ArrayList<String>();
		auditRecord = this.auditRecordService.create();
		final String att = "http://www.attachement1.com";
		final Trip trip = this.tripService.findOne(this.tripService.findAll().iterator().next().getId());

		attachments.add(att);
		auditRecord.setAttachments(attachments);
		auditRecord.setTrip(trip);
		auditRecord.setDescription("description1");
		auditRecord.setFinalVersion(false);
		auditRecord.setTitle("title1");

		saved = this.auditRecordService.save(auditRecord);
		auditRecordRetrieved = this.auditRecordService.findOneToEdit(saved.getId());

		Assert.isTrue(saved == auditRecordRetrieved);

		super.unauthenticate();
	}

	@Test
	public void testDeleteByAuditor() {

		super.authenticate("auditor1");

		final Auditor auditor = this.auditorService.findByPrincipal();
		final Collection<AuditRecord> auditRecords = auditor.getAuditRecords();

		this.auditRecordService.deleteByAuditor(auditor);

		Assert.isTrue(!auditor.getAuditRecords().contains(auditRecords));

		super.unauthenticate();
	}

	@Test
	public void testFindByAuditor() {

		super.authenticate("auditor1");

		final Auditor auditor = this.auditorService.findByPrincipal();
		final Collection<AuditRecord> auditRecords = auditor.getAuditRecords();

		Assert.isTrue(auditor.getAuditRecords().equals(auditRecords));

		super.unauthenticate();

	}

}
