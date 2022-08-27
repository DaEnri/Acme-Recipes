package acme.features.epicure.memorandum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumListService implements AbstractListService<Epicure, Memorandum>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected EpicureMemorandumRepository repository;
	
	// AbstractListService<Epicure, Memorandum> interface --------------
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Memorandum> findMany(final Request<Memorandum> request) {
		assert request != null;
		
		Collection<Memorandum> result;
		
		final int epicureId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findMemorandumsByEpicureId(epicureId);
		
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
