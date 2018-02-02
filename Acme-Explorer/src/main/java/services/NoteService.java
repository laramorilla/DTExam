
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Auditor;
import domain.Manager;
import domain.Note;
import domain.Trip;

@Service
@Transactional
public class NoteService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private NoteRepository	noteRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private ManagerService	managerService;


	// Constructors -----------------------------------------------------------

	public NoteService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Note create() {

		Auditor auditor = null;
		Note note = null;

		auditor = this.auditorService.findByPrincipal();

		note = new Note();
		note.setMoment(new Date(System.currentTimeMillis() - 1000));
		note.setAuditor(auditor);

		auditor.getNotes().add(note);

		return note;
	}

	public Collection<Note> findAll() {
		return this.noteRepository.findAll();
	}

	public Note findOne(final int id) {

		return this.noteRepository.findOne(id);
	}

	public Note save(final Note note) {

		Note saved = null;

		Assert.notNull(note);

		if (this.checkByPrincipalAuditor(note))
			saved = this.noteRepository.save(note);
		else if (this.checkByPrincipalManager(note)) {
			note.setReplyMoment(new Date(System.currentTimeMillis() - 1000));
			saved = this.noteRepository.save(note);
		}
		return saved;
	}
	// Other business methods -------------------------------------------------

	public void deleteNotes(final Trip trip) {
		Collection<Note> notes = null;
		notes = trip.getNotes();
		this.noteRepository.delete(notes);

	}

	public Collection<Note> findByAuditor(final Auditor auditor) {
		Collection<Note> result = null;
		result = this.noteRepository.findByAuditor(auditor);
		return result;
	}

	public boolean checkByPrincipalAuditor(final Note note) {
		Boolean res = null;
		Auditor principal = null;

		principal = this.auditorService.findByPrincipal();
		res = false;

		if (note.getAuditor().equals(principal))
			res = true;

		return res;
	}

	public boolean checkByPrincipalManager(final Note note) {
		Boolean res = null;
		Manager principal = null;

		res = false;
		principal = this.managerService.findByPrincipal();

		if (note.getTrip().getManager().equals(principal))
			res = true;

		return res;
	}

	public Note findOneToEdit(final int noteId) {

		final Note note = this.noteRepository.findOne(noteId);
		this.checkByPrincipalManager(note);
		Assert.isNull(note.getReply());

		return note;

	}

}
