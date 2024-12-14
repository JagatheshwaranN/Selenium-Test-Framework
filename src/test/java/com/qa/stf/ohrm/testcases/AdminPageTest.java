package com.qa.stf.ohrm.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.commons.lang3.StringUtils;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.qa.stf.ohrm.base.BaseTest;
import com.qa.stf.base.DriverManager;
import com.qa.stf.ohrm.pages.PageManager;
import com.qa.stf.util.DataSupplier;

public class AdminPageTest extends BaseTest {

	PageManager pageManager;

	@Test(dataProviderClass = DataSupplier.class, dataProvider = "fetchData")
	public void adminPageTest(Hashtable<String, String> data) throws InterruptedException {

		var classObject = AdminPageTest.class;
		Method[] methods = classObject.getMethods();
		if (!DataSupplier.isTestRunnable(methods[0].getName(), DriverManager.excelReader)) {
			throw new SkipException("Skipping the Test - " + StringUtils.capitalize(methods[0].getName())
					+ " as the RunMode is set to N");
		}
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Skipping the TestCase - " + StringUtils.capitalize(methods[0].getName())
					+ " as the RunMode for the Test Data is set to N");
		}
		pageManager = new PageManager();
		pageManager.getLoginPage().doLogin(data.get("UserName"), data.get("Password"));
		Thread.sleep(5000);
		pageManager.getDashboardPage().navigateToAdminPage();
		Thread.sleep(5000);
		pageManager.getAdminPage().searchUser(data.get("User"), data.get("SearchCriteria"));
	}
}

