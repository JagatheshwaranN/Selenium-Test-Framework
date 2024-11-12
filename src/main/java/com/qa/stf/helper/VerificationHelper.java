package com.qa.stf.helper;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.Objects;

/**
 * A helper class for verifying properties of WebElements.
 */
public class VerificationHelper {

    private static final Logger log = LogManager.getLogger(VerificationHelper.class);

    /**
     * Checks if the specified element is displayed.
     *
     * @param element      The WebElement to check.
     * @param elementLabel The label for logging purposes.
     * @return true if the element is displayed, false otherwise.
     */
    public boolean isElementDisplayed(WebElement element, String elementLabel) {
        if (!isElementNotNull(element, elementLabel)) {
            return false;
        }
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            log.error("Error occurred while checking the presence of the '{}' element. Exception: {}", elementLabel, ex.getMessage(), ex);
            throw new BaseException.ElementNotFoundException(elementLabel, ex);
        } catch (Exception ex) {
            log.error("An unexpected error occurred while checking the presence of the '{}' element", elementLabel, ex);
            throw ex;
        }
    }

    /**
     * Verifies that the text of the element matches the expected text.
     *
     * @param element      The WebElement to check.
     * @param expectedText The expected text.
     * @param elementLabel The label for logging purposes.
     * @return true if the text matches, false otherwise.
     */
    public boolean isTextEqualTo(WebElement element, String expectedText, String elementLabel) {
        if (!isElementValid(element, elementLabel)) return false;
        try {
            String actualText = element.getText();
            if (actualText.isEmpty()) {
                log.error("The '{}' element's text is empty, expected text: '{}'", elementLabel, expectedText);
                return false;
            }
            return Objects.equals(actualText, expectedText);
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            log.error("Error checking text for '{}' element. Exception: {}", elementLabel, ex.getMessage(), ex);
            throw new BaseException.ElementNotFoundException(elementLabel, ex);
        } catch (Exception ex) {
            log.error("Unexpected error verifying text of '{}' element", elementLabel, ex);
            throw ex;
        }
    }

    /**
     * Reads the text value from an element.
     *
     * @param element      The WebElement to read from.
     * @param elementLabel The label for logging purposes.
     * @return The text of the element, or null if invalid.
     */
    public String readTextValueFromElement(WebElement element, String elementLabel) {
        if (!isElementValid(element, elementLabel)) return null;
        String text = element.getText();
        log.info("The '{}' element's text is :: '{}'", elementLabel, text);
        return text;
    }

    /**
     * Reads the value attribute from an input element.
     *
     * @param element      The WebElement to read from.
     * @param elementLabel The label for logging purposes.
     * @return The value of the input element, or null if invalid.
     */
    public String readValueFromInput(WebElement element, String elementLabel) {
        if (!isElementValid(element, elementLabel)) return null;
        String value = element.getAttribute("value");
        if (value == null || value.isEmpty()) {
            log.info("The '{}' element has no value or is empty.", elementLabel);
        } else {
            log.info("The '{}' element's value is :: '{}'", elementLabel, value);
        }
        return value;
    }

    /**
     * Checks if the element is not null and logs an error if it is.
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

    /**
     * Validates that the element is not null and is displayed.
     *
     * @param element      The WebElement to validate.
     * @param elementLabel The label for logging purposes.
     * @return true if the element is valid and displayed, false otherwise.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isElementValid(WebElement element, String elementLabel) {
        if (!isElementNotNull(element, elementLabel)) {
            return false;
        }
        if (!isElementDisplayed(element, elementLabel)) {
            log.warn("The '{}' element is not displayed.", elementLabel);
            return false;
        }
        return true;
    }

}
