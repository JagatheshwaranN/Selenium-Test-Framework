package com.qa.stf.helper;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import com.qa.stf.base.BasePage;

import java.util.Optional;

/**
 * The AlertHandler class provides utility methods for interacting with JavaScript
 * alerts, confirms, and prompts on a web page using Selenium WebDriver.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve the currently active alert on the page.</li>
 *     <li>Accept the active alert.</li>
 *     <li>Dismiss the active alert.</li>
 *     <li>Retrieve the text from the active alert.</li>
 *     <li>Send text to a prompt alert and accept it.</li>
 * </ul>
 * <p>
 * All methods handle scenarios where an alert may not be present and log appropriate
 * messages. In the case where an alert is not present, the
 * {@link BaseException.AlertNotFoundException} is thrown. The class leverages the
 * Selenium WebDriver's alert handling API, with built-in logging using
 * {@link org.apache.logging.log4j.Logger}.
 * </p>
 * <p>
 * Usage of this class is primarily for dealing with browser-based alerts that are
 * displayed during test automation.
 * This class helps to centralize alert management logic and enables reusability across
 * test scripts.
 * </p>
 */
public class AlertHandler extends BasePage {

    private static final Logger log = LogManager.getLogger(AlertHandler.class);

    /**
     * Retrieves the currently active alert on the page.
     * <p>
     * This method attempts to switch to the currently active alert and retrieve it.
     * If no alert is found, it throws a
     * custom exception (AlertNotFoundException).
     * </p>
     *
     * @return An Optional containing the Alert if present.
     * @throws BaseException.AlertNotFoundException If no alert is found on the page.
     */
    public Optional<Alert> getAlert() {
        try {
            Alert alert = driverManager.getDriver().switchTo().alert();
            log.info("Alert found and retrieved.");
            return Optional.of(alert);
        } catch (NoAlertPresentException ex) {
            log.error("No alert present on the page.");
            throw new BaseException.AlertNotFoundException(ex);
        }
    }

    /**
     * Accepts the currently active alert if present.
     * <p>
     * This method checks if an alert is present. If an alert is found, it accepts
     * the alert. If no alert is found, the method performs no action.
     * </p>
     */
    public void acceptAlert() {
        getAlert().ifPresent(alert -> {
            alert.accept();
            log.info("The alert popup is accepted");
        });
    }

    /**
     * Dismisses the currently active alert if present.
     * <p>
     * This method checks if an alert is present. If an alert is found, it dismisses
     * the alert. If no alert is found, the method performs no action.
     * </p>
     */
    public void dismissAlert() {
        getAlert().ifPresent(alert -> {
            alert.dismiss();
            log.info("The alert popup is dismissed");
        });
    }

    /**
     * Retrieves the text from the currently active alert.
     * <p>
     * This method retrieves the text of the active alert. If no alert is present,
     * it returns the string "No Alert Present".
     * </p>
     *
     * @return The text of the alert, or "No Alert Present" if no alert is active.
     */
    public String getAlertText() {
        Optional<Alert> alert = getAlert();
        String text = alert.map(Alert::getText).orElse("No Alert Present");
        if (text.isEmpty()) {
            log.error("No alert is active, returning empty string.");
        }
        log.info("Alert text retrieved :: '{}'", text);
        return text;
    }

    /**
     * Sends the specified text to the alert (prompt) and accepts it.
     * <p>
     * This method checks if a prompt alert is present. If found, it sends the
     * specified text to the prompt and accepts the alert.
     * If no prompt alert is found, the method performs no action.
     * </p>
     *
     * @param text The text to send to the alert prompt.
     */
    public void acceptPrompt(String text) {
        getAlert().ifPresent(alert -> {
            alert.sendKeys(text);
            alert.accept();
            log.info("The prompt alert window is accepted");
        });
    }

}
