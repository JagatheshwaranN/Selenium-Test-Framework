package com.qa.stf.helper;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.qa.stf.base.BasePage;
import com.qa.stf.base.WebPage;

public class JavaScriptHandler extends BasePage implements WebPage {

    private static final Logger log = LogManager.getLogger(JavaScriptHandler.class);

    JavascriptExecutor executor = (JavascriptExecutor) driverManager.getDriver();

    private final VerificationHandler verificationHandler;

    public JavaScriptHandler(VerificationHandler verificationHandler) {
        this.verificationHandler = verificationHandler;
    }

    @Override
    public void clickElement(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript("arguments[0].click();", element);
                log.info("The '{}' element is clicked by JSE", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while click {} using JSE", elementLabel, ex);
            throw new JavascriptException("Exception occurred while click using JSE", ex);
        }
    }

    @Override
    public void clickElement(By locator, String value, String elementLabel) {
        String locatorToString;
        WebElement element = null;
        try {
            if (locator != null) {
                locatorToString = locator.toString();
                if (locatorToString.contains("@1@")) {
                    locatorToString = locatorToString.replaceAll("@1@", value);
                }
                locatorToString = locatorToString.split(":")[1].trim();
                log.info("The locator string replaced with the value :: '{}'", locatorToString);
                element = generateElement(locatorToString, elementLabel);
            }
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript("arguments[0].click();", element);
                log.info("The '{}' element is clicked by JSE", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while click {} using JSE", elementLabel, ex);
            throw new JavascriptException("Exception occurred while click using JSE", ex);
        }
    }

    @Override
    public void typeElement(WebElement element, String text, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript("arguments[0].value='" + text + "';", element);
                log.info("The text '{}' is entered into the '{}' element by JSE", text, elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while type into {} using JSE", elementLabel, ex);
            throw new JavascriptException("Exception occurred while type using JSE", ex);
        }
    }

    @Override
    public void clearElement(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript("arguments[0].value = '';", element);
                log.info("The content of the '{}' element is cleared by JSE", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while clear the content of {} using JSE", elementLabel, ex);
            throw new JavascriptException("Exception occurred while clear the content using JSE", ex);
        }
    }

    public void scrollToElement(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
                log.info("The control is scrolled to '{}' by JSE", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while scroll to {} using JSE", elementLabel, ex);
            throw new JavascriptException("Exception occurred while scroll using JSE", ex);
        }
    }

    public void scrollToElementAndClick(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                scrollToElement(element, elementLabel);
                element.click();
                log.info("The control is scrolled to '{}' and clicked by JSE", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while scroll & click {} using JSE", elementLabel, ex);
            throw new JavascriptException("Exception occurred while scroll & click using JSE", ex);
        }
    }

    public void scrollIntoView(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript("arguments[0].scrollIntoView()", element);
                log.info("The control is scrolled into view of '{}' by JSE", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while scroll into view of {} using JSE", elementLabel, ex);
            throw new JavascriptException("Exception occurred while scroll into view using JSE", ex);
        }
    }

    public void scrollIntoViewAndClick(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                scrollIntoView(element, elementLabel);
                element.click();
                log.info("The control is scrolled into view of '{}' element and clicked by JSE", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while scroll into view & click of {} using JSE", elementLabel, ex);
            throw new JavascriptException("Exception occurred while scroll into view & click using JSE", ex);
        }
    }

    public void scrollUpVertical() {
        try {
            executor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
            log.info("The control is scrolled up vertically to top of the page by JSE");
        } catch (Exception ex) {
            log.error("Exception occurred while scroll to top of page using JSE");
            throw new JavascriptException("Exception occurred while scroll to top of page using JSE", ex);
        }
    }

    public void scrollDownVertical() {
        try {
            executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            log.info("The control is scrolled down vertically to top of the page by JSE");
        } catch (Exception ex) {
            log.error("Exception occurred while scroll to down of page using JSE");
            throw new JavascriptException("Exception occurred while scroll to down of page using JSE", ex);
        }
    }

    public void scrollUpByPixel(String pixel) {
        try {
            executor.executeScript("window.scrollBy(0, -'" + pixel + "')");
            log.info("The page is scrolled up vertically by '{}' pixel by JSE", pixel);
        } catch (Exception ex) {
            log.error("Exception occurred while scroll up using JSE");
            throw new JavascriptException("Exception occurred while scroll up using JSE", ex);
        }
    }

    public void scrollDownByPixel(String pixel) {
        try {
            executor.executeScript("window.scrollBy(0, '" + pixel + "')");
            log.info("The page is scrolled down vertically by '{}' pixel by JSE", pixel);
        } catch (Exception ex) {
            log.error("Exception occurred while scroll down using JSE");
            throw new JavascriptException("Exception occurred while scroll down using JSE", ex);
        }
    }

    public void zoomInByPercentage(String percent) {
        try {
            executor.executeScript("document.body.style.zoom='" + percent + "'");
            log.info("The page is zoom in by '{}' percent by JSE", percent);
        } catch (Exception ex) {
            log.error("Exception occurred while zoom in page using JSE");
            throw new JavascriptException("Exception occurred while zoom in page using JSE", ex);
        }
    }

}

