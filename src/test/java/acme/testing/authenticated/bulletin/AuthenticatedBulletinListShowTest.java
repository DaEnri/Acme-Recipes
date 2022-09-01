
package acme.testing.authenticated.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedBulletinListShowTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/bulletin/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String heading, final String instantiationMoment, final String critical, final String text, final String optionalLink) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Authenticated", "Recent Bulletins");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 1, instantiationMoment);
		super.checkColumnHasValue(recordIndex, 2, critical);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("heading", heading);
		if (critical.equals("false")) {
			super.checkInputBoxHasValue("critical", "NOT CRITICAL");
		} else {
			super.checkInputBoxHasValue("critical", "CRITICAL");
		}
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("optionalLink", optionalLink);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
