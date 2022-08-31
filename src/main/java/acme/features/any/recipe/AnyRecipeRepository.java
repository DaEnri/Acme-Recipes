package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.repositories.AbstractRepository;
import acme.system.configuration.SystemConfiguration;

@Repository
public interface AnyRecipeRepository extends AbstractRepository {
	
	@Query("select r from Recipe r where r.published = 1")
	Collection<Recipe> findAllPublishedRecipes();
	
	@Query("select r from Recipe r where r.id = :id and r.published = 1")
	Recipe findOneRecipeById(int id);

	@Query("select i from SystemConfiguration i")
	SystemConfiguration getSystemConfiguration();
	
	@Query("select q from Quantity q where q.recipe.published = 1 and q.recipe.id = :id")
	Collection<Quantity> getKitchenItemsAndQuantitiesByRecipeId(int id);
	
	@Query("select q.recipe from Quantity q where q.kitchenItem.id = :itemId and q.recipe.published = 1")
	Collection<Recipe> getRecipesWithKitchenItemById(int itemId);
	
}
