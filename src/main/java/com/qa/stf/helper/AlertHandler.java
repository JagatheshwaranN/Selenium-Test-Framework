package com.qa.stf.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import com.qa.stf.base.BasePage;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


public class AlertHandler extends BasePage {

    private static final Logger log = LogManager.getLogger(AlertHandler.class);

    public Optional<Alert> getAlert() {
        try{
            return Optional.of(driverManager.getDriver().switchTo().alert());
        }catch (NoAlertPresentException ex){
            return Optional.empty();
        }
    }

    public void acceptAlert() {
        getAlert().ifPresent(alert -> {
           alert.accept();
           log.info("The alert popup window present on the page is accepted");
        });
    }

    public void dismissAlert() {
        getAlert().ifPresent(alert -> {
            alert.dismiss();
            log.info("The alert popup window present on the page is dismissed");
        });
    }

    public String getAlertText() {
        Optional<Alert> alert = getAlert();
        String text = alert.map(Alert::getText).orElse("No Alert Present");
        log.info("The text '{}' from alert popup window is :: ", text);
        return text;
    }

    public void acceptAlertIfPresent() {
        if (!hasAlertPresent()) {
            log.info("No alert present, skipping acceptance.");
            return;
        }
        log.info("The alert popup window is present on the page and accepted");
        acceptAlert();
    }

    public void dismissAlertIfPresent() {
        if (!hasAlertPresent()) {
            log.info("No alert present, skipping dismissal.");
            return;
        }
        log.info("The alert popup window is present on the page and dismissed");
        dismissAlert();
    }

    public void acceptPrompt(String text) {
        if (!hasAlertPresent()) {
            return;
        }
        getAlert().ifPresent(alert -> {
            alert.sendKeys(text);
            alert.accept();
        });
        log.info("The alert prompt window is present on the page and accepted");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasAlertPresent() {
        try {
            driverManager.getDriver().switchTo().alert();
            log.info("The alert popup window is present on the page");
            return true;
        } catch (NoAlertPresentException ex) {
            log.error("The alert popup window is not present on the page");
            return false;
        } catch (Exception ex) {
            log.error("An unexpected error occurred while checking for alert", ex);
            return false;
        }
    }

}

