
package controllers.explorer;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import controllers.AbstractController;
import domain.Finder;
import domain.Trip;

@Controller
@RequestMapping("/finder/explorer")
public class FinderExplorerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private FinderService	finderService;


	// Constructor -------------------------------------------------------------

	public FinderExplorerController() {
		super();
	}

	// Edition  -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView result;
		Finder finder;
		finder = this.finderService.findByPrincipal();
		Assert.notNull(finder);
		result = this.createEditModelAndView(finder);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;
		Collection<Trip> trips;
		final Finder res;

		if (binding.hasErrors())
			result = this.createEditModelAndView(finder);
		else
			try {
				trips = this.finderService.findTripsPerFinder(finder);
				finder.setTrips(trips);
				res = this.finderService.save(finder);

				result = this.createEditModelAndView(res);

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(finder, "finder.commit.error");
			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;

		result = this.createEditModelAndView(finder, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {
		final ModelAndView result;

		Collection<Trip> tripsfound = null;

		if (this.finderService.dateCache(finder))
			tripsfound = new ArrayList<Trip>();
		else
			tripsfound = finder.getTrips();

		result = new ModelAndView("finder/edit");
		result.addObject("finder", finder);
		result.addObject("trips", tripsfound);
		result.addObject("actionURI", "finder/explorer/edit.do");
		result.addObject("cancelURI", "trip/explorer/list.do");
		result.addObject("message", messageCode);
		return result;
	}

}
