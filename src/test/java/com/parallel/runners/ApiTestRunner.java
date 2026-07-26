package com.parallel.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features/api",
	    glue = "com.parallel.steps.api",
	    plugin = {
		         "pretty", 
				 "html:target/cucumber-reports/api-report.html",
		    	 "json:target/cucumber-reports/api-report.json",
		    	 "rerun:target/api-failed.txt"
		    		},
	    dryRun = false,
	    monochrome = true,
	    tags = "@getAllResources"
	)

public class ApiTestRunner extends AbstractTestNGCucumberTests  {
	 
	@Override
	@DataProvider(parallel = true) // runs all the scenarios in parallel
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
