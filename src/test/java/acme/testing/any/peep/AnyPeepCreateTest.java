package acme.testing.any.peep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyPeepCreateTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String heading, final String writer, final String text, final String optionalEmail) {
		
		super.clickOnMenu("Anonymous", "Recent peeps");
		super.checkListingExists();
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("writer", writer);
		super.fillInputBoxIn("optionalEmail", optionalEmail);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, writer);
		super.checkColumnHasValue(recordIndex, 3, text);
		super.checkColumnHasValue(recordIndex, 4, optionalEmail);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String heading, final String writer, final String text, final String optionalEmail, final String confirmation) {
		
		super.clickOnMenu("Anonymous", "Recent peeps");
		super.checkListingExists();
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("writer", writer);
		super.fillInputBoxIn("optionalEmail", optionalEmail);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");
				
		super.checkErrorsExist();
		
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
