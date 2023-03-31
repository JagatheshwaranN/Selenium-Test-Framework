package com.qa.taf.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.taf.util.FileReaderUtil;

public class BaseTest {

	DriverManager driverManager = new DriverManager();

	@BeforeMethod
	public void testStartUp() {
		FileReaderUtil.loadPropertyFile();

		driverManager.launchBrowser();
	}

	@AfterMethod
	public void testTearDown() {
		if (driverManager.getDriver() != null) {
			driverManager.getDriver().quit();
		}
	}

}
