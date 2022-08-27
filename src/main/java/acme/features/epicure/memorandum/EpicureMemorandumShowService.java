package acme.features.epicure.memorandum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumShowService implements AbstractShowService<Epicure, Memorandum>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected EpicureMemorandumRepository repository;
		
	// AbstractShowtService<Epicure, Memorandum> interface --------------
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		
		boolean result = false;
		final int epicureId = request.getPrincipal().getActiveRoleId();
		final int id = request.getModel().getInteger("id");
		final Memorandum memorandum = this.repository.findOneMemorandumById(id);
		
		if (epicureId == memorandum.getFineDish().getEpicure().getId()) {
			result = true;
		}
		
		return result;
	}

	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		assert request != null;
		
		Memorandum result;
		
		final int id = request.getModel().getInteger("id");
		result = this.repository.findOneMemorandumById(id );
		
		return result;
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "serialCode", "instantiationMoment", "report", "optionalLink");
	}

}
