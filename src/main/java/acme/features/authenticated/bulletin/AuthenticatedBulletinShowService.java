package acme.features.authenticated.bulletin;

import java.util.Calendar;
import java.util.Date;

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
		
		Calendar calendar;
		Date deadline;
		
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline = calendar.getTime();
		
		Bulletin b;
		int id;
		
		id = request.getModel().getInteger("id");
		b = this.repository.findOneBulletinById(id);
		
		Boolean result;
		
		result = b.getInstantiationMoment().after(deadline);
		
		return result;
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
