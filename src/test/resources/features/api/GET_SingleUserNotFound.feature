@API
Feature: Single User Not Found

  @singleUserNotFound
  Scenario Outline: Validate single user is not found
    Given I prepare and create a request
    And I provide token
    When I send the request for these "<userId>"
    Then I validate status code is 404
    And I validate users are not found and response body is null

    Examples: 
      | userId |
      |     23 |
      |     35 |
      |     36 |
      |     45 |
