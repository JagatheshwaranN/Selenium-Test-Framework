<<<<<<< HEAD
package com.qa.taf.base;

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
=======
package com.qa.taf.base;

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
>>>>>>> 3a5b23362e15d382589ba9ebe11d0cd0a0c77f68
