
package acme.testing.epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String request, final String budget, final String startDate, final String finishDate, final String moreInfo,
		final String chef) {
		super.signIn("epicure2", "epicure2");

		super.clickOnMenu("Epicure", "My fine dishes");
		super.clickOnButton("Create fine dish");
		super.checkFormExists();

		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("chef", chef);

		super.clickOnSubmit("Create");

		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		super.checkInputBoxHasValue("published", "false");

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String status, final String code, final String request, final String budget, final String startDate, final String finishDate, final String moreInfo) {

		super.signIn("epicure2", "epicure2");

		super.clickOnMenu("Epicure", "My fine dishes");
		super.clickOnButton("Create fine dish");
		super.checkFormExists();

		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("moreInfo", moreInfo);

		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	@Order(10)
	public void hackingTest() {

		super.checkNotLinkExists("Account");
		super.navigate("/epicure/fine-dish/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/epicure/fine-dish/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("chef1", "chef1");
		super.navigate("/epicure/fine-dish/create");
		super.checkPanicExists();
		super.signOut();
	}

}
