package acme.features.epicure.epicureDashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.Status;
import acme.forms.EpicureDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureEpicureDashboardShowService implements AbstractShowService<Epicure, EpicureDashboard> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	EpicureEpicureDashboardRepository repository;
	
	// AbstractShowService<Epicure, EpicureDashboard> interface ---------------
	
	@Override
	public boolean authorise(final Request<EpicureDashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public EpicureDashboard findOne(final Request<EpicureDashboard> request) {
		assert request != null;
		
		final EpicureDashboard result = new EpicureDashboard();
		
		final Map<Status, Integer> counterFineDishesMap = new EnumMap<>(Status.class);
		final List<Object[]> countFineDishesCollection = this.repository.totalNumberFineDishesOfStatus();
		for (int i = 0; i < countFineDishesCollection.size(); i++) {
			final Status status = (Status) countFineDishesCollection.get(i)[0];
			final Long count = (Long) countFineDishesCollection.get(i)[1];
			
			counterFineDishesMap.put(status, count.intValue());
		}
		result.setTotalNumberFineDishesOfStatus(counterFineDishesMap);
		
		final Map<Pair<Status, String>, Money> avgFineDishMap = new HashMap<>();
		final List<Object[]> avgFineDishCollection = this.repository.averageBudgetFineDishesOfStatusByCurrency();
		for (int i = 0; i < avgFineDishCollection.size(); i++) {
			final Status status = (Status) avgFineDishCollection.get(i)[0];
			final String currency = (String) avgFineDishCollection.get(i)[1];
			final Money m = new Money();
			final Double amount = (Double) avgFineDishCollection.get(i)[2];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			avgFineDishMap.put(Pair.of(status, currency), m);
		}
		result.setAverageBudgetFineDishesOfStatusByCurrency(avgFineDishMap);
		
		final Map<Pair<Status, String>, Money> devFineDishMap = new HashMap<>();
		final List<Object[]> devFineDishCollection = this.repository.deviationBudgetFineDishesOfStatusByCurrency();
		for (int i = 0; i < devFineDishCollection.size(); i++) {
			final Status status = (Status) devFineDishCollection.get(i)[0];
			final String currency = (String) devFineDishCollection.get(i)[1];
			final Money m = new Money();
			final Double amount = (Double) devFineDishCollection.get(i)[2];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			devFineDishMap.put(Pair.of(status, currency), m);
		}
		result.setDeviationBudgetFineDishesOfStatusByCurrency(devFineDishMap);
		
		final Map<Pair<Status, String>, Money> minFineDishMap = new HashMap<>();
		final List<Object[]> minFineDishCollection = this.repository.minimumBudgetFineDishesOfStatusByCurrency();
		for (int i = 0; i < minFineDishCollection.size(); i++) {
			final Status status = (Status) minFineDishCollection.get(i)[0];
			final String currency = (String) minFineDishCollection.get(i)[1];
			final Money m = new Money();
			final Double amount = (Double) minFineDishCollection.get(i)[2];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			minFineDishMap.put(Pair.of(status, currency), m);
		}
		result.setMinimumBudgetFineDishesOfStatusByCurrency(minFineDishMap);
		
		final Map<Pair<Status, String>, Money> maxFineDishMap = new HashMap<>();
		final List<Object[]> maxFineDishCollection = this.repository.maximumBudgetFineDishesOfStatusByCurrency();
		for (int i = 0; i < maxFineDishCollection.size(); i++) {
			final Status status = (Status) maxFineDishCollection.get(i)[0];
			final String currency = (String) maxFineDishCollection.get(i)[1];
			final Money m = new Money();
			final Double amount = (Double) maxFineDishCollection.get(i)[2];
			m.setAmount(amount);
			m.setCurrency(currency);
			
			maxFineDishMap.put(Pair.of(status, currency), m);
		}
		result.setMaximumBudgetFineDishesOfStatusByCurrency(maxFineDishMap);
		
		return result;
	}

	@Override
	public void unbind(final Request<EpicureDashboard> request, final EpicureDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberFineDishesOfStatus", "averageBudgetFineDishesOfStatusByCurrency", "deviationBudgetFineDishesOfStatusByCurrency", "minimumBudgetFineDishesOfStatusByCurrency", "maximumBudgetFineDishesOfStatusByCurrency");
		
	}

}
