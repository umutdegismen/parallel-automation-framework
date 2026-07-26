package com.parallel.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigsReader {

	private static Properties prop;

	/**
	 * This method reads the configuration file and loads the properties.
	 * 
	 */
	private static void readProperties(String filePath) {

		// I use try-with-resources to avoid using a finally block, which makes the code
		// better and clearer.
		try (FileInputStream fis = new FileInputStream(filePath)) {
			prop = new Properties();
			prop.load(fis);
			System.out.println("Loaded configuration from: " + Constants.CONFIGURATION_FILEPATH);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load configuration file: " + Constants.CONFIGURATION_FILEPATH, e);
		}
	}

	/**
	 * This method gets the property from the configuration.properties file based on
	 * the provided key parameter.
	 * <p>
	 * If property file is null, it will read the file first, then returns the
	 * value.
	 * <p>
	 * <p>
	 * If the value is null, It will throw a warning on the console.
	 * <p>
	 * 
	 * @param key
	 * @return properties
	 */
	public static String getProperties(String filePath, String key) {

		if (prop == null) {
			readProperties(filePath);
		}
		String value = prop.getProperty(key);

		if (value == null) {
			System.err.println("Warning: Property key '" + key + "' not found!");
		}
		return value;
	}

	/**
	 * This method updates the configuration.properties file based on the provided
	 * key-value property.
	 *
	 * @param filePath the path of the properties file to set
	 * @param key      property to set
	 * @param value    value to set the property
	 */
	public static void setProperties(String filePath, String key, String value) {

		// I use try-with-resources to avoid using a finally block, which makes the code
		// better and clearer.

		// Load existing properties
		try (FileInputStream fis = new FileInputStream(filePath)) {
			prop = new Properties();
			prop.load(fis);
			// Set the new property value
			prop.setProperty(key, value);
		} catch (IOException e) {
			throw new RuntimeException("Properties file loading error!", e);
		}

		// Write updated properties back to the file
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			prop.store(fos, key + " is updated!");
		} catch (IOException e) {
			throw new RuntimeException("Properties file saving error!", e);
		}
	}
}
