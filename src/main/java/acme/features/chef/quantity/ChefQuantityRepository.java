package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.KitchenItem;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefQuantityRepository extends AbstractRepository {
	
	@Query("select r from Recipe r where r.id = :recipeId")
	Recipe findRecipeById(Integer recipeId);
	
	@Query("select q from Quantity q where q.recipe.id = :recipeId and q.kitchenItem.published = 1")
	Collection<Quantity> findAllKitchenItemsFromRecipeByIdAllPublished(int recipeId);
	
	@Query("select q from Quantity q where q.id = :quantityId")
	Quantity findOneQuantityById(int quantityId);
	
	@Query("select ki from KitchenItem ki where ki.code = :code")
	KitchenItem findKitchenItemByCode(String code);
	
	@Query("select q from Quantity q where q.kitchenItem.code = :kiCode and q.recipe.id = :recipeId")
	Quantity findQuantitiesByKitchenItemCodeInRecipe(String kiCode, int recipeId);

}
