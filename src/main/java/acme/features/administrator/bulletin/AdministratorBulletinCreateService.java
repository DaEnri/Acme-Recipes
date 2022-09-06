
package acme.features.administrator.bulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBulletinCreateService implements AbstractCreateService<Administrator, Bulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorBulletinRepository repository;

	// AbstractCreateService<Administrator, Bulletin> interface --------------


	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
		
		return true;
		
	}

	@Override
	public void bind(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "heading", "text", "critical", "optionalLink");
		
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "heading", "text", "critical", "optionalLink");
		model.setAttribute("confirmation", false);
		
	}

	@Override
	public Bulletin instantiate(final Request<Bulletin> request) {
		assert request != null;
		
		Bulletin result;
		
		result = new Bulletin();
		final Date instantiationMoment = new Date(System.currentTimeMillis() - 1);
		
		result.setInstantiationMoment(instantiationMoment);
		
		return result;
	}

	@Override
	public void validate(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final boolean confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
	}

	@Override
	public void create(final Request<Bulletin> request, final Bulletin entity) {
		assert request != null;
		assert entity != null;
		
		Date instantiationMoment;
		instantiationMoment = new Date(System.currentTimeMillis()-1);
		entity.setInstantiationMoment(instantiationMoment);
		
		this.repository.save(entity);
		
	}

}
