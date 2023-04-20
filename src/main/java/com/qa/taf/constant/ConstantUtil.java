package com.qa.taf.constant;

public class ConstantUtil {

	public static final String CONFIG_FILE_PATH = "/src/main/resources/config/configuration.properties";
	public static final String EXCEL_FILE_PATH = "/src/test/resources/data/testData.xlsx";

	public static final String TEST_SUITE_NAME = "TestSuite";
	public static final String TEST_CASE_NAME = "TestCaseName";
	public static final String TEST_RUN_MODE = "RunMode";

	public static final String BROWSER = "Browser";
	public static final String ENV = "Env";
	public static final String APP_URL = "AppUrl";

	public static final String GC_BROWSER = "chrome";
	public static final String FF_BROWSER = "firefox";
	public static final String ME_BROWSER = "MicrosoftEdge";

	public static final String LC_ENV = "Local";
	public static final String RE_ENV = "Remote";

	public static final String CHROME_REMOTE_ORIGIN = "--remote-allow-origins=*";
	public static final String BROWSER_MAXIMIZE = "--start-maximized";
	
	public static final int IMPLICIT_WAIT_TIME = 10;
}
