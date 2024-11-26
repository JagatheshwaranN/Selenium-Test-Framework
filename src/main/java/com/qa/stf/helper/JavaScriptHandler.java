package com.qa.stf.helper;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.qa.stf.base.BasePage;
import com.qa.stf.base.WebPage;

/**
 * The JavaScriptHandler class provides utility methods for executing JavaScript
 * code within the context of a Selenium WebDriver session. These methods allow
 * for enhanced interaction with the web page beyond the native WebDriver commands.
 *
 * <p>Features:
 * <ul>
 *   <li>JavaScript Execution: Methods to execute custom JavaScript code in the
 *   context of the current page.</li>
 *   <li>Returning Element Data: Methods to fetch properties of elements using
 *   JavaScript, such as text or attributes.</li>
 *   <li>Page Manipulation: Methods to manipulate the page or perform actions
 *   that require JavaScript execution (e.g., scrolling).</li>
 *   <li>Custom Script Handling: Utility methods to execute complex JavaScript
 *   logic that may not be directly achievable through WebDriver API calls.</li>
 * </ul>
 *
 * <p>Exception Handling:
 * <ul>
 *   <li>Custom exceptions from the {@link com.qa.stf.handler.BaseException} class
 *       are thrown for descriptive error handling in case of JavaScript execution failures.</li>
 *   <li>Detailed logging is provided for both successful script executions and error scenarios.</li>
 * </ul>
 *
 * <p>Note:
 * This class assumes the WebDriver is properly set up with support for JavaScript
 * execution in the browser. Ensure that JavaScript execution is enabled and supported
 * in the current browser context.
 *
 * <p>Example:
 * <pre>
 * {@code
 * JavaScriptHandler jsHandler = new JavaScriptHandler();
 * WebElement element = driver.findElement(By.id("example"));
 * jsHandler.executeJavaScript("arguments[0].style.backgroundColor = 'red';", element);
 * String elementText = jsHandler.executeJavaScript("return arguments[0].innerText;", element);
 * }
 * </pre>
 *
 * @author Jagatheshwaran N
 * @version 1.1
 */
public class JavaScriptHandler extends BasePage implements WebPage {

    private static final Logger log = LogManager.getLogger(JavaScriptHandler.class);

    protected static final String CLICK_ELEMENT = "arguments[0].click();";
    protected static final String TYPE_INTO_ELEMENT = "arguments[0].value='%s';";
    protected static final String CLEAR_ELEMENT = "arguments[0].value = '';";
    protected static final String SCROLL_TO_ELEMENT = "window.scrollTo(arguments[0],arguments[1])";
    protected static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView()";
    protected static final String SCROLL_UP_VERTICAL = "window.scrollTo(0, -document.body.scrollHeight)";
    protected static final String SCROLL_DOWN_VERTICAL = "window.scrollTo(0, document.body.scrollHeight)";
    protected static final String SCROLL_VERTICAL_PIXEL = "window.scrollBy(0, %s);";
    protected static final String PAGE_ZOOM = "document.body.style.zoom='%s';";

    private final JavascriptExecutor executor;
    private final VerificationHandler verificationHandler;

    public JavaScriptHandler(VerificationHandler verificationHandler) {
        this.executor = (JavascriptExecutor) driverManager.getDriver();
        this.verificationHandler = verificationHandler;
    }

