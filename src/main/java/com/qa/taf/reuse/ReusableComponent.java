package com.qa.taf.reuse;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qa.taf.base.BasePage;

import com.qa.taf.base.WebPage;

public class ReusableComponent extends BasePage implements WebPage {

	public static boolean isElementPresent(WebElement element, String elementLabel) {

		try {
			if (element != null) {
				// test.log(Status.INFO, elementLabel + " is present on the page");
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static void elementSelect(WebElement element, String value, String elementLabel) {

		try {
			if (element != null) {
				Select select = new Select(element);
				select.selectByVisibleText(value);
//				test.log(Status.INFO, "Selected " + value + " in the dropdown " + elementLabel);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void elementSelect(WebElement element, int index, String elementLabel) {

		try {
			if (element != null) {
				Select select = new Select(element);
				select.selectByIndex(index);
//				test.log(Status.INFO, "Selected " + index + " in the dropdown " + elementLabel);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void elementSelect(WebElement element1, List<WebElement> element2, String value,
			String elementLabel1) {

		try {
			element1.click();
			List<WebElement> options = element2;
			boolean flag = false;
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase(value)) {
					flag = true;

					option.click();
//					test.log(Status.INFO, "Selected " + value + " in the dropdown " + elementLabel1);
					break;
				}
			}
			if (flag == false) {
				System.out.println(flag + "-" + value + " option not found on the " + elementLabel1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

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
