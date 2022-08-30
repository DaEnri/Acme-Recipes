package acme.forms;

import java.io.Serializable;
import java.util.Map;

import acme.entities.Status;
import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	private static final long			serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	int									totalNumberIngredients;
	
	Map<String, Money>					averageRetailPriceIngredientsByCurrency;
	Map<String, Money>					deviationRetailPriceIngredientsByCurrency;
	Map<String, Money>					minimumRetailPriceIngredientsByCurrency;
	Map<String, Money>					maximumRetailPriceIngredientsByCurrency;
	
	int									totalNumberKitchenUtensils;
	
	Map<String, Money>					averageRetailPriceKitchenUtensilsByCurrency;
	Map<String, Money>					deviationRetailPriceKitchenUtensilsByCurrency;
	Map<String, Money>					minimumRetailPriceKitchenUtensilsByCurrency;
	Map<String, Money>					maximumRetailPriceKitchenUtensilsByCurrency;
	
	Map<Status, Integer>				totalNumberFineDishesOfStatus;
	
	Map<Status, Money>					averageBudgetFineDishesOfStatus;
	Map<Status, Money>					deviationBudgetFineDishesOfStatus;
	Map<Status, Money>					minimumBudgetFineDishesOfStatus;
	Map<Status, Money>					maximumBudgetFineDishesOfStatus;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
}
