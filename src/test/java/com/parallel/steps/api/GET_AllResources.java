package com.parallel.steps.api;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.asserts.SoftAssert;

import com.parallel.api.ReqresApiEndpoints;
import com.parallel.utils.ConfigsReader;
import com.parallel.utils.Constants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_AllResources extends ReqresApiEndpoints {

	RequestSpecification request;
	Response response;

	@Given("I create request and provide request details")
	public void i_create_request_and_provide_request_details() {
		
		RestAssured.baseURI = ConfigsReader.getProperties(Constants.CONFIGURATION_FILEPATH, "reqres_base_uri");
		request = RestAssured.given().contentType(ContentType.JSON);
	}

	@Given("I provide header")
	public void i_provide_header() {
		request.header(System.getenv("REQRES_API_TOKEN_KEY"), System.getenv("REQRES_API_TOKEN_VALUE"));
	}

	@Given("I send a GET request")
	public void i_send_a_get_request() {
		response = request.when().get(GET_LIST_ALL_RESOURCES).prettyPeek();
	}

	@Then("the status code should be {int}")
	public void the_status_code_should_be(int expectedStatusCode) {
		response.then().statusCode(expectedStatusCode);
	}

	@Then("the response should contain colors {string} and {string}")
	public void the_response_should_contain_colors_and(String color1, String color2) {

		SoftAssert soft = new SoftAssert();

		List<Object> listOfColors = response.getBody().jsonPath().getList("data.name");

		if (!listOfColors.contains(color1)) {
			soft.assertTrue(false, color1 + " DOES NOT EXIST");
		}
		if (!listOfColors.contains(color2)) {
			soft.assertTrue(false, color2 + " DOES NOT EXIST");
		}

		soft.assertAll();

		// 2nd way
//		boolean isColor1True = false;
//		boolean isColor2True = false;
//		
//		for(Object color:listOfColors) {
//			if(color.equals(color1)) {
//				isColor1True = true;
//			}
//			if(color.equals(color2)) {
//				isColor2True = true;
//			}
//		}
//		
//		if(!isColor1True) {
//			System.out.println(color1 + " DOES NOT EXIST!");
//			soft.assertTrue(false);
//		}
//		if(!isColor2True) {
//			System.out.println(color2 + " DOES NOT EXIST!");
//			soft.assertTrue(false);
//		}

// 		soft.assertAll();		

	}

	@Then("the page number should be {int}")
	public void the_page_number_should_be(int pageNumber) {
		response.then().assertThat().body("page", Matchers.equalTo(pageNumber));
	}

	@Then("the per page number should be {int}")
	public void the_per_page_number_should_be(int perPageNumber) {
		response.then().assertThat().body("per_page", Matchers.equalTo(perPageNumber));
	}

	@Then("the total pages number should be {int}")
	public void the_total_pages_number_should_be(int totalPageNumber) {
		response.then().assertThat().body("total_pages", Matchers.equalTo(totalPageNumber));
	}

	@Then("the total color count should be {int}")
	public void the_total_color_count_should_be(int totalColorCount) {
		response.then().assertThat().body("data.size()", Matchers.equalTo(totalColorCount));

	}

	@Then("the support text should be {string}")
	public void the_support_text_should_be(String expectedText) {
		response.then().assertThat().body("support.text", Matchers.equalTo(expectedText));
	}
}
