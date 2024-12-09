package com.qa.stf.util;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.qa.stf.base.DriverManager;
import com.qa.stf.constant.TestConstants;

/**
 * The DataSupplier class provides utility methods for supplying test data to TestNG
 * test methods and determining test run based on configurations in an Excel file.
 *
 * <p>Features:
 * <ul>
 *     <li>Fetch dynamic test data from an Excel sheet based on the test method name.</li>
 *     <li>Provide test data as a DataProvider for TestNG test methods.</li>
 *     <li>Determine if a test case is runnable based on its run mode in the Excel
 *     	configuration.</li>
 * </ul>
 *
 * <p>Exception Handling:
 * <ul>
 *   <li>Custom exceptions from the {@link ExceptionUtil} class are used for handling
 *   	data retrieval errors.</li>
 *   <li>Comprehensive logging ensures detailed tracking of data loading and test run
 *   	checks.</li>
 * </ul>
 *
 * <p>Dependencies:
 * <ul>
 *   <li>Relies on the {@link ExcelReader} class to fetch data from the Excel file.</li>
 *   <li>Requires the test method name to match the sheet name for fetching test data.</li>
 *   <li>Depends on constants defined in the {@link TestConstants} class for sheet and
 *   	column names.</li>
 * </ul>
 *
 * <p>Example:
 * <pre>
 * {@code
 * @Test(dataProvider = "fetchData", dataProviderClass = DataSupplier.class)
 * public void testExample(Hashtable<String, String> data) {
 *     // Use the provided data for test execution
 * }
 *
 * boolean isRunnable = DataSupplier.isTestRunnable("testExample", excelReaderUtil);
 * }
 * </pre>
 *
 * @author Jagatheshwaran N
 * @version 1.1
 */
public class DataSupplier extends DriverManager {

	private static final Logger log = LogManager.getLogger(DataSupplier.class);

	/**
	 * Provides test data from an Excel sheet to be used in TestNG test methods.
	 * <p>
	 * This DataProvider dynamically fetches data from an Excel sheet where the sheet
	 * name corresponds to the test method name. It creates a 2D Object array where
	 * each row contains a Hashtable mapping column headers to their respective values
	 * for that row.
	 * </p>
	 *
	 * @param method The TestNG method for which the DataProvider is fetching data.
	 *               The sheet name is assumed to match the method's name.
	 * @return A 2D Object array containing test data as Hashtable.
	 *         Each Hashtable maps column headers to their respective row values.
	 */
	@DataProvider(name = "fetchData")
	public static Object[][] fetchDataFromExcel(Method method) {
		var sheetName = method.getName();
		var totalRows = excelReader.getRowCount(sheetName);
		var totalColumns = excelReader.getColumnCount(sheetName);
		if (totalRows < 2 || totalColumns == 0) {
			log.warn("The sheet '{}' does not contain enough data to fetch. Returning empty data.", sheetName);
			return new Object[0][0];
		}
		var data = new Object[totalRows - 1][1];
		for (var row = 2; row <= totalRows; row++) {
			Hashtable<String, String> table = new Hashtable<>();
			for (var col = 0; col < totalColumns; col++) {
				table.put(
						excelReader.getCellData(sheetName, col, 1), // Column header
						excelReader.getCellData(sheetName, col, row) // Cell value
				);
			}
			data[row - 2][0] = table;
			log.debug("Fetched data for row {}: {}", row, table);
		}
		log.info("Data fetched successfully from sheet '{}'. Total rows: {}", sheetName, totalRows - 1);
		return data;
	}


	/**
	 * Determines whether the specified test is runnable based on the run mode in the
	 * Excel sheet.
	 * <p>
	 * This method checks the test's run mode in the provided Excel sheet. If the test's
	 * run mode is "Y", it returns {@code true}, indicating the test is runnable. If the
	 * run mode is "N" or the test is not found, it returns {@code false}.
	 * </p>
	 *
	 * @param testName       The name of the test case to check.
	 * @param excelReader Utility to read data from the Excel file.
	 * @return {@code true} if the test is runnable (run mode is "Y"); otherwise, {@code false}.
	 */
	public static boolean isTestRunnable(String testName, ExcelReader excelReader) {
		var sheetName = TestConstants.TEST_SUITE_NAME;
		var testCaseColumn = TestConstants.TEST_CASE_NAME;
		var runModeColumn = TestConstants.TEST_RUN_MODE;
		var rows = excelReader.getRowCount(sheetName);
		for (var row = 2; row <= rows; row++) {
			var testCase = excelReader.getCellData(sheetName, testCaseColumn, row);
			if (testCase != null && testCase.equalsIgnoreCase(testName)) {
				var runMode = excelReader.getCellData(sheetName, runModeColumn, row);
				if (TestConstants.RUN_MODE_YES.equalsIgnoreCase(runMode)) {
					log.info("The '{}' test case is runnable. Sheet: '{}', Run Mode: '{}'", testName, sheetName, runMode);
					return true;
				} else {
					log.info("The '{}' test case is not runnable. Sheet: '{}', Run Mode: '{}'", testName, sheetName, runMode);
					return false;
				}
			}
		}
		log.warn("Test case '{}' not found in sheet '{}'. Defaulting to not runnable.", testName, sheetName);
		return false;
	}

}

