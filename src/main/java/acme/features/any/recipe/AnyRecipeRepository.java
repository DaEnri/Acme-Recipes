package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyRecipeRepository extends AbstractRepository {
	
	@Query("select r from Recipe r where r.published = 1")
	Collection<Recipe> findAllPublishedRecipes();
	
	@Query("select r from Recipe r where r.id = :id and r.published = 1")
	Recipe findOneRecipeById(int id);
	
}
