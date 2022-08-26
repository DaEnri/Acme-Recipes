package acme.features.chef.kitchenItem;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.KitchenItem;
import acme.entities.KitchenItemType;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
@Repository
public interface ChefKitchenItemRepository extends AbstractRepository{
	
	@Query("select i from KitchenItem i where i.type = :type and i.chef.id = :id")
	Collection<KitchenItem> findKitchenItemsByChefIdAndKitchenItemType(int id, KitchenItemType type);
	
	@Query("select i from KitchenItem i where i.id = :id")
	KitchenItem findOneKitchenItemByKitchenItemId(int id);
	
	@Query("select i from KitchenItem i where i.code = :code")
	KitchenItem findKitchenItemByCode(String code);
	
	@Query("select c from Chef c where c.id = :id")
	Chef findChefById(int id);

	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();
	
}
