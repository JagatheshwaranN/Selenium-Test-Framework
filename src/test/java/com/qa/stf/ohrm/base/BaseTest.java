package com.qa.stf.ohrm.base;

import com.qa.stf.base.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.stf.util.FileReader;

public class BaseTest extends DriverManager {

	@BeforeMethod
	public void testStartUp() {
		FileReader.loadPropertyFile();
		initializeDriver();
	}

	@AfterMethod
	public void testTearDown() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
}

