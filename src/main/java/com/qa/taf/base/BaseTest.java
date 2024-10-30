<<<<<<< HEAD
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
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
}
=======
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
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
}
>>>>>>> 3a5b23362e15d382589ba9ebe11d0cd0a0c77f68
