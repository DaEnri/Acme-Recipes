
package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefRecipeCreateService implements AbstractCreateService<Chef, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeRepository repository;

	// AbstractCreateService<Chef, Recipe> interface ----------------------------

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		return true;
		
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "heading", "description", "preparationNotes", "optionalLink", "published");
		
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "optionalLink", "published");
		
	}

	@Override
	public Recipe instantiate(final Request<Recipe> request) {
		assert request != null;
		
		Recipe result;
		Chef recipeChef;
		int chefId;
		
		result = new Recipe();
		chefId = request.getPrincipal().getActiveRoleId();
		recipeChef = this.repository.findChefById(chefId);
		
		result.setPublished(false);
		result.setChef(recipeChef);
		
		return result;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			Recipe recipeWithSameCode;
			Boolean isCodeRepeated;
			recipeWithSameCode = this.repository.findRecipeByCode(entity.getCode());
			isCodeRepeated = recipeWithSameCode == null || recipeWithSameCode.getId() == entity.getId();
			errors.state(request, isCodeRepeated, "code", "chef.recipe.form.error.duplicated-code");
		}
		
	}

	@Override
	public void create(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
