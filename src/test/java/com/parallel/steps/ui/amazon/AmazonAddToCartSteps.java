package com.parallel.steps.ui.amazon;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.parallel.utils.CommonMethods;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmazonAddToCartSteps extends CommonMethods {

	String brandToFilter;
	WebElement firstItemToCart;
	String firstItemNameInCart;

	// Open the browser and navigate to Amazon/FIipkart
	@Given("I navigate to Amazon website")
	public void i_navigate_to_amazon_website() {
		click(getAmazonMainPageElements().submitButton);
		// navigating to url is happening in setup method
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://www.amazon.com/");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
	}

	// Search for a product (e.g., "laptop")
	@When("I search for a product {string}")
	public void i_search_for_a_product(String productToSearch) {
		click(getAmazonMainPageElements().searchBar);
		sendText(getAmazonMainPageElements().searchBar, productToSearch);
		click(getAmazonMainPageElements().searchButton);
	}

	// Filter the results by a specific brand
	@When("I filter the results by a specific brand {string}")
	public void i_filter_the_results_by_a_specific_brand(String brandToFilter) {
		this.brandToFilter = brandToFilter;
		waitForClickable(getAmazonSearchResultsPageElements().brandsFilter);
		for (WebElement brandElement : getAmazonSearchResultsPageElements().brandsList) {
			// get the brand name
			String brand = brandElement.getText().toLowerCase();

			// if the brand name is not in the list, click on 'more result' button
			if (brand == null || brand.isBlank()) {
				click(getAmazonSearchResultsPageElements().moreResultBrand);
				brand = brandElement.getText().toLowerCase(); // assign again not to miss any brand
			}
			if (brand.equals(brandToFilter.toLowerCase())) {
				click(brandElement);
				System.out.println(brand + " CLICKED!");
				break;
			}

		}
	}

	// Retrieve & print names and prices of first 5 products
	@When("I print names and prices of first {int} products to the console")
	public void i_print_names_and_prices_of_first_products_to_the_console(int productNumber) {

		waitForVisibility(getAmazonSearchResultsPageElements().resultsLabel);
		wait(1);

		int count = 0; // item count

		System.out.println("The first " + productNumber + " items:");

		// I get the products as a productCards to match the item name and the price
		for (WebElement card : getAmazonSearchResultsPageElements().productCards) {
			// get the name
			String brandName = card.findElement(By.xpath(".//h2")).getText().trim().toLowerCase();
			// get the price -> price, dom'da hidden oldugu icin getAttribute("textContent")
			// kullaniyorum.
			String price = card.findElement(By.xpath(".//span[@data-a-size='xl']/span[@class='a-offscreen']"))
					.getAttribute("textContent");
			// get the links of the products
			WebElement productLink = card.findElement(By.tagName("a"));
			// get the 'Add to Cart' button for the specific element
			WebElement addToCartButton = card.findElement(By.name("submit.addToCart"));

			// if the item is a sponsored item, skip.
			if (!brandName.contains(brandToFilter.toLowerCase())) {
				continue;
			}

			System.out.println(price + " -> " + brandName);
			count++;

			if (count == 1) {
				firstItemToCart = addToCartButton;
				firstItemNameInCart = brandName;
			}
			if (count == productNumber)
				break;
		}
	}

	// Add the first product to the cart
	@When("I add the first product to the cart")
	public void i_add_the_first_product_to_the_cart() {
		click(firstItemToCart);
		System.out.println("FIRST ITEM ADDED TO THE CART");
	}

	// Verify the product is added to the cart
	@Then("I validate that the product is added to the cart")
	public void i_validate_that_the_product_is_added_to_the_cart() {
		click(getAmazonSearchResultsPageElements().goToCart);
		System.out.println("CLICKED TO GO TO CART");
		for (WebElement item : getAmazonCartElements().cartItems) {
			waitForVisibility(item);
			String actualItemName = item.getText().toLowerCase().substring(0, 50);
			String expectedItemName = firstItemNameInCart.substring(0, 50);
			Assert.assertEquals(expectedItemName,actualItemName);
		}
	}

}
