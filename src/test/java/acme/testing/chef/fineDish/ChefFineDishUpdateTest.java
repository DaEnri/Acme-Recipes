
package acme.testing.chef.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefFineDishUpdateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dish/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My fine dishes");
		super.checkListingExists();
		
		super.sortListing(1, "desc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkSubmitExists("Update");
		
		super.fillInputBoxIn("status", status);
		super.clickOnSubmit("Update");
		
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.checkColumnHasValue(recordIndex, 0, status);
		
		super.signOut();
		
	}
	
	@Test
	@Order(10)
	public void hackingTest() {

		super.checkNotLinkExists("Account");
		super.navigate("/chef/fine-dish/update");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/fine-dish/update");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/fine-dish/update");
		super.checkPanicExists();
		super.signOut();
	}

}
