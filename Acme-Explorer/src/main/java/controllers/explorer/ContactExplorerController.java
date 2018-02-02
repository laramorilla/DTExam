
package controllers.explorer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContactService;
import services.ExplorerService;
import controllers.AbstractController;
import domain.Contact;
import domain.Explorer;

@Controller
@RequestMapping("/contact/explorer")
public class ContactExplorerController extends AbstractController {

	// Services --------------------------------------------------------------------

	@Autowired
	private ContactService	contactService;

	@Autowired
	private ExplorerService	explorerService;


	// constructor ----------------------------------------------

	public ContactExplorerController() {
		super();
	}

	// Creation ------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Contact contact;

		contact = this.contactService.create();

		result = this.createEditModelAndView(contact);

		return result;
	}

	// Listing -------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Contact> contacts;
		final Explorer explorer = this.explorerService.findByPrincipal();

		contacts = this.contactService.findContactPerExplorer(explorer.getId());

		result = new ModelAndView("contact/list");
		result.addObject("requestURI", "contact/explorer/list.do");
		result.addObject("contacts", contacts);

		return result;

	}

	// Edition -------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int contactId) {

		final ModelAndView result;
		Contact contact;

		contact = this.contactService.findOne(contactId);

		result = this.createEditModelAndView(contact);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Contact contact, final BindingResult binding) {

		ModelAndView result = null;

		if (binding.hasErrors())
			result = this.createEditModelAndView(contact);
		else if ((contact.getEmail().isEmpty() && contact.getPhone().isEmpty()))
			return this.createEditModelAndView(contact, "contact.commit.imputNull");
		try {
			this.contactService.save(contact);
			result = new ModelAndView("redirect:/contact/explorer/list.do");

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(contact, "contact.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Contact contact, final BindingResult binding) {

		ModelAndView result;

		try {
			this.contactService.delete(contact);
			result = new ModelAndView("redirect:/contact/explorer/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(contact, "contact.commit.error");
		}

		return result;
	}
	// Ancillary methods ---------------------------------------------

	protected ModelAndView createEditModelAndView(final Contact contact) {

		ModelAndView result;

		result = this.createEditModelAndView(contact, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Contact contact, final String messageCode) {

		ModelAndView result;
		final Explorer explorer = this.explorerService.findByPrincipal();

		result = new ModelAndView("contact/edit");
		result.addObject("explorer", explorer);
		result.addObject("contact", contact);
		result.addObject("message", messageCode);

		return result;
	}
}
