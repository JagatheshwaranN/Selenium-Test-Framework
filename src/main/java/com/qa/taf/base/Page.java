package com.qa.taf.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

	private WebDriver driver;
	public WebDriverWait wait;

	public Page(WebDriver driver) {
		this.setDriver(driver);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public abstract String getPageTitle();

	public abstract String getPageHeader(WebElement element);

	public abstract void waitForElementVisible(WebElement element);

	public abstract void waitForPageTitle(String title);

	public abstract WebElement generateElement(By locator);

	public abstract WebElement generateElement(String locator);

	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.getDriver());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
