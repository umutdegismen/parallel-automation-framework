@API @singleUser
Feature: Get single user

  Scenario: Validate single user details
	Given I prepare and create request
	And I provide the authentication
	When I send a GET request to get single user with the id 1
	Then the status code is 200
	And I validate the users name is "George"
	And I validate the users lastname is "Bluth"
	And I validate users email is "george.bluth@reqres.in"
	And I assert the result
