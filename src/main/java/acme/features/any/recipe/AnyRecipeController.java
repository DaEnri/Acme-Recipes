package acme.features.any.recipe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Recipe;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
@RequestMapping("/any/recipe/")
public class AnyRecipeController extends AbstractController<Any, Recipe> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnyRecipeListService listService;
	
	@Autowired
	protected AnyRecipeShowService showService;
	
	@Autowired
	protected AnyRecipeListKitchenItemFilterService listKitchenItemFilterService;
	
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("list-item-filter", "list", this.listKitchenItemFilterService);
	}
	
}
