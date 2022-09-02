package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyRecipeListService implements AbstractListService<Any, Recipe>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnyRecipeRepository repository;
	
	// AbstractListService<Any, Recipe> interface -----------------------------
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		return true;
		
	}

	@Override
	public Collection<Recipe> findMany(final Request<Recipe> request) {
		assert request != null;
		
		Collection<Recipe> result;
		
		result = this.repository.findAllPublishedRecipes();
		
		return result;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("chefUsername", entity.getChef().getUserAccount().getUsername());
		
		request.unbind(entity, model, "code", "heading");
		
	}

}
