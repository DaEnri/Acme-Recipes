package acme.testing.authenticated.epicure;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedEpicureCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/epicure/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String organisation, final String assertion, final String info) {
			
			super.signUp("test1", "test1", "test1", "test1", "test@gmail.com");
			super.signIn("test1", "test1");
			
			super.clickOnMenu("Account", "Become a epicure");
			super.checkFormExists();
			super.fillInputBoxIn("organisation", organisation);
			super.fillInputBoxIn("assertion", assertion);
			super.fillInputBoxIn("info", info);
			super.clickOnSubmit("Register");
			
			super.clickOnMenu("Account", "Epicure data");
			super.checkFormExists();
			super.checkInputBoxHasValue("organisation", organisation);
			super.checkInputBoxHasValue("assertion", assertion);
			super.checkInputBoxHasValue("info", info);

			super.signOut();
		}

		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/epicure/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void negativeTest(final int recordIndex, final String organisation, final String assertion, final String info) {
			
			super.signIn("chef1", "chef1");
			
			super.clickOnMenu("Account", "Become a epicure");
			super.checkFormExists();
			super.fillInputBoxIn("organisation", organisation);
			super.fillInputBoxIn("assertion", assertion);
			super.fillInputBoxIn("info", info);
			super.clickOnSubmit("Register");
			
			super.checkErrorsExist();
			super.signOut();
		}
	
		@Test
		@Order(10)
		public void hackingTest() {
			
			super.checkNotLinkExists("Account");
			super.navigate("/authenticated/epicure/create");
			super.checkPanicExists();
			
			super.signIn("epicure1", "epicure1");
			super.navigate("/authenticated/epicure/create");
			super.checkPanicExists();
			super.signOut();
		}
}
