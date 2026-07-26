package com.parallel.steps.ui.ebay;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.parallel.utils.CommonMethods;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class EbayMainPageFieldValidationSteps extends CommonMethods {

	@Given("user is on the main page")
	public void user_is_on_the_main_page() {
		assertTrue(getEbayDashBoardPageElements().logo.isDisplayed());
	}

	@Then("user should see the following fields")
	public void user_should_see_the_following_fields(DataTable expectedFields) {
		waitForVisibility(getEbayDashBoardPageElements().ebayLiveElement);
		
		List<String> expectedStrings = expectedFields.asList();
		List<WebElement> actualWebElements = getEbayDashBoardPageElements().headerFieldList;
		List<String> actualStrings = new ArrayList<>();
		
		for(WebElement actualItem:actualWebElements) {
			actualStrings.add(actualItem.getText());
		}
		
		assertEquals(actualStrings, expectedStrings);
		
	}
	
	@Then("user should see the following fields in the middle of the page")
	public void user_should_see_the_following_fields_in_the_middle_of_the_page(DataTable expectedFields) {
	    List<String> expectStrings = expectedFields.asList();
	    List<WebElement> actualWebElements = getEbayDashBoardPageElements().carouselFieldList;
	    List<String> actualStrings = new ArrayList<>();
	    
	    for(WebElement actualItem:actualWebElements) {
	    	actualStrings.add(actualItem.getText());
	    }
	    
	    assertEquals(actualStrings, expectStrings);
	}
}
