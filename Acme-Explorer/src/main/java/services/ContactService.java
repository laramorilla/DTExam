
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContactRepository;
import domain.Contact;
import domain.Explorer;

@Service
@Transactional
public class ContactService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ContactRepository	contactRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ExplorerService		explorerService;


	// Constructors -----------------------------------------------------------

	public ContactService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Contact findOne(final int contactId) {
		Contact res;
		res = this.contactRepository.findOne(contactId);
		return res;
	}

	public Collection<Contact> findAll() {
		final Collection<Contact> res = this.contactRepository.findAll();
		return res;
	}

	public Contact create() {
		final Explorer explorer = this.explorerService.findByPrincipal();
		final Contact res = new Contact();
		res.setExplorer(explorer);
		return res;
	}

	public Contact save(final Contact contact) {

		Assert.notNull(contact);
		final Explorer explorer = this.explorerService.findByPrincipal();
		Assert.isTrue(contact.getExplorer().equals(explorer));
		Contact saved = null;
		if (contact.getId() == 0) {
			final Collection<Contact> contacts = this.findAll();
			contacts.add(contact);
			this.explorerService.save(explorer);
		}
		saved = this.contactRepository.save(contact);
		return saved;
	}

	public void delete(final Contact contact) {
		Assert.notNull(contact);
		final Explorer explorer = this.explorerService.findByPrincipal();
		Assert.isTrue(contact.getExplorer().equals(explorer));
		this.contactRepository.delete(contact);

	}

	// Other business methods -------------------------------------------------

	public Collection<Contact> findContactPerExplorer(final int explorerId) {

		final Collection<Contact> res = this.contactRepository.findContactsPerExplorer(explorerId);
		return res;
	}

}
