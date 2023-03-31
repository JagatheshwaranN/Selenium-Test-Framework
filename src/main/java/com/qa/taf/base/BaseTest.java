package com.qa.taf.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.taf.util.ConstantUtil;

public class BaseTest extends DriverManager {

	private static String browser;

	public static String getBrowser() {
		return browser;
	}

	public static void setBrowser(String browser) {
		BaseTest.browser = browser;
	}

	@BeforeMethod
	public void testStartUp() {
		loadPropertyFile();
		if (System.getenv(ConstantUtil.BROWSER ) != null && !System.getenv(ConstantUtil.BROWSER).isEmpty()) {
			setBrowser(System.getenv(ConstantUtil.BROWSER));
		} else {
			setBrowser(getDataFromPropFile(ConstantUtil.BROWSER));
		}
		properties.setProperty(ConstantUtil.BROWSER, getBrowser());
		launchBrowser(getBrowser());
	}

	@AfterMethod
	public void testTearDown() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
}
