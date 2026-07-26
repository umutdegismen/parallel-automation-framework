@API @getAllUsers
Feature: Get all users

  Background: 
    Given I prepare and create the request

  Scenario: Validate user list and page details for page 1
    Given I provide authentication
    When I send a GET request for page 1
    Then the response status code should be 200
    And the response should contain 6 users per page
    And the total number of pages should be 2
    And the first name of the user 2 in the data array should be "Janet"
