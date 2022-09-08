
package acme.testing.chef.memorandum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandumCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String report, final String optionalLink, final String fineDishCode) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My memorandums");
		
		super.checkListingExists();
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.fillInputBoxIn("fineDish", fineDishCode);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.checkColumnHasValue(recordIndex, 2, report);
		super.checkColumnHasValue(recordIndex, 3, optionalLink);
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String report, final String optionalLink, final String fineDishCode) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My memorandums");
		
		super.checkListingExists();
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.fillInputBoxIn("fineDish", fineDishCode);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();

		super.signOut();
		
	}
	
	@Test
	@Order(10)
	public void hackingTest() {

		super.checkNotLinkExists("Account");
		super.navigate("/chef/memorandum/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/memorandum/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/memorandum/create");
		super.checkPanicExists();
		super.signOut();
	}
	

}
