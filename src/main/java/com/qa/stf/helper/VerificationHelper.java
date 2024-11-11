package com.qa.stf.helper;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class VerificationHelper {

    private static final Logger log = LogManager.getLogger(VerificationHelper.class);

    public boolean isElementDisplayed(WebElement element, String elementLabel) {
        if (element == null) {
            log.error("The '{}' element is null.", elementLabel);
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

    private boolean isElementValid(WebElement element, String elementLabel) {
        if (element == null) {
            log.error("The '{}' element is null.", elementLabel);
            return false;
        }
        if (!isElementDisplayed(element, elementLabel)) {
            log.warn("The '{}' element is not displayed.", elementLabel);
            return false;
        }
        return true;
    }

    public boolean isTextEqualTo(WebElement element, String expectedText, String elementLabel) {
        if (isElementValid(element, elementLabel)) return false;
        try {
            String actualText = element.getText();
            if (actualText.isEmpty()) {
                log.error("The '{}' element's text is null, expected text: '{}'", elementLabel, expectedText);
                return false;
            }
            return Objects.equals(actualText, expectedText);
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            log.error("The '{}' element's text '{}' does not match the expected text '{}' due to exception: {}", elementLabel, element.getText(), expectedText, ex.getMessage(), ex);
            throw new BaseException.ElementNotFoundException(elementLabel, ex);
        } catch (Exception ex) {
            log.error("An unexpected error occurred while verifying the text of '{}' element", elementLabel, ex);
            throw ex;
        }
    }

    public String readTextValueFromElement(WebElement element, String elementLabel) {
        if (isElementValid(element, elementLabel)) return null;
        String text = element.getText();
        log.info("The '{}' element's text is :: '{}'", elementLabel, text);
        return text;
    }

    public String readValueFromInput(WebElement element, String elementLabel) {
        if (isElementValid(element, elementLabel)) return null;
        String value = element.getAttribute("value");
        if (value == null || value.isEmpty()) {
            log.info("The '{}' element has no value or is empty.", elementLabel);
        }
        log.info("The '{}' element's value is :: '{}'", elementLabel, value);
        return value;
    }

}

