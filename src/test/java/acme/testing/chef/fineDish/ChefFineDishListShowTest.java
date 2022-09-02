package acme.testing.chef.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefFineDishListShowTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
		
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dish/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String request, final String budget, final String creationDate, final String startDate, final String finishDate, final String published, final String moreInfo) {
		
		super.signIn("chef2", "chef2");
		super.clickOnMenu("Chef", "My fine dishes");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, request);
		super.checkColumnHasValue(recordIndex, 3, budget);
		super.checkColumnHasValue(recordIndex, 4, creationDate);
		super.checkColumnHasValue(recordIndex, 5, startDate);
		super.checkColumnHasValue(recordIndex, 6, finishDate);
		super.checkColumnHasValue(recordIndex, 7, moreInfo);
		super.checkColumnHasValue(recordIndex, 8, published);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		super.checkInputBoxHasValue("published", published);
		
	}
}
