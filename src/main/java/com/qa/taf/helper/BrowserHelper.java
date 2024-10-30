package com.qa.taf.helper;

import java.util.LinkedList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		driverManager.getDriver().navigate().back();
		log.info("The browser is navigated to previous page");
	}

	public void goForward() {
		driverManager.getDriver().navigate().forward();
		log.info("The browser is navigated to front page");
	}

	public void refresh() {
		driverManager.getDriver().navigate().refresh();
		log.info("The browser is refreshed the current page");
	}

	public Set<String> getWindowHandles() {
		log.info("The browser window handles are captured");
		return driverManager.getDriver().getWindowHandles();
	}

	public void SwitchToWindow(int index) {
		var windowsId = new LinkedList<String>(getWindowHandles());
		if (index < 0 || index > windowsId.size())
			throw new IllegalArgumentException("Browser window handle has invalid index ==> " + "'" + index + "'");
		driverManager.getDriver().switchTo().window(windowsId.get(index));
		log.info("The control is switched to browser window whose index is ==> " + "'" + index + "'");
	}

	public void switchToParentWindow() {
		var windowsId = new LinkedList<String>(getWindowHandles());
		driverManager.getDriver().switchTo().window(windowsId.get(0));
		log.info("The control is switched to the parent browser window");
	}

	public void switchToParentWithChildClose() {
		switchToParentWindow();
		var windowsId = new LinkedList<String>(getWindowHandles());
		for (var i = 1; i < windowsId.size(); i++) {
			log.info("The child browser window id ==> " + "'" + windowsId.get(i) + "'");
			driverManager.getDriver().switchTo().window(windowsId.get(i));
			driverManager.getDriver().close();
		}
		switchToParentWindow();
	}

	public void switchToFrame(String nameOrid) {
		driverManager.getDriver().switchTo().frame(nameOrid);
		log.info("The control is switched to the frame whose name or id is ==> " + "'" + nameOrid + "'");
	}

	public String getCurrentPageUrl() {
		String url = null;
		url = driverManager.getDriver().getCurrentUrl();
		log.info("The browser current page url is ==> " + "'" + url + "'");
		return url;
	}
}
