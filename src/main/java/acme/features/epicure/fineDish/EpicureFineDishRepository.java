package acme.features.epicure.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.FineDish;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;

@Repository
public interface EpicureFineDishRepository extends AbstractRepository {

	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneFineDishById(int id);
	
	@Query("select f from FineDish f where f.code = :code")
	FineDish findOneFineDishByCode(String code);

	@Query("select f from FineDish f")
	Collection<FineDish> findAllFineDishes();
	
	@Query("select f from FineDish f where f.epicure.id = :id")
	Collection<FineDish> findFineDishesByEpicureId(int id);
	
	// --------------------------------------------------
	
	@Query("select e from Epicure e where e.id = :epicureId")
	Epicure findEpicureByEpicureId(int epicureId);
	
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();
	
	@Query("select c from Chef c")
	Collection<Chef> findAllChefs();
	
	@Query("select c from Chef c where c.userAccount.username = :chefUsername")
	Chef findChefByChefUsername(String chefUsername);
	
}
