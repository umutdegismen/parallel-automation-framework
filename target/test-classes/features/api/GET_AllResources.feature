@API @getAllResources
Feature: Get all resources

  Background: 
    Given I create request and provide request details
    And I provide header
    And I send a GET request

  Scenario: Verify the status code returns 200
    Then the status code should be 200

  Scenario: Verify the details of the response body
    Then the page number should be 1
    And the per page number should be 6
    And the total pages number should be 2
    And the total color count should be 6

  Scenario: Verify the support text
    Then the support text should be "Tired of writing endless social media content? Let Content Caddy generate it for you."

  Scenario: Verify the colors that cerulean and aqua sky are exists
    Then the response should contain colors "cerulean" and "aqua sky"
