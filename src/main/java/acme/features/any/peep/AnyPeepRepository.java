package acme.features.any.peep;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Peep;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyPeepRepository  extends AbstractRepository{

	@Query("select p from Peep p")
	Collection<Peep> findAllPeeps();
	
	@Query("select p from Peep p WHERE p.instantiationMoment >= :deadline")
	Collection<Peep> findAllPeepsFromLastMonth(Date deadline);
	
}
