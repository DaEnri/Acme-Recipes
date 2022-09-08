package acme.testing.chef.kitchenItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKitchenItemFilterRecipeListShowTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchen-item/list-filter-recipe.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String type, final String name, final String code, final String description, final String retailPrice, final String published, final String optionalLink) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.checkButtonExists("List the recipe ingredients/kitchen utensils and its details");
		super.clickOnButton("List the recipe ingredients/kitchen utensils and its details");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, optionalLink);
		super.checkColumnHasValue(recordIndex, 5, published);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("type", type);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		super.checkInputBoxHasValue("published", published);
		
	}
	
	// Ancillary methods ------------------------------------------------------
}
