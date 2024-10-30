package com.qa.taf.constant;

public class ConstantUtil {

	// File Path Details
	public static final String CONFIG_FILE_PATH = "/src/main/resources/config/configuration.properties";
	public static final String EXCEL_FILE_PATH = "/src/test/resources/data/testData.xlsx";
	public static final String EXTENT_REPORT_CONFIG_FILE_PATH = "//src//test//resources//report//report-config.xml";
	public static final String EXTENT_REPORT_PATH = "//target//report//ExtentSpark.html";
	public static final String SNAPSHOT_PATH = "//src//test//resources//screenshots//";

	// Test Case Details
	public static final String TEST_SUITE_NAME = "TestSuite";
	public static final String TEST_CASE_NAME = "TestCaseName";
	public static final String TEST_RUN_MODE = "RunMode";

	// Environment Details
	public static final String ENV = "Env";
	public static final String LC_ENV = "Local";
	public static final String RE_ENV = "Remote";

	// Browser Details
	public static final String BROWSER = "Browser";
	public static final String GC_BROWSER = "chrome";
	public static final String FF_BROWSER = "firefox";
	public static final String ME_BROWSER = "MicrosoftEdge";

	// Browser Option Details
	public static final String CHROME_REMOTE_ORIGIN = "--remote-allow-origins=*";
	public static final String BROWSER_MAXIMIZE = "--start-maximized";

	// App URL
	public static final String APP_URL = "AppUrl";

	// Wait Time Details
	public static final int EXPLICIT_WAIT_TIME = 5;
}
