package com.qa.taf.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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
			log.info("The " + elementLabel + " element is present on the page");
		} catch (Exception ex) {
			log.error(
					"Error occured while check for the presence of the " + elementLabel + " element" + "\n" + ex);
			Assert.fail();
		}
		return isDisplayed;
	}

	public boolean verifyTextEquals(WebElement element, String text, String elementLabel) {
		boolean flag = false;
		try {
			var actualText = element.getText();
			if (actualText.equals(text)) {
				log.info("The " + elementLabel + "element's text and given text are equal");
				return flag = true;
			} else {
				return flag;
			}
		} catch (Exception ex) {
			log.error("Error occured while check the " + elementLabel + " element's text" + "\n" + ex);
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
			log.info("The " + elementLabel + " element's text is ==> " + text);
		} catch (Exception ex) {
			log.error("Error occured while read the text of the " + elementLabel + " element" + "\n" + ex);
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
			log.info("The " + elementLabel + " element's value is ==> " + value);
		} catch (Exception ex) {
			log.error("Error occured while read the value of the " + elementLabel + " element" + "\n" + ex);
			Assert.fail();
		}
		return value;
	}

	public boolean isDisplayed(WebElement element, String elementLabel) {
		boolean flag = false;
		try {
			element.isDisplayed();
			flag = true;
			log.info("The " + elementLabel + " element is displayed on the page");
		} catch (Exception ex) {
			log.error(
					"Error occured while check for the display of the " + elementLabel + " element" + "\n" + ex);
			Assert.fail();
		}
		return flag;
	}

}
