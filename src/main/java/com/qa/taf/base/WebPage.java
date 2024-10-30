<<<<<<< HEAD
package com.qa.taf.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WebPage {

	public void clearElement(WebElement element, String elementLabel);

	public void clickElement(WebElement element, String elementLabel);

	public void clickElement(By locator, String value, String elementLabel);

	public void typeElement(WebElement element, String text, String elementLabel);

}
=======
package com.qa.taf.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WebPage {

	public void clearElement(WebElement element, String elementLabel);

	public void clickElement(WebElement element, String elementLabel);

	public void clickElement(By locator, String value, String elementLabel);

	public void typeElement(WebElement element, String text, String elementLabel);

}
>>>>>>> 3a5b23362e15d382589ba9ebe11d0cd0a0c77f68
