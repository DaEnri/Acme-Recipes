
package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefRecipeUpdateService implements AbstractUpdateService<Chef, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeRepository repository;

	// AbstractUpdateService<Chef, Recipe> interface ----------------------------


	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		Boolean isPublished;
		Boolean isRecipeDoneByChef;
		
		Recipe recipe;
		
		recipe = this.repository.findOneRecipeById(request.getModel().getInteger("id"));
		isPublished = recipe.isPublished();
		isRecipeDoneByChef = request.getPrincipal().getActiveRoleId() == recipe.getChef().getId();
		
		return isRecipeDoneByChef && !isPublished;
		
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
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		Recipe result;
		result = this.repository.findOneRecipeById(request.getModel().getInteger("id"));
		
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
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
