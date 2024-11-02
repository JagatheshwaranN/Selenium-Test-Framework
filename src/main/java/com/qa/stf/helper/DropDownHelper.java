package com.qa.stf.helper;

import java.util.LinkedList;
import java.util.List;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.stf.base.BasePage;

/**
 * 
 * @author Jaga
 *
 */
public class DropDownHelper extends BasePage {

	private static Logger log = LogManager.getFormatterLogger(DropDownHelper.class);

	public void selectByValue(WebElement element, String value, String elementLabel) {
		var select = new Select(element);
		select.selectByValue(value);
		log.info("The value " + "'" + value + "'" + " is selected from the " + "'" + elementLabel + "'" + " dropdown");
	}

	public void selectByIndex(WebElement element, int index, String elementLabel) {
		var select = new Select(element);
		select.selectByIndex(index);
		log.info("The value whose index " + "'" + index + "'" + " is selected from the " + "'" + elementLabel + "'"
				+ " dropdown");
	}

	public void selectByVisibleText(WebElement element, String visibleText, String elementLabel) {
		var select = new Select(element);
		select.selectByVisibleText(visibleText);
		log.info("The visible text " + "'" + visibleText + "'" + " is selected from the " + "'" + elementLabel + "'"
				+ " dropdown");
	}

	public void elementSelect(WebElement element1, List<WebElement> element2, String value, String elementLabel) {
		element1.click();
		List<WebElement> options = element2;
		boolean flag = false;
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(value)) {
				flag = true;
				option.click();
				log.info("The option " + "'" + value + "'" + " is selected from the " + "'" + elementLabel + "'"
						+ " dropdown");
				break;
			}
		}
		if (!flag) {
            log.error("{}-'{}' option not found on the '{}' dropdown", flag, value, elementLabel);
			throw new BaseException.OptionNotFoundException(value, elementLabel);
		}
	}

	public String getSelectedValue(WebElement element, String elementLabel) {
		String value = null;
		value = new Select(element).getFirstSelectedOption().getText();
		log.info("The " + "'" + value + "'" + " option is selected in the " + "'" + elementLabel + "'" + " dropdown");
		return value;
	}

	public List<String> getAllDropDownValue(WebElement element, String elementLabel) {
		List<String> dropdownvalues = null;
		var select = new Select(element);
		var listelements = select.getOptions();
		dropdownvalues = new LinkedList<String>();
		for (var elements : listelements) {
			log.info("The " + "'" + elementLabel + "'" + " dropdown has the option value as " + "'" + elements.getText()
					+ "'");
			dropdownvalues.add(elements.getText());
		}
		return dropdownvalues;
	}
}

