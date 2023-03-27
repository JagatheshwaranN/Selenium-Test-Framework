package com.qa.taf.ohrm.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.commons.lang3.StringUtils;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.qa.taf.base.DriverManager;
import com.qa.taf.ohrm.pages.AdminPage;
import com.qa.taf.ohrm.pages.DashboardPage;
import com.qa.taf.ohrm.pages.LoginPage;
import com.qa.taf.util.TestDataUtil;

public class AdminPageTest extends DriverManager {

	@Test(dataProviderClass = TestDataUtil.class, dataProvider = "fetchData")
	public void adminPageTest(Hashtable<String, String> data) throws InterruptedException {

		var classObject = AdminPageTest.class;
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
		DashboardPage dashboardPage = loginPage.doLogin("Admin", "admin123");
		Thread.sleep(5000);
		AdminPage adminPage = dashboardPage.navigateToAdminPage();
		Thread.sleep(5000);
		adminPage.searchUser("Admin", "Enabled");
	}
}
