package com.qa.stf.base;

import com.aventstack.extentreports.Status;
import com.qa.stf.handler.WaitHandler;
import com.qa.stf.report.ExtentReportManager;
import com.qa.stf.util.ExceptionHub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @version 1.6
 */
public class BasePage extends Page implements ElementActions {

    // Logger instance for the BasePage class to enable logging during the execution
    private static final Logger log = LogManager.getLogger(BasePage.class);

    // Instance of DriverManager to manage the WebDriver for interacting with the browser
    private final DriverManager driverManager;

    // Instance of ExtentReportManager to manage the extent report
    private final ExtentReportManager extentReportManager;

    // HashMap to store key-value pairs of string data
    public static HashMap<String, String> anyObject;

    // List instance to hold string values, initialized as null
    List<String> list = null;

    // Static string variable to store an object value
    public static String objValue;

    // Static string variable to store an object key
    public static String objKey;

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
        new WaitHandler(DriverManager.getInstance()).waitForElementVisible(element, elementLabel);
        String headerText = element.getText();
        log.info("Page header retrieved for '{}': '{}'", elementLabel, headerText);
        extentReportManager.getExtentTest().log(Status.PASS, String.format("Page header retrieved for '%s': '%s'", elementLabel, headerText));
        return headerText;
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
     * Types the specified text in sequence into the element.
     * <p>
     * This method checks if the given element is displayed and, if so, types the
     * provided text sequentially into it. If the text is not null, the {@code sendKeys()}
     * method is used to enter the text into the element.
     * </p>
     *
     * @param element      The WebElement to type the text into.
     * @param text         The text to be entered into the element.
     * @param elementLabel The label or description of the element.
     */
    public void typeTextInSequence(WebElement element, String text, String elementLabel) {
        if (text != null) {
            for(char ch : text.toCharArray()){
                element.sendKeys(String.valueOf(ch));
            }
            log.info("Entered '{}' text into the '{}' element", text, elementLabel);
            extentReportManager.getExtentTest().log(Status.PASS, String.format("Entered '%s' text into the '%s' element", text, elementLabel));
        }
    }

    /**
     * Stores a key-value pair in the `anyObject` map.
     * If the key already exists, its value will be updated.
     *
     * @param key   The key to store.
     * @param value The value to associate with the key.
     */
    public static void setAnyElement(String key, String value) {
        try {
            if (key == null || value == null) {
                throw new IllegalArgumentException("Key or Value cannot be null.");
            }
            anyObject.put(key, value);
            log.info("Stored object: {}", anyObject);
        } catch (IllegalArgumentException ex) {
            log.error("Invalid input: {}", ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error("Unexpected error while setting an object value", ex);
        }
    }

    /**
     * Retrieves the map containing all stored key-value pairs.
     *
     * @return The static map `anyObject` containing stored elements.
     */
    public static Map<String, String> getAnyElement() {
        return new HashMap<>(anyObject); // Return a copy to avoid direct modification
    }

    /**
     * Gets the list.
     *
     * @return The list of strings.
     */
    public List<String> getList() {
        return list;
    }

    /**
     * Sets the list.
     *
     * @param list The list to be set.
     */
    public void setList(List<String> list) {
        this.list = list;
    }

}

