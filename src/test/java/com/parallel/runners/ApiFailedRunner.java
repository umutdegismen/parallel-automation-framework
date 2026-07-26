package com.parallel.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "@target/api-failed.txt",
	    glue = "com.parallel.steps.api",
	    plugin = {
		         "pretty", 
				 "html:target/cucumber-reports/api-report.html",
		    	 "json:target/cucumber-reports/api-report.json",
		    	 "rerun:target/api-failed.txt"
	    		},
	    monochrome = true
	)

public class ApiFailedRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
