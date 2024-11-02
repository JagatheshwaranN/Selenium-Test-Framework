package com.qa.stf.base;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

public abstract class Page {

	protected DriverManager driverManager = new DriverManager();

	public abstract String getPageTitle();

	public abstract String getPageHeader(WebElement element, String elementLabel);

	public abstract void waitForElementVisible(WebElement element, String elementLabel);

	public abstract void waitForPageTitle(String title);

	public abstract WebElement generateElement(By locator, String locatorLabel);

	public abstract WebElement generateElement(String locator, String locatorLabel);

}

