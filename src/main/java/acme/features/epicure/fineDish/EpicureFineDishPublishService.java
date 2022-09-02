package acme.features.epicure.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.FineDish;
import acme.entities.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishPublishService implements AbstractUpdateService<Epicure, FineDish>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureFineDishRepository repository;

	// AbstractUpdateService<Epicure, FineDish> interface --------------
	
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
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "published");
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
		
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		entity.setStatus(Status.PROPOSED);
		entity.setPublished(true);
		this.repository.save(entity);
	}

}
