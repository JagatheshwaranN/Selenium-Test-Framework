package com.qa.taf.reuse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

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
		if (element != null) {
			element.clear();
			log.info("The " + "'" + elementLabel + "'" + " element's content is cleared");
		}
	}

	@Override
	public void clickElement(WebElement element, String elementLabel) {
		if (element != null) {
			element.click();
			log.info("The " + "'" + elementLabel + "'" + " element is clicked");
		}
	}

	@Override
	public void clickElement(By locator, String value, String elementLabel) {
		String locatorToString = null;
		String updatedLocatorToString = null;
		WebElement element;
		locatorToString = locator.toString();
		if (locatorToString.contains("@1@")) {
			updatedLocatorToString = locatorToString.replaceAll("@1@", value);
		}
		updatedLocatorToString = updatedLocatorToString.split(":")[1].trim();
		log.info("The locator string replaced with the value ==> " + updatedLocatorToString);
		element = generateElement(updatedLocatorToString, elementLabel);
		element.click();
	}

	@Override
	public void typeElement(WebElement element, String text, String elementLabel) {
		if (element != null) {
			element.sendKeys(text);
			log.info("The text " + "'" + text + "'" + " is entered into the " + "'" + elementLabel + "'" + " element");
		}
	}
}

