package acme.features.epicure.fineDish;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.FineDish;
import acme.entities.Status;
import acme.features.spam.SpamDetectorService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.datatypes.Money;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishUpdateService implements AbstractUpdateService<Epicure, FineDish>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureFineDishRepository repository;
	
	@Autowired
	protected SpamDetectorService spamDetectorService;

	// AbstractUpdateService<Epicure, FineDish> interface --------------
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		boolean result = false;
		
		final int epicureId = request.getPrincipal().getActiveRoleId();
		final int id = request.getModel().getInteger("id");
		final FineDish gineDish = this.repository.findOneFineDishById(id);
		if (epicureId == gineDish.getEpicure().getId() && !gineDish.isPublished()) {
			result = true;
		}
		return result;
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "request", "budget", "creationDate", "startDate", "finishDate", "published", "moreInfo");
//		entity.setChef(this.repository.findChefByChefUsername(request.getModel().getAttribute("chef").toString()));
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "request", "budget", "creationDate", "startDate", "finishDate", "published", "moreInfo");
		model.setAttribute("chefs", this.repository.findAllChefs());
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
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("request")) {
			errors.state(request, !this.spamDetectorService.isTextSpam(request.getModel().getString("request")), "request", "epicure.fine-dish.error.request.isSpam");
		}
		
		if (!errors.hasErrors("code")) {
			FineDish fdWithSameCode;
			fdWithSameCode = this.repository.findOneFineDishByCode(entity.getCode());
			final Boolean sameCodeExists = fdWithSameCode == null || fdWithSameCode.getId() == entity.getId();
			errors.state(request, sameCodeExists, "code", "epicure.fineDish.form.error.code");
		}
		if (!errors.hasErrors("budget")) {
			final Money budget = entity.getBudget();
			final Boolean isBudgetOverZero = budget.getAmount() > 0.;
			final String[] splits = this.repository.findAcceptedCurrencies().split(",");
			Boolean isCurrencyAccepted;
			isCurrencyAccepted = false;
			for (int i = 0; i < splits.length; i++) {
				if (splits[i].equals(budget.getCurrency())) {
					isCurrencyAccepted = true;
				}
			}
			errors.state(request, isBudgetOverZero, "budget", "epicure.fineDish.form.error.budget.amount");
			errors.state(request, isCurrencyAccepted, "budget", "epicure.fineDish.form.error.budget.currency");
		}
		if (!errors.hasErrors("startDate")) {
			Date minimumStartDate;
			minimumStartDate = DateUtils.addMonths(entity.getCreationDate(), 1);
			final Boolean isStartDateAfterMinimum = entity.getStartDate().after(DateUtils.addMinutes(minimumStartDate, -1));
			errors.state(request, isStartDateAfterMinimum, "startDate", "epicure.fineDish.form.error.startDate");
		}
		if (!errors.hasErrors("finishDate")) {
			Date minimumFinishDate;
			minimumFinishDate = DateUtils.addMonths(entity.getStartDate(), 1);
			final Boolean isFinishDateAfterMinimum = entity.getFinishDate().after(DateUtils.addMinutes(minimumFinishDate, -1));
			errors.state(request, isFinishDateAfterMinimum, "finishDate", "epicure.fineDish.form.error.finishDate");
		}
		if (!errors.hasErrors("status")) {
			final Status statusProposed = Status.PROPOSED;
			final Boolean isStatusProposed = entity.getStatus() == statusProposed;
			errors.state(request, isStatusProposed, "status", "epicure.fineDish.form.error.status");
		}
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		entity.setStatus(Status.PROPOSED);
		this.repository.save(entity);
	}
	
	@Override
	public void onSuccess(final Request<FineDish> request, final Response<FineDish> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	
}
