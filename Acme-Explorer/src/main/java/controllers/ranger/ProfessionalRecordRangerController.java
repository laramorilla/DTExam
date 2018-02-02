
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

import services.ProfessionalRecordService;
import controllers.AbstractController;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("/professionalRecord/ranger")
public class ProfessionalRecordRangerController extends AbstractController {

	// Services ------------------------------------------------------------

	@Autowired
	private ProfessionalRecordService	professionalRecordService;


	// Constructors --------------------------------------------------------

	public ProfessionalRecordRangerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	// Display --------------------------------------------------------------

	// Creation  --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createProfessionalRecord() {
		ModelAndView result = null;
		ProfessionalRecord professionalRecord = null;

		professionalRecord = this.professionalRecordService.create();
		result = this.createEditModelAndView(professionalRecord);

		return result;
	}

	// Edition    --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int professionalRecordId) {
		ModelAndView result = null;
		ProfessionalRecord professionalRecord = null;

		professionalRecord = this.professionalRecordService.findOne(professionalRecordId);
		Assert.notNull(professionalRecord);
		result = this.createEditModelAndView(professionalRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveProfessionalRecord(@Valid final ProfessionalRecord professionalRecord, final BindingResult bindingResult) {

		ModelAndView result = null;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(professionalRecord);
		else
			try {
				this.professionalRecordService.save(professionalRecord);
				result = new ModelAndView("redirect:../../curriculum/display.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(professionalRecord, "professionalRecord.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final ProfessionalRecord professionalRecord, final BindingResult bindingResult) {

		ModelAndView result = null;

		try {
			this.professionalRecordService.delete(professionalRecord);
			result = new ModelAndView("redirect:../../curriculum/display.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(professionalRecord, "professionalRecord.commit.error");
		}

		return result;
	}
	// Ancillary methods ----------------------------------------------------

	protected ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord) {
		ModelAndView result = null;
		result = this.createEditModelAndView(professionalRecord, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord, final String messageCode) {

		ModelAndView result = null;
		String cancelURI = null;

		cancelURI = "curriculum/display.do";

		result = new ModelAndView("professionalRecord/edit");
		result.addObject("professionalRecord", professionalRecord);
		result.addObject("cancelURI", cancelURI);

		return result;
	}
}
