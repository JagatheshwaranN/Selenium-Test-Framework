package com.qa.stf.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WebPage {

	public void clearElement(WebElement element, String elementLabel);

	public void clickElement(WebElement element, String elementLabel);

	public void clickElement(By locator, String value, String elementLabel);

	public void typeElement(WebElement element, String text, String elementLabel);

}

