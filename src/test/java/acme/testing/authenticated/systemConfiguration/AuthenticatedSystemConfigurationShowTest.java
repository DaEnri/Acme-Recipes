package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationShowTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/systemConfiguration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String acceptedCurrencies, final String defaultSystemCurrency,
		final String spamTerms, final Double spamThreshold) {
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Authenticated", "Currencies Information");
		
		super.checkInputBoxHasValue("defaultSystemCurrency", defaultSystemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
	}
}
