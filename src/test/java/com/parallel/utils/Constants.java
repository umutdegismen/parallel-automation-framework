package com.parallel.utils;

public class Constants {

	/*
	   The environment is being selected based on the provided parameter via CI/CD command.
	   
	   Check if the system property named env is set. If not, use 'qa' by default.
	 
	   SAMPLE maven command:
	   -> mvn test -Denv=api -Dtest=com.project.runners.ApiTestRunner -Dcucumber.filter.tags="@smoke"
	   -> mvn test -Denv=stage -Dtest=com.project.runners.UiTestRunner -Dcucumber.filter.tags="@smoke"
	   -> mvn test -Denv=qa -Dtest=com.project.runners.UiTestRunner -Dcucumber.filter.tags="@smoke"
	 
	   OR you want to change the runner class to UI runnerclass that are on STAGE env through maven command: 
	   -> mvn test -Denv=stage -Dtest=com.project.runners.UiTestRunner
	 
	   OR execute only the smoke UI tests through maven command without editing the pom.xml: 
	   -> mvn test -Denv=stage -Dtest=com\parallel\runners\UiTestRunner -Dcucumber.filter.tags="@smoke"
	   
	   OR - change the parallelThreadCount through maven command
	   -> mvn clean test -Denv=api -Ddataproviderthreadcount=3
	 */
	public static final String CONFIGURATION_FILEPATH = System.getProperty("user.dir") + "/src/test/resources/configs/env/"
			+ System.getProperty("env", "qa") + "/configuration.properties";

	public static final String SCREENSHOT_FILEPATH = System.getProperty("user.dir") + "/screenshots/";

	public static final String DATA_FILEPATH = System.getProperty("user.dir")
			+ "/src/test/resources/testdata/sample.xlsx";

	// This path is the path for API-globals. Provide a file name while using it.
	public static final String API_GLOBALS_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/configs/api/";

	public static final int IMPLICIT_WAIT_TIME = 20;

	public static final int EXPLICIT_WAIT_TIME = 30;
}
