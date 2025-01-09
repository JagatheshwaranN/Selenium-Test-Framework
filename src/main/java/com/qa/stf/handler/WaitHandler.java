package com.qa.stf.handler;

import com.aventstack.extentreports.Status;
import com.qa.stf.base.DriverManager;
import com.qa.stf.report.ExtentReportManager;
import com.qa.stf.util.ExceptionHub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static com.qa.stf.constant.TestConstants.*;

// version 1.2

public class WaitHandler {

    // Logger instance for the DropDownHandler class to enable logging during the execution
    private static final Logger log = LogManager.getLogger(DatePickerHandler.class);

    // Instance of WebDriverWait to handle waiting for elements to appear on the page
    protected WebDriverWait wait;

    // Instance of DriverManager to manage the WebDriver for interacting with the browser
    private final DriverManager driverManager;

    // Instance of ExtentReportManager to manage the extent report
    protected ExtentReportManager extentReportManager;

    public WaitHandler(DriverManager driverManager) {
        if (driverManager == null) {
            throw new IllegalArgumentException("DriverManager cannot be null.");
        }
        this.driverManager = driverManager;
        extentReportManager = ExtentReportManager.getInstance();
        this.wait = new WebDriverWait(driverManager.getDriver(),
                Duration.ofSeconds(EXPLICIT_WAIT_TIME));
    }

    /**
     * Waits for the specified element to be visible on the page.
     * <p>
     * This method waits until the given element becomes visible. If the element
     * is not visible within the specified timeout, an exception is thrown.
     * </p>
     *
     * @param element      The WebElement to wait for.
     * @param elementLabel The label or description of the element.
     * @throws ExceptionHub.ElementNotFoundException if the element is not found
     *                                               within the timeout.
     */
    public void waitForElementVisible(WebElement element, String elementLabel) {
        if (element == null) {
            throw new ExceptionHub(elementLabel + " element is null.");
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            log.info("Element is visible: '{}'", elementLabel);
            extentReportManager.getExtentTest().log(Status.PASS, String.format("Element is visible: '%s'", elementLabel));
        } catch (NoSuchElementException ex) {
            log.error("Element not found: '{}'", elementLabel, ex);
            extentReportManager.getExtentTest().log(Status.FAIL, String.format("Element not found: '%s'", elementLabel));
            throw new ExceptionHub.ElementNotFoundException(elementLabel, ex);
        }
    }

    /**
     * Waits for the page title to contain the specified string.
     * <p>
     * This method waits until the title of the page contains the specified
     * string. The page title is logged for informational purposes.
     * </p>
     *
     * @param title The title or part of the title to wait for.
     */
    public void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
        log.info("Page title contains: '{}'", title);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Page title contains: '%s'", title));
    }
    private WebElement waitForElementToBeClickable(WebElement element) {
        return new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIME), Duration.ofSeconds(WAIT_SLEEP))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForDOMToBeStable() {
        String initialDom = driverManager.getDriver().getPageSource();
        wait.until(driver -> !Objects.equals(driverManager.getDriver().getPageSource(), initialDom));
    }

    /**
     * Pauses the execution for 5 seconds.
     * <p>
     * This method uses the Uninterruptibles.sleepUninterruptibly method to make the
     * current thread sleep for 5 seconds without being interrupted.
     * </p>
     */
    public void waitForSeconds() {
        synchronized (DriverManager.getInstance().getDriver()) {
            try {
                DriverManager.getInstance().getDriver().wait(WAIT_TIME);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
