package com.qa.taf.reuse;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qa.taf.base.BasePage;

import com.qa.taf.base.WebPage;

public class ReusableComponent extends BasePage implements WebPage {

	private static Logger log = LogManager.getFormatterLogger(ReusableComponent.class);

	

	
	

	public static void waitForSomeTime() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void clearElement(WebElement element, String elementLabel) {
		try {
			if (element != null) {
				element.clear();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void clickElement(WebElement element, String elementLabel) {
		try {
			if (element != null) {
				element.click();
				// test.log(Status.INFO, "Clicked on the " + elementLabel);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void clickElement(By locator, String value, String elementLabel) {
		String locatorToString = null;
		String updatedLocatorToString = null;
		WebElement element;
		try {
			locatorToString = locator.toString();
			if (locatorToString.contains("@1@")) {
				updatedLocatorToString = locatorToString.replaceAll("@1@", value);
			}
			updatedLocatorToString = updatedLocatorToString.split(":")[1].trim();
			System.out.println("The updated locator string with the value : " + updatedLocatorToString);
			element = generateElement(updatedLocatorToString, elementLabel);
			element.click();
		} catch (Exception ex) {
			System.out.println("Error occured while click on an " + elementLabel + " element" + "\n" + ex);
			Assert.fail();
		}

	}

	@Override
	public void typeElement(WebElement element, String text, String elementLabel) {
		try {
			if (element != null) {
				element.sendKeys(text);
				// test.log(Status.INFO, "Typed into " + elementLabel + " with value as " +
				// value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
