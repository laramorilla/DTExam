
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FolderService;
import domain.Folder;

@Controller
@RequestMapping("/folder")
public class FolderController extends AbstractController {

	// Services ------------------------------------------------------

	@Autowired
	private FolderService	folderService;


	// Constructors --------------------------------------------------

	public FolderController() {
		super();
	}

	// Listing -------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer folderId) {

		final ModelAndView result;
		Collection<Folder> folders;

		folders = this.folderService.findByFolderId(folderId);

		result = new ModelAndView("folder/list");
		result.addObject("folders", folders);

		return result;
	}

	// Creation ------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Folder folder;

		folder = this.folderService.create(false, null);

		result = this.createEditModelAndView(folder);

		return result;
	}

	// Edition -------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int folderId) {

		final ModelAndView result;
		Folder folder;

		folder = this.folderService.findOneToEdit(folderId);

		result = this.createEditModelAndView(folder);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Folder folder, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(folder);
		else
			try {
				this.folderService.save(folder);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(folder, "folder.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Folder folder, final BindingResult binding) {

		ModelAndView result;

		try {
			this.folderService.delete(folder);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(folder, "folder.commit.error");
		}

		return result;
	}

	// Ancillary methods ---------------------------------------------

	protected ModelAndView createEditModelAndView(final Folder folder) {

		ModelAndView result;

		result = this.createEditModelAndView(folder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Folder folder, final String messageCode) {

		ModelAndView result;
		Collection<Folder> folders;

		folders = this.folderService.findByPrincipal();
		folders.remove(folder);

		result = new ModelAndView("folder/edit");
		result.addObject("folder", folder);
		result.addObject("folders", folders);

		return result;
	}
}
