package com.parallel.pages.ebay;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.parallel.testbase.DriverFactory;

public class EbayRegisterPageElements {

	@FindBy(className = "prenodeTxt")
	public WebElement haveAnAccountLabel;

	@FindBy(className = "action-link")
	public WebElement signInButton;

	@FindBy(id = "mainContent h1")
	public WebElement createAnAccountLabel;

	@FindBy(id = "personalaccount-radio")
	public WebElement personalTab;

	@FindBy(id = "businessaccount-radio")
	public WebElement businessTab;

	@FindBy(id = "firstname")
	public WebElement firstname;

	@FindBy(id = "lastname")
	public WebElement lastname;

	@FindBy(id = "Email")
	public WebElement email;

	@FindBy(id = "password")
	public WebElement password;

	@FindBy(id = "EMAIL_REG_FORM_SUBMIT")
	public WebElement submitButton;

	@FindBy(id = "google")
	public WebElement googleButton;

	@FindBy(id = "facebook")
	public WebElement facebookButton;

	@FindBy(id = "apple")
	public WebElement appleButton;

	public EbayRegisterPageElements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
}
