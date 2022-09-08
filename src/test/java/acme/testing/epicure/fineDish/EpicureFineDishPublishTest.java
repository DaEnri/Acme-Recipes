
package acme.testing.epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishPublishTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex) {
		
		super.signIn("epicure2", "epicure2");

		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.sortListing(1, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		super.clickOnSubmit("Publish");

		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 8, "true");

		super.signOut();
		
	}

	@Test
	@Order(10)
	public void hackingTest() {

		super.checkNotLinkExists("Account");
		super.navigate("/epicure/fine-dish/publish");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/epicure/fine-dish/publish");
		super.checkPanicExists();
		super.signOut();

		super.signIn("chef1", "chef1");
		super.navigate("/epicure/fine-dish/publish");
		super.checkPanicExists();
		super.signOut();
	}

}
