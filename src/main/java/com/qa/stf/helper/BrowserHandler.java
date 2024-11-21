package com.qa.stf.helper;

import java.util.LinkedList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.stf.base.BasePage;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class BrowserHandler extends BasePage {

    private static final Logger log = LogManager.getLogger(BrowserHandler.class);

    /**
     * Navigates the browser to the previous page.
     */
    public void navigateBack() {
        try {
            driverManager.getDriver().navigate().back();
            log.info("Navigated to the previous page in the browser.");
        } catch (WebDriverException ex) {
            log.error("Failed to navigate back. Exception: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error navigating back", ex);
        }
    }

    /**
     * Navigates the browser to the next page.
     */
    public void navigateForward() {
        try {
            driverManager.getDriver().navigate().forward();
            log.info("Navigated to the next page in the browser.");
        } catch (WebDriverException ex) {
            log.error("Failed to navigate forward. Exception: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error navigating forward", ex);
        }
    }

    /**
     * Refreshes the current page in the browser.
     */
    public void refresh() {
        try {
            driverManager.getDriver().navigate().refresh();
            log.info("The current page in the browser is refreshed.");
        } catch (WebDriverException ex) {
            log.error("Failed to refresh the current page. Exception: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error refreshing page", ex);
        }
    }

    /**
     * Retrieves the handle of the current browser window.
     *
     * @return The browser window handle.
     */
    public String getBrowserWindowHandle() {
        try {
            String handle = driverManager.getDriver().getWindowHandle();
            log.info("Captured browser window handle :: {}", handle);
            return handle;
        } catch (WebDriverException ex) {
            log.error("Failed to retrieve the current browser window handle. Exception: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error retrieving current window handle", ex);
        }
    }

    /**
     * Retrieves the handles of all open browser windows.
     *
     * @return A set of browser window handles.
     */
    public Set<String> getBrowserWindowHandles() {
        try {
            Set<String> handles = driverManager.getDriver().getWindowHandles();
            log.info("Captured {} browser window handles :: {}", handles.size(), handles);
            return handles;
        } catch (WebDriverException ex) {
            log.error("Failed to retrieve browser window handles. Exception: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error retrieving all window handles", ex);
        }
    }

    public void SwitchToWindow(int index) {
        var windowsId = new LinkedList<>(getBrowserWindowHandles());
        if (index < 0 || index > windowsId.size())
            throw new IllegalArgumentException("Browser window handle has invalid index :: " + "'" + index + "'");
        driverManager.getDriver().switchTo().window(windowsId.get(index));
        log.info("The control is switched to browser window whose index is :: '{}'", index);
    }

    public void switchToParentWindow() {
        var windowsId = new LinkedList<>(getBrowserWindowHandles());
        driverManager.getDriver().switchTo().window(windowsId.getFirst());
        log.info("The control is switched to the parent browser window");
    }

    public void switchToParentWithChildClose() {
        var windowsId = new LinkedList<>(getBrowserWindowHandles());
        for (var i = 1; i < windowsId.size(); i++) {
            log.info("The child browser window id :: '{}'", windowsId.get(i));
            driverManager.getDriver().switchTo().window(windowsId.get(i));
            driverManager.getDriver().close();
        }
        driverManager.getDriver().switchTo().defaultContent();
    }

    /**
     * Switches to a frame using its name or ID.
     *
     * @param frameNameOrId The name or ID of the frame.
     */
    public void switchToFrame(String frameNameOrId) {
        driverManager.getDriver().switchTo().frame(frameNameOrId);
        log.info("The control is switched to the frame using name or ID :: '{}'", frameNameOrId);
    }

    /**
     * Switches to a frame using its index.
     *
     * @param frameIndex The index of the frame.
     */
    public void switchToFrame(int frameIndex) {
        driverManager.getDriver().switchTo().frame(frameIndex);
        log.info("The control is switched to the frame using index :: '{}'", frameIndex);
    }

    /**
     * Switches to a frame using a WebElement reference.
     *
     * @param frameElement The WebElement of the frame.
     */
    public void switchToFrame(WebElement frameElement) {
        driverManager.getDriver().switchTo().frame(frameElement);
        log.info("The control is switched to the frame using WebElement :: '{}'", frameElement);
    }


    public String getPageUrl() {
        String url;
        url = driverManager.getDriver().getCurrentUrl();
        log.info("The browser page url is :: '{}'", url);
        return url;
    }

    public String getPageTitle() {
        String title;
        title = driverManager.getDriver().getTitle();
        log.info("The browser page title is :: '{}'", title);
        return title;
    }

}
