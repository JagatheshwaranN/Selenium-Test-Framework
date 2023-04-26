package com.qa.taf.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

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
		executor.executeScript("arguments[0].click();", element);
		log.info("The " + "'" + elementLabel + "'" + " element is clicked by JSE");
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
		log.info("The locator string replaced with the value ==> " + "'" + updatedLocatorToString + "'");
		element = generateElement(updatedLocatorToString, elementLabel);
		executor.executeScript("arguments[0].click();", element);
		log.info("The " + "'" + elementLabel + "'" + " element is clicked by JSE");
	}

	@Override
	public void typeElement(WebElement element, String text, String elementLabel) {
		executor.executeScript("arguments[0].value='" + text + "';", element);
		log.info("The text " + "'" + text + "'" + " is entered into the " + "'" + elementLabel + "'"
				+ " element by JSE");
	}

	@Override
	public void clearElement(WebElement element, String elementLabel) {
		executor.executeScript("arguments[0].value = '';", element);
		log.info("The content of the " + "'" + elementLabel + "'" + " element is cleared by JSE");
	}

	public Object executeScript(String script) {
		return executor.executeScript(script);
	}

	public Object executeScript(String script, Object... arguments) {
		return executor.executeScript(script, arguments);
	}

	public void scrollToElement(WebElement element, String elementLabel) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		log.info("The control is scrolled to the " + "'" + elementLabel + "'" + " element by JSE");
	}

	public void scrollToElementAndClick(WebElement element, String elementLabel) {
		scrollToElement(element, elementLabel);
		element.click();
		log.info("The control is scrolled to the " + "'" + elementLabel + "'" + " element and clicked by JSE");
	}

	public void scrollIntoView(WebElement element, String elementLabel) {
		executeScript("arguments[0].scrollIntoView()", element);
		log.info("The control is scrolled into the view of the " + "'" + elementLabel + "'" + " element by JSE");
	}

	public void scrollIntoViewAndClick(WebElement element, String elementLabel) {
		scrollIntoView(element, elementLabel);
		element.click();
		log.info("The control is scrolled into the view of the " + "'" + elementLabel + "'"
				+ " element and clicked by JSE");
	}

	public void scrollUpVertical() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		log.info("The control is scrolled up vertically to the top of the page by JSE");
	}

	public void scrollDownVertical() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
		log.info("The control is scrolled down vertically to the bottom of the page by JSE");
	}

	public void ScrolUpByPixel(String pixel) {
		executeScript("window.scrollBy(0, -'" + pixel + "')");
		log.info("The page is scrolled up vertically by " + "'" + pixel + "'" + " pixel by JSE");
	}

	public void ScrolDownByPixel(String pixel) {
		executeScript("window.scrollBy(0, '" + pixel + "')");
		log.info("The page is scrolled down vertically by " + "'" + pixel + "'" + " pixel by JSE");
	}

	public void zoomInByPercentage(String percent) {
		executeScript("document.body.style.zoom='" + percent + "'");
		log.info("The page is zoom in by " + "'" + percent + "'" + " percent on the page by JSE");
	}
}
