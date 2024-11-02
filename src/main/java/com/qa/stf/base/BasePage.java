package com.qa.stf.base;

import java.time.Duration;

import com.qa.stf.handler.BaseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.stf.constant.ConstantUtil;

public class BasePage extends Page {

    public WebDriverWait wait = new WebDriverWait(driverManager.getDriver(),
            Duration.ofSeconds(ConstantUtil.EXPLICIT_WAIT_TIME));

    @Override
    public String getPageTitle() {
        return driverManager.getDriver().getTitle();
    }

    @Override
    public String getPageHeader(WebElement element, String elementLabel) {
        waitForElementVisible(element, elementLabel);
        return element.getText();
    }

    @Override
    public void waitForElementVisible(WebElement element, String elementLabel) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException ex) {
            throw new BaseException.ElementNotFoundException(elementLabel);
        }
    }

    @Override
    public void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }

    @Override
    public WebElement generateElement(By locator, String locatorLabel) {
        return driverManager.getDriver().findElement(locator);
    }

    @Override
    public WebElement generateElement(String locator, String locatorLabel) {
        return driverManager.getDriver().findElement(By.xpath(locator));
    }

}

