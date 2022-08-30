package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyRecipeShowService implements AbstractShowService<Any, Recipe>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnyRecipeRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchangeService;
		
	// AbstractShowService<Any, Recipe> interface -----------------------------
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		Boolean isPublished;
		int id;
		
		id = request.getModel().getInteger("id");
		isPublished = this.repository.findOneRecipeById(id).isPublished();
		
		return isPublished;
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		Recipe result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneRecipeById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Money totalMoneyRecipe;
		
		totalMoneyRecipe = new Money();
		
		Double totalAmountRecipe;
		String currency;
		
		currency = this.repository.getSystemConfiguration().getDefaultSystemCurrency();
		
		final Collection<Quantity> kitchenItemsRecipe = this.repository.getKitchenItemsAndQuantitiesByRecipeId(request.getModel().getInteger("id"));
		
		totalAmountRecipe = kitchenItemsRecipe.stream().mapToDouble(ki -> ki.getAmount() * this.moneyExchangeService.computeMoneyExchange(ki.getKitchenItem().getRetailPrice(), currency).getTarget().getAmount()).sum();
		
		totalMoneyRecipe.setAmount(totalAmountRecipe);
		totalMoneyRecipe.setCurrency(currency);
		
		model.setAttribute("totalMoneyRecipe", totalMoneyRecipe);
		
		model.setAttribute("chefUsername", entity.getChef().getUserAccount().getUsername());
		
		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "optionalLink");
		
	}

}
