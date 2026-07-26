package com.parallel.steps.ui.hrm;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.parallel.utils.CommonMethods;
import com.parallel.utils.ConfigsReader;
import com.parallel.utils.Constants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HrmLoginSteps extends CommonMethods {

	@Given("I enter valid credentials")
	public void i_enter_valid_credentials() {
		String filePath = Constants.CONFIGURATION_FILEPATH;
		sendText(getHrmLoginPage().username, ConfigsReader.getProperties(filePath,"username"));
		sendText(getHrmLoginPage().password, ConfigsReader.getProperties(filePath,"password"));
		click(getHrmLoginPage().submitButton);
	}

	@Then("I should see the dashboard")
	public void i_should_see_the_dashboard() {
		WebElement profilePicture = getHrmDashboardPage().profilePicture;
		waitForVisibility(profilePicture);
		Assert.assertTrue(profilePicture.isDisplayed());
	}

	@Given("I enter invalid credentials")
	public void i_enter_invalid_credentials() {
		sendText(getHrmLoginPage().username, "wrongInfo");
		sendText(getHrmLoginPage().password, "wrongInfo");
		click(getHrmLoginPage().submitButton);
	}

	@Given("I should not see the dashboard")
	public void i_should_not_see_the_dashboard() {
		waitForVisibility(getHrmDashboardPage().toastMessage);
		Assert.assertTrue(getHrmDashboardPage().toastMessage.isDisplayed());
	}

	Map<String, String> firstRowData;

	// excel data
	@Given("I get the data from excel {string}")
	public void i_get_the_data_from_excel(String rowIndex) {
		String filePath = Constants.DATA_FILEPATH;
		String sheetName = "Sheet1";
		String excelColumnName = "userId";
		firstRowData = getRowByColumnValue(filePath, sheetName, rowIndex, excelColumnName );
	}

	@When("I enter valid credentials from excel")
	public void i_enter_valid_credentials_from_excel() {
		sendText(getHrmLoginPage().username, firstRowData.get("firstname"));
		sendText(getHrmLoginPage().password, firstRowData.get("lastname"));
		click(getHrmLoginPage().submitButton);
	}

}
