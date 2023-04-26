package com.qa.taf.util;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.qa.taf.base.DriverManager;
import com.qa.taf.constant.ConstantUtil;

public class TestDataUtil extends DriverManager {

	private static Logger log = LogManager.getFormatterLogger(TestDataUtil.class);

	@DataProvider(name = "fetchData")
	public static Object[][] fetchDataFromExcel(Method method) {
		var sheetName = method.getName();
		var totalRows = excelReaderUtil.getRowCount(sheetName);
		var totalColumns = excelReaderUtil.getColumnCount(sheetName);
		var data = new Object[totalRows - 1][1];
		Hashtable<String, String> table = null;
		for (var row = 2; row <= totalRows; row++) {
			table = new Hashtable<String, String>();
			for (var col = 0; col < totalColumns; col++) {
				table.put(excelReaderUtil.getCellData(sheetName, col, 1),
						excelReaderUtil.getCellData(sheetName, col, row));
				data[row - 2][0] = table;
				log.info("The " + "'" + data[row - 2][0] + "'" + " is fetched from the " + "'" + sheetName + "'"
						+ " excel sheet");
			}
		}
		return data;
	}

	public static boolean isTestRunnable(String testName, ExcelReaderUtil excelReaderUtil) {

		var sheetName = ConstantUtil.TEST_SUITE_NAME;
		var testCaseColumn = ConstantUtil.TEST_CASE_NAME;
		var runModeColumn = ConstantUtil.TEST_RUN_MODE;
		var rows = excelReaderUtil.getRowCount(sheetName);
		for (var row = 2; row <= rows; row++) {
			var testCase = excelReaderUtil.getCellData(sheetName, testCaseColumn, row);
			if (testCase.equalsIgnoreCase(testName)) {
				var runMode = excelReaderUtil.getCellData(sheetName, runModeColumn, row);
				if (runMode.equalsIgnoreCase("Y")) {
					log.info("The " + "'" + testName + "'" + " test case is available in the excel sheet " + "'"
							+ sheetName + "'" + " and runmode is set to Y");
					return true;
				} else {
					log.info("The " + "'" + testName + "'" + " is available in the excel sheet " + "'" + sheetName + "'"
							+ " and runmode is set to N");
					return false;
				}
			}
		}
		return false;
	}
}
