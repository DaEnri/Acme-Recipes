
package acme.features.administrator.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;
import acme.system.configuration.SystemConfiguration;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration> {

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;

		return true;
		
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "acceptedCurrencies", "defaultSystemCurrency", "spamTerms", "spamThreshold");
		
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "acceptedCurrencies", "defaultSystemCurrency", "spamTerms", "spamThreshold");
		
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		
		SystemConfiguration sysConf;
		
		sysConf = this.repository.getSystemConfiguration();

		return sysConf;
		
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		String[] allAcceptedCurrencies;
		allAcceptedCurrencies = entity.getAcceptedCurrencies().split(",");
		
		Boolean isDefaultSystemCurrencyAccepted;
		isDefaultSystemCurrencyAccepted = false;
		
		for (int i = 0; i < allAcceptedCurrencies.length && isDefaultSystemCurrencyAccepted.equals(false); i++) {
			if (allAcceptedCurrencies[i].equals(entity.getDefaultSystemCurrency())) {
				isDefaultSystemCurrencyAccepted = true;
			}
		}
		
		if (!errors.hasErrors("defaultSystemCurrency")) {
			errors.state(request, isDefaultSystemCurrencyAccepted, "defaultSystemCurrency", "administrator.system-configuration.form.error.not-default-currency");
		}
		
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	

}
