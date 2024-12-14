package com.qa.stf.reuse;

import com.google.common.util.concurrent.Uninterruptibles;
import com.qa.stf.base.DriverManager;
import com.qa.stf.handler.VerificationHandler;
import com.qa.stf.util.ExceptionHub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import com.qa.stf.base.ElementActions;

import java.util.concurrent.TimeUnit;

/**
 * The Component class provides utility methods for interacting with web elements
 * using Selenium WebDriver within the context of a web page.
 *
 * <p>Features:
 * <ul>
 *     <li>Clear the content of specified elements.</li>
 *     <li>Click on specified elements.</li>
 *     <li>Click on elements located by dynamic locators with value substitution.</li>
 *     <li>Type text into specified elements.</li>
 * </ul>
 *
 * <p>Exception Handling:
 * <ul>
 *   <li>Handles {@link ElementClickInterceptedException} for element click failures.</li>
 *   <li>Uses custom exceptions from the {@link ExceptionHub} class for error handling
 *       when interacting with elements.</li>
 *   <li>Detailed logging is provided for all operations, including successes and
 *      failures.</li>
 * </ul>
 *
 * <p>Note:
 * The class assumes proper WebDriver setup, along with a verification handler. Users
 * must handle WebDriver initialization and termination separately.
 * The {@link VerificationHandler} is used for verifying the display status of elements
 * before interacting with them.
 *
 * <p>Example:
 * <pre>
 * {@code
 * VerificationHandler verificationHandler = new VerificationHandler();
 * Component component = new Component(verificationHandler);
 * WebElement element = driver.findElement(By.id("submit"));
 * component.clearElement(element, "Submit Button");
 * component.clickElement(element, "Submit Button");
 * component.typeElement(element, "Some text", "Input Field");
 * }
 * </pre>
 *
 * @author Jagatheshwaran N
 * @version 1.2
 */
public class Component implements ElementActions {

    // Logger instance for the Component class to enable logging during the execution
    private static final Logger log = LogManager.getLogger(Component.class);

    // Instance of DriverManager to handle WebDriver management for the component
    private final DriverManager driverManager;

    // Instance of VerificationHandler to handle verification actions for the component
    private final VerificationHandler verificationHandler;


    /**
     * Constructs a Component instance and initializes it with the provided
     * DriverManager and VerificationHandler.
     * <p>
     * This constructor sets up the necessary dependencies, including the DriverManager
     * for managing WebDriver instances, and the VerificationHandler for handling
     * verification tasks.
     * </p>
     *
     * @param driverManager       The DriverManager instance for managing WebDriver.
     * @param verificationHandler The VerificationHandler instance for handling
     *                            verification tasks.
     * @throws IllegalArgumentException If the provided DriverManager is null.
     */
    public Component(DriverManager driverManager, VerificationHandler verificationHandler) {
        if (driverManager == null) {
            throw new IllegalArgumentException("DriverManager cannot be null.");
        }
        this.driverManager = driverManager;
        this.verificationHandler = verificationHandler;
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
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            element.clear();
            log.info("Cleared the content of '{}' element", elementLabel);
        }
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
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            element.click();
            log.info("Clicked the '{}' element", elementLabel);
        }
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
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                element.click();
                log.info("Clicked the '{}' element", elementLabel);
            }
        } catch (ElementClickInterceptedException ex) {
            log.error("Failed to click the '{}' element", elementLabel, ex);
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
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            if (text != null) {
                element.sendKeys(text);
                log.info("Entered '{}' text into the '{}' element", text, elementLabel);
            }
        }
    }

    /**
     * Pauses the execution for 2 seconds.
     * <p>
     * This method uses the Uninterruptibles.sleepUninterruptibly method to make the
     * current thread sleep for 2 seconds without being interrupted.
     * </p>
     */
    public static void waitForSeconds() {
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    }

}
