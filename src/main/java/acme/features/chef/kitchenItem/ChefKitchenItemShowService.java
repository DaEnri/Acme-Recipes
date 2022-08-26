package acme.features.chef.kitchenItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefKitchenItemShowService implements AbstractShowService<Chef, KitchenItem>{

	@Autowired
	protected ChefKitchenItemRepository repository;
	
	@Override
	public boolean authorise(final Request<KitchenItem> request) {
		assert request != null;
		
		int itemId;
		int chefId;
		KitchenItem item;
		itemId = request.getModel().getInteger("id"); 
		item = this.repository.findOneKitchenItemByKitchenItemId(itemId);
		chefId = request.getPrincipal().getActiveRoleId();
		return chefId==item.getChef().getId();
	}

	@Override
	public KitchenItem findOne(final Request<KitchenItem> request) {
		KitchenItem result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneKitchenItemByKitchenItemId(id);
		return result;
	}

	@Override
	public void unbind(final Request<KitchenItem> request, final KitchenItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name", "code", "description", "retailPrice", "optionalLink","published");
	}

}
