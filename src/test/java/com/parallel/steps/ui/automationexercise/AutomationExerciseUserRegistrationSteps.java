package com.parallel.steps.ui.automationexercise;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;

import com.parallel.utils.CommonMethods;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AutomationExerciseUserRegistrationSteps extends CommonMethods{


	@Given("the user launches the browser")
	public void the_user_launches_the_browser() {
		// handled in setup method
	}
	
	@Given("the user navigates to the url {string}")
	public void the_user_navigates_to_the_url(String url) {
		// handled in setup method
	}
	
	@Then("the home page should be visible successfully")
	public void the_home_page_should_be_visible_successfully() {
		// validate that the logo is displayed
		WebElement mainLogo = getAEMainPageElements().mainLogo;
		waitForVisibility(mainLogo);
		assertTrue(mainLogo.isDisplayed());
	}
	
	@When("the user clicks on [Signup_Login] button")
	public void the_user_clicks_on_signup_login_button() {
		WebElement signUpButton = getAEMainPageElements().signupLoginbutton;
		click(signUpButton);
	}
	
	@Then("the [New User Signup!] section should be visible")
	public void the_new_user_signup_section_should_be_visible() {
		WebElement newUserSignupForm = getAEMainPageElements().newUserSignupForm;
		waitForVisibility(newUserSignupForm);
		assertTrue(newUserSignupForm.isDisplayed());
	}
	

	@When("the user enters a valid name and a valid email address")
	public void the_user_enters_a_valid_name_and_a_valid_email_address() {
		
	}
	
	@When("the user clicks [Signup] button")
	public void the_user_clicks_signup_button() {
		
	}
	
	@Then("the [ENTER ACCOUNT INFORMATION] section should be visible")
	public void the_enter_account_information_section_should_be_visible() {
		
	}
	
	
	@When("the user fills the following details [Title, Name, Email, Password, Date of birth]")
	public void the_user_fills_the_following_details_title_name_email_password_date_of_birth() {
		
	}
	
	@When("the user selects signupCheckbox {string}")
	public void the_user_selects_signupCheckbox(String selection) {
		
	}
	
	@When("the user selects specialOffersCheckbox {string}")
	public void the_user_selects_specialOffersCheckbox(String selection) {
		
	}
	
	
	@When("the user fills the following details [First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number]")
	public void the_user_fills_the_following_details_first_name_last_name_company_address_address2_country_state_city_zipcode_mobile_number() {
		
	}
	
	@When("the user clicks the [Create Account] button")
	public void the_user_clicks_the_create_account_button() {
		
	}
	
	@Then("the {string} message should be visible")
	public void the_message_should_be_visible(String expectedMessage) {
		
	}
	
	@When("the user clicks the [Continue] button")
	public void the_user_clicks_the_continue_button() {
		
	}
	
	@Then("the user should see {string}")
	public void the_user_should_see(String expectedMessage) {
		
	}
	
	@When("the user clicks [Delete Account] button")
	public void the_user_clicks_delete_account_button() {
		
	}
	
	@Then("the user should see {string} message")
	public void the_user_should_see_message(String expectedMessage) {
		
	}
	
	@Then("the user clicks continue button")
	public void the_user_clicks_continue_button() {
		
	}
}
