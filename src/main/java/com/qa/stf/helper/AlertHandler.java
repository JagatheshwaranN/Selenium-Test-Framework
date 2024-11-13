package com.qa.stf.helper;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import com.qa.stf.base.BasePage;

import java.util.Optional;


public class AlertHandler extends BasePage {

    private static final Logger log = LogManager.getLogger(AlertHandler.class);

    public Optional<Alert> getAlert() {
        try{
            return Optional.of(driverManager.getDriver().switchTo().alert());
        }catch (NoAlertPresentException ex){
            throw new BaseException.AlertNotFoundException(ex);
        }
    }

    public void acceptAlert() {
        getAlert().ifPresent(alert -> {
           alert.accept();
           log.info("The alert popup is accepted");
        });
    }

    public void dismissAlert() {
        getAlert().ifPresent(alert -> {
            alert.dismiss();
            log.info("The alert popup is dismissed");
        });
    }

    public String getAlertText() {
        Optional<Alert> alert = getAlert();
        String text = alert.map(Alert::getText).orElse("No Alert Present");
        log.info("Alert text retrieved :: '{}'", text);
        return text;
    }

    public void acceptPrompt(String text) {
        getAlert().ifPresent(alert -> {
            alert.sendKeys(text);
            alert.accept();
        });
        log.info("The prompt alert window is accepted");
    }

}

