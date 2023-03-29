package com.qa.taf.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

	private WebDriver driver;
	public WebDriverWait wait;

	public Page(WebDriver driver) {
		this.setDriver(driver);
		this.wait = new WebDriverWait(this.getDriver(), Duration.ofSeconds(30));
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public abstract String getPageTitle();

	public abstract String getPageHeader(WebElement element, String elementLabel);

	public abstract void waitForElementVisible(WebElement element, String elementLabel);

	public abstract void waitForPageTitle(String title);

	public abstract WebElement generateElement(By locator, String locatorLabel);

	public abstract WebElement generateElement(String locator, String locatorLabel);

	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(getDriver());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
