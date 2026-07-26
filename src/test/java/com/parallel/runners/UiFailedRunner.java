package com.parallel.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "@target/ui-failed.txt",
	    glue = "com.parallel.steps.ui",
	    plugin = {
    			"pretty", 
	   			"html:target/cucumber-reports/ui-report.html",
	   		    "json:target/cucumber-reports/ui-report.json",
	   		    "rerun:target/ui-failed.txt"
	    		},
	    monochrome = true
	)

public class UiFailedRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
