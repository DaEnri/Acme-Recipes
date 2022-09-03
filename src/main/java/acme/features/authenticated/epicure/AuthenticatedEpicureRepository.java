package acme.features.authenticated.epicure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure;

@Repository
public interface AuthenticatedEpicureRepository extends AbstractRepository {
	
	@Query("select ua from UserAccount ua where ua.id = :userAccountId")
	UserAccount findOneUserAccountById(int userAccountId);
	
	@Query("select e from Epicure e where e.userAccount.id = :userAccountId")
	Epicure findOneEpicureByUserAccountId(int userAccountId);
	
}
