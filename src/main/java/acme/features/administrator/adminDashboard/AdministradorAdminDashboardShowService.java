package acme.features.administrator.adminDashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Status;
import acme.forms.AdminDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministradorAdminDashboardShowService implements AbstractShowService<Administrator, AdminDashboard>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	AdministradorAdminDashboardRepository repository;

	// AbstractShowService<Administrator, AdminDashboard> interface --------------
	
	@Override
	public boolean authorise(final Request<AdminDashboard> request) {
		
		assert request != null;
		
		return true;
		
	}

	@Override
	public AdminDashboard findOne(final Request<AdminDashboard> request) {
		assert request != null;
		
		final AdminDashboard result = new AdminDashboard();
		
		// Ingredients metrics
		
		final int totalNumberIngredients = this.repository.totalNumberIngredients();
		result.setTotalNumberIngredients(totalNumberIngredients);
		
		final Map<String, Money> avgIngredientsMap = new HashMap<>();
		final List<Object[]> avgIngredientsCollection = this.repository.averageRetailPriceIngredientsByCurrency();
		for (int i = 0; i < avgIngredientsCollection.size(); i++) {
			final Money m = new Money();
			final String currency = (String) avgIngredientsCollection.get(i)[0];
			final Double amount = (Double) avgIngredientsCollection.get(i)[1];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			avgIngredientsMap.put(currency, m);
		}
		result.setAverageRetailPriceIngredientsByCurrency(avgIngredientsMap);
		
		final Map<String, Money> devIngredientsMap = new HashMap<>();
		final List<Object[]> devIngredientsCollection = this.repository.deviationRetailPriceIngredientsByCurrency();
		for (int i = 0; i < devIngredientsCollection.size(); i++) {
			final Money m = new Money();
			final String currency = (String) devIngredientsCollection.get(i)[0];
			final Double amount = (Double) devIngredientsCollection.get(i)[1];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			devIngredientsMap.put(currency, m);
		}
		result.setDeviationRetailPriceIngredientsByCurrency(devIngredientsMap);
		
		final Map<String, Money> minIngredientsMap = new HashMap<>();
		final List<Object[]> minIngredientsCollection = this.repository.minimumRetailPriceIngredientsByCurrency();
		for (int i = 0; i < minIngredientsCollection.size(); i++) {
			final Money m = new Money();
			final String currency = (String) minIngredientsCollection.get(i)[0];
			final Double amount = (Double) minIngredientsCollection.get(i)[1];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			minIngredientsMap.put(currency, m);
		}
		result.setMinimumRetailPriceIngredientsByCurrency(minIngredientsMap);
		
		final Map<String, Money> maxIngredientsMap = new HashMap<>();
		final List<Object[]> maxIngredientsCollection = this.repository.maximumRetailPriceIngredientsByCurrency();
		for (int i = 0; i < maxIngredientsCollection.size(); i++) {
			final Money m = new Money();
			final String currency = (String) maxIngredientsCollection.get(i)[0];
			final Double amount = (Double) maxIngredientsCollection.get(i)[1];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			maxIngredientsMap.put(currency, m);
		}
		result.setMaximumRetailPriceIngredientsByCurrency(maxIngredientsMap);
		
		// Kitchen Utensils metrics
		
		final int totalNumberKitchenUtensils = this.repository.totalNumberKitchenUtensils();
		result.setTotalNumberKitchenUtensils(totalNumberKitchenUtensils);
		
		final Map<String, Money> avgKitchenUtensilsMap = new HashMap<>();
		final List<Object[]> avgKitchenUtensilsCollection = this.repository.averageRetailPriceKitchenUtensilsByCurrency();
		for (int i = 0; i < avgKitchenUtensilsCollection.size(); i++) {
			final String currency = (String) avgKitchenUtensilsCollection.get(i)[0];
			final Money m = new Money();
			final Double amount = (Double) avgKitchenUtensilsCollection.get(i)[1];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			avgKitchenUtensilsMap.put(currency, m);
		}
		result.setAverageRetailPriceKitchenUtensilsByCurrency(avgKitchenUtensilsMap);
		
		final Map<String, Money> devKitchenUtensilsMap = new HashMap<>();
		final List<Object[]> devKitchenUtensilsCollection = this.repository.deviationRetailPriceKitchenUtensilsByCurrency();
		for (int i = 0; i < devKitchenUtensilsCollection.size(); i++) {
			final String currency = (String) devKitchenUtensilsCollection.get(i)[0];
			final Money m = new Money();
			final Double amount = (Double) devKitchenUtensilsCollection.get(i)[1];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			devKitchenUtensilsMap.put(currency, m);
		}
		result.setDeviationRetailPriceKitchenUtensilsByCurrency(devKitchenUtensilsMap);
		
		final Map<String, Money> minKitchenUtensilsMap = new HashMap<>();
		final List<Object[]> minKitchenUtensilsCollection = this.repository.minimumRetailPriceKitchenUtensilsByCurrency();
		for (int i = 0; i < minKitchenUtensilsCollection.size(); i++) {
			final String currency = (String) minKitchenUtensilsCollection.get(i)[0];
			final Money m = new Money();
			final Double amount = (Double) minKitchenUtensilsCollection.get(i)[1];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			minKitchenUtensilsMap.put(currency, m);
		}
		result.setMinimumRetailPriceKitchenUtensilsByCurrency(minKitchenUtensilsMap);
		
		final Map<String, Money> maxKitchenUtensilsMap = new HashMap<>();
		final List<Object[]> maxKitchenUtensilsCollection = this.repository.maximumRetailPriceKitchenUtensilsByCurrency();
		for (int i = 0; i < maxKitchenUtensilsCollection.size(); i++) {
			final String currency = (String) maxKitchenUtensilsCollection.get(i)[0];
			final Money m = new Money();
			final Double amount = (Double) maxKitchenUtensilsCollection.get(i)[1];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			maxKitchenUtensilsMap.put(currency, m);
		}
		result.setMaximumRetailPriceKitchenUtensilsByCurrency(maxKitchenUtensilsMap);
		
		//Fine Dishes metrics
		
		final Map<Status, Integer> countFineDishesMap = new EnumMap<>(Status.class);
		final List<Object[]> countFineDishesCollection = this.repository.totalNumberFineDishesOfStatus();
		for (int i = 0; i < countFineDishesCollection.size(); i++) {
			final Status status = (Status) countFineDishesCollection.get(i)[0];
			final Long count = (Long) countFineDishesCollection.get(i)[1];
			
			countFineDishesMap.put(status, count.intValue());
		}
		result.setTotalNumberFineDishesOfStatus(countFineDishesMap);
		
		final Map<Status, Money> averageFineDishesMap = new EnumMap<>(Status.class);
		final List<Object[]> averageFineDishesCollection = this.repository.averageBudgetFineDishesOfStatus();
		for (int i = 0; i < averageFineDishesCollection.size(); i++) {
			final Status status = (Status) averageFineDishesCollection.get(i)[0];
			final Money m = new Money();
			final String currency = (String) averageFineDishesCollection.get(i)[1];
			final Double amount = (Double) averageFineDishesCollection.get(i)[2];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			averageFineDishesMap.put(status, m);
		}
		result.setAverageBudgetFineDishesOfStatus(averageFineDishesMap);
		
		final Map<Status, Money> deviationFineDishesMap = new EnumMap<>(Status.class);
		final List<Object[]> deviationFineDishesCollection = this.repository.deviationBudgetFineDishesOfStatus();
		for (int i = 0; i < deviationFineDishesCollection.size(); i++) {
			final Status status = (Status) deviationFineDishesCollection.get(i)[0];
			final Money m = new Money();
			final String currency = (String) deviationFineDishesCollection.get(i)[1];
			final Double amount = (Double) deviationFineDishesCollection.get(i)[2];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			deviationFineDishesMap.put(status, m);
		}
		result.setDeviationBudgetFineDishesOfStatus(deviationFineDishesMap);
		
		final Map<Status, Money> minimumFineDishesMap = new EnumMap<>(Status.class);
		final List<Object[]> minimumFineDishesCollection = this.repository.minimumBudgetFineDishesOfStatus();
		for (int i = 0; i < minimumFineDishesCollection.size(); i++) {
			final Status status = (Status) minimumFineDishesCollection.get(i)[0];
			final Money m = new Money();
			final String currency = (String) minimumFineDishesCollection.get(i)[1];
			final Double amount = (Double) minimumFineDishesCollection.get(i)[2];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			minimumFineDishesMap.put(status, m);
		}
		result.setMinimumBudgetFineDishesOfStatus(minimumFineDishesMap);
		
		final Map<Status, Money> maximumFineDishesMap = new EnumMap<>(Status.class);
		final List<Object[]> maximumFineDishesCollection = this.repository.maximumBudgetFineDishesOfStatus();
		for (int i = 0; i < maximumFineDishesCollection.size(); i++) {
			final Status status = (Status) maximumFineDishesCollection.get(i)[0];
			final Money m = new Money();
			final String currency = (String) maximumFineDishesCollection.get(i)[1];
			final Double amount = (Double) maximumFineDishesCollection.get(i)[2];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			maximumFineDishesMap.put(status, m);
		}
		result.setMaximumBudgetFineDishesOfStatus(maximumFineDishesMap);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdminDashboard> request, final AdminDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberIngredients", "averageRetailPriceIngredientsByCurrency", "deviationRetailPriceIngredientsByCurrency", "minimumRetailPriceIngredientsByCurrency", "maximumRetailPriceIngredientsByCurrency",
			"totalNumberKitchenUtensils", "averageRetailPriceKitchenUtensilsByCurrency", "deviationRetailPriceKitchenUtensilsByCurrency", "minimumRetailPriceKitchenUtensilsByCurrency", "maximumRetailPriceKitchenUtensilsByCurrency", 
			"totalNumberFineDishesOfStatus", "averageBudgetFineDishesOfStatus", "deviationBudgetFineDishesOfStatus", "minimumBudgetFineDishesOfStatus", "maximumBudgetFineDishesOfStatus");
	}
	
}
