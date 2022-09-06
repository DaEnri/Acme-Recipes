package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.FineDish;
import acme.framework.repositories.AbstractRepository;
import acme.system.configuration.SystemConfiguration;

@Repository
public interface ChefFineDishRepository extends AbstractRepository {

	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneFineDishById(int id);

	@Query("select f from FineDish f")
	Collection<FineDish> findAllFineDishes();
	
	@Query("select f from FineDish f where f.chef.id = :id")
	Collection<FineDish> findFineDishesByChefId(int id);
	
	@Query("select i from SystemConfiguration i")
	SystemConfiguration getSystemConfiguration();

}
