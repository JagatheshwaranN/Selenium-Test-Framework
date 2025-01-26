package com.qa.stf.app.testcases;

import com.qa.stf.app.base.BaseTest;
import com.qa.stf.app.pages.*;
import com.qa.stf.base.BasePage;
import com.qa.stf.base.DriverManager;
import com.qa.stf.constant.TestConstants;
import com.qa.stf.util.DataSupplier;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Hashtable;

import static com.qa.stf.app.constant.AppConstants.*;

public class TimesheetPageTest extends BaseTest {

    PageManager pageManager;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    TimesheetPage timesheetPage;

    @Test(dataProviderClass = DataSupplier.class, dataProvider = "fetchData")
    public void timePageTest(Hashtable<String, String> data) {

        var classObject = TimesheetPageTest.class;
        Method[] methods = classObject.getMethods();
        if (!DataSupplier.isTestRunnable(methods[0].getName(), DriverManager.excelReader)) {
            throw new SkipException("Skipping the Test - " + StringUtils.capitalize(methods[0].getName())
                    + " as the RunMode is set to N");
        }
        if (data.get("RunMode").equalsIgnoreCase(TestConstants.RUN_MODE_NO)) {
            throw new SkipException("Skipping the TestCase - " + StringUtils.capitalize(methods[0].getName())
                    + " as the RunMode for the Test Data is set to N");
        }

        pageManager = new PageManager();
        basePage = pageManager.getPageComponent();
        loginPage = pageManager.getLoginPage();
        dashboardPage = pageManager.getDashboardPage();
        timesheetPage = pageManager.getTimesheetPage();

        Assert.assertEquals(basePage.getPageUrl(), LOGIN_PAGE_URL);
        Assert.assertEquals(basePage.getPageTitle(), LOGIN_PAGE_TITLE);
        Assert.assertEquals(loginPage.verifyLoginPageHeader(), LOGIN_PAGE_HEADER);
        loginPage.doLogin(data.get("UserName"), pageManager.getEncryptionManager().decryptData(data.get("Password")));
        Assert.assertEquals(dashboardPage.verifyDashboardPageHeader(), DASHBOARD_PAGE_HEADER);
        dashboardPage.navigateToTimePage();
        Assert.assertEquals(timesheetPage.verifyTimeSheetPageHeader(), TIMESHEET_PAGE_HEADER);
        timesheetPage.navigateToPunchInOutSection();
        timesheetPage.addPunchInDetail(data.get("Notes"));
        timesheetPage.addPunchOutDetail(data.get("Notes"));
        timesheetPage.navigateToEmployeeRecordsSection();
        String userName = pageManager.getDashboardPage().fetchUserNameFromDropDown();
        Assert.assertTrue(timesheetPage.searchEmployeeAttendanceRecord(userName));
    }

}
