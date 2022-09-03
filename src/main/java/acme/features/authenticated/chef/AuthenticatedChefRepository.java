package acme.features.authenticated.chef;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface AuthenticatedChefRepository extends AbstractRepository {
	
	@Query("select ua from UserAccount ua where ua.id = :userAccountId")
	UserAccount findOneUserAccountById(int userAccountId);
	
	@Query("select c from Chef c where c.userAccount.id = :userAccountId")
	Chef findOneChefByUserAccountId(int userAccountId);

}
