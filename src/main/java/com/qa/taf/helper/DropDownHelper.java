package com.qa.taf.helper;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qa.taf.base.BasePage;

/**
 * 
 * @author Jaga
 *
 */
public class DropDownHelper extends BasePage {

	private static Logger log = LogManager.getFormatterLogger(DropDownHelper.class);

	public void selectByValue(WebElement element, String value, String elementLabel) {
		try {
			var select = new Select(element);
			select.selectByValue(value);
			log.info("The value " + value + " is selected from the " + elementLabel + " dropdown");
		} catch (Exception ex) {
			log.error("Error occured while select the " + value + " option from the " + elementLabel + " dropdown"
					+ "\n" + ex);
			Assert.fail();
		}
	}

	public void selectByIndex(WebElement element, int index, String elementLabel) {
		try {
			var select = new Select(element);
			select.selectByIndex(index);
			log.info("The value whose index " + index + " is selected from the " + elementLabel + " dropdown");
		} catch (Exception ex) {
			log.error("Error occured while select the option with " + index + " index from the " + elementLabel
					+ " dropdown" + "\n" + ex);
			Assert.fail();
		}
	}

	public void selectByVisibleText(WebElement element, String visibleText, String elementLabel) {
		try {
			var select = new Select(element);
			select.selectByVisibleText(visibleText);
			log.info("The visible text " + visibleText + " is selected from the " + elementLabel + " dropdown");
		} catch (Exception ex) {
			log.error("Error occured while select the " + visibleText + " option from the " + elementLabel + " dropdown"
					+ "\n" + ex);
			Assert.fail();
		}
	}

	public void elementSelect(WebElement element1, List<WebElement> element2, String value, String elementLabel) {
		try {
			element1.click();
			List<WebElement> options = element2;
			boolean flag = false;
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase(value)) {
					flag = true;
					option.click();
					log.info("The option " + value + " is selected from the " + elementLabel + " dropdown");
					break;
				}
			}
			if (flag == false) {
				log.error(flag + "-" + value + " option not found on the " + elementLabel + " dropdown");
				Assert.fail(flag + "-" + value + " option not found on the " + elementLabel + " dropdown");
			}
		} catch (Exception ex) {
			log.error("Error occured while select the " + value + " option from the " + elementLabel + " dropdown"
					+ "\n" + ex);
			Assert.fail();
		}
	}

	public String getSelectedValue(WebElement element, String elementLabel) {
		String value = null;
		try {
			value = new Select(element).getFirstSelectedOption().getText();
			log.info("The " + value + " option is selected in the " + elementLabel + " dropdown");
		} catch (Exception ex) {
			log.error("Error occured while get the selected value from the " + elementLabel + " dropdown" + "\n" + ex);
			Assert.fail();
		}
		return value;
	}

	public List<String> getAllDropDownValue(WebElement element, String elementLabel) {
		List<String> dropdownvalues = null;
		try {
			var select = new Select(element);
			var listelements = select.getOptions();
			dropdownvalues = new LinkedList<String>();
			for (var elements : listelements) {
				log.info("The " + elementLabel + " dropdown has the option value as " + elements.getText());
				dropdownvalues.add(elements.getText());
			}
		} catch (Exception ex) {
			log.error("Error occured while get the option values from the " + elementLabel + " dropdown" + "\n" + ex);
			Assert.fail();
		}
		return dropdownvalues;
	}
}
