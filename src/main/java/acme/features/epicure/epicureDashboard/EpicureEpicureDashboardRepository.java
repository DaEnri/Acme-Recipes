package acme.features.epicure.epicureDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureEpicureDashboardRepository extends AbstractRepository {
	
	@Query("select fd.status, count(fd) from FineDish fd group by fd.status")
	List<Object[]> totalNumberFineDishesOfStatus();
	
	@Query("select fd.status, fd.budget.currency, avg(fd.budget.amount) from FineDish fd group by fd.status, fd.budget.currency")
	List<Object[]> averageBudgetFineDishesOfStatusByCurrency();
	
	@Query("select fd.status, fd.budget.currency, stddev(fd.budget.amount) from FineDish fd group by fd.status, fd.budget.currency")
	List<Object[]> deviationBudgetFineDishesOfStatusByCurrency();
	
	@Query("select fd.status, fd.budget.currency, min(fd.budget.amount) from FineDish fd group by fd.status, fd.budget.currency")
	List<Object[]> minimumBudgetFineDishesOfStatusByCurrency();
	
	@Query("select fd.status, fd.budget.currency, max(fd.budget.amount) from FineDish fd group by fd.status, fd.budget.currency")
	List<Object[]> maximumBudgetFineDishesOfStatusByCurrency();
	
}
