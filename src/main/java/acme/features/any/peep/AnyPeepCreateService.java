package acme.features.any.peep;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Peep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyPeepCreateService implements AbstractCreateService<Any, Peep>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnyPeepRepository repository;
	
	// AbstractCreateService<Any, Peep> interface -------------------------
	
	@Override
	public boolean authorise(final Request<Peep> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "instantiationMoment", "heading", "writer", "text", "optionalEmail");
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "instantiationMoment", "heading", "writer", "text", "optionalEmail");
		model.setAttribute("confirmation", false);
	}

	@Override
	public Peep instantiate(final Request<Peep> request) {
		assert request != null;
		
		final Peep result;
		
		result = new Peep();
		result.setWriter("");
		result.setHeading("");
		result.setOptionalEmail("");
		result.setText("");
		final Date instantiationMoment = new Date(System.currentTimeMillis()-1);
		result.setInstantiationMoment(instantiationMoment);
		
		return result;
	}

	@Override
	public void validate(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final boolean confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

	}

	@Override
	public void create(final Request<Peep> request, final Peep entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
