package com.qa.stf.helper;

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

    protected final String CLICK_ELEMENT = "arguments[0].click();";
    protected final String TYPE_INTO_ELEMENT = "arguments[0].value='";
    protected final String CLEAR_ELEMENT = "arguments[0].value = '';";
    protected final String SCROLL_TO_ELEMENT = "window.scrollTo(arguments[0],arguments[1])";
    protected final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView()";
    protected final String SCROLL_UP_VERTICAL = "window.scrollTo(0, -document.body.scrollHeight)";
    protected final String SCROLL_DOWN_VERTICAL = "window.scrollTo(0, document.body.scrollHeight)";
    protected final String SCROLL_UP_PIXEL = "window.scrollBy(0, -'";
    protected final String SCROLL_DOWN_PIXEL = "window.scrollBy(0, '";
    protected final String PAGE_ZOOM = "document.body.style.zoom='";
    protected final String CLOSE_TYPE1 = "';";
    protected final String CLOSE_TYPE2 = "')";

    JavascriptExecutor executor = (JavascriptExecutor) driverManager.getDriver();

    private final VerificationHandler verificationHandler;

    public JavaScriptHandler(VerificationHandler verificationHandler) {
        this.verificationHandler = verificationHandler;
    }

    @Override
    public void clickElement(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript(CLICK_ELEMENT, element);
                log.info("Clicked the '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to click the '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new JavascriptException("Exception occurred while clicking " + elementLabel + " element using JavaScriptExecutor", ex);
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
                executor.executeScript(CLICK_ELEMENT, element);
                log.info("Clicked the '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to click the '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new JavascriptException("Exception occurred while clicking " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    @Override
    public void typeElement(WebElement element, String text, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                if(text != null) {
                    executor.executeScript(TYPE_INTO_ELEMENT + text + CLOSE_TYPE1, element);
                    log.info("Entered the '{}' text into the '{}' element using JavaScriptExecutor", text, elementLabel);
                }
            }
        } catch (Exception ex) {
            log.error("Failed to type into the '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new JavascriptException("Exception occurred while typing into " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    @Override
    public void clearElement(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript(CLEAR_ELEMENT, element);
                log.info("Cleared the content of '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to clear the content of '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new JavascriptException("Exception occurred while clearing the content of " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    public void scrollToElement(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript(SCROLL_TO_ELEMENT, element.getLocation().x, element.getLocation().y);
                log.info("Scrolled to the '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll to the '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new JavascriptException("Exception occurred while scrolling to " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    public void scrollToElementAndClick(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                scrollToElement(element, elementLabel);
                clickElement(element, elementLabel);
                log.info("Scrolled to the '{}' element and clicked using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll to '{}' element & click using JavaScriptExecutor", elementLabel, ex);
            throw new JavascriptException("Exception occurred while scrolling to " + elementLabel + " element and click using JavaScriptExecutor", ex);
        }
    }

    public void scrollIntoView(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript(SCROLL_INTO_VIEW, element);
                log.info("Scrolled into view of '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll into view of '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new JavascriptException("Exception occurred while scrolling into view of " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    public void scrollIntoViewAndClick(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                scrollIntoView(element, elementLabel);
                clickElement(element, elementLabel);
                log.info("Scrolled into view of '{}' element and clicked using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll into view of '{}' element and click using JavaScriptExecutor", elementLabel, ex);
            throw new JavascriptException("Exception occurred while scrolling into view of " + elementLabel + " element and click using JavaScriptExecutor", ex);
        }
    }

    public void scrollUpVertical() {
        try {
            executor.executeScript(SCROLL_UP_VERTICAL);
            log.info("Scrolled up vertically to top of the page using JavaScriptExecutor");
        } catch (Exception ex) {
            log.error("Failed to scroll to top of the page using JavaScriptExecutor");
            throw new JavascriptException("Exception occurred while scrolling to top of the page using JavaScriptExecutor", ex);
        }
    }

    public void scrollDownVertical() {
        try {
            executor.executeScript(SCROLL_DOWN_VERTICAL);
            log.info("Scrolled down vertically to bottom of the page using JavaScriptExecutor");
        } catch (Exception ex) {
            log.error("Failed to scroll to bottom of the page using JavaScriptExecutor");
            throw new JavascriptException("Exception occurred while scrolling to bottom of the page using JavaScriptExecutor", ex);
        }
    }

    public void scrollUpByPixel(String pixel) {
        try {
            if (pixel != null) {
                executor.executeScript(SCROLL_UP_PIXEL + pixel + CLOSE_TYPE2);
                log.info("Scrolled up vertically by '{}' pixel using JavaScriptExecutor", pixel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll up using JavaScriptExecutor");
            throw new JavascriptException("Exception occurred while scrolling up using JavaScriptExecutor", ex);
        }
    }

    public void scrollDownByPixel(String pixel) {
        try {
            if (pixel != null) {
                executor.executeScript(SCROLL_DOWN_PIXEL + pixel + CLOSE_TYPE2);
                log.info("Scrolled down vertically by '{}' pixel using JavaScriptExecutor", pixel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll down using JavaScriptExecutor");
            throw new JavascriptException("Exception occurred while scrolling down using JavaScriptExecutor", ex);
        }
    }

    public void zoomInByPercentage(String percent) {
        try {
            if (percent != null) {
                executor.executeScript(PAGE_ZOOM + percent + CLOSE_TYPE1);
                log.info("Page is zoomed in by '{}' percent using JavaScriptExecutor", percent);
            }
        } catch (Exception ex) {
            log.error("Failed to zoom in page using JavaScriptExecutor");
            throw new JavascriptException("Exception occurred while zooming into the page using JavaScriptExecutor", ex);
        }
    }

}

