package acme.features.any.kitchenItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyKitchenItemListRecipeFilterService implements AbstractListService<Any, KitchenItem> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnyKitchenItemRepository repository;
	
	// AbstractListService<Any, KitchenItem> interface ------------------------
	
	@Override
	public boolean authorise(final Request<KitchenItem> request) {
		assert request != null;

		return true;
		
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
		
		request.unbind(entity, model, "type", "name", "code", "description", "retailPrice", "optionalLink", "published");
		
	}
	
}
