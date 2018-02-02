
package controllers.manager;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.LegalTextService;
import services.ManagerService;
import services.RangerService;
import services.TagService;
import services.TripService;
import controllers.AbstractController;
import domain.Category;
import domain.LegalText;
import domain.Manager;
import domain.Ranger;
import domain.Tag;
import domain.Trip;

@Controller
@RequestMapping("/trip/manager")
public class TripManagerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private TripService			tripService;

	@Autowired
	private CategoryService		categoryService;

	@Autowired
	private RangerService		rangerService;

	@Autowired
	private TagService			tagService;

	@Autowired
	private LegalTextService	legalTextService;

	@Autowired
	private ManagerService		managerService;


	// Constructors -----------------------------------------------------------

	public TripManagerController() {
		super();
	}

	// Creation  -------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Trip trip;

		trip = this.tripService.create();
		result = this.createEditModelAndView(trip);

		return result;
	}

	// Listing  -------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Manager manager = this.managerService.findByPrincipal();
		Collection<Trip> trips;

		trips = this.tripService.findByManager(manager);

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("date", new Date());
		result.addObject("requestURI", "trip/manager/list.do");
		return result;
	}

	// Edition  -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tripId) {
		final ModelAndView result;
		Trip trip;
		trip = this.tripService.findOneToEdit(tripId);
		Assert.notNull(trip);
		result = this.createEditModelAndView(trip);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Trip trip, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(trip);
		else
			try {
				this.tripService.save(trip);
				result = new ModelAndView("redirect:list.do");
			}
			catch (final Throwable oops) {
				if(oops.getMessage().contains("start date before"))
					result = this.createEditModelAndView(trip, "trip.commit.error.start");
                else if(oops.getMessage().contains("publication"))
                    result = this.createEditModelAndView(trip, "trip.commit.error.publication");
				else
					result = this.createEditModelAndView(trip, "trip.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Trip trip, final BindingResult binding) {
		ModelAndView result;

		try {
			this.tripService.delete(trip);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(trip, "trip.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "cancelTrip")
	public ModelAndView cancelTrip(@Valid final Trip trip, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(trip);
		else
			try {
				this.tripService.cancelTrip(trip, trip.getCancelledReason());
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(trip, "trip.commit.error");
			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Trip trip) {
		ModelAndView result;

		result = this.createEditModelAndView(trip, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Trip trip, final String messageCode) {
		ModelAndView result;
		Collection<Category> categories;
		Collection<Ranger> rangers;
		Collection<LegalText> legalTexts;
		final Collection<Tag> tags;

		categories = this.categoryService.findAll();
		categories.remove(this.categoryService.findByName("CATEGORY"));
		rangers = this.rangerService.findAll();
		legalTexts = this.legalTextService.findAll();
		tags = this.tagService.findAll();

		result = new ModelAndView("trip/edit");
		result.addObject("trip", trip);
		result.addObject("categories", categories);
		result.addObject("rangers", rangers);
		result.addObject("legalTexts", legalTexts);
		result.addObject("tags", tags);
		result.addObject("trip/manager/list.do");
		result.addObject("message", messageCode);
		return result;
	}
}
