package acme.features.authenticated.bulletin;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedBulletinListService implements AbstractListService<Authenticated, Bulletin> {
	
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
	public Collection<Bulletin> findMany(final Request<Bulletin> request) {
		assert request != null;
		Collection<Bulletin> result;
		Calendar calendar;
		Date deadline;
		
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline = calendar.getTime();
		
		result = this.repository.findRecentBulletins(deadline);
		return result;
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert entity != null;
		assert model != null;
		assert request != null;
		
		request.unbind(entity, model, "heading", "instantiationMoment", "critical");
		
	}
		
}
