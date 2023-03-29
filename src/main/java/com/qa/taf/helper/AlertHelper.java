package com.qa.taf.helper;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import org.testng.Assert;

import com.qa.taf.base.BasePage;

/**
 * 
 * @author Jaga
 *
 */
public class AlertHelper extends BasePage {

	public Alert getAlert() {
		try {
			System.out.println("The control switched to alert popup window");
		} catch (Exception ex) {
			System.out.println("Error occured while the get the alert window" + "\n" + ex);
			Assert.fail();
		}
		return driverManager.getDriver().switchTo().alert();
	}

	public void acceptAlert() {
		try {
			getAlert().accept();
			System.out.println("The alert popup window is accepted");
		} catch (Exception ex) {
			System.out.println("Error occured while accept the alert window" + "\n" + ex);
			Assert.fail();
		}
	}

	public void dismissAlert() {
		try {
			getAlert().dismiss();
			System.out.println("The alert popup window is dismissed");
		} catch (Exception ex) {
			System.out.println("Error occured while dismiss the alert window" + "\n" + ex);
			Assert.fail();
		}
	}

	public String getAlertText() {
		String text = null;
		try {
			text = getAlert().getText();
			System.out.println("The text " + text + " from alert window is captured");
		} catch (Exception ex) {
			System.out.println("Error occured while get the alert window text" + "\n" + ex);
			Assert.fail();
		}
		return text;

	}

	public boolean isAlertPresent() {
		try {
			driverManager.getDriver().switchTo().alert();
			System.out.println("The alert popup window is present on the page");
			return true;
		} catch (NoAlertPresentException ex) {
			System.out.println("The alert popup window is not present on the page");
			return false;
		}

	}

	public void acceptAlertIfPresent() {
		try {
			if (!isAlertPresent()) {
				return;
			}
			System.out.println("The alert popup window is present on the page and accepted");
			getAlertText();
			acceptAlert();
		} catch (Exception ex) {
			System.out.println("Error occured while check for alert window to accept" + "\n" + ex);
			Assert.fail();
		}
	}

	public void dismissAlertIfPresent() {
		try {
			if (!isAlertPresent()) {
				return;
			}
			System.out.println("The alert popup window is present on the page and dismissed");
			dismissAlert();
		} catch (Exception ex) {
			System.out.println("Error occured while check for alert window to dismiss" + "\n" + ex);
			Assert.fail();
		}
	}

	public void acceptPrompt(String text) {
		try {
			if (!isAlertPresent()) {
				return;
			}
			var alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			System.out.println("The alert prompt window is present on the page and accepted");
		} catch (Exception ex) {
			System.out.println("Error occured while accept the alert prompt window" + "\n" + ex);
			Assert.fail();
		}
	}
}
