package com.qa.taf.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

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
		getAlert().accept();
		log.info("The alert popup window present on the page is accepted");
	}

	public void dismissAlert() {
		getAlert().dismiss();
		log.info("The alert popup window present on the page is dismissed");
	}

	public String getAlertText() {
		String text = null;
		text = getAlert().getText();
		log.info("The text " + "'" + text + "'" + " from alert popup window is captured");
		return text;
	}

	public void acceptAlertIfPresent() {
		if (!isAlertPresent()) {
			return;
		}
		log.info("The alert popup window is present on the page and accepted");
		getAlertText();
		acceptAlert();
	}

	public void dismissAlertIfPresent() {
		if (!isAlertPresent()) {
			return;
		}
		log.info("The alert popup window is present on the page and dismissed");
		dismissAlert();
	}

	public void acceptPrompt(String text) {
		if (!isAlertPresent()) {
			return;
		}
		var alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		log.info("The alert prompt window is present on the page and accepted");
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
}
