package com.qa.taf.ohrm.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.commons.lang3.StringUtils;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.qa.taf.base.DriverManager;
import com.qa.taf.ohrm.pages.LoginPage;
import com.qa.taf.util.TestDataUtil;

public class LoginPageTest extends DriverManager {

	@Test(dataProviderClass = TestDataUtil.class, dataProvider = "fetchData")
	public void loginPageTest(Hashtable<String, String> data) {

		Class<LoginPageTest> classObject = LoginPageTest.class;
		Method[] methods = classObject.getMethods();
		if (!TestDataUtil.isTestRunnable(methods[0].getName(), excelReaderUtil)) {
			throw new SkipException("Skipping the Test - " + StringUtils.capitalize(methods[0].getName())
					+ " as the RunMode is set to N");
		}
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Skipping the TestCase - " + StringUtils.capitalize(methods[0].getName())
					+ " as the RunMode for the Test Data is set to N");
		}
		LoginPage loginPage = new LoginPage();
		loginPage.doLogin(data.get("UserName"), data.get("Password"));
	}
}
