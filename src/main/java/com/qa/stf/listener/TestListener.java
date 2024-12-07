package com.qa.stf.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.stf.report.ExtentReport;
import com.qa.stf.util.ExceptionUtil;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.stf.base.DriverManager;
import com.qa.stf.constant.TestConstants;

public class TestListener extends DriverManager implements ITestListener, ISuiteListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    DriverManager driverManager;
    ExtentReports extentReports;
    ExtentTest extentTest;

    // private static String messageBody;
    private static final String REPORT_CONFIG_KEY = "org.uncommons.reportng.escape-output";
    private static final String REPORT_CONFIG_VALUE = "false";
    private static final String SNAPSHOT_PATH = System.getProperty("user.dir") + TestConstants.SNAPSHOT_PATH;
    private static final String IMG_FORMAT = ".png";

    public TestListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void onStart(ISuite suite) {
        log.info("Test Suite started: {}", suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("Test Suite finished: {}", suite.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReport.setupExtentReport();
        log.info("Test Context started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test Context finished: {}", context.getName());
        extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
        driverManager.setExtentTest(extentTest);
        driverManager.getExtentTest().log(Status.INFO, () -> result.getName().toUpperCase() + TestConstants.TEST_START);
        Reporter.log(result.getName().toUpperCase() + TestConstants.TEST_START);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        handleTestResult(result, Status.PASS, TestConstants.TEST_PASS);
        log.warn("Test Passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        handleTestResult(result, Status.FAIL, TestConstants.TEST_FAIL);
        log.warn("Test Failed: {}", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        driverManager.getExtentTest().log(Status.SKIP, () -> result.getName().toUpperCase() + TestConstants.TEST_SKIP);
        log.warn("Test Skipped: {}", result.getName());
    }

    private void handleTestResult(ITestResult result, Status status, String message) {
        System.setProperty(REPORT_CONFIG_KEY, REPORT_CONFIG_VALUE);
        String snapshotPath = captureScreenshot();
        try {
            String base64Snapshot = ((TakesScreenshot) driverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
            driverManager.getExtentTest().log(status, result.getName().toUpperCase() + message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Snapshot).build());
        } catch (Exception ex) {
            log.error("Failed to capture Base64 screenshot: {}", ex.getMessage(), ex);
        }
        testNGReporterUpdate(result.getName().toUpperCase() + message, snapshotPath);
    }

    private String captureScreenshot() {
        String timestamp = new SimpleDateFormat(TestConstants.DATE_FORMAT).format(Calendar.getInstance().getTime());
        String uniqueId = UUID.randomUUID().toString();
        File source = ((TakesScreenshot) driverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        File destination = new File(SNAPSHOT_PATH + timestamp + "_" + uniqueId + IMG_FORMAT);
        try {
            FileUtils.copyFile(source, destination);
            log.info("Screenshot saved: {}", destination.getAbsolutePath());
        } catch (IOException ex) {
            log.error("Failed to save screenshot: {}", ex.getMessage(), ex);
            throw new ExceptionUtil.ScreenshotException("Failed to create the screenshot", ex);
        }
        return destination.getAbsolutePath();
    }

    private void testNGReporterUpdate(String testStatus, String screenshotPath) {
        Reporter.log("<br>");
        Reporter.log(testStatus);
        Reporter.log("<br>");
        Reporter.log("<a target='_blank' href='" + screenshotPath + "'><img src='" + screenshotPath
                + "' height='100' width='100' /></a>");
    }

}

