package acme.testing.epicure.memorandum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureMemorandumCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memorandum/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String report, final String optionalLink, final String fineDishCode) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My memoranda");
		
		super.checkListingExists();
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		super.checkSubmitExists("Create");
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
	@CsvFileSource(resources = "/epicure/memorandum/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String report, final String optionalLink, final String fineDishCode) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My memoranda");
		
		super.checkListingExists();
		super.checkButtonExists("Create");
		
		super.clickOnButton("Create");
		super.checkFormExists();
		super.checkSubmitExists("Create");
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
		super.navigate("/epicure/memorandum/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/epicure/memorandum/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("chef1", "chef1");
		super.navigate("/epicure/memorandum/create");
		super.checkPanicExists();
		super.signOut();
	}
	
}
