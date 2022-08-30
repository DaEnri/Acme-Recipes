package acme.features.administrator.adminDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministradorAdminDashboardRepository extends AbstractRepository{
	
	@Query("select count(ki) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.INGREDIENT")
	int totalNumberIngredients();
	
	@Query("select ki.retailPrice.currency, avg(ki.retailPrice.amount) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.INGREDIENT group by ki.retailPrice.currency")
	List<Object[]> averageRetailPriceIngredientsByCurrency();
	@Query("select ki.retailPrice.currency, stddev(ki.retailPrice.amount) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.INGREDIENT group ki.retailPrice.currency")
	List<Object[]> deviationRetailPriceIngredientsByCurrency();
	@Query("select ki.retailPrice.currency, min(ki.retailPrice.amount) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.INGREDIENT group by ki.retailPrice.currency")
	List<Object[]> minimumRetailPriceIngredientsByCurrency();
	@Query("select ki.retailPrice.currency, max(ki.retailPrice.amount) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.INGREDIENT group by ki.retailPrice.currency")
	List<Object[]> maximumRetailPriceIngredientsByCurrency();
	
	@Query("select count(ki) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.KITCHEN_UTENSIL")
	int totalNumberKitchenUtensils();

	@Query("select ki.retailPrice.currency, avg(ki.retailPrice.amount) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.KITCHEN_UTENSIL group by ki.retailPrice.currency")
	List<Object[]> averageRetailPriceKitchenUtensilsByCurrency();
	@Query("select ki.retailPrice.currency, stddev(ki.retailPrice.amount) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.KITCHEN_UTENSIL group by ki.retailPrice.currency")
	List<Object[]> deviationRetailPriceKitchenUtensilsByCurrency();
	@Query("select ki.retailPrice.currency, min(ki.retailPrice.amount) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.KITCHEN_UTENSIL group by ki.retailPrice.currency")
	List<Object[]> minimumRetailPriceKitchenUtensilsByCurrency();
	@Query("select ki.retailPrice.currency, max(ki.retailPrice.amount) from KitchenItem ki where ki.type = acme.entities.KitchenItemType.KITCHEN_UTENSIL group by ki.retailPrice.currency")
	List<Object[]> maximumRetailPriceKitchenUtensilsByCurrency();

	@Query("select fd.status, count(fd) from FineDish fd group by fd.status")
	List<Object[]> totalNumberFineDishesOfStatus();

	@Query("select fd.status, fd.budget.currency, avg(fd.budget.amount) from FineDish fd group by fd.status")
	List<Object[]> averageBudgetFineDishesOfStatus();
	@Query("select fd.status, fd.budget.currency, stddev(fd.budget.amount) from FineDish fd group by fd.status")
	List<Object[]> deviationBudgetFineDishesOfStatus();
	@Query("select fd.status, fd.budget.currency, min(fd.budget.amount) from FineDish fd group by fd.status")
	List<Object[]> minimumBudgetFineDishesOfStatus();
	@Query("select fd.status, fd.budget.currency, max(fd.budget.amount) from FineDish fd group by fd.status")
	List<Object[]> maximumBudgetFineDishesOfStatus();
	
}
