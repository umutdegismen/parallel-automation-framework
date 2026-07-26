package com.parallel.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/ui",
		glue = "com.parallel.steps.ui",
		dryRun = true,
		monochrome = true,
		tags = "@amazon"
		)



public class RunnerToCopyPaste {

}
