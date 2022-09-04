package acme.testing.authenticated.chef;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedChefUpdateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/chef/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String organisation, final String assertion, final String info) {
			
			super.signIn("chef1", "chef1");
			
			super.clickOnMenu("Account", "Chef data");
			super.checkFormExists();
			super.fillInputBoxIn("organisation", organisation);
			super.fillInputBoxIn("assertion", assertion);
			super.fillInputBoxIn("info", info);
			super.clickOnSubmit("Update");
			
			super.clickOnMenu("Account", "Chef data");
			super.checkFormExists();
			super.checkInputBoxHasValue("organisation", organisation);
			super.checkInputBoxHasValue("assertion", assertion);
			super.checkInputBoxHasValue("info", info);

			super.signOut();
		}

		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/chef/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void negativeTest(final int recordIndex, final String organisation, final String assertion, final String info) {
			
			super.signIn("chef1", "chef1");
			
			super.clickOnMenu("Account", "Chef data");
			super.checkFormExists();
			super.fillInputBoxIn("organisation", organisation);
			super.fillInputBoxIn("assertion", assertion);
			super.fillInputBoxIn("info", info);
			super.clickOnSubmit("Update");
			
			super.checkErrorsExist();
			super.signOut();
		}
		
		@Test
		@Order(10)
		public void hackingTest() {
			
			super.checkNotLinkExists("Account");
			super.navigate("/authenticated/chef/update");
			super.checkPanicExists();
			
			super.signIn("administrator", "administrator");
			super.navigate("/authenticated/chef/update");
			super.checkPanicExists();
			super.signOut();
			
			super.signIn("epicure1", "epicure1");
			super.navigate("/authenticated/chef/update");
			super.checkPanicExists();
			super.signOut();
		}
}
