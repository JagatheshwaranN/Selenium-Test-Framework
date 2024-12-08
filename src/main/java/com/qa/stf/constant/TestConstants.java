package com.qa.stf.constant;

public class TestConstants {

	// User Directory
	public static final String CWD = System.getProperty("user.dir");

	// File Path Details
	public static final String CONFIG_FILE_PATH = "/src/main/resources/config/configuration.properties";
	public static final String EXCEL_FILE_PATH = "/src/test/resources/data/testData.xlsx";
	public static final String EXTENT_REPORT_CONFIG_FILE_PATH = "//src//test//resources//report//report-config.xml";
	public static final String EXTENT_REPORT_PATH = "//target//report//";
	public static final String EXTENT_REPORT_FILE_NAME = "ExtentReport_%s.html";
	public static final String SNAPSHOT_PATH = "//src//test//resources//screenshots//";

	// Test Case Details
	public static final String TEST_SUITE_NAME = "TestSuite";
	public static final String TEST_CASE_NAME = "TestCaseName";
	public static final String TEST_RUN_MODE = "RunMode";
	public static final String RUN_MODE_YES = "Y";
	public static final String RUN_MODE_NO = "N";

	// Browser Details
	public static final String CHROME = "Chrome";
	public static final String FIREFOX = "Firefox";
	public static final String EDGE = "MicrosoftEdge";

	// Browser Option Details
	public static final String CHROME_REMOTE_ORIGIN = "--remote-allow-origins=*";
	public static final String BROWSER_MAXIMIZE = "--start-maximized";

	// App URL
	public static final String APP_URL = "AppUrl";

	// Wait Time Details
	public static final int EXPLICIT_WAIT_TIME = 5;

	// Test Status Details
	public static final String TEST_PASS = " Test Passed";
	public static final String TEST_FAIL = " Test Failed";
	public static final String TEST_START = " Test Started";
	public static final String TEST_SKIP = " Test Skipped. As the RUN MODE is set to N";

	// Extent Report Details
	public static final String DATE_FORMAT = "dd_MM_yyyy_hh_mm_ss";
	public static final String EXTENT_REPORT_TITLE = "Test Execution Report";
	public static final String EXTENT_REPORT_NAME = "Test Automation";
	public static final String EXTENT_REPORT_OS_INFO = "Operating System";
	public static final String EXTENT_REPORT_USER_INFO = "User Name";
	public static final String EXTENT_REPORT_BROWSER_INFO = "Test Browser";

}

