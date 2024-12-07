package com.qa.stf.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.stf.constant.TestConstants;
import com.qa.stf.util.FileReaderUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {

    static ExtentReports extentReports;

    public static ExtentReports setupExtentReport() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TestConstants.DATE_FORMAT);
        Date date = new Date();
        String actualDate = simpleDateFormat.format(date);
        String reportPath = TestConstants.CWD + TestConstants.EXTENT_REPORT_PATH + TestConstants.EXTENT_REPORT_FILE_NAME;
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(String.format(reportPath,actualDate));
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setDocumentTitle(TestConstants.EXTENT_REPORT_TITLE);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setReportName(TestConstants.EXTENT_REPORT_NAME);
        extentReports.setSystemInfo(TestConstants.EXTENT_REPORT_OS_INFO, System.getProperty("os.name"));
        extentReports.setSystemInfo(TestConstants.EXTENT_REPORT_USER_INFO, System.getProperty("user.name"));
        extentReports.setSystemInfo(TestConstants.EXTENT_REPORT_BROWSER_INFO, FileReaderUtil.getDataFromPropFile("Browser"));
        //extentReports.setSystemInfo("Test App URL", FileReader.getDataFromPropFile("appURL"));
        return extentReports;
    }

}
