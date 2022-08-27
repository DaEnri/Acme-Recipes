package acme.features.any.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyRecipeShowService implements AbstractShowService<Any, Recipe>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnyRecipeRepository repository;
		
	// AbstractShowService<Any, Recipe> interface -----------------------------
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		return true;
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		Recipe result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneRecipeById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "optionalLink", "chef");
		
	}

}
