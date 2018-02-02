
package controllers.ranger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.MiscellaneousRecordService;
import controllers.AbstractController;
import domain.Curriculum;
import domain.MiscellaneousRecord;

@Controller
@RequestMapping("/miscellaneousRecord/ranger")
public class MiscellaneousRecordRangerController extends AbstractController {

	// Services ------------------------------------------------------------

	@Autowired
	private CurriculumService			curriculumService;

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;


	// Constructors --------------------------------------------------------

	public MiscellaneousRecordRangerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	// Display --------------------------------------------------------------

	// Creation  --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = null;
		MiscellaneousRecord miscellaneousRecord = null;

		miscellaneousRecord = this.miscellaneousRecordService.create();
		result = this.createEditModelAndView(miscellaneousRecord);

		return result;
	}

	// Edition    --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int miscellaneousRecordId) {
		ModelAndView result = null;
		MiscellaneousRecord miscellaneousRecord = null;

		miscellaneousRecord = this.miscellaneousRecordService.findOne(miscellaneousRecordId);
		Assert.notNull(miscellaneousRecord);
		result = this.createEditModelAndView(miscellaneousRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MiscellaneousRecord miscellaneousRecord, final BindingResult bindingResult) {

		ModelAndView result = null;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(miscellaneousRecord);
		else
			try {
				this.miscellaneousRecordService.save(miscellaneousRecord);
				result = new ModelAndView("redirect:../../curriculum/display.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(miscellaneousRecord, "miscellaneousRecord.commit.error");
			}

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final MiscellaneousRecord miscellaneousRecord, final BindingResult bindingResult) {

		ModelAndView result = null;

		try {
			this.miscellaneousRecordService.delete(miscellaneousRecord);
			result = new ModelAndView("redirect:../../curriculum/display.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(miscellaneousRecord, "miscellaneousRecord.commit.error");
		}

		return result;
	}

	// Ancillary methods ----------------------------------------------------

	protected ModelAndView createEditModelAndView(final MiscellaneousRecord miscellaneousRecord) {
		ModelAndView result = null;
		result = this.createEditModelAndView(miscellaneousRecord, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final MiscellaneousRecord miscellaneousRecord, final String messageCode) {
		ModelAndView result = null;
		Curriculum curriculum = null;
		String cancelURI = null;

		curriculum = this.curriculumService.findByPrincipal();
		cancelURI = "curriculum/display.do?curriculumId=" + curriculum.getId();

		result = new ModelAndView("miscellaneousRecord/edit");
		result.addObject("miscellaneousRecord", miscellaneousRecord);
		result.addObject("cancelURI", cancelURI);

		return result;
	}
}
