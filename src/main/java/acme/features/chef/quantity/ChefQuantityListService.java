package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefQuantityListService implements AbstractListService<Chef, Quantity>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ChefQuantityRepository repository;
	
	// AbstractListService<Chef, Quantity> interface --------------
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		Recipe r;
		
		r = this.repository.findRecipeById(request.getModel().getInteger("recipeId"));
		
		return request.getPrincipal().getActiveRoleId() == r.getChef().getId();
		
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;

		Collection<Quantity> result;
		int recipeId;
		
		recipeId = request.getModel().getInteger("recipeId");
		result = this.repository.findAllKitchenItemsFromRecipeByIdAllPublished(recipeId);
		
		return result;
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;
		
		int recipeId;
		
		recipeId = request.getModel().getInteger("recipeId");
		model.setAttribute("recipeId", recipeId);
		
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		KitchenItem ki;
		ki = entity.getKitchenItem();
		
		model.setAttribute("kitchenItemCode", ki.getCode());
		model.setAttribute("kitchenItemName", ki.getName());
		model.setAttribute("kitchenItemRetailPrice", ki.getRetailPrice());
		
		request.unbind(entity, model, "amount", "unit");
	}

}
