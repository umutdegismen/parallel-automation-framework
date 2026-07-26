package com.parallel.pages.ebay;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.parallel.testbase.DriverFactory;

public class EbayDashBoardPageElements {

	@FindBy(id = "ebayLogoTitle")
	public WebElement logo;

	@FindBy(linkText = "register")
	public WebElement registerButton;

	@FindBy(xpath = "//span[text()='eBay Live']")
	public WebElement ebayLiveElement;

	@FindBy(xpath = "//div[@id='vl-flyout-nav']//li//span")
	public List<WebElement> headerFieldList;

	@FindBy(xpath = "//aside[@class='page-grid-container flex flex-col gap-300 md:gap-200 dp-popular-destinations-module dp-popular-destinations-module-grow']//li")
	public List<WebElement> carouselFieldList;

	public EbayDashBoardPageElements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
}
