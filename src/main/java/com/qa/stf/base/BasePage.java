package com.qa.stf.base;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.Status;
import com.google.common.util.concurrent.Uninterruptibles;
import com.qa.stf.report.ExtentReportManager;
import com.qa.stf.util.ExceptionHub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.qa.stf.constant.TestConstants.*;

/**
 * The BasePage class serves as a foundational class for interacting with
 * web pages using Selenium WebDriver. It provides utility methods for common
 * page-level actions such as retrieving page titles, URLs, headers, and
 * interacting with page elements like clicking, typing, and waiting for
 * elements to be visible.
 *
 * <p>Features:
 * <ul>
 *     <li>Retrieve the title and URL of the current page.</li>
 *     <li>Retrieve and log the header text of a given element on the page.</li>
 *     <li>Wait for elements to become visible on the page.</li>
 *     <li>Generate WebElements using various locators (By, XPath).</li>
 *     <li>Clear, click, and type text into elements.</li>
 *     <li>Handle element click interception with proper exception handling.</li>
 *     <li>Pause execution for a specified duration.</li>
 * </ul>
 *
 * <p>Exception Handling:
 * <ul>
 *   <li>Custom exceptions from the {@link ExceptionHub} class
 *       are thrown for specific error scenarios such as element not found or
 *       interaction failures.</li>
 *   <li>Detailed logging is provided for every action, including success and
 *      failure cases.</li>
 * </ul>
 *
 * <p>Note:
 * This class assumes that the WebDriver has been properly set up and initialized
 * through the {@link DriverManager}. The page operations are designed to interact
 * with standard web pages but may require customization for specific page structures.
 *
 * <p>Example:
 * <pre>
 * {@code
 * BasePage basePage = new BasePage(driverManager);
 * basePage.getPageTitle();
 * basePage.waitForElementVisible(element, "Sample Element");
 * basePage.clickElement(element, "Submit Button");
 * }
 * </pre>
 *
 * @author Jagatheshwaran N
 * @version 1.4
 */
public class BasePage extends Page implements ElementActions {

    // Logger instance for the BasePage class to enable logging during the execution
    private static final Logger log = LogManager.getLogger(BasePage.class);

    // Instance of DriverManager to manage the WebDriver for interacting with the browser
    protected DriverManager driverManager;

    // Instance of ExtentReportManager to manage the extent report
    protected ExtentReportManager extentReportManager;

    // Instance of WebDriverWait to handle waiting for elements to appear on the page
    protected WebDriverWait wait;

    /**
     * Constructs a BasePage instance and initializes the WebDriver and WebDriverWait.
     * <p>
     * This constructor ensures that the DriverManager is not null and sets up the
     * WebDriverWait using the driver from the provided DriverManager instance. The
     * WebDriverWait is configured to use a duration defined in the TestConstants class.
     * </p>
     *
     * @param driverManager The DriverManager instance used to initialize the WebDriver
     *                      and WebDriverWait.
     * @throws IllegalArgumentException If the provided DriverManager is null.
     */
    public BasePage(DriverManager driverManager) {
        if (driverManager == null) {
            throw new IllegalArgumentException("DriverManager cannot be null.");
        }
        this.driverManager = driverManager;
        extentReportManager = ExtentReportManager.getInstance();
        this.wait = new WebDriverWait(driverManager.getDriver(),
                Duration.ofSeconds(EXPLICIT_WAIT_TIME));
    }

    /**
     * Retrieves the title of the current page.
     * <p>
     * This method fetches the title of the page currently displayed in the
     * browser and logs it for informational purposes.
     * </p>
     *
     * @return The title of the current page.
     */
    @Override
    public String getPageTitle() {
        String title = driverManager.getDriver().getTitle();
        log.info("Page title retrieved: '{}'", title);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Page title retrieved: '%s'", title));
        return title;
    }

