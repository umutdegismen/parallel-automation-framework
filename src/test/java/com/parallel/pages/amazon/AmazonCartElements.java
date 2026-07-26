package com.parallel.pages.amazon;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.parallel.testbase.DriverFactory;

public class AmazonCartElements {

	@FindBy(xpath="//ul[@data-name='Active Items']//span[@class='a-truncate-cut']")
	public List<WebElement> cartItems;
	
	public AmazonCartElements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
}
