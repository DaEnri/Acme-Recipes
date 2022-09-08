package acme.testing.chef.kitchenItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKitchenItemIngredientCreateTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchen-item/create-positive-ingredient.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String optionalLink) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My ingredients");
		
		super.checkListingExists();
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		super.fillInputBoxIn("type", "INGREDIENT");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.clickOnSubmit("Create");
		
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
	@CsvFileSource(resources = "/chef/kitchen-item/create-negative-ingredient.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String optionalLink) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My ingredients");
		
		super.checkListingExists();
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		super.fillInputBoxIn("type", "INGREDIENT");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.clickOnSubmit("Create");
				
		super.checkErrorsExist();
		
		super.signOut();
	}
	
}
