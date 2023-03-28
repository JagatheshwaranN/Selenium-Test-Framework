package com.qa.taf.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WebPage {

	public void elementClear(WebElement element, String elementLabel);

	public void elementClick(WebElement element, String elementLabel);

	public void elementClick(By locator, String value, String elementLabel);

	public void enterText(WebElement element, String text, String elementLabel);

}
