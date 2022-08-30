package acme.features.chef.memorandum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefMemorandumShowService implements AbstractShowService<Chef, Memorandum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefMemorandumRepository repository;

	// AbstractShowService<Chef, Memorandum> interface --------------


	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		boolean result = false;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final int id = request.getModel().getInteger("id");
		final Memorandum memorandum = this.repository.findOneMemorandumById(id);
		if (chefId == memorandum.getFineDish().getChef().getId()) {
			result = true;
		}
		return result;
	}

	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		assert request != null;

		Memorandum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMemorandumById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "serialCode", "instantiationMoment", "report", "optionalLink");
		model.setAttribute("fineDishCode", entity.getFineDish().getCode());
		model.setAttribute("fineDishRequest", entity.getFineDish().getRequest());
	}
	
}
