
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Auditor;
import domain.Note;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	// Service under test ---------------------------------------

	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private NoteService		noteService;

	@Autowired
	private TripService		tripService;


	// Tests ----------------------------------------------------

	@Test
	public void TestCreate() {
		super.authenticate("auditor1");

		Note note = null;

		note = this.noteService.create();

		Assert.notNull(note.getMoment());
		Assert.notNull(note);
		Assert.isNull(note.getRemark());
		Assert.isNull(note.getReply());
		Assert.isNull(note.getTrip());
		Assert.isNull(note.getReplyMoment());

		super.unauthenticate();
	}

	@Test
	public void testSave() {

		super.authenticate("auditor1");

		Note note = null;
		Note saved = null;
		Trip trip = null;
		Collection<Note> notes = null;

		trip = this.tripService.findOne(this.tripService.findAll().iterator().next().getId());

		note = this.noteService.create();
		note.setRemark("remark10");
		note.setReply("reply10");
		note.setTrip(trip);

		notes = this.noteService.findAll();
		saved = this.noteService.save(note);

		System.out.println("Antes de persistir en la base de datos:");
		System.out.println("---------------------------------------");
		System.out.println(notes);
		System.out.println(notes.size());

		System.out.println("");
		System.out.println("Nota persistida en la base de datos:  " + saved + "\n");

		notes = this.noteService.findAll();

		System.out.println("Despu�s de persistir en la base de datos:");
		System.out.println("-----------------------------------------");
		System.out.println(notes);
		System.out.println(notes.size());

		Assert.notNull(this.noteService.findOne(saved.getId()));
		Assert.isTrue(saved.getTrip().equals(trip));
		Assert.isTrue(trip.getNotes().contains(saved));

		super.unauthenticate();
	}

	@Test
	public void testReplyToNote() {
		super.authenticate("auditor1");

		String reply = null;
		Note note = null;
		Note saved = null;
		Trip trip = null;

		trip = this.tripService.findOne(this.tripService.findAll().iterator().next().getId());
		reply = "reply10";

		note = this.noteService.create();
		note.setRemark("remark10");
		note.setTrip(trip);
		note.setReply(reply);

		saved = this.noteService.save(note);

		Assert.isTrue(saved.getReply().equals(reply));

		super.unauthenticate();
	}

	@Test
	public void testFindByAuditor() {
		super.authenticate("auditor1");

		Auditor auditor = null;
		auditor = this.auditorService.findByPrincipal();
		Assert.isTrue(!this.noteService.findByAuditor(auditor).isEmpty());

		super.unauthenticate();
	}

}
