package com.parallel.steps.ui;

import com.parallel.testbase.PageInitializer;
import com.parallel.utils.CommonMethods;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends CommonMethods {

	@Before
	public void start() {
		setUp();
		PageInitializer.initialize();
	}

	@After
	public void end(Scenario scenario) {

		if (scenario.isFailed()) {
			byte[] screenshot = takeScreenShot("failed/" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_"));
			scenario.attach(screenshot, "image/png", scenario.getName());
		}

		tearDown();
		PageInitializer.cleanUp();
	}
}
