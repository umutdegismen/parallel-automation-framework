package com.parallel.pages.automationexercise;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.parallel.testbase.DriverFactory;

public class AutomationExerciseMainPageElements {

	@FindBy(xpath="//img[contains(@src, 'logo.png')]")
	public WebElement mainLogo;
	
	@FindBy(xpath = "//a[@href='/login']")
	public WebElement signupLoginbutton;
	
	@FindBy(css = ".signup-form")
	public WebElement newUserSignupForm;
	
	
	public AutomationExerciseMainPageElements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
}
