package com.parallel.steps.ui.hrm;

import org.junit.Assert;
import com.parallel.utils.CommonMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HrmListEmployeeSteps extends CommonMethods {

	@When("I click on PIM on the menu")
	public void i_click_on_pim_on_the_menu() {
		click(getHrmDashboardPage().pimMenuButton);
	}

	@When("I click on Employee List on the menu")
	public void i_click_on_employee_list_on_the_menu() {
		click(getHrmDashboardPage().employeeListButton);
	}

	@Then("I should see the employee list")
	public void i_should_see_the_employee_list() {
		waitForVisibility(getHrmDashboardPage().employeeListLabel);
		Assert.assertTrue(getHrmDashboardPage().employeeListLabel.isDisplayed());
	}

}
