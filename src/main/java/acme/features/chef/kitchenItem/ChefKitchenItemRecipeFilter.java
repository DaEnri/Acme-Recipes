package acme.features.chef.kitchenItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefKitchenItemRecipeFilter implements AbstractListService<Chef, KitchenItem> {
	
	@Autowired
	protected ChefKitchenItemRepository repository;
	
	@Override
	public boolean authorise(final Request<KitchenItem> request) {
		assert request != null;
		
		Boolean result;
		
		Integer recipeId;
		Recipe recipe;
		
		recipeId = request.getModel().getInteger("recipeId");
		recipe = this.repository.findOneRecipeById(recipeId);
		
		result = recipe.isPublished() || recipe.getChef().getId() == request.getPrincipal().getActiveRoleId();
		
		return result;
	}

	@Override
	public Collection<KitchenItem> findMany(final Request<KitchenItem> request) {
		assert request != null;
		
		Collection<KitchenItem> result;
		Integer recipeId;
		
		recipeId = request.getModel().getInteger("recipeId");
		result = this.repository.findAllKitchenItemsOfRecipe(recipeId);
		
		return result;
	}

	@Override
	public void unbind(final Request<KitchenItem> request, final KitchenItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "type","name","code","description","retailPrice","optionalLink","published");
	}

}
