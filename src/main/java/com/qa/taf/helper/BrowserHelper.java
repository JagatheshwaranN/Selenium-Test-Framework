package com.qa.taf.helper;

import java.util.LinkedList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.qa.taf.base.BasePage;

;

/**
 * 
 * @author Jaga
 *
 */
public class BrowserHelper extends BasePage {

	private static Logger log = LogManager.getFormatterLogger(BrowserHelper.class);

	public void goBack() {
		try {
			driverManager.getDriver().navigate().back();
			log.info("The browser is navigated to previous page");
		} catch (Exception ex) {
			log.error("Error occured while the browser navigate to previous page" + "\n" + ex);
			Assert.fail();
		}
	}

	public void goForward() {
		try {
			driverManager.getDriver().navigate().forward();
			log.info("The browser is navigated to front page");
		} catch (Exception ex) {
			log.error("Error occured while the browser navigate to front page" + "\n" + ex);
			Assert.fail();
		}
	}

	public void refresh() {
		try {
			driverManager.getDriver().navigate().refresh();
			log.info("The browser is refreshed the current page");
		} catch (Exception ex) {
			log.error("Error occured while the browser refresh the current page" + "\n" + ex);
			Assert.fail();
		}
	}

	public Set<String> getWindowHandles() {
		try {
			log.info("The browser window handles are captured");
		} catch (Exception ex) {
			log.error("Error occured while capturing the browser window handles" + "\n" + ex);
			Assert.fail();
		}
		return driverManager.getDriver().getWindowHandles();
	}

	public void SwitchToWindow(int index) {
		try {
			var windowsId = new LinkedList<String>(getWindowHandles());
			if (index < 0 || index > windowsId.size())
				throw new IllegalArgumentException("Browser window handle has invalid index ==> " + index);
			driverManager.getDriver().switchTo().window(windowsId.get(index));
			log.info("The control is switched to browser window whose index is ==> " + index);
		} catch (Exception ex) {
			log.error("Error occured while the control switch between the browser windows" + "\n" + ex);
			Assert.fail();
		}
	}

	public void switchToParentWindow() {
		try {
			var windowsId = new LinkedList<String>(getWindowHandles());
			driverManager.getDriver().switchTo().window(windowsId.get(0));
			log.info("The control is switched to the parent browser window");
		} catch (Exception ex) {
			log.error("Error occured while the control switch to the parent browser window" + "\n" + ex);
			Assert.fail();
		}
	}

	public void switchToParentWithChildClose() {
		try {
			switchToParentWindow();
			var windowsId = new LinkedList<String>(getWindowHandles());
			for (var i = 1; i < windowsId.size(); i++) {
				log.info("The child browser window id ==> " + windowsId.get(i));
				driverManager.getDriver().switchTo().window(windowsId.get(i));
				driverManager.getDriver().close();
			}
			switchToParentWindow();
		} catch (Exception ex) {
			log.error("Error occured while the control switch to the parent browser window" + "\n" + ex);
			Assert.fail();
		}
	}

	public void switchToFrame(String nameOrid) {
		try {
			driverManager.getDriver().switchTo().frame(nameOrid);
			log.info("The control is switched to the frame whose name or id is ==> " + nameOrid);
		} catch (Exception ex) {
			log.error("Error occured while the control switch to the frame" + "\n" + ex);
			Assert.fail();
		}
	}

	public String getCurrentPageUrl() {
		String url = null;
		try {
			url = driverManager.getDriver().getCurrentUrl();
			log.info("The browser current page url is ==> " + url);
		} catch (Exception ex) {
			log.error("Error occured while the get the browser window's current page URL" + "\n" + ex);
			Assert.fail();
		}
		return url;
	}
}
