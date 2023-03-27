package com.qa.taf.util;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.qa.taf.base.DriverManager;

public class TestDataUtil extends DriverManager {

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
			}
		}
		return data;
	}

	public static boolean isTestRunnable(String testName, ExcelReaderUtil excelReaderUtil) {

		var sheetName = ConstantUtil.Test_Suite_Name;
		var testCaseColumn = ConstantUtil.Test_Case_Name;
		var runModeColumn = ConstantUtil.Test_Run_Mode;
		var rows = excelReaderUtil.getRowCount(sheetName);
		for (var row = 2; row <= rows; row++) {
			var testCase = excelReaderUtil.getCellData(sheetName, testCaseColumn, row);
			if (testCase.equalsIgnoreCase(testName)) {
				var runMode = excelReaderUtil.getCellData(sheetName, runModeColumn, row);
				if (runMode.equalsIgnoreCase("Y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
