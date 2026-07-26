package com.parallel.utils;

import com.parallel.api.NeotechSchoolEndoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/*
 * This class manages API tokens by creating a new token if one does not already exist 
 * and storing it in a ThreadLocal variable. 
 * This ensures that each parallel test scenario has its own token instance and can safely access it.
 */

public class APITokenManager {

	
	// Initialize the configuration filePath
	private static String filePath = //Constants.CONFIGURATION_FILEPATH;
			System.getProperty("user.dir") + "/src/test/resources/configs/api/configuration.properties";
	
	// Get the credentials from windows environment
	private static String username = System.getenv("NEOTECH_API_USERNAME");
	private static String password = System.getenv("NEOTECH_API_PASSWORD");

	// Get the base uri
	private static String baseUri = ConfigsReader.getProperties(filePath, "neotech_base_uri");
			

	// Creating a ThreadLocal variable. So, every single thread will be able to use
	// their own tokens.
	private static ThreadLocal<String> threadLocalToken = new ThreadLocal<>();

	/**
	 * This method gets new token.
	 * 
	 * @return
	 */
	public static String getToken() {
		
		String token = threadLocalToken.get();

		if (token == null) {
			token = requestNewToken();
			threadLocalToken.set(token); // set for this thread.
		}
		return token;
	}
	

	/**
	 * This method sends a new request to get a new token.
	 * 
	 * @return
	 */
	private static String requestNewToken() {

		String payload = String.format("""
				{
				  "userNameOrEmailAddress": "%s",
				  "password": "%s",
				  "rememberClient": true
				}
				""", username, password);

		return RestAssured.given()
				.baseUri(baseUri)
				.body(payload)
				.contentType(ContentType.JSON)
				.when()
				.post(NeotechSchoolEndoints.GENERATE_TOKEN_ENDPOINT)
				.prettyPeek()
				.then()
				.extract()
				.path("result.accessToken");
	}

	/**
	 * This method cleans ThreadLocal after the test is done.
	 * 
	 */
	public static void clearToken() {
		threadLocalToken.remove();
	}
	
	
	public static void main(String[] args) {
		System.out.println(getToken());
		clearToken();
	}
}
