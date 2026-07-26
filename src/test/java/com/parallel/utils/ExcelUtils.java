package com.parallel.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.parallel.testbase.PageInitializer;

/**
 * ExcelReader class to facilitate reading data from Excel files. It supports
 * loading data from a specified sheet into various formats, such as a list of
 * maps or a 2D array.
 */
public class ExcelUtils extends PageInitializer {

	private static Workbook book;
	private static Sheet sheet;

	/**
	 * This method will initialize a Workbook object given the filePath.
	 * 
	 * @param filePath the path of the Excel file to open
	 */
	private static void openExcel(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			book = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will load a sheet given the sheetName.
	 *
	 * @param sheetName the name of the sheet to load
	 */
	private static void loadSheet(String sheetName) {
		sheet = book.getSheet(sheetName);
	}

	/**
	 * This method will return the row count of the current sheet.
	 * 
	 * @return the number of physical rows
	 */
	private static int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

	/**
	 * This method will return the column count for a given row index in the current
	 * sheet.
	 * 
	 * @param rowIndex the index of the row
	 * @return the number of columns in the row
	 */
	private static int getColCount(int rowIndex) {
		return sheet.getRow(rowIndex).getLastCellNum();
	}

	/**
	 * This method retrieves the data from a specific cell in the currently loaded
	 * sheet.
	 * 
	 * @param rowIndex    the index of the row
	 * @param columnIndex the index of the column
	 * @return the data from the specified cell as a String
	 */
	private static String getCellData(int rowIndex, int columnIndex) {

		return sheet.getRow(rowIndex).getCell(columnIndex).toString();
	}

	/**
	 * This method will return the cell data as a List of Map of String
	 * 
	 * @param filePath  the path of the Excel file
	 * @param sheetName the name of the sheet to read from
	 * @return a List of Maps containing the data from the sheet
	 */
	public static List<Map<String, String>> getExcelData(String filePath, String sheetName) {
		// Open the Excel file located at the specified filePath
		openExcel(filePath);
		// Load the Excel sheet by its name
		loadSheet(sheetName);
		// Get the total number of rows in the sheet
		int rowNumber = getRowCount();

		// Initialize a list to store each row's data as a map (key-value pairs)
		List<Map<String, String>> dataList = new ArrayList<>();

		// Loop through each row starting from row index 1 (ignoring the header row at
		// index 0)
		for (int row = 1; row < rowNumber; row++) {
			// Create a LinkedHashMap to maintain the order of insertion for each row's data
			Map<String, String> dataMap = new LinkedHashMap<>();
			// Loop through each column in the current row
			for (int col = 0; col < getColCount(row); col++) {
				String key = getCellData(0, col);
				String value = getCellData(row, col);
				// Store the header-value pair in the map
				dataMap.put(key, value);
			}
			// Add the current header-value pairs to the list
			dataList.add(dataMap);
		}

		// Return the list of maps, where each map represents a row with key-value pairs
		return dataList;
	}

	/**
	 * This method reads the data from the specified Excel file and sheet and stores
	 * in a 2D array.
	 * 
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public static Object[][] excelToArray(String filePath, String sheetName) {
		openExcel(filePath);
		loadSheet(sheetName);
		int rowCount = getRowCount();
		int columnCount = getColCount(0);

		// create a 2D array with [rowCount-1]. We don't want to count the header.
		Object[][] dataArray = new Object[rowCount - 1][columnCount];

		for (int row = 1; row < rowCount; row++) {
			for (int col = 0; col < columnCount; col++) {
				dataArray[row - 1][col] = getCellData(row, col);
			}
		}
		return dataArray;
	}

	/**
	 * This method retrieves a single row of data from the given Excel sheet and returns it as a map.
	 * <p>
	 * This method is primarily intended for use with Scenario Outlines in Cucumber 
	 * to support Data-Driven Testing (DDT). Each map entry corresponds to a 
	 * column header and its associated cell value.
	 * </p>
	 *
	 * @param filePath   the path to the Excel file
	 * @param sheetName  the name of the sheet to read from
	 * @param rowIndex   the row identifier value to match (e.g., "1")
	 * @param columnName the column name used to filter the target row (e.g., "userId")
	 * @return a map representing the matching row, where keys are column headers 
	 *         and values are cell contents
	 * @throws RuntimeException if no matching row is found
	 */
	public static Map<String, String> getRowByColumnValue(String filePath, String sheetName, String rowIndex, String columnName) {

		// Create a List of maps to store the map data from excel file.
		List<Map<String, String>> dataList = getExcelData(filePath, sheetName);
		
		// stream() method returns a --Map<String,String>--
		// convert the parameters into Integer to prevent "numeric/double" issues.
		// Data may be retrieved from excel as double (1.0) and may be sent from
		// scenario outline as an int (1).
		return dataList
				.stream()
				.filter(row -> Integer.parseInt(row.get(columnName).split("\\.")[0]) == Integer.parseInt(rowIndex))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Row not found for " + rowIndex));
	}
}
