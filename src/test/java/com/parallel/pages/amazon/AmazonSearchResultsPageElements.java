package com.parallel.pages.amazon;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.parallel.testbase.DriverFactory;

public class AmazonSearchResultsPageElements {

	
	@FindBy(xpath = "//h2[text()='Results']")
	public WebElement resultsLabel;
	
	@FindBy(xpath = "//span[text()='Brands']")
	public WebElement brandsFilter;
	
	@FindBy(xpath = "//ul[@id='filter-p_123']//span[text()='See more']")
	public WebElement moreResultBrand;
	
	@FindBy(xpath="//div[@id='brandsRefinements']/ul//a/span")
	public List<WebElement> brandsList;
	
	@FindBy(xpath="//div[@role='listitem']//a/h2")
	public List<WebElement> itemNameList;
	
	@FindBy(xpath = "//div[@data-component-type='s-search-result']//span[@data-a-size='xl']/span[@class='a-offscreen']")
	public List<WebElement> itemPriceList;
	
	@FindBy(xpath = "//a[contains(text(),'Go to Cart')]")
	public WebElement goToCart;
	
	@FindBy(xpath = "//div[@data-component-type='s-search-result']")
	public List<WebElement> productCards;

	
	
	
	public AmazonSearchResultsPageElements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
}
