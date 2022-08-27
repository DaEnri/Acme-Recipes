package acme.features.chef.memorandum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.FineDish;
import acme.entities.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefMemorandumRepository extends AbstractRepository {

	@Query("select m from Memorandum m where m.id = :id")
	Memorandum findOneMemorandumById(int id);

	@Query("select m from Memorandum m")
	Collection<Memorandum> findAllMemorandums();
	
	@Query("select m from Memorandum m where m.fineDish.chef.id = :id")
	Collection<Memorandum> findMemorandumsByChefId(int id);

	@Query("select f from FineDish f where f.chef.id = :id")
	Collection<FineDish> findAllFineDishesByChefId(int id);
	
	@Query("select f from FineDish f where f.code = :code")
	FineDish findFineDishByCode(String code);

}
