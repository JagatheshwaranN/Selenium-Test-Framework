package com.qa.taf.helper;

import java.util.LinkedList;
import java.util.List;

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

	public void selectByValue(WebElement element, String value, String elementLabel) {
		try {
			var select = new Select(element);
			select.selectByValue(value);
			System.out.println("The value " + value + " is selected from " + elementLabel + " dropdown");
		} catch (Exception ex) {
			System.out.println(
					"Error occured while select option by value from " + elementLabel + " dropdown" + "\n" + ex);
			Assert.fail();
		}
	}

	public void selectByIndex(WebElement element, int index, String elementLabel) {
		try {
			var select = new Select(element);
			select.selectByIndex(index);
			System.out.println("The value at index " + index + " is selected from " + elementLabel + " dropdown");
		} catch (Exception ex) {
			System.out.println(
					"Error occured while select option by index from " + elementLabel + " dropdown" + "\n" + ex);
			Assert.fail();
		}
	}

	public void selectByVisibleText(WebElement element, String visibleText, String elementLabel) {
		try {
			var select = new Select(element);
			select.selectByVisibleText(visibleText);
			System.out.println("The visible text " + visibleText + " is selected from " + elementLabel + " dropdown");
		} catch (Exception ex) {
			System.out.println(
					"Error occured while select option by visible text from " + elementLabel + " dropdown" + "\n" + ex);
			Assert.fail();
		}
	}

	public String getSelectValue(WebElement element, String elementLabel) {
		String value = null;
		try {
			value = new Select(element).getFirstSelectedOption().getText();
			System.out.println("The selected value in " + elementLabel + " dropdown is : " + value);
		} catch (Exception ex) {
			System.out.println("Error occured while get selected value from " + elementLabel + " dropdown" + "\n" + ex);
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
				System.out
						.println("The option values of the " + elementLabel + " dropdown are : " + elements.getText());
				dropdownvalues.add(elements.getText());
			}
		} catch (Exception ex) {
			System.out.println(
					"Error occured while get the option values from " + elementLabel + " dropdown" + "\n" + ex);
			Assert.fail();
		}
		return dropdownvalues;
	}
}
