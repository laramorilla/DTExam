
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController extends AbstractController {

	// Services ------------------------------------------------------------

	@Autowired
	private CurriculumService	curriculumService;


	// Constructors --------------------------------------------------------

	public CurriculumController() {
		super();
	}

	// Listing --------------------------------------------------------------

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = false) final Integer curriculumId) {
		ModelAndView result = null;
		PersonalRecord personalRecord = null;
		Curriculum curriculum = null;
		Collection<EducationRecord> educationRecords = null;
		Collection<EndorserRecord> endorserRecords = null;
		Collection<MiscellaneousRecord> miscellaneousRecords = null;
		Collection<ProfessionalRecord> professionalRecords = null;

		if (curriculumId == null)
			curriculum = this.curriculumService.findByPrincipal();
		else
			curriculum = this.curriculumService.findOne(curriculumId);

		if (curriculum != null) {
			personalRecord = curriculum.getPersonalRecord();
			educationRecords = curriculum.getEducationRecords();
			endorserRecords = curriculum.getEndorserRecords();
			miscellaneousRecords = curriculum.getMiscellaneousRecords();
			professionalRecords = curriculum.getProfessionalRecords();
		}

		result = new ModelAndView("curriculum/display");
		result.addObject("curriculum", curriculum);
		result.addObject("personalRecord", personalRecord);
		result.addObject("educationRecords", educationRecords);
		result.addObject("endorserRecords", endorserRecords);
		result.addObject("miscellaneousRecords", miscellaneousRecords);
		result.addObject("professionalRecords", professionalRecords);

		return result;
	}

	// Creation  --------------------------------------------------------------

	// Edition    --------------------------------------------------------------

	// Ancillary methods ----------------------------------------------------
}
