
package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefQuantityCreateService implements AbstractCreateService<Chef, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefQuantityRepository repository;

	// AbstractCreateService<Chef, Quantity> interface --------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		int recipeId;
		Recipe r;
		
		recipeId = request.getModel().getInteger("recipeId");
		r = this.repository.findRecipeById(recipeId);
		
		return !r.isPublished() && request.getPrincipal().getActiveRoleId() == r.getChef().getId();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		KitchenItem kiSelected;
		
		kiSelected = this.repository.findKitchenItemByCode(request.getModel().getString("kitchenItemCode"));
		
		entity.setKitchenItem(kiSelected);
		
		request.bind(entity, errors, "amount", "unit");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("recipeId", request.getModel().getInteger("recipeId"));
		request.unbind(entity, model, "amount", "unit");
		
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		
		Quantity result;
		
		Recipe r;
		r = this.repository.findRecipeById(request.getModel().getInteger("recipeId"));
		
		result = new Quantity();
		result.setRecipe(r);
		
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		KitchenItem ki;
		String kiCode;
		
		kiCode = request.getModel().getString("kitchenItemCode");
		ki = this.repository.findKitchenItemByCode(kiCode);
		
		if(!errors.hasErrors("kitchenItemCode")) {			
			errors.state(request, ki != null, "kitchenItemCode", "chef.quantity.form.error.not-exists.kitchen-item");
		}
		
		if(!errors.hasErrors("kitchenItemCode")) {
			errors.state(request, ki.isPublished(), "kitchenItemCode", "chef.quantity.form.error.not-published.kitchen-item");
		}
		
		if(!errors.hasErrors("kitchenItemCode")) {
			
			final Quantity qSameKiCode = this.repository.findQuantitiesByKitchenItemCodeInRecipe(kiCode, entity.getRecipe().getId());
			
			errors.state(request, qSameKiCode == null || qSameKiCode.getId() == entity.getId(), "kitchenItemCode", "chef.quantity.form.error.is-in-recipe-already.kitchen-item");
		}
		
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
