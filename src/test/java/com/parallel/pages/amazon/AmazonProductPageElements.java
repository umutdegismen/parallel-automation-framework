package com.parallel.pages.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.parallel.testbase.DriverFactory;

public class AmazonProductPageElements {

	@FindBy(xpath="//input[@value='Add to Cart']")
	public WebElement addToCartButton;
	
	public AmazonProductPageElements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
}
