package acme.features.epicure.fineDish;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.FineDish;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishShowService implements AbstractShowService<Epicure, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureFineDishRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchangeService;

	// AbstractShowService<Epicure, FineDish> interface --------------


	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		boolean result = false;
		
		final int epicureId = request.getPrincipal().getActiveRoleId();
		final int id = request.getModel().getInteger("id");
		final FineDish fineDish = this.repository.findOneFineDishById(id);
		if (epicureId == fineDish.getEpicure().getId()) {
			result = true;
		}
		return result;
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneFineDishById(id);

		return result;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("chefId", entity.getChef().getUserAccount().getId());
		model.setAttribute("chefs", this.repository.findAllChefs());
		model.setAttribute("chefName", entity.getChef().getUserAccount().getUsername());
		
		model.setAttribute("budgetDefaultCurrency", this.moneyExchangeService.computeMoneyExchange(entity.getBudget(), this.repository.getSystemConfiguration().getDefaultSystemCurrency()).getTarget());
		
		request.unbind(entity, model, "status", "code", "request", "budget", "creationDate", "startDate", "finishDate", "published", "moreInfo");
	}
	
}
