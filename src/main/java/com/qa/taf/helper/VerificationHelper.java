package com.qa.taf.helper;

import com.qa.taf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


import com.qa.taf.base.BasePage;

/**
 * 
 * @author Jaga
 *
 */
public class VerificationHelper extends BasePage {

	private static Logger log = LogManager.getFormatterLogger(VerificationHelper.class);

	public boolean verifyElementPresent(WebElement element, String elementLabel) {
		boolean isDisplayed = false;
		try {
			isDisplayed = element.isDisplayed();
			log.info("The " + "'" + elementLabel + "'" + " element is present on the page");
		} catch (NoSuchElementException ex) {
			log.error("Error occured while check for the presence of the " + "'" + elementLabel + "'" + " element"
					+ "\n" + ex);
			throw new BaseException.ElementNotFoundException(elementLabel);
		}
		return isDisplayed;
	}

	public boolean verifyTextEquals(WebElement element, String text, String elementLabel) {
		boolean flag = false;
		var actualText = element.getText();
		if (actualText.equals(text)) {
			log.info("The " + "'" + elementLabel + "'" + "element's text and given text are equal");
			return flag = true;
		} else {
			log.info("The " + "'" + elementLabel + "'" + "element's text and given text are not equal");
			return flag;
		}
	}

	public String readTextValueFromElement(WebElement element, String elementLabel) {
		String text = null;
		if (!verifyElementPresent(element, elementLabel))
			return null;
		text = element.getText();
		log.info("The " + "'" + elementLabel + "'" + " element's text is ==> " + "'" + text + "'");
		return text;
	}

	public String readValueFromInput(WebElement element, String elementLabel) {
		String value = null;
		if (!verifyElementPresent(element, elementLabel))
			return null;
		value = element.getAttribute("value");
		log.info("The " + "'" + elementLabel + "'" + " element's value is ==> " + "'" + value + "'");
		return value;
	}
}

