package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationShowTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String acceptedCurrencies, final String defaultSystemCurrency,
		final String spamTerms, final String spamThreshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Configuration");
		
		super.checkInputBoxHasValue("defaultSystemCurrency", defaultSystemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("spamTerms", spamTerms);
		super.checkInputBoxHasValue("spamThreshold", spamThreshold);
	}
}
