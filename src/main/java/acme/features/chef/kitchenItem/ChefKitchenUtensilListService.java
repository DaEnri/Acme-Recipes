package acme.features.chef.kitchenItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.entities.KitchenItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;
@Service
public class ChefKitchenUtensilListService implements AbstractListService<Chef,KitchenItem>{

	@Autowired
	protected ChefKitchenItemRepository repository;
	@Override
	public boolean authorise(final Request<KitchenItem> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<KitchenItem> findMany(final Request<KitchenItem> request) {
		int id;
		Collection<KitchenItem> result;
		
		id = request.getPrincipal().getActiveRoleId();
		final KitchenItemType type = KitchenItemType.KITCHEN_UTENSIL;
		result = this.repository.findKitchenItemsByChefIdAndKitchenItemType(id,type);
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
