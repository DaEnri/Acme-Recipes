package acme.features.spam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.system.configuration.SystemConfiguration;

@Repository
public interface SpamDetectorRepository extends AbstractRepository {

	@Query("select i from SystemConfiguration i")
	SystemConfiguration getSystemConfiguration();
	
}
