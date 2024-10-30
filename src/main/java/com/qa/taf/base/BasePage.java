package com.qa.taf.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.taf.constant.ConstantUtil;

public class BasePage extends Page {

	private static Logger log = LogManager.getFormatterLogger(BasePage.class);
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
			log.error("Error occured while wait for the element " + "'" + elementLabel + "'" + "\n" + ex);
			Assert.fail();
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