    /**
     * Clicks on the specified WebElement using JavaScriptExecutor.
     * <p>
     * Verifies the visibility of the element before performing the click operation.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @param element      The WebElement to be clicked.
     * @param elementLabel The label describing the element.
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    @Override
    public void clickElement(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript(CLICK_ELEMENT, element);
                log.info("Clicked the '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to click the '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while clicking " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    /**
     * Clicks on an element located dynamically using a provided locator and value.
     * <p>
     * Constructs a dynamic locator using the value, retrieves the element, verifies its
     * visibility, and performs the click operation using JavaScriptExecutor.
     * </p>
     *
     * @param locator      The dynamic locator for the element.
     * @param value        The value to substitute in the locator.
     * @param elementLabel The label describing the element.
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    @Override
    public void clickElement(By locator, String value, String elementLabel) {
        try {
            WebElement element = driverManager.getDriver().findElement(By.xpath(String.format(locator.toString().replace("By.xpath: ", ""), value)));
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript(CLICK_ELEMENT, element);
                log.info("Clicked the '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to click the '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while clicking " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    /**
     * Types text into the specified WebElement using JavaScriptExecutor.
     * <p>
     * Verifies the visibility of the element before performing the type operation.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @param element      The WebElement to enter text into.
     * @param text         The text to be entered.
     * @param elementLabel The label describing the element.
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    @Override
    public void typeElement(WebElement element, String text, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                if (text != null) {
                    executor.executeScript(String.format(TYPE_INTO_ELEMENT, text), element);
                    log.info("Entered '{}' text into the '{}' element using JavaScriptExecutor", text, elementLabel);
                }
            }
        } catch (Exception ex) {
            log.error("Failed to type into the '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while typing into " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    /**
     * Clears the content of the specified WebElement using JavaScriptExecutor.
     * <p>
     * Verifies the visibility of the element before clearing its content.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @param element      The WebElement to clear.
     * @param elementLabel The label describing the element.
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    @Override
    public void clearElement(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript(CLEAR_ELEMENT, element);
                log.info("Cleared the content of '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to clear the content of '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while clearing the content of " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    /**
     * Scrolls to a specific web element using its x and y coordinates.
     * <p>
     * Verifies the visibility of the element before scrolling to it.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @param element      The WebElement to scroll to.
     * @param elementLabel A descriptive label for the element (used for logging).
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    public void scrollToElement(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript(SCROLL_TO_ELEMENT, element.getLocation().x, element.getLocation().y);
                log.info("Scrolled to the '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll to the '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while scrolling to " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    /**
     * Scrolls to a specific web element and clicks on it.
     * <p>
     * Verifies the visibility of the element before scrolling and clicking.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @param element      The WebElement to scroll to and click.
     * @param elementLabel A descriptive label for the element (used for logging).
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    public void scrollToElementAndClick(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                scrollToElement(element, elementLabel);
                clickElement(element, elementLabel);
                log.info("Scrolled to the '{}' element and clicked using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll to '{}' element & click using JavaScriptExecutor", elementLabel, ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while scrolling to " + elementLabel + " element and click using JavaScriptExecutor", ex);
        }
    }

    /**
     * Scrolls a specific web element into the viewport using JavaScriptExecutor.
     * <p>
     * Verifies the visibility of the element before scrolling it into view.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @param element      The WebElement to scroll into view.
     * @param elementLabel A descriptive label for the element (used for logging).
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    public void scrollIntoView(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                executor.executeScript(SCROLL_INTO_VIEW, element);
                log.info("Scrolled into view of '{}' element using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll into view of '{}' element using JavaScriptExecutor", elementLabel, ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while scrolling into view of " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    /**
     * Scrolls a specific web element into the viewport and clicks on it.
     * <p>
     * Verifies the visibility of the element before scrolling and clicking.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @param element      The WebElement to scroll into view and click.
     * @param elementLabel A descriptive label for the element (used for logging).
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    public void scrollIntoViewAndClick(WebElement element, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                scrollIntoView(element, elementLabel);
                clickElement(element, elementLabel);
                log.info("Scrolled into view of '{}' element and clicked using JavaScriptExecutor", elementLabel);
            }
        } catch (Exception ex) {
            log.error("Failed to scroll into view of '{}' element and click using JavaScriptExecutor", elementLabel, ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while scrolling into view of " + elementLabel + " element and click using JavaScriptExecutor", ex);
        }
    }

    /**
     * Scrolls to the top of the web page.
     * <p>
     * Uses JavaScriptExecutor to perform a vertical scroll to the top of the page.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    public void scrollToTop() {
        try {
            executor.executeScript(SCROLL_UP_VERTICAL);
            log.info("Scrolled up vertically to top of the page using JavaScriptExecutor");
        } catch (Exception ex) {
            log.error("Failed to scroll to top of the page using JavaScriptExecutor", ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while scrolling to top of the page using JavaScriptExecutor", ex);
        }
    }

    /**
     * Scrolls to the bottom of the web page.
     * <p>
     * Uses JavaScriptExecutor to perform a vertical scroll to the bottom of the page.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    public void scrollToBottom() {
        try {
            executor.executeScript(SCROLL_DOWN_VERTICAL);
            log.info("Scrolled down vertically to bottom of the page using JavaScriptExecutor");
        } catch (Exception ex) {
            log.error("Failed to scroll to bottom of the page using JavaScriptExecutor", ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while scrolling to bottom of the page using JavaScriptExecutor", ex);
        }
    }

    /**
     * Scrolls the page by a specified number of pixels vertically.
     * <p>
     * Positive values scroll down, while negative values scroll up.
     * Throws an exception if the operation fails.
     * </p>
     *
     * @param pixels The number of pixels to scroll (positive for down, negative for up).
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    public void scrollByPixel(int pixels) {
        try {
            String direction = pixels > 0 ? "down" : "up";
            executor.executeScript(String.format(SCROLL_VERTICAL_PIXEL, pixels));
            log.info("Scrolled {} vertically by '{}' pixels using JavaScriptExecutor", direction, Math.abs(pixels));
        } catch (Exception ex) {
            log.error("Failed to scroll using JavaScriptExecutor", ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while scrolling using JavaScriptExecutor", ex);
        }
    }

    /**
     * Zooms the page to a specified percentage using JavaScriptExecutor.
     * <p>
     * Throws an exception if the operation fails.
     * </p>
     *
     * @param zoomPercentage The zoom percentage to set (e.g., 150 for 150%).
     * @throws BaseException.JavascriptExecutorException If an error occurs during execution.
     */
    public void zoomInByPercentage(double zoomPercentage) {
        try {
            executor.executeScript(String.format(PAGE_ZOOM, zoomPercentage + "%"));
            log.info("Page zoomed to '{}' percent using JavaScriptExecutor", zoomPercentage);
        } catch (Exception ex) {
            log.error("Failed to zoom in page using JavaScriptExecutor", ex);
            throw new BaseException.JavascriptExecutorException("Exception occurred while zooming into the page using JavaScriptExecutor", ex);
        }
    }

}

/*
    ==========================
    For future work
    ==========================
    private By generateDynamicLocator(By locator, String value) {
        return switch (locator) {
            case By.ByXPath byXPath -> By.xpath(String.format(locator.toString().replace("By.xpath: ", ""), value));
            case By.ByCssSelector byCssSelector ->
                    By.cssSelector(String.format(byCssSelector.toString().replace("By.cssSelector: ", ""), value));
            case By.ById byId -> By.id(String.format(byId.toString().replace("By.id: ", ""), value));
            case By.ByClassName byClassName ->
                    By.className(String.format(byClassName.toString().replace("By.className: ", ""), value));
            case By.ByTagName byTagName ->
                    By.tagName(String.format(byTagName.toString().replace("By.tagName: ", ""), value));
            case By.ByLinkText byLinkText ->
                    By.linkText(String.format(byLinkText.toString().replace("By.linkText: ", ""), value));
            case By.ByPartialLinkText byPartialLinkText ->
                    By.partialLinkText(String.format(byPartialLinkText.toString().replace("By.partialLinkText: ", ""), value));
            case null, default ->
                    throw new UnsupportedOperationException("Dynamic locator generation is not supported for the provided locator type: " + locator.getClass().getName());
        };
    }
*/


