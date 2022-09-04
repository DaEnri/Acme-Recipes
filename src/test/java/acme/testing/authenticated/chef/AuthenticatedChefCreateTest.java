package acme.testing.authenticated.chef;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedChefCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/chef/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String organisation, final String assertion, final String info) {
			
			super.signUp("test1", "test1", "test1", "test1", "test@gmail.com");
			super.signIn("test1", "test1");
			
			super.clickOnMenu("Account", "Become a chef");
			super.checkFormExists();
			super.fillInputBoxIn("organisation", organisation);
			super.fillInputBoxIn("assertion", assertion);
			super.fillInputBoxIn("info", info);
			super.clickOnSubmit("Register");
			
			super.clickOnMenu("Account", "Chef data");
			super.checkFormExists();
			super.checkInputBoxHasValue("organisation", organisation);
			super.checkInputBoxHasValue("assertion", assertion);
			super.checkInputBoxHasValue("info", info);

			super.signOut();
		}

		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/chef/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void negativeTest(final int recordIndex, final String organisation, final String assertion, final String info) {
			
			super.signIn("epicure1", "epicure1");
			
			super.clickOnMenu("Account", "Become a chef");
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
			super.navigate("/authenticated/chef/create");
			super.checkPanicExists();
			
			super.signIn("chef1", "chef1");
			super.navigate("/authenticated/chef/create");
			super.checkPanicExists();
			super.signOut();
		}
}
