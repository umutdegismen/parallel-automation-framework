@automationexercise
Feature: User Registeration and Account Deletion

  @register
  Scenario: Register a new user and delete the account successfully
    Given the user launches the browser
    And the user navigates to the url "http://automationexercise.com"
    Then the home page should be visible successfully
    
    When the user clicks on [Signup_Login] button
    Then the [New User Signup!] section should be visible
    
    When the user enters a valid name and a valid email address
    And the user clicks [Signup] button
    Then the [ENTER ACCOUNT INFORMATION] section should be visible
    
    When the user fills the following details [Title, Name, Email, Password, Date of birth]
    And the user selects signupCheckbox "Sign up for our newsletter!"
    And the user selects specialOffersCheckbox "Receive special offers from our partners!"
    And the user fills the following details [First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number]
    And the user clicks the [Create Account] button
    Then the "ACCOUNT CREATED!" message should be visible
    
    When the user clicks the [Continue] button
    Then the user should see "Logged in as username"
    
    When the user clicks [Delete Account] button
    Then the user should see "ACCOUNT DELETED!" message
    And the user clicks continue button
