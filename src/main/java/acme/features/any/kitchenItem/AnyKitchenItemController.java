package acme.features.any.kitchenItem;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.KitchenItem;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyKitchenItemController extends AbstractController<Any, KitchenItem>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnyKitchenItemListIngredientService listIngredientService;
	
	@Autowired
	protected AnyKitchenItemListKitchenUtensilService listKitchenUtensilService;
	
	@Autowired
	protected AnyKitchenItemShowService showService;
	
	@Autowired
	protected AnyKitchenItemListRecipeFilterService listRecipeFilterService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list-ingredient", "list", this.listIngredientService);
		super.addCommand("list-utensil", "list", this.listKitchenUtensilService);
		super.addCommand("show", this.showService);
		super.addCommand("list-recipe-filter", "list", this.listRecipeFilterService);
	}
		
}
