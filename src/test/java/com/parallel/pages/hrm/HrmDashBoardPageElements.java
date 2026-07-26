package com.parallel.pages.hrm;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.parallel.testbase.DriverFactory;

public class HrmDashBoardPageElements {

	@FindBy(css = "#menu-profile")
	public WebElement profilePicture;

	@FindBy(css = ".toast-message")
	public WebElement toastMessage;
	
	@FindBy(xpath = "//span[text()='PIM']")
	public WebElement pimMenuButton;
	
	@FindBy(xpath = "//span[text()='Employee List']")
	public WebElement employeeListButton;
	
	@FindBy(xpath = "//li[text()='Employee List']")
	public WebElement employeeListLabel;

	public HrmDashBoardPageElements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
}
