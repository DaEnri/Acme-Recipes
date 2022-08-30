package acme.features.chef.fineDish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefFineDishController extends AbstractController<Chef, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefFineDishListService	listService;

	@Autowired
	protected ChefFineDishShowService	showService;

	@Autowired
	protected ChefFineDishUpdateService	updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		super.addCommand("update", this.updateService);
	}

}
