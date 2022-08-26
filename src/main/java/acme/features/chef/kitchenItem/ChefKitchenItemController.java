package acme.features.chef.kitchenItem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.KitchenItem;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefKitchenItemController extends AbstractController<Chef,KitchenItem> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ChefIngredientListService ingredientListService;
	
	@Autowired
	protected ChefKitchenUtensilListService kitchenUtensilListService;
	
	@Autowired
	protected ChefKitchenItemShowService showService;
	
//	@Autowired
//	protected ChefKitchenItemCreateService createService;
//	
//	@Autowired
//	protected ChefKitchenItemDeleteService deleteService;
//	
//	@Autowired
//	protected ChefKitchenItemUpdateService updateService;
//	
//	@Autowired
//	protected ChefKitchenItemPublishService publishService;
	
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-ingredient", "list",  this.ingredientListService);
		super.addCommand("list-utensil", "list",  this.kitchenUtensilListService);
		super.addCommand("show", this.showService);
//		super.addCommand("create", this.createService);
//		super.addCommand("delete", this.deleteService);
//		super.addCommand("update", this.updateService);
//		super.addCommand("publish", "update", this.publishService);
	}
	
}
