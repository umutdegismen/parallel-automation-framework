package com.parallel.steps.api;

import org.testng.Assert;

import com.parallel.api.ReqresApiEndpoints;
import com.parallel.utils.ConfigsReader;
import com.parallel.utils.Constants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_SingleUserNotFound extends ReqresApiEndpoints {

	RequestSpecification request;
	Response response;

	@Given("I prepare and create a request")
	public void i_prepare_and_create_a_request() {
		RestAssured.baseURI = ConfigsReader.getProperties(Constants.CONFIGURATION_FILEPATH, "reqres_base_uri");
		request = RestAssured.given().contentType(ContentType.JSON);
	}

	@Given("I provide token")
	public void i_provide_token() {
		request
		.header(System.getenv("REQRES_API_TOKEN_KEY"), System.getenv("REQRES_API_TOKEN_VALUE"));
	}

	@When("I send the request for these {string}")
	public void i_send_the_request_for_these(String userId) {
		response = request
					.when()
					.get(GET_SINGLE_USER_ENDPOINT + userId)
					.prettyPeek();
		
		System.out.println("I SEND THE REQUEST!");
	}

	@Then("I validate status code is {int}")
	public void i_validate_status_code_is(int expectedStatus) {
		response
		.then()
		.assertThat().statusCode(expectedStatus);
	}

	@Then("I validate users are not found and response body is null")
	public void i_validate_users_are_not_found_and_response_body_is_null() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals("{}", responseBody);
	}
}
