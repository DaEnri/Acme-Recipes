package acme.features.any.kitchenItem;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.KitchenItem;
import acme.entities.KitchenItemType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyKitchenItemRepository extends AbstractRepository{

	@Query("select i from KitchenItem i where i.id = :id")
	KitchenItem findOneItemById(int id);
	
	@Query("select i from KitchenItem i where i.type = :type and i.published = true")
	Collection<KitchenItem> findAllByTypeIfPublished(KitchenItemType type);
	
	@Query("select i from KitchenItem i where i.published = true")
	Collection<KitchenItem> findAllIfPublished();
	
	@Query("select q.kitchenItem from Quantity q where q.recipe.id = :recipeId")
	Collection<KitchenItem> findAllKitchenItemsOfRecipe(Integer recipeId);
}
