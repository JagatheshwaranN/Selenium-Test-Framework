package com.qa.taf.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.taf.base.BasePage;
import com.qa.taf.base.WebPage;

/**
 * 
 * @author Jaga
 *
 */
public class JavaScriptHelper extends BasePage implements WebPage {

	private static Logger log = LogManager.getFormatterLogger(JavaScriptHelper.class);
	JavascriptExecutor executor = (JavascriptExecutor) driverManager.getDriver();;

	@Override
	public void clickElement(WebElement element, String elementLabel) {
		try {
			executor.executeScript("arguments[0].click();", element);
			log.info("The " + elementLabel + " element is clicked by javascript");
		} catch (Exception ex) {
			System.out
					.println("Error occured while click on the " + elementLabel + " element by javascript" + "\n" + ex);
			Assert.fail();
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
			log.info("The locator string replaced with the value ==> " + updatedLocatorToString);
			element = generateElement(updatedLocatorToString, elementLabel);
			executor.executeScript("arguments[0].click();", element);
			log.info("The " + elementLabel + " element is clicked by javascript");
		} catch (Exception ex) {
			System.out
					.println("Error occured while click on the " + elementLabel + " element by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	@Override
	public void typeElement(WebElement element, String text, String elementLabel) {
		try {
			executor.executeScript("arguments[0].value='" + text + "';", element);
			log.info("The text " + text + " is entered into the " + elementLabel + " element by javascript");
		} catch (Exception ex) {
			log.error(
					"Error occured while enter text into the " + elementLabel + " element by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	@Override
	public void clearElement(WebElement element, String elementLabel) {
		try {
			executor.executeScript("arguments[0].value = '';", element);
			log.info("The content of the " + elementLabel + " element is cleared by javascript");
		} catch (Exception ex) {
			log.error("Error occured while clear the content of the " + elementLabel + " element by javascript"
					+ "\n" + ex);
			Assert.fail();
		}
	}

	public Object executeScript(String script) {
		return executor.executeScript(script);
	}

	public Object executeScript(String script, Object... arguments) {
		return executor.executeScript(script, arguments);
	}

	public void scrollToElement(WebElement element, String elementLabel) {
		try {
			executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,
					element.getLocation().y);
			log.info("The control is scrolled to the " + elementLabel + " element by javascript");
		} catch (Exception ex) {
			log.error(
					"Error occured while scroll to the " + elementLabel + " element by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollToElementAndClick(WebElement element, String elementLabel) {
		try {
			scrollToElement(element, elementLabel);
			element.click();
			log.info("The control is scrolled to the " + elementLabel + " element and clicked by javascript");
		} catch (Exception ex) {
			log.error(
					"Error occured while scroll to the " + elementLabel + " element by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollIntoView(WebElement element, String elementLabel) {
		try {
			executeScript("arguments[0].scrollIntoView()", element);
			log.info("The control is scrolled into the view of the " + elementLabel + " element by javascript");
		} catch (Exception ex) {
			log.error("Error occured while scroll into view of the " + elementLabel + " element by javascript"
					+ "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollIntoViewAndClick(WebElement element, String elementLabel) {
		try {
			scrollIntoView(element, elementLabel);
			element.click();
			log.info("The control is scrolled into the view of the " + elementLabel
					+ " element and clicked by javascript");
		} catch (Exception ex) {
			log.error("Error occured while scroll into view of the " + elementLabel + " element by javascript"
					+ "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollUpVertical() {
		try {
			executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			log.info("The control is scrolled up vertically to the top of the page by javscript");
		} catch (Exception ex) {
			log.error(
					"Error occured while the control is scroll up vertically to the top of the page by javascript"
							+ "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollDownVertical() {
		try {
			executeScript("window.scrollTo(0, document.body.scrollHeight)");
			log.info("The control is scrolled down vertically to the bottom of the page by javscript");
		} catch (Exception ex) {
			log.error(
					"Error occured while the control is scroll down vertically to the bottom of the page by javascript"
							+ "\n" + ex);
			Assert.fail();
		}
	}

	public void ScrolUpByPixel(String pixel) {
		try {
			executeScript("window.scrollBy(0, -'" + pixel + "')");
			log.info("The page is scrolled up vertically by " + pixel + " pixel by javascript");
		} catch (Exception ex) {
			log.error("Error occured while the page scroll up by pixel by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void ScrolDownByPixel(String pixel) {
		try {
			executeScript("window.scrollBy(0, '" + pixel + "')");
			log.info("The page is scrolled down vertically by " + pixel + " pixel by javascript");
		} catch (Exception ex) {
			log.error("Error occured while the page scroll down by pixel by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void zoomInByPercentage(String percent) {
		try {
			executeScript("document.body.style.zoom='" + percent + "'");
			log.info("The page is zoom in by " + percent + " percent on the page by javascript");
		} catch (Exception ex) {
			log.error("Error occured while the page zoom in by percent by javascript" + "\n" + ex);
			Assert.fail();
		}
	}
}
