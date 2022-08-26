package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefFineDishListService implements AbstractListService<Chef, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefFineDishRepository repository;

	// AbstractListService<Chef, FineDish> interface --------------


	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<FineDish> findMany(final Request<FineDish> request) {
		assert request != null;

		Collection<FineDish> result;
		final int ChefId = request.getPrincipal().getActiveRoleId();

		result = this.repository.findFineDishesByChefId(ChefId);

		return result;
	}
	
	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "request", "budget", "creationDate", "startDate", "finishDate", "published", "moreInfo");
	}

}