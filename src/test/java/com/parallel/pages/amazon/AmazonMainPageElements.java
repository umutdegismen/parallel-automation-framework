package com.parallel.pages.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.parallel.testbase.DriverFactory;

public class AmazonMainPageElements{
	
	@FindBy(xpath="//div[@id='nav-iss-attach']//preceding-sibling::div//input")
	public WebElement searchBar;
	
	@FindBy(id="nav-search-submit-button")
	public WebElement searchButton;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement submitButton;
	
	
	public AmazonMainPageElements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

}
