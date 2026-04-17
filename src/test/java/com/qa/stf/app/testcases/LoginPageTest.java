package com.qa.stf.app.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import com.qa.stf.app.pages.DashboardPage;
import com.qa.stf.app.pages.LoginPage;
import com.qa.stf.base.BasePage;
import com.qa.stf.constant.TestConstants;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.qa.stf.app.base.BaseTest;
import com.qa.stf.base.DriverManager;

import com.qa.stf.app.pages.PageManager;
import com.qa.stf.util.DataSupplier;

import static com.qa.stf.app.constant.AppConstants.*;

public class LoginPageTest extends BaseTest {

    PageManager pageManager;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test(dataProviderClass = DataSupplier.class, dataProvider = "fetchData")
    public void loginPageTest(Method method, Hashtable<String, String> data) {

        var classObject = LoginPageTest.class;
        //Method[] methods = classObject.getMethods();
        if (!DataSupplier.isTestRunnable(method.getName(), DriverManager.excelReader)) {
            throw new SkipException("Skipping the Test - " + StringUtils.capitalize(method.getName())
                    + " as the RunMode is set to N");
        }
        if (data.get("RunMode").equalsIgnoreCase(TestConstants.RUN_MODE_NO)) {
            throw new SkipException("Skipping the TestCase - " + StringUtils.capitalize(method.getName())
                    + " as the RunMode for the Test Data is set to N");
        }

        pageManager = new PageManager();
        basePage = pageManager.getPageComponent();
        loginPage = pageManager.getLoginPage();
        dashboardPage = pageManager.getDashboardPage();

        Assert.assertEquals(basePage.getPageUrl(), LOGIN_PAGE_URL);
        Assert.assertEquals(basePage.getPageTitle(), LOGIN_PAGE_TITLE);
        Assert.assertEquals(loginPage.verifyLoginPageHeader(), LOGIN_PAGE_HEADER);
        loginPage.doLogin(data.get("UserName"), pageManager.getEncryptionManager().decryptData(data.get("Password")));
        Assert.assertEquals(dashboardPage.verifyDashboardPageHeader(),DASHBOARD_PAGE_HEADER);
        System.out.println("Login Page Test Executed Successfully");
    }

}

