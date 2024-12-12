package com.qa.stf.base;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;
import com.qa.stf.reuse.Component;
import com.qa.stf.util.ExceptionHub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.stf.constant.TestConstants;

public class BasePage extends Page implements WebElementActions {

    private static final Logger log = LogManager.getLogger(BasePage.class);

    protected DriverManager driverManager;

    protected WebDriverWait wait;

    public BasePage(DriverManager driverManager) {
        this.driverManager = driverManager;
        wait = new WebDriverWait(driverManager.getDriver(),
                Duration.ofSeconds(TestConstants.EXPLICIT_WAIT_TIME));

    }

    @Override
    public String getPageTitle() {
        return driverManager.getDriver().getTitle();
    }

    @Override
    public String getPageUrl() {
        return driverManager.getDriver().getCurrentUrl();
    }

    @Override
    public String getPageHeader(WebElement element, String elementLabel) {
        waitForElementVisible(element, elementLabel);
        return element.getText();
    }

    @Override
    public void waitForElementVisible(WebElement element, String elementLabel) {
        if (!isElementNotNull(element, elementLabel)) {
            throw new ExceptionHub(elementLabel + " element is null.");
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException ex) {
            throw new ExceptionHub.ElementNotFoundException(elementLabel, ex);
        }
    }

    @Override
    public void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }

    @Override
    public WebElement generateElement(By locator, String locatorLabel) {
        return driverManager.getDriver().findElement(locator);
    }

    @Override
    public WebElement generateElement(String locator, String locatorLabel) {
        return driverManager.getDriver().findElement(By.xpath(locator));
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
     *                                            the element.
     */
    @Override
    public void clickElement(By locator, String value, String elementLabel) {
        try {
            WebElement element = driverManager.getDriver().findElement(By.xpath(String.format(locator.toString().replace("By.xpath: ", ""), value)));
                element.click();
                log.info("Clicked the '{}' element", elementLabel);
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
            if (text != null) {
                element.sendKeys(text);
                log.info("Entered '{}' text into the '{}' element", text, elementLabel);
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
            return false;
        }
        return true;
    }


}

