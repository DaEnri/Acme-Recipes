package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefRecipeShowService implements AbstractShowService<Chef, Recipe> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ChefRecipeRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchangeService;
	
	// AbstractShowService<Chef, Memorandum> interface --------------
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		boolean result = false;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final int recipeId = request.getModel().getInteger("id");
		final Recipe recipe = this.repository.findOneRecipeById(recipeId);
		if (chefId == recipe.getChef().getId()) {
			result = true;
		}
		return result;
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		Recipe result;
		final int recipeId = request.getModel().getInteger("id");
		result = this.repository.findOneRecipeById(recipeId);
		
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
		
		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "optionalLink", "published");
		
	}

}
