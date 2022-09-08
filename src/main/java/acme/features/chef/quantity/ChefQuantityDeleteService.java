package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.entities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefQuantityDeleteService implements AbstractDeleteService<Chef, Quantity> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefQuantityRepository repository;

	// AbstractDeleteService<Chef, Quantity> interface --------------
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		Quantity q;
		q = this.repository.findOneQuantityById(request.getModel().getInteger("id"));
		
		return !q.getRecipe().isPublished() && request.getPrincipal().getActiveRoleId() == q.getRecipe().getChef().getId();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "amount", "unit");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		KitchenItem ki;
		
		ki = this.repository.findOneQuantityById(request.getModel().getInteger("id")).getKitchenItem();
		
		model.setAttribute("kitchenItemCode", ki.getCode());
		
		request.unbind(entity, model, "amount", "unit");
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		Quantity result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);
		
		return result;
		
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}

}
