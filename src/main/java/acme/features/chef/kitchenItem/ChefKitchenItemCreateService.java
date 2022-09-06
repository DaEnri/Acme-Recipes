package acme.features.chef.kitchenItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.KitchenItem;
import acme.features.spam.SpamDetectorService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefKitchenItemCreateService implements AbstractCreateService<Chef, KitchenItem> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ChefKitchenItemRepository repository;
	
	@Autowired
	protected SpamDetectorService spamDetectorService;
	
	// AbstractCreateService<Chef, KitchenItem> interface -------------------------

	@Override
	public boolean authorise(final Request<KitchenItem> request) {
		assert request != null;
		return true;
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
	public KitchenItem instantiate(final Request<KitchenItem> request) {
		assert request != null;
		KitchenItem result;
		Chef chef;
		chef = this.repository.findChefById(request.getPrincipal().getActiveRoleId());
		result = new KitchenItem();
		result.setPublished(false);
		result.setChef(chef);
		return result;
	}

	@Override
	public void validate(final Request<KitchenItem> request, final KitchenItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("name")) {
			errors.state(request, !this.spamDetectorService.isTextSpam(request.getModel().getString("name")), "name", "chef.kitchen-item.error.name.isSpam");
		}
		
		if (!errors.hasErrors("description")) {
			errors.state(request, !this.spamDetectorService.isTextSpam(request.getModel().getString("description")), "description", "chef.kitchen-item.error.description.isSpam");
		}
		
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
	public void create(final Request<KitchenItem> request, final KitchenItem entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
