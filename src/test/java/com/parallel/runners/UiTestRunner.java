package com.parallel.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features/ui",
	    glue = "com.parallel.steps.ui",
	    plugin = {
	    			"pretty", 
	    			"html:target/cucumber-reports/ui-report.html",
	    		    "json:target/cucumber-reports/ui-report.json",
	    			"rerun:target/ui-failed.txt"
	    		},
	    dryRun = false,
	    monochrome = true,
	    // tags = "@automationexercise" 
	    // tags = "@ui or @run"
	    tags = "@ui"
	)

public class UiTestRunner extends AbstractTestNGCucumberTests  {
	 
	@Override
	@DataProvider(parallel = true) // runs all the scenarios in parallel
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
