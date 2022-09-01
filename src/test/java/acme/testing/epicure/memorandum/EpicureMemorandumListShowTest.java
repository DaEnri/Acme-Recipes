
package acme.testing.epicure.memorandum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureMemorandumListShowTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memorandum/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String serialCode, final String instantiationMoment, final String report, final String optionalLink) {

		super.signIn("epicure2", "epicure2");
		super.clickOnMenu("Epicure", "My memoranda");
		super.checkListingExists();

		super.checkColumnHasValue(recordIndex, 0, serialCode);
		super.checkColumnHasValue(recordIndex, 1, instantiationMoment);
		super.checkColumnHasValue(recordIndex, 2, report);
		super.checkColumnHasValue(recordIndex, 3, optionalLink);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("serialCode", serialCode);
		super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
		super.checkInputBoxHasValue("report", report);
		super.checkInputBoxHasValue("optionalLink", optionalLink);

		super.signOut();
	}
}
