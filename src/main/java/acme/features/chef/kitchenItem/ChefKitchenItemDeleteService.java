package acme.features.chef.kitchenItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefKitchenItemDeleteService implements AbstractDeleteService<Chef, KitchenItem>  {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ChefKitchenItemRepository repository;
	
	// AbstractDeleteService<Chef, KitchenItem> interface -------------------------

	@Override
	public boolean authorise(final Request<KitchenItem> request) {
		assert request != null;
		
		boolean result;
		KitchenItem item;
		Boolean published;
		
		item = this.repository.findOneKitchenItemByKitchenItemId(request.getModel().getInteger("id"));
		published = item.isPublished();
		result = request.getPrincipal().getActiveRoleId() == item.getChef().getId();
		
		return result && !published;
	}

	@Override
	public void bind(final Request<KitchenItem> request, final KitchenItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "type", "name", "code", "description", "retailPrice", "published", "optionalLink");
	}

	@Override
	public void unbind(final Request<KitchenItem> request, final KitchenItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name", "code", "description", "retailPrice", "published", "optionalLink");
	}

	@Override
	public KitchenItem findOne(final Request<KitchenItem> request) {
		assert request != null;
		KitchenItem result;
		result = this.repository.findOneKitchenItemByKitchenItemId(request.getModel().getInteger("id"));
		return result;
	}

	@Override
	public void validate(final Request<KitchenItem> request, final KitchenItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<KitchenItem> request, final KitchenItem entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
	}

	
}
