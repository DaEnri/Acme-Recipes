package acme.features.epicure.epicureDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.EpicureDashboard;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureEpicureDashboardController  extends AbstractController<Epicure, EpicureDashboard> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	EpicureEpicureDashboardShowService showService;
	
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}
	
}
