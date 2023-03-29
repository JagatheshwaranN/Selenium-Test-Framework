package com.qa.taf.helper;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.taf.base.BasePage;

/**
 * 
 * @author Jaga
 *
 */
public class VerificationHelper extends BasePage {

	public VerificationHelper(WebDriver driver) {
		super(driver);
	}

	

	public boolean verifyElementPresent(WebElement element, String elementLabel) {
		boolean isDisplayed = false;
		try {
			isDisplayed = element.isDisplayed();
			System.out.println("The element " + elementLabel + " is present on the page");
		} catch (Exception ex) {
			System.out.println("Error occured while check for presence of an " + elementLabel + " element" + "\n" + ex);
			Assert.fail();
		}
		return isDisplayed;
	}

	public boolean verifyTextEquals(WebElement element, String text, String elementLabel) {
		boolean flag = false;
		try {
			var actualText = element.getText();
			if (actualText.equals(text)) {
				System.out.println("The element " + elementLabel + " text and given text is equal");
				return flag = true;
			} else {
				return flag;
			}
		} catch (Exception ex) {
			System.out.println("Error occured while check an " + elementLabel + " element text" + "\n" + ex);
			Assert.fail();
		}
		return flag;
	}

	public String readTextValueFromElement(WebElement element, String elementLabel) {
		boolean displayed = false;
		String text = null;
		try {
			displayed = isDisplayed(element, elementLabel);
			if (!displayed)
				return null;
			text = element.getText();
			System.out.println("The element " + elementLabel + " text is : " + text);
		} catch (Exception ex) {
			System.out.println("Error occured while read value of an " + elementLabel + " element" + "\n" + ex);
			Assert.fail();
		}
		return text;
	}

	public String readValueFromInput(WebElement element, String elementLabel) {
		String value = null;
		try {
			if (!isDisplayed(element, elementLabel))
				return null;
			value = element.getAttribute("value");
			System.out.println("The element " + elementLabel + " text is : " + value);
		} catch (Exception ex) {
			System.out.println("Error occured while read text of an " + elementLabel + " element" + "\n" + ex);
			Assert.fail();
		}
		return value;
	}

	public boolean isDisplayed(WebElement element, String elementLabel) {
		boolean flag = false;
		try {
			element.isDisplayed();
			flag = true;
			System.out.println("The element " + elementLabel + " is displayed on the page");
		} catch (Exception ex) {
			System.out.println("Error occured while check for display of an " + elementLabel + " element" + "\n" + ex);
			Assert.fail();
		}
		return flag;
	}

}
