@amazon
Feature: Add a product to cart

  #1. Open the browser and navigate to Amazon/FIipkart
  #2. Search for a product (e.g., "laptop")
  #3. Filter the results by a specific brand
  #4. Retrieve & print names and prices of first 5 products
  #5. Add the first product to the cart
  #6. Verify the product is added to the cart
  Scenario: Validate a product is added to the cart successfully
    Given I navigate to Amazon website
    When I search for a product "laptop"
    And I filter the results by a specific brand "Samsung"
    And I print names and prices of first 5 products to the console
    And I add the first product to the cart
    Then I validate that the product is added to the cart
