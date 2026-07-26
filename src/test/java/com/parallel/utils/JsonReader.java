package com.parallel.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

	/**
	 * Utility class for reading values from JSON files.
	 * <p>
	 * Provides a method to fetch data from a JSON file using a dot-separated key path.
	 * </p>
	 *
	 * <pre>
	 * Example usage:
	 * 
	 * String studentName = JsonReader.getValue("api-globals.json", "School.Name");
	 * System.out.println("Student name: " + studentName);
	 *
	 * Output:
	 * XYZ High School
	 * </pre>
	 */

	
	

	/**
	 * This method reads a JSON file and returns the value of a given JSON path.
	 * 
	 * @param jsonPath the dot-separated path to the desired key (e.g. "data.student.name")
	 * @param fileName the name of the JSON file located under the API_GLOBALS_PATH
	 * @return the value found at the specified JSON path as a String
	 */
	public static String getValue(String jsonPath, String fileName) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(new File(Constants.API_GLOBALS_PATH + fileName ));
			
			// fileName : api-globals.json
			
			// .split("\\.") is to split json data into separate datas such as:
			// {data.student} -> [data, student]
			// {result.success} -> [result, success]
			String[] keys = jsonPath.split("\\.");

			// re-initialize JsonNode to keep the root unchanged in case we need it later on.
			// 'root' is the beginning of the file
			// 'node' is the current node
			JsonNode node = root;

			for (String key : keys) {
				node = node.get(key);
				if (node == null) {
					throw new RuntimeException("Key not found: " + jsonPath);
				}
			}
			return node.asText();

		} catch (IOException e) {
			throw new RuntimeException("Failed to read JSON file: " + fileName, e);
		}
	}

	// TEST THE FILE
//	public static void main(String[] args) {
//		
//		String value = JsonReader.getValue("Student.Name", "api-globals.json");
//		System.out.println(value);
//	}

}
