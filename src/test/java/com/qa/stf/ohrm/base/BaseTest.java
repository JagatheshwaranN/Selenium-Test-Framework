package com.qa.stf.ohrm.base;

import com.qa.stf.base.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.stf.util.FileReader;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest extends DriverManager {

	@BeforeSuite
	public void init(){
		FileReader.loadPropertyFile();
	}

	@BeforeMethod
	public void testStartUp() {
		initializeDriver();
	}

	@AfterMethod
	public void testTearDown() {
		closeDriver();
	}
}

