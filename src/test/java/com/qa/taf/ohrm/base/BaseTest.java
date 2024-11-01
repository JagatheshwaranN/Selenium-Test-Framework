package com.qa.taf.ohrm.base;

import com.qa.taf.base.DriverManager;
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
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
}

