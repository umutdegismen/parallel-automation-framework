@smoke2
Feature: Login tests

  #		 * A SAMPLE APPROACH:
  #		 *
  #		 * To be able to apply a HYBRID APPROACH (e.g. first, addEmployee (as API), 2nd
  #		 * deleteEmployee (as UI test)) I provide url in a gherkin step. Otherwise, it
  #		 * may try to delete the undefined employee. Since my main test focus is not
  #		 * "createEmployee" in the "deleteEmployee" functionality, I create employee in
  #		 * backend API, then delete employee in UI.
  @login
  Scenario: Valid login
    Given I enter valid credentials
    Then I should see the dashboard
	@login
  Scenario: Invalid login
    Given I enter invalid credentials
    Then I should not see the dashboard

  Scenario: Validate seeing employee list
    Given I enter valid credentials
    When I click on PIM on the menu
    And I click on Employee List on the menu
    Then I should see the employee list

  @deneme2
  Scenario Outline: Validate invalid login from excel file
    Given I get the data from excel "<userId>"
    When I enter valid credentials from excel
    Then I should not see the dashboard

    Examples: 
      | userId |
      |      1 |
      |      2 |
      |      3 |
      |      4 |
      |      5 |
      |      6 |
      |      7 |
