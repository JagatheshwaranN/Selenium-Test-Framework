package com.qa.taf.helper;

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

	JavascriptExecutor executor = (JavascriptExecutor) driverManager.getDriver();;

	@Override
	public void clickElement(WebElement element, String elementLabel) {
		try {
			executor.executeScript("arguments[0].click();", element);
			System.out.println("The " + elementLabel + " element is clicked by javascript");
		} catch (Exception ex) {
			System.out
					.println("Error occured while click on an " + elementLabel + " element by javascript" + "\n" + ex);
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
			System.out.println("The updated locator string with the value : " + updatedLocatorToString);
			element = generateElement(updatedLocatorToString, elementLabel);
			executor.executeScript("arguments[0].click();", element);
			System.out.println("The " + elementLabel + " element is clicked by javascript");
		} catch (Exception ex) {
			System.out
					.println("Error occured while click on an " + elementLabel + " element by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	@Override
	public void typeElement(WebElement element, String text, String elementLabel) {
		try {
			executor.executeScript("arguments[0].value='" + text + "';", element);
			System.out.println("The text " + text + " is entered into an " + elementLabel + " element by javascript");
		} catch (Exception ex) {
			System.out.println(
					"Error occured while enter text into an " + elementLabel + " element by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	@Override
	public void clearElement(WebElement element, String elementLabel) {
		try {
			executor.executeScript("arguments[0].value = '';", element);
		} catch (Exception ex) {
			System.out
					.println("Error occured while clear on an " + elementLabel + " element by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public Object executeScript(String script) {
		try {
			System.out.println("The script is : " + script);
		} catch (Exception ex) {
			System.out.println("Error occured while execute script by javascript" + "\n" + ex);
			Assert.fail();
		}
		return executor.executeScript(script);
	}

	public Object executeScript(String script, Object... arguments) {
		try {
			System.out.println("The Script is : " + script);
		} catch (Exception ex) {
			System.out.println("Error occured while execute script by javascript" + "\n" + ex);
			Assert.fail();
		}
		return executor.executeScript(script, arguments);
	}

	public void scrollToElement(WebElement element) {
		try {
			executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,
					element.getLocation().y);
			System.out.println("The control is scrolled to an element by javascript");
		} catch (Exception ex) {
			System.out.println("Error occured while scroll to an element by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollToElementAndClick(WebElement element, String elementLabel) {
		try {
			scrollToElement(element);
			element.click();
			System.out.println("The control is scrolled to an " + elementLabel + " element and clicked by javascript");
		} catch (Exception ex) {
			System.out.println("Error occured while scroll to an " + elementLabel + " element and click by javascript"
					+ "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollIntoView(WebElement element, String elementLabel) {
		try {
			executeScript("arguments[0].scrollIntoView()", element);
			System.out
					.println("The control is scrolled into the view of an " + elementLabel + " element by javascript");
		} catch (Exception ex) {
			System.out.println("Error occured while scroll into view of an " + elementLabel + " element by javascript"
					+ "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollIntoViewAndClick(WebElement element, String elementLabel) {
		try {
			scrollIntoView(element, elementLabel);
			element.click();
			System.out.println("The control is scrolled into the view of an " + elementLabel
					+ " element and clicked by javascript");
		} catch (Exception ex) {
			System.out.println("Error occured while scroll into view of an " + elementLabel
					+ " element and click by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollUpVertical() {
		try {
			executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			System.out.println("The page is scrolled up vertically to the top by javscript");
		} catch (Exception ex) {
			System.out.println("Error occured while page scroll up vertically by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void scrollDownVertical() {
		try {
			executeScript("window.scrollTo(0, document.body.scrollHeight)");
			System.out.println("The page is scrolled down vertically to the bottom by javascript");
		} catch (Exception ex) {
			System.out.println("Error occured while page scroll down vertically by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void ScrolUpByPixel(String pixel) {
		try {
			executeScript("window.scrollBy(0, -'" + pixel + "')");
			System.out.println("The page is scrolled up by pixel by javascript");
		} catch (Exception ex) {
			System.out.println("Error occured while page scroll up by pixel by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void ScrolDownByPixel(String pixel) {
		try {
			executeScript("window.scrollBy(0, '" + pixel + "')");
			System.out.println("The page is scrolled down by pixel by javascript");
		} catch (Exception ex) {
			System.out.println("Error occured while page scroll down by pixel by javascript" + "\n" + ex);
			Assert.fail();
		}
	}

	public void zoomInByPercentage(String percent) {
		try {
			executeScript("document.body.style.zoom='" + percent + "'");
			System.out.println("The page is Zoom in by percent on the page by javascript");
		} catch (Exception ex) {
			System.out.println("Error occured while page zoom in by percent by javascript" + "\n" + ex);
			Assert.fail();
		}
	}
}
