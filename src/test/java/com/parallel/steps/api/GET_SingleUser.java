package com.parallel.steps.api;

import org.testng.asserts.SoftAssert;

import com.parallel.api.ReqresApiEndpoints;
import com.parallel.utils.ConfigsReader;
import com.parallel.utils.Constants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_SingleUser extends ReqresApiEndpoints{

	RequestSpecification request;
	Response response;
	SoftAssert softAssert;
	JsonPath json;
	
	@Given("I prepare and create request")
	public void i_prepare_and_create_request() {
		RestAssured.baseURI = ConfigsReader.getProperties(Constants.CONFIGURATION_FILEPATH, "reqres_base_uri");
		request = RestAssured.given()
				.contentType(ContentType.JSON);
		
		softAssert = new SoftAssert();
	}
	
	@Given("I provide the authentication")
	public void i_provide_the_authentication() {
		String key = System.getenv("REQRES_API_TOKEN_KEY");
		String value = System.getenv("REQRES_API_TOKEN_VALUE");
		request.header(key, value);
	}
	
	@When("I send a GET request to get single user with the id {int}")
	public void i_send_a_get_request_to_get_single_user_with_the_id(int id) {
		response = request
					.when()
					.get(GET_SINGLE_USER_ENDPOINT + id)
					.prettyPeek();
		json = response.jsonPath();
	}
	
	@Then("the status code is {int}")
	public void the_status_code_is(int statusCode) {
		response.then()
		.assertThat().statusCode(statusCode);
	}
	
	@Then("I validate the users name is {string}")
	public void i_validate_the_users_name_is(String expectedName) {
		
		String actualName = json.getString("data.first_name");
		softAssert.assertEquals(actualName, expectedName);
		//response.then()
		//.body("data.first_name", Matchers.equalTo(name));
		
	}
	
	@Then("I validate the users lastname is {string}")
	public void i_validate_the_users_lastname_is(String expectedLastname) {
		String actualLastname = json.getString("data.last_name");
		softAssert.assertEquals(actualLastname, expectedLastname);
		//		response.then()
		//		.body("data.last_name", Matchers.equalTo(lastname));
	}
	
	@Then("I validate users email is {string}")
	public void i_validate_users_email_is(String expectedEmail) {
		String actualEmail = json.getString("data.email");
		softAssert.assertEquals(actualEmail, expectedEmail);
		//		response.then()
		//		.body("data.email", Matchers.equalTo(email));
	}
	
	@Then("I assert the result")
	public void i_assert_the_result() {
		softAssert.assertAll();
	}
}
