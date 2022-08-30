package acme.features.chef.kitchenItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefKitchenItemUpdateService implements AbstractUpdateService<Chef, KitchenItem>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ChefKitchenItemRepository repository;
		
	// AbstractUpdateService<Inventor, Item> interface -------------------------
	
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
		if(!errors.hasErrors("code")) {
			KitchenItem item;
			item = this.repository.findKitchenItemByCode(entity.getCode());
			errors.state(request, item == null || item.getCode() == entity.getCode(), "code", "chef.kitchenItem.form.error.duplicated");
		}
		if (!errors.hasErrors("retailPrice")) {
			final Money budget = entity.getRetailPrice();
			final Boolean isBudgetOverZero = budget.getAmount() > 0.;
			final String[] splits = this.repository.findAcceptedCurrencies().split(",");
			Boolean isCurrencyAccepted;
			isCurrencyAccepted = false;
			for (int i = 0; i < splits.length; i++) {
				if (splits[i].equals(budget.getCurrency())) {
					isCurrencyAccepted = true;
				}
			}
			errors.state(request, isBudgetOverZero, "retailPrice", "chef.kitchenItem.form.error.retailPrice.amount");
			errors.state(request, isCurrencyAccepted, "retailPrice", "chef.kitchenItem.form.error.retailPrice.currency");
		}
	}

	@Override
	public void update(final Request<KitchenItem> request, final KitchenItem entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
