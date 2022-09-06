package acme.testing.administrator.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorBulletinCreateTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/bulletin/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String heading, final String text, final String critical, final String optionalLink) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Create a new bulletin");
		super.checkFormExists();
		
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.fillInputBoxIn("confirmation", "true");
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Authenticated", "Recent Bulletins");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("critical", "CRITICAL");
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/bulletin/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String heading, final String text, final String critical, final String optionalLink) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Create a new bulletin");
		super.checkFormExists();
		
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.fillInputBoxIn("confirmation", "true");
		
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
    @Order(10)
    public void hackingTest() {

        super.checkNotLinkExists("Account");
        super.navigate("/administrator/bulletin/create");
        super.checkPanicExists();

        super.signIn("epicure1", "epicure1");
        super.navigate("/administrator/bulletin/create");
        super.checkPanicExists();
        super.signOut();

        super.signIn("chef1", "chef1");
        super.navigate("/administrator/bulletin/create");
        super.checkPanicExists();
        super.signOut();
    }
	
}
