package acme.features.any.kitchenItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyKitchenItemShowService implements AbstractShowService<Any, KitchenItem> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyKitchenItemRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchangeService;
	
	@Override
	public boolean authorise(final Request<KitchenItem> request) {
		assert request != null;

		boolean result = false;
		
		final Collection<KitchenItem> publishedItems = this.repository.findAllIfPublished();
		final Integer itemId = request.getModel().getInteger("id");
		final KitchenItem item = this.repository.findOneItemById(itemId);
		if (publishedItems.contains(item)) {
			result = true;
		}
		
		return result;
	}

	@Override
	public KitchenItem findOne(final Request<KitchenItem> request) {
		assert request != null;
		
		KitchenItem result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<KitchenItem> request, final KitchenItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Money defaultRetailPrice;
		String defaultCurrency;
		
		defaultCurrency = this.repository.getSystemConfiguration().getDefaultSystemCurrency();
		defaultRetailPrice = this.moneyExchangeService.computeMoneyExchange(entity.getRetailPrice(), defaultCurrency).getTarget();
		
		model.setAttribute("defaultRetailPrice", defaultRetailPrice);
		
		request.unbind(entity, model, "type", "name", "code", "description", "retailPrice", "optionalLink", "published");
	}

	
}