    /**
     * Retrieves the URL of the current page.
     * <p>
     * This method fetches the URL of the page currently displayed in the
     * browser and logs it for informational purposes.
     * </p>
     *
     * @return The URL of the current page.
     */
    @Override
    public String getPageUrl() {
        String url = driverManager.getDriver().getCurrentUrl();
        log.info("Page URL retrieved: '{}'", url);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Page URL retrieved: '%s'", url));
        return url;
    }

    /**
     * Retrieves the header text of a given element on the page.
     * <p>
     * This method waits for the given element to become visible, then fetches
     * the text of the element. The retrieved text is logged for informational
     * purposes.
     * </p>
     *
     * @param element      The WebElement representing the header.
     * @param elementLabel The label or description of the element.
     * @return The text content of the header element.
     */
    @Override
    public String getPageHeader(WebElement element, String elementLabel) {
        waitForElementVisible(element, elementLabel);
        String headerText = element.getText();
        log.info("Page header retrieved for '{}': '{}'", elementLabel, headerText);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Page header retrieved for '%s': '%s'", elementLabel, headerText));
        return headerText;
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
    @Override
    public void waitForElementVisible(WebElement element, String elementLabel) {
        if (!isElementNotNull(element, elementLabel)) {
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
    @Override
    public void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
        log.info("Page title contains: '{}'", title);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Page title contains: '%s'", title));
    }

    /**
     * Generates a WebElement using the specified By locator.
     * <p>
     * This method retrieves the element based on the provided locator and
     * logs the action. If the locator is null, an IllegalArgumentException
     * is thrown.
     * </p>
     *
     * @param locator      The By locator to locate the element.
     * @param locatorLabel The label or description of the locator.
     * @return The located WebElement.
     * @throws IllegalArgumentException if the locator is null.
     */
    @Override
    public WebElement generateElement(By locator, String locatorLabel) {
        if (locator == null) {
            throw new IllegalArgumentException("Locator cannot be null for: " + locatorLabel);
        }
        WebElement element = driverManager.getDriver().findElement(locator);
        log.debug("Element generated for '{}' using locator: '{}'", locatorLabel, locator);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Element generated for '%s' using locator: '%s'", locatorLabel, locator));
        return element;
    }

    /**
     * Generates a WebElement using the specified XPath locator.
     * <p>
     * This method retrieves the element based on the provided XPath locator and
     * logs the action. If the locator is null or blank, an IllegalArgumentException
     * is thrown.
     * </p>
     *
     * @param locator      The XPath locator to locate the element.
     * @param locatorLabel The label or description of the locator.
     * @return The located WebElement.
     * @throws IllegalArgumentException if the locator is null or blank.
     */
    @Override
    public WebElement generateElement(String locator, String locatorLabel) {
        if (locator == null || locator.isBlank()) {
            throw new IllegalArgumentException("Locator cannot be null or blank for: " + locatorLabel);
        }
        WebElement element = driverManager.getDriver().findElement(By.xpath(locator));
        log.debug("Element generated for '{}' using XPath: '{}'", locatorLabel, locator);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Element generated for '%s' using XPath: '%s'", locatorLabel, locator));
        return element;
    }

    /**
     * Clears the content of the specified element.
     * <p>
     * This method checks if the given element is displayed and then clears its
     * content. If the element is displayed, the content is cleared using the
     * {@code clear()} method.
     * </p>
     *
     * @param element      The WebElement whose content is to be cleared.
     * @param elementLabel The label or description of the element.
     */
    @Override
    public void clearElement(WebElement element, String elementLabel) {
        element.clear();
        log.info("Cleared the content of '{}' element", elementLabel);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Cleared the content of '%s' element", elementLabel));
    }

    /**
     * Clicks on the specified element.
     * <p>
     * This method checks if the element is displayed and then clicks on it.
     * If the element is displayed, the {@code click()} method is invoked.
     * </p>
     *
     * @param element      The WebElement to be clicked.
     * @param elementLabel The label or description of the element.
     */
    @Override
    public void clickElement(WebElement element, String elementLabel) {
        element.click();
        log.info("Clicked the '{}' element", elementLabel);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Clicked the '%s' element", elementLabel));
    }

    /**
     * Clicks on the specified element using a locator and value.
     * <p>
     * This method attempts to locate the element using the provided locator and
     * value, and if the element is displayed, it clicks on it. If an
     * ElementClickInterceptedException occurs during the click, an exception is
     * thrown with an appropriate message.
     * </p>
     *
     * @param locator      The locator to find the element.
     * @param value        The value to be used with the locator for locating the
     *                     element.
     * @param elementLabel The label or description of the element.
     * @throws ExceptionHub.InteractionException If an error occurs while clicking
     *                                           the element.
     */
    @Override
    public void clickElement(By locator, String value, String elementLabel) {
        try {
            WebElement element = driverManager.getDriver().findElement(By.xpath(String.format(locator.toString().replace("By.xpath: ", ""), value)));
            element.click();
            log.info("Clicked the '{}' element", elementLabel);
            extentReportManager.getExtentTest().log(Status.PASS, String.format("Clicked the '%s' element", elementLabel));
        } catch (ElementClickInterceptedException ex) {
            log.error("Failed to click the '{}' element", elementLabel, ex);
            extentReportManager.getExtentTest().log(Status.FAIL, String.format("Failed to click the '%s' element", elementLabel));
            throw new ExceptionHub.InteractionException("Exception occurred while clicking '" + elementLabel + "' element", ex);
        }
    }

    /**
     * Types the specified text into the element.
     * <p>
     * This method checks if the given element is displayed and, if so, types the
     * provided text into it. If the text is not null, the {@code sendKeys()} method
     * is used to enter the text into the element.
     * </p>
     *
     * @param element      The WebElement to type the text into.
     * @param text         The text to be entered into the element.
     * @param elementLabel The label or description of the element.
     */
    @Override
    public void typeText(WebElement element, String text, String elementLabel) {
        if (text != null) {
            element.sendKeys(text);
            log.info("Entered '{}' text into the '{}' element", text, elementLabel);
            extentReportManager.getExtentTest().log(Status.PASS, String.format("Entered '%s' text into the '%s' element", text, elementLabel));
        }
    }

    /**
     * Checks if the element is not null and logs an error if it is.
     * <p>
     * This method ensures that a given web element is not null. If the element is
     * null, it logs an error and returns false.
     * </p>
     *
     * @param element      The WebElement to check.
     * @param elementLabel The label for logging purposes.
     * @return true if the element is not null, false otherwise.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isElementNotNull(WebElement element, String elementLabel) {
        if (element == null) {
            log.error("The '{}' element is null.", elementLabel);
            extentReportManager.getExtentTest().log(Status.FAIL, String.format("The '%s' element is null.", elementLabel));
            return false;
        }
        return true;
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

    public void waitForDOMToBeStable() {
        String initialDom = driverManager.getDriver().getPageSource();
        wait.until(driver -> !Objects.equals(driver.getPageSource(), initialDom));
    }

    public void waitForElementClickable(WebElement element, String elementLabel) {
        if (!isElementNotNull(element, elementLabel)) {
            throw new ExceptionHub(elementLabel + " element is null.");
        }
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            log.info("Element is visible: '{}'", elementLabel);
            extentReportManager.getExtentTest().log(Status.PASS, String.format("Element is visible: '%s'", elementLabel));
        } catch (NoSuchElementException ex) {
            log.error("Element not found: '{}'", elementLabel, ex);
            extentReportManager.getExtentTest().log(Status.FAIL, String.format("Element not found: '%s'", elementLabel));
            throw new ExceptionHub.ElementNotFoundException(elementLabel, ex);
        }
    }


}

