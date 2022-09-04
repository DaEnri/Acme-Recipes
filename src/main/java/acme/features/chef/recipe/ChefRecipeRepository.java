package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.system.configuration.SystemConfiguration;

@Repository
public interface ChefRecipeRepository extends AbstractRepository {
	
	@Query("select r from Recipe r where r.chef.id = :chefId")
	Collection<Recipe> findRecipesByChefId(int chefId);
	
	@Query("select r from Recipe r where r.id = :recipeId")
	Recipe findOneRecipeById(int recipeId);
	
	@Query("select i from SystemConfiguration i")
	SystemConfiguration getSystemConfiguration();
	
	@Query("select q from Quantity q where q.recipe.id = :id")
	Collection<Quantity> getKitchenItemsAndQuantitiesByRecipeId(int id);
	
	@Query("select c from Chef c where c.id = :chefId")
	Chef findChefById(int chefId);
	
	@Query("select r from Recipe r where r.code = :code")
	Recipe findRecipeByCode(String code);

}
