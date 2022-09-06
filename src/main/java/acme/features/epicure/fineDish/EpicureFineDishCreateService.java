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
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishCreateService implements AbstractCreateService<Epicure, FineDish>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected EpicureFineDishRepository repository;
	
	@Autowired
	protected SpamDetectorService spamDetectorService;
	
	// AbstractCreateService<Epicure, FineDish> interface -------------------------
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "request", "budget", "startDate", "finishDate", "moreInfo");
		entity.setChef(this.repository.findChefByChefUsername(request.getModel().getAttribute("chef").toString()));
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Date creationDate;
		creationDate = new Date(System.currentTimeMillis()-1);
		entity.setCreationDate(creationDate);
		entity.setPublished(false);
		
		request.unbind(entity, model, "status", "code", "request", "budget", "creationDate", "startDate", "finishDate", "published", "moreInfo");
		model.setAttribute("chefs", this.repository.findAllChefs());
	}

	@Override
	public FineDish instantiate(final Request<FineDish> request) {
		FineDish result;
		Date creationDate;
		Date startDate;
		Date finishDate;
		
		result = new FineDish();
		creationDate = new Date(System.currentTimeMillis()-1);
		startDate = DateUtils.addMonths(creationDate, 1);
		finishDate = DateUtils.addMonths(startDate, 1);
		
		result.setStatus(Status.PROPOSED);
		result.setCreationDate(creationDate);
		result.setStartDate(startDate);
		result.setFinishDate(finishDate);
		
		final Integer epicureId = request.getPrincipal().getActiveRoleId();
		result.setEpicure(this.repository.findEpicureByEpicureId(epicureId));
		
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
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
