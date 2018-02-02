
package controllers.ranger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.RangerService;
import controllers.AbstractController;
import domain.Ranger;

@Controller
@RequestMapping("/ranger/ranger")
public class RangerRangerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public RangerService	rangerService;

	@Autowired
	public ActorService actorService;


	// Constructor -------------------------------------------------------------

	public RangerRangerController() {
		super();
	}

	// Edition  ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;

		final Ranger ranger = this.rangerService.findByPrincipal();
		result = this.createEditModelAndView(ranger);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Ranger ranger, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(ranger);
		else
			try {
				this.actorService.editPhone(ranger);
				this.rangerService.save(ranger);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(ranger, "explorer.commit.error");
			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Ranger ranger) {

		ModelAndView result;

		result = this.createEditModelAndView(ranger, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Ranger ranger, final String message) {

		Assert.notNull(ranger);

		ModelAndView result;

		result = new ModelAndView("ranger/edit");
		result.addObject("actionURI", "ranger/ranger/edit.do");
		result.addObject("ranger", ranger);
		result.addObject("message", message);

		return result;
	}

}
