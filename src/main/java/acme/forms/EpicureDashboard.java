package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.Status;
import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpicureDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	private static final long			serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Map<Status, Integer>				totalNumberFineDishesOfStatus;
	
	Map<Pair<Status, String>, Money>	averageBudgetFineDishesOfStatusByCurrency;
	Map<Pair<Status, String>, Money>	deviationBudgetFineDishesOfStatusByCurrency;
	Map<Pair<Status, String>, Money>	minimumBudgetFineDishesOfStatusByCurrency;
	Map<Pair<Status, String>, Money>	maximumBudgetFineDishesOfStatusByCurrency;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
}
