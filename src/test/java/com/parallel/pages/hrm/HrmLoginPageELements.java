package com.parallel.pages.hrm;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.parallel.testbase.DriverFactory;

public class HrmLoginPageELements {
	
	@FindBy(id = "txtUsername")
	public WebElement username;
	
	@FindBy(id = "txtPassword")
	public WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement submitButton;
	
	public HrmLoginPageELements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
}
