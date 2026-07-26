@ui
Feature: Login page registration field validations

  Background: 
    Given user is on the main page

  Scenario: Verify displaying of the following fields
    Then user should see the following fields
      | eBay Live                     |
      | Saved                         |
      | Motors                        |
      | Electronics                   |
      | Collectibles                  |
      | Home & Garden                 |
      | Clothing, Shoes & Accessories |
      | Toys                          |
      | Sporting Goods                |
      | Business & Industrial         |
      | Jewelry & Watches             |
      | Refurbished                   |
