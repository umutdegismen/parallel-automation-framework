package com.parallel.testbase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.parallel.utils.ConfigsReader;
import com.parallel.utils.Constants;

public class DriverFactory {

	// ThreadLocal to support parallel execution
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/**
	 * Gets driver for current thread
	 * 
	 * @return WebDriver
	 */
	public static WebDriver getDriver() {
		return driver.get();
	}

	// Set driver for current thread
	private static void setDriver(WebDriver driverInstance) {
		driver.set(driverInstance);
	}

	/**
	 * Initializes WebDriver based on config.properties file
	 * <p>
	 * Maximizes the window and sets implicit wait
	 * <p>
	 */
	public static void setUp() {

		if (getDriver() == null) {
			String filePath = Constants.CONFIGURATION_FILEPATH;
			WebDriver drv;
			
			String browser = ConfigsReader.getProperties(filePath, "browser").toLowerCase();

			switch (browser) {
			case "chrome":
				drv = new ChromeDriver();
				break;
			case "edge":
				drv = new EdgeDriver();
				break;
			case "firefox":
				drv = new FirefoxDriver();
				break;
			default:
				throw new RuntimeException("Browser not supported: " + browser);
			}

			// Set driver for ThreadLocal
			setDriver(drv);

			// Maximize window and set implicit wait
			drv.manage().window().maximize();
			drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));

			// Navigate to the URL from config.properties
			getDriver().get(ConfigsReader.getProperties(filePath, "url"));
		}
	}

	/**
	 * Quit the WebDriver and removes from ThreadLocal
	 * 
	 */
	public static void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
		}
	}
	
}
