package acme.features.authenticated.bulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedBulletinShowService implements AbstractShowService<Authenticated, Bulletin> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AuthenticatedBulletinRepository repository;
			
	// AbstractListService<Authenticated, Bulletin> interface -----------------
	
	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Bulletin findOne(final Request<Bulletin> request) {
		assert request != null;
		
		Bulletin result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneBulletinById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "heading","instantiationMoment","critical","text","optionalLink");
		
	}
	
	
	
}
