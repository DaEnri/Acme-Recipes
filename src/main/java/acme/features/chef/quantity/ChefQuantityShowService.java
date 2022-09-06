
package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.entities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefQuantityShowService implements AbstractShowService<Chef, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefQuantityRepository repository;

	// AbstractShowService<Chef, Quantity> interface --------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		Quantity q;
		int quantityId;
		
		quantityId = request.getModel().getInteger("id");
		q = this.repository.findOneQuantityById(quantityId);
		
		return q.getRecipe().getChef().getId() == request.getPrincipal().getActiveRoleId();
		
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
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		KitchenItem ki;
		ki = entity.getKitchenItem();
		
		model.setAttribute("kitchenItemCode", ki.getCode());
		
		request.unbind(entity, model, "amount", "unit");
		
	}

}
