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

/**
 * The TestListener class implements the TestNG {@link ITestListener} and {@link ISuiteListener}
 * interfaces to provide custom behavior during test execution and suite lifecycle events.
 *
 * <p>Features:
 * <ul>
 *     <li>Logs the start and end of test suites and test contexts.</li>
 *     <li>Integrates with the ExtentReports library to generate detailed HTML reports.</li>
 *     <li>Captures and attaches screenshots for failed or passed test cases.</li>
 *     <li>Customizes logging and reporting for each test result (start, success, failure,
 *     skipped).</li>
 *     <li>Enhances TestNG reports with screenshots and formatted messages.</li>
 * </ul>
 *
 * <p>Exception Handling:
 * <ul>
 *   <li>Uses the {@link ExceptionUtil} class for handling exceptions during screenshot
 *   capture.</li>
 *   <li>Provides detailed logging for unexpected behaviors or failures.</li>
 * </ul>
 *
 * <p>Note:
 * The class assumes proper setup of WebDriver and ExtentReports through {@link DriverManager}.
 * Users must ensure correct configuration of the snapshot path and date format constants
 * defined in {@link TestConstants}.
 *
 * <p>Example Usage:
 * <pre>
 * {@code
 * public class TestExecution {
 *     @BeforeClass
 *     public void setup() {
 *         DriverManager driverManager = new DriverManager();
 *         TestListener listener = new TestListener(driverManager);
 *         TestNG.addListener(listener);
 *     }
 * }
 * }
 * </pre>
 *
 * @author Jagatheshwaran N
 * @version 1.1
 */
public class TestListener extends DriverManager implements ITestListener, ISuiteListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    DriverManager driverManager;
    ExtentReports extentReports;
    ExtentTest extentTest;

    private static final String REPORT_CONFIG_KEY = "org.uncommons.reportng.escape-output";
    private static final String REPORT_CONFIG_VALUE = "false";
    private static final String SNAPSHOT_PATH = System.getProperty("user.dir") + TestConstants.SNAPSHOT_PATH;
    private static final String IMG_FORMAT = ".png";

    /**
     * Constructs a TestListener instance.
     *
     * @param driverManager The {@link DriverManager} instance used to manage WebDriver
     *                      and ExtentTest objects.
     */
    public TestListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    /**
     * Called when a test suite starts execution.
     *
     * @param suite The test suite that is starting.
     */
    @Override
    public void onStart(ISuite suite) {
        log.info("Test Suite started: {}", suite.getName());
    }

    /**
     * Called when a test suite finishes execution.
     *
     * @param suite The test suite that has finished.
     */
    @Override
    public void onFinish(ISuite suite) {
        log.info("Test Suite finished: {}", suite.getName());
    }

    /**
     * Called when a test context (set of test methods) starts execution.
     *
     * @param context The test context that is starting.
     */
    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReport.setupExtentReport();
        log.info("Test Context started: {}", context.getName());
    }

    /**
     * Called when a test context (set of test methods) finishes execution.
     *
     * @param context The test context that has finished.
     */
    @Override
    public void onFinish(ITestContext context) {
        log.info("Test Context finished: {}", context.getName());
        extentReports.flush();
    }

    /**
     * Called when a test method starts execution.
     *
     * @param result The result object containing details of the test method.
     */
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
        driverManager.setExtentTest(extentTest);
        driverManager.getExtentTest().log(Status.INFO, () -> result.getName().toUpperCase() + TestConstants.TEST_START);
        Reporter.log(result.getName().toUpperCase() + TestConstants.TEST_START);
    }

    /**
     * Called when a test method passes.
     *
     * @param result The result object containing details of the test method.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        handleTestResult(result, Status.PASS, TestConstants.TEST_PASS);
        log.warn("Test Passed: {}", result.getName());
    }

    /**
     * Called when a test method fails.
     *
     * @param result The result object containing details of the test method.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        handleTestResult(result, Status.FAIL, TestConstants.TEST_FAIL);
        log.warn("Test Failed: {}", result.getName());
    }

    /**
     * Called when a test method is skipped.
     *
     * @param result The result object containing details of the test method.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        driverManager.getExtentTest().log(Status.SKIP, () -> result.getName().toUpperCase() + TestConstants.TEST_SKIP);
        log.warn("Test Skipped: {}", result.getName());
    }

    /**
     * Handles test results by logging the status, capturing screenshots, and updating
     * TestNG reports.
     *
     * @param result  The result object containing details of the test method.
     * @param status  The {@link Status} of the test (PASS, FAIL, or SKIP).
     * @param message The message to log for the test status.
     */
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

    /**
     * Captures a screenshot and saves it to the predefined snapshot path.
     *
     * @return The absolute path of the saved screenshot.
     * @throws ExceptionUtil.ScreenshotException If the screenshot cannot be saved.
     */
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

    /**
     * Updates the TestNG Reporter with the test status and an embedded screenshot.
     *
     * @param testStatus     The test status message to log.
     * @param screenshotPath The path of the screenshot to embed.
     */
    private void testNGReporterUpdate(String testStatus, String screenshotPath) {
        Reporter.log("<br>");
        Reporter.log(testStatus);
        Reporter.log("<br>");
        Reporter.log("<a target='_blank' href='" + screenshotPath + "'><img src='" + screenshotPath
                + "' height='100' width='100' /></a>");
    }

}
