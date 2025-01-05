package com.qa.stf.handler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHandler {

    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration WAIT_SLEEP = Duration.ofMillis(1500);

    private WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, WAIT_TIMEOUT, WAIT_SLEEP)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement waitForElementToBeClickable1(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, WAIT_TIMEOUT, WAIT_SLEEP)
                .until(ExpectedConditions.visibilityOf(element));
    }
}
