package com.qa.stf.helper;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import com.qa.stf.base.BasePage;

import java.util.Optional;

/**
 * A helper class for handling browser alerts, including regular alerts,
 * confirmation boxes, and prompt dialogs. Provides methods to interact
 * with and retrieve information from alerts.
 */
public class AlertHandler extends BasePage {

    private static final Logger log = LogManager.getLogger(AlertHandler.class);

    /**
     * Retrieves the currently active alert on the page.
     *
     * @return An Optional containing the Alert if present, or throws an exception if
     * no alert is found.
     */
    public Optional<Alert> getAlert() {
        try {
            return Optional.of(driverManager.getDriver().switchTo().alert());
        } catch (NoAlertPresentException ex) {
            throw new BaseException.AlertNotFoundException(ex);
        }
    }

    /**
     * Accepts the currently active alert if present.
     */
    public void acceptAlert() {
        getAlert().ifPresent(alert -> {
            alert.accept();
            log.info("The alert popup is accepted");
        });
    }

    /**
     * Dismisses the currently active alert if present.
     */
    public void dismissAlert() {
        getAlert().ifPresent(alert -> {
            alert.dismiss();
            log.info("The alert popup is dismissed");
        });
    }

    /**
     * Retrieves the text from the currently active alert.
     *
     * @return The text of the alert, or "No Alert Present" if no alert is active.
     */
    public String getAlertText() {
        Optional<Alert> alert = getAlert();
        String text = alert.map(Alert::getText).orElse("No Alert Present");
        log.info("Alert text retrieved :: '{}'", text);
        return text;
    }

    /**
     * Sends the specified text to the alert (prompt) and accepts it.
     *
     * @param text The text to send to the alert prompt.
     */
    public void acceptPrompt(String text) {
        getAlert().ifPresent(alert -> {
            alert.sendKeys(text);
            alert.accept();
        });
        log.info("The prompt alert window is accepted");
    }

}
