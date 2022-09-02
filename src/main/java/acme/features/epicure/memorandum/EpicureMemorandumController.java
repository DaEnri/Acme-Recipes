package acme.features.epicure.memorandum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Memorandum;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureMemorandumController extends AbstractController<Epicure, Memorandum>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected EpicureMemorandumListService	listService;

		@Autowired
		protected EpicureMemorandumShowService	showService;

		@Autowired
		protected EpicureMemorandumCreateService createService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("show", this.showService);
			super.addCommand("list", this.listService);
			super.addCommand("create", this.createService);
		}
	
}
