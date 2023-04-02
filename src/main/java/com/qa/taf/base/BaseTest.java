package com.qa.taf.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.taf.util.FileReaderUtil;

public class BaseTest extends DriverManager {

	@BeforeMethod
	public void testStartUp() {

		FileReaderUtil.loadPropertyFile();
		launchBrowser();
	}

	@AfterMethod
	public void testTearDown() {
		// DriverManager driverManager = new DriverManager();
		if (getDriver() != null) {
			getDriver().quit();
		}
	}

}
