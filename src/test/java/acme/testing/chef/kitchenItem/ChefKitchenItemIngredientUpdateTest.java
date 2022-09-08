
package acme.testing.chef.kitchenItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKitchenItemIngredientUpdateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchen-item/update-positive-ingredient.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String optionalLink) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkSubmitExists("Update");
		
		super.fillInputBoxIn("type", "INGREDIENT");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.clickOnSubmit("Update");

		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, optionalLink);
		super.checkColumnHasValue(recordIndex, 5, "false");

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchen-item/update-negative-ingredient.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String optionalLink) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkSubmitExists("Update");

		super.fillInputBoxIn("type", "INGREDIENT");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.clickOnSubmit("Update");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	@Order(10)
	public void hackingTest() {

		super.checkNotLinkExists("Account");
		super.navigate("/chef/kitchen-item/update");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/kitchen-item/update");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/kitchen-item/update");
		super.checkPanicExists();
		super.signOut();

	}

}
