package com.qa.taf.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static Logger log = LogManager.getFormatterLogger(AlertHelper.class);

	public Alert getAlert() {
		return driverManager.getDriver().switchTo().alert();
	}

	public void acceptAlert() {
		try {
			getAlert().accept();
			log.info("The alert popup window present on the page is accepted");
		} catch (Exception ex) {
			log.error("Error occured while accept the alert popup window" + "\n" + ex);
			Assert.fail();
		}
	}

	public void dismissAlert() {
		try {
			getAlert().dismiss();
			log.info("The alert popup window present on the page is dismissed");
		} catch (Exception ex) {
			log.error("Error occured while dismiss the alert popup window" + "\n" + ex);
			Assert.fail();
		}
	}

	public String getAlertText() {
		String text = null;
		try {
			text = getAlert().getText();
			log.info("The text " + text + " from alert popup window is captured");
		} catch (Exception ex) {
			log.error("Error occured while get the alert popup window's text" + "\n" + ex);
			Assert.fail();
		}
		return text;

	}

	public boolean isAlertPresent() {
		try {
			driverManager.getDriver().switchTo().alert();
			log.info("The alert popup window is present on the page");
//			try {
//				wait.until(ExpectedConditions.alertIsPresent());
//			} catch (TimeoutException ex) {
//				ex.printStackTrace();
//			}
			return true;
		} catch (NoAlertPresentException ex) {
			log.error("The alert popup window is not present on the page");
			return false;
		}

	}

	public void acceptAlertIfPresent() {
		try {
			if (!isAlertPresent()) {
				return;
			}
			log.info("The alert popup window is present on the page and accepted");
			getAlertText();
			acceptAlert();
		} catch (Exception ex) {
			log.error("Error occured while check for alert popup window" + "\n" + ex);
			Assert.fail();
		}
	}

	public void dismissAlertIfPresent() {
		try {
			if (!isAlertPresent()) {
				return;
			}
			log.info("The alert popup window is present on the page and dismissed");
			dismissAlert();
		} catch (Exception ex) {
			log.error("Error occured while check for alert popup window" + "\n" + ex);
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
			log.info("The alert prompt window is present on the page and accepted");
		} catch (Exception ex) {
			log.error("Error occured while accept the alert prompt window" + "\n" + ex);
			Assert.fail();
		}
	}
}
