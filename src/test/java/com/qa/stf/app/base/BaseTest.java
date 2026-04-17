package com.qa.stf.app.base;

import com.qa.stf.base.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected DriverManager driverManager = DriverManager.getInstance();

    @BeforeMethod
	public void testStartUp() {
        driverManager.initializeDriver();
	}

	@AfterMethod(alwaysRun = true)
	public void testTearDown() {
        System.out.println("Test Execution Completed. Closing the Driver.");
        driverManager.closeDriver();
	}
}
