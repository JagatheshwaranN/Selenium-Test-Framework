package com.qa.stf.app.base;

import com.qa.stf.base.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends DriverManager {

    @BeforeMethod
	public void testStartUp() {
		initializeDriver();
	}

	@AfterMethod
	public void testTearDown() {
		closeDriver();
	}
}

