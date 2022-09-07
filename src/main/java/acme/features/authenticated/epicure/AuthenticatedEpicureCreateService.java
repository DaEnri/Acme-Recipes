package acme.features.authenticated.epicure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.spam.SpamDetectorService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class AuthenticatedEpicureCreateService implements AbstractCreateService<Authenticated, Epicure>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AuthenticatedEpicureRepository repository;
	
	@Autowired
	protected SpamDetectorService spamDetectorService;
	
	// AbstractCreateService<Authenticated, Epicure> interface ----------------
	
	@Override
	public boolean authorise(final Request<Epicure> request) {
		assert request != null;

		boolean hasEpicureRole;

		hasEpicureRole = request.getPrincipal().hasRole(Epicure.class);

		return !hasEpicureRole;
		
	}

	@Override
	public void bind(final Request<Epicure> request, final Epicure entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "organisation", "assertion", "info");
		
	}

	@Override
	public void unbind(final Request<Epicure> request, final Epicure entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organisation", "assertion", "info");
		
	}

	@Override
	public Epicure instantiate(final Request<Epicure> request) {
assert request != null;
		
		Epicure result;
		int userAccountId;
		UserAccount userAccount;
		
		userAccountId = request.getPrincipal().getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);
		
		result = new Epicure();
		result.setUserAccount(userAccount);
		
		return result;
		
	}

	@Override
	public void validate(final Request<Epicure> request, final Epicure entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("organisation")) {
			errors.state(request, !this.spamDetectorService.isTextSpam(request.getModel().getString("organisation")), "organisation", "authenticated.epicure.error.organisation.isSpam");
		}
		
		if (!errors.hasErrors("assertion")) {
			errors.state(request, !this.spamDetectorService.isTextSpam(request.getModel().getString("assertion")), "assertion", "authenticated.epicure.error.assertion.isSpam");
		}
		
	}

	@Override
	public void create(final Request<Epicure> request, final Epicure entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
	
	@Override
	public void onSuccess(final Request<Epicure> request, final Response<Epicure> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	
}
