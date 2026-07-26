package com.parallel.steps.api;

import org.hamcrest.Matchers;

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

public class GET_AllUsers extends ReqresApiEndpoints {

	RequestSpecification request;
	Response response;

	@Given("I prepare and create the request")
	public void i_prepare_and_create_the_request() {
		String filepath = Constants.CONFIGURATION_FILEPATH;
				//System.getProperty("user.dir")+"/src/test/resources/configs/api/configuration.properties";
		RestAssured.baseURI = ConfigsReader.getProperties(filepath, "reqres_base_uri");
		
		request = RestAssured.given()
				.contentType(ContentType.JSON);
	}
	
	@Given("I provide authentication")
	public void i_provide_authentication() {
		String authKey = System.getenv("REQRES_API_TOKEN_KEY");
		String authValue = System.getenv("REQRES_API_TOKEN_VALUE");
		request.header(authKey,authValue);
	}

	@When("I send a GET request for page {int}")
	public void i_send_a_get_request_for_page(int page) {
		response = request
					.when()
					.get(GET_ALL_USERS_ENDPOINT + page)
					.prettyPeek();
	}

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(int expectedStatusCode) {
		response
		.then()
		.statusCode(expectedStatusCode);
	}

	@Then("the response should contain {int} users per page")
	public void the_response_should_contain_users_per_page(int expectedCount) {
		response
		.then()
		.assertThat().body("per_page", Matchers.equalTo(expectedCount));
	}

	@Then("the total number of pages should be {int}")
	public void the_total_number_of_pages_should_be(int expectedTotalPages) {
		response
		.then()
		.assertThat().body("total_pages", Matchers.equalTo(expectedTotalPages));
	}

	@Then("the first name of the user {int} in the data array should be {string}")
	public void the_first_name_of_the_user_at_position_in_the_data_array_should_be(int position, String firstName) {

		int index = 0;

		if (position <= 0) {
			index = 0;
		}
		if (position > 0) {
			index = position - 1;
		}

		response
		.then()
		.assertThat().body(String.format("data[%s].first_name", index), Matchers.equalTo(firstName));

	}
}
