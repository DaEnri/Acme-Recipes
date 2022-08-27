package acme.features.authenticated.bulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Bulletin;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
@RequestMapping("/authenticated/bulletin/")
public class AuthenticatedBulletinController extends AbstractController<Authenticated, Bulletin>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AuthenticatedBulletinListService listService;
	
	@Autowired
	protected AuthenticatedBulletinShowService showService;
	
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
	
}
