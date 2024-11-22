package com.qa.stf.helper;

import java.util.LinkedList;
import java.util.Set;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.stf.base.BasePage;
import org.openqa.selenium.*;

public class BrowserHandler extends BasePage {

    private static final Logger log = LogManager.getLogger(BrowserHandler.class);

    /**
     * Navigates the browser back to the previous page in the session history.
     *
     * @throws BaseException.NavigationException If navigation back fails due to
     *                                           a WebDriver exception.
     */
    public void navigateBack() {
        try {
            driverManager.getDriver().navigate().back();
            log.info("Navigated to the previous page in the browser.");
        } catch (WebDriverException ex) {
            log.error("Failed to navigate back. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.NavigationException("Error navigating back", ex);
        }
    }

    /**
     * Navigates the browser forward to the next page in the session history.
     *
     * @throws BaseException.NavigationException If navigation forward fails due to
     *                                           a WebDriver exception.
     */
    public void navigateForward() {
        try {
            driverManager.getDriver().navigate().forward();
            log.info("Navigated to the next page in the browser.");
        } catch (WebDriverException ex) {
            log.error("Failed to navigate forward. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.NavigationException("Error navigating forward", ex);
        }
    }

    /**
     * Refreshes the current page in the browser.
     *
     * @throws BaseException.NavigationException If the page refresh fails due to a
     *                                           WebDriver exception.
     */
    public void refresh() {
        try {
            driverManager.getDriver().navigate().refresh();
            log.info("The current page in the browser is refreshed.");
        } catch (WebDriverException ex) {
            log.error("Failed to refresh the current page. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.NavigationException("Error refreshing page", ex);
        }
    }

    /**
     * Retrieves the handle of the currently active browser window.
     *
     * @return The handle of the current browser window.
     * @throws BaseException.WindowException If retrieving the window handle fails due to
     *                                       a WebDriver exception.
     */
    public String getBrowserWindowHandle() {
        try {
            String handle = driverManager.getDriver().getWindowHandle();
            log.info("Captured browser window handle :: {}", handle);
            return handle;
        } catch (WebDriverException ex) {
            log.error("Failed to retrieve the current browser window handle. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.WindowException("Error retrieving current window handle", ex);
        }
    }

    /**
     * Retrieves the handles of all currently open browser windows.
     *
     * @return A set of handles for all open browser windows.
     * @throws BaseException.WindowException If retrieving the window handles fails due to
     *                                       a WebDriver exception.
     */
    public Set<String> getBrowserWindowHandles() {
        try {
            Set<String> handles = driverManager.getDriver().getWindowHandles();
            log.info("Captured {} browser window handles :: {}", handles.size(), handles);
            return handles;
        } catch (WebDriverException ex) {
            log.error("Failed to retrieve browser window handles. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.WindowException("Error retrieving all window handles", ex);
        }
    }

    /**
     * Switches to a browser window based on the specified index.
     *
     * @param index The index of the window to switch to (0-based).
     * @throws IllegalArgumentException      If the provided index is out of bounds.
     * @throws BaseException.WindowException If the window cannot be switched due to an exception.
     */
    public void switchToWindow(int index) {
        var windowsId = new LinkedList<>(getBrowserWindowHandles());
        if (index < 0 || index >= windowsId.size()) {
            throw new IllegalArgumentException("Browser window handle has invalid index :: " + "'" + index + "'");
        }
        try {
            driverManager.getDriver().switchTo().window(windowsId.get(index));
            log.info("Switched to browser window at index :: '{}'", index);
        } catch (NoSuchWindowException ex) {
            log.error("Failed to switch to window at index '{}'. Exception: {}", index, ex.getMessage(), ex);
            throw new BaseException.WindowException("Failed to switch to window", ex);
        }
    }

    /**
     * Switches control to the parent browser window.
     *
     * @throws IllegalStateException         If there are no browser windows available to switch.
     * @throws BaseException.WindowException If the parent window cannot be switched to due to
     *                                       an exception.
     */
    public void switchToParentWindow() {
        var windowsId = new LinkedList<>(getBrowserWindowHandles());
        if (windowsId.isEmpty()) {
            throw new IllegalStateException("No browser windows are available to switch.");
        }
        try {
            driverManager.getDriver().switchTo().window(windowsId.getFirst());
            log.info("Switched to the parent browser window with handle: '{}'", windowsId.getFirst());
        } catch (NoSuchWindowException ex) {
            log.error("Failed to switch to the parent window. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.WindowException("Failed to switch to the parent window", ex);
        }
    }

    /**
     * Switches to the parent browser window after closing all child windows.
     *
     * @throws IllegalArgumentException      If there are no browser windows available to close.
     * @throws BaseException.WindowException If there is an error closing child windows or
     *                                       switching to the parent window.
     */
    public void switchToParentWithChildClose() {
        var windowsId = new LinkedList<>(getBrowserWindowHandles());
        if (windowsId.isEmpty()) {
            throw new IllegalArgumentException("No browser windows are available.");
        }
        log.info("Total windows before closing the children :: {}", windowsId.size());
        try {
            for (var i = 1; i < windowsId.size(); i++) {
                var childWindow = windowsId.get(i);
                log.info("Closing child browser window with handle :: '{}'", childWindow);
                driverManager.getDriver().switchTo().window(childWindow).close();
            }
            driverManager.getDriver().switchTo().window(windowsId.getFirst());
            log.info("Switched back to the parent browser window with handle: '{}'", windowsId.getFirst());
        } catch (NoSuchWindowException ex) {
            log.error("Error closing child windows or switching to parent window. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.WindowException("Error closing child windows or switching to parent window", ex);
        }
    }

    /**
     * Switches to a frame using its name or ID.
     *
     * @param frameNameOrId The name or ID of the frame.
     * @throws IllegalArgumentException If the frame name or ID is null or empty.
     * @throws NoSuchFrameException     If the frame with the given name or ID is not found.
     */
    public void switchToFrame(String frameNameOrId) {
        if (frameNameOrId == null || frameNameOrId.trim().isEmpty()) {
            throw new IllegalArgumentException("Frame name or ID cannot be null or empty.");
        }
        try {
            driverManager.getDriver().switchTo().frame(frameNameOrId);
            log.info("The control is switched to the frame using name or ID :: '{}'", frameNameOrId);
        } catch (NoSuchFrameException ex) {
            log.error("Failed to switch to frame with name or ID '{}'. Exception: {}", frameNameOrId, ex.getMessage(), ex);
            throw new BaseException.FrameException("Error switching to frame with name or ID", ex);
        }
    }

    /**
     * Switches to a frame using its index.
     *
     * @param frameIndex The index of the frame.
     * @throws IllegalArgumentException If the frame index is negative.
     * @throws NoSuchFrameException     If the frame with the given index is not found.
     */
    public void switchToFrame(int frameIndex) {
        if (frameIndex < 0) {
            throw new IllegalArgumentException("Frame index cannot be negative.");
        }
        try {
            driverManager.getDriver().switchTo().frame(frameIndex);
            log.info("The control is switched to the frame using index :: '{}'", frameIndex);
        } catch (NoSuchFrameException ex) {
            log.error("Failed to switch to frame with index '{}'. Exception: {}", frameIndex, ex.getMessage(), ex);
            throw new BaseException.FrameException("Error switching to frame with index", ex);
        }
    }

    /**
     * Switches to a frame using a WebElement reference.
     *
     * @param frameElement The WebElement of the frame.
     * @throws IllegalArgumentException       If the frame WebElement is null.
     * @throws StaleElementReferenceException If the frame WebElement is stale.
     * @throws NoSuchFrameException           If the frame is not found.
     */
    public void switchToFrame(WebElement frameElement) {
        if (frameElement == null) {
            throw new IllegalArgumentException("Frame WebElement cannot be null.");
        }
        try {
            driverManager.getDriver().switchTo().frame(frameElement);
            log.info("The control is switched to the frame using WebElement :: '{}'", frameElement);
        } catch (StaleElementReferenceException ex) {
            log.error("Failed to switch to frame due to stale element reference. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.FrameException("Error switching to frame due to stale element reference", ex);
        } catch (NoSuchFrameException ex) {
            log.error("Failed to switch to frame using WebElement '{}'. Exception: {}", frameElement, ex.getMessage(), ex);
            throw new BaseException.FrameException("Error switching to frame using WebElement", ex);
        }
    }

    /**
     * Retrieves the current URL of the browser page.
     *
     * @return The current page URL as a String.
     * @throws WebDriverException If the URL cannot be retrieved.
     */
    public String getPageUrl() {
        try {
            String url = driverManager.getDriver().getCurrentUrl();
            log.info("The browser page url is :: '{}'", url);
            return url;
        } catch (WebDriverException ex) {
            log.error("Failed to retrieve the current page URL. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.NavigationException("Error retrieving the current page URL", ex);
        }
    }

    /**
     * Retrieves the title of the current browser page.
     *
     * @return The current page title as a String.
     * @throws WebDriverException If the title cannot be retrieved.
     */
    public String getPageTitle() {
        try {
            String title = driverManager.getDriver().getTitle();
            log.info("The browser page title is :: '{}'", title);
            return title;
        } catch (WebDriverException ex) {
            log.error("Failed to retrieve the current page title. Exception: {}", ex.getMessage(), ex);
            throw new BaseException.NavigationException("Error retrieving the current page title", ex);
        }
    }

}
