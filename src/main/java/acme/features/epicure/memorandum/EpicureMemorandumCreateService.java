package acme.features.epicure.memorandum;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.features.spam.SpamDetectorService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumCreateService implements AbstractCreateService<Epicure, Memorandum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureMemorandumRepository repository;
	
	@Autowired
	protected SpamDetectorService spamDetectorService;
	
	// AbstractListService<Epicure, Memorandum> interface --------------
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public void bind(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final int epicureId = request.getPrincipal().getActiveRoleId();
		final int tam = this.repository.findAllFineDishesByEpicureId(epicureId).size();
		if(tam>0) {
			entity.setFineDish(this.repository.findFineDishByCode(request.getModel().getAttribute("fineDish").toString()));
			entity.setSerialCode(entity.getFineDish().getCode() +":"+ String.format("%04d", this.repository.findAllMemorandums().size()+1));
		} 
		request.bind(entity, errors, "instantiationMoment", "report", "optionalLink");
	}
	
	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int epicureId = request.getPrincipal().getActiveRoleId();
		request.unbind(entity, model, "instantiationMoment", "report", "serialCode", "optionalLink");
		model.setAttribute("fineDishes", this.repository.findAllFineDishesByEpicureId(epicureId));
		model.setAttribute("confirmation", false);
	}

	@Override
	public Memorandum instantiate(final Request<Memorandum> request) {
		assert request != null;
		
		final Memorandum result;
		result = new Memorandum();
		final Date instantiationMoment = new Date(System.currentTimeMillis()-1);
		result.setInstantiationMoment(instantiationMoment);
		result.setReport("");
		result.setSerialCode("");
		result.setOptionalLink("");

		return result;
	}

	@Override
	public void validate(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("report")) {
			errors.state(request, !this.spamDetectorService.isTextSpam(request.getModel().getString("report")), "report", "epicure.memorandum.error.report.isSpam");
		}
		
		final boolean confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	
	@Override
	public void create(final Request<Memorandum> request, final Memorandum entity) {
		assert request != null;
		assert entity != null;

		entity.setSerialCode(entity.getFineDish().getCode() +":"+ String.format("%04d", this.repository.findAllMemorandums().size()+1));
		this.repository.save(entity);		
	}

	
}
