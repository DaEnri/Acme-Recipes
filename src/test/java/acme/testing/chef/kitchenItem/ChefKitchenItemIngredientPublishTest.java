
package acme.testing.chef.kitchenItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKitchenItemIngredientPublishTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchen-item/publish-positive-ingredient.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.clickOnSubmit("Publish");

		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 5, "true");

	}

	@Test
	@Order(10)
	public void hackingTest() {

		super.checkNotLinkExists("Account");
		super.navigate("/chef/kitchen-item/publish");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/kitchen-item/publish");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/kitchen-item/publish");
		super.checkPanicExists();
		super.signOut();
	}

}
