package com.qa.taf.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.qa.taf.base.BasePage;

/**
 * 
 * @author Jaga
 *
 */
public class MouseActionHelper extends BasePage {

	public MouseActionHelper(WebDriver driver) {
		super(driver);
	}

	public void mouseHover(WebElement element1, WebElement element2, String elementLabel) {
		try {
			var builder = new Actions(getDriver());
			builder.moveToElement(element1).build().perform();
			element2.click();
			System.out.println("The control is mouse hovered and clicked on an " + elementLabel + " element");
		} catch (Exception ex) {
			System.out.println("Error occured while mouse hover and click on " + elementLabel + " element" + "\n" + ex);
			Assert.fail();
		}
	}
}
