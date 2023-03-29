package com.qa.taf.helper;

import java.util.LinkedList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.taf.base.BasePage;

;

/**
 * 
 * @author Jaga
 *
 */
public class BrowserHelper extends BasePage {

	public BrowserHelper(WebDriver driver) {
		super(driver);
	}

	public void goBack() {
		try {
			getDriver().navigate().back();
			System.out.println("The browser navigated to previous page");
		} catch (Exception ex) {
			System.out.println("Error occured while browser navigate to previous page" + "\n" + ex);
			Assert.fail();
		}
	}

	public void goForward() {
		try {
			getDriver().navigate().forward();
			System.out.println("The browser navigated to front page");
		} catch (Exception ex) {
			System.out.println("Error occured while browser navigate to front page" + "\n" + ex);
			Assert.fail();
		}
	}

	public void refresh() {
		try {
			getDriver().navigate().refresh();
			System.out.println("The browser refreshed the current page");
		} catch (Exception ex) {
			System.out.println("Error occured while browser refresh the current page" + "\n" + ex);
			Assert.fail();
		}
	}

	public Set<String> getWindowHandles() {
		try {
			System.out.println("The browser window handles are captured");
		} catch (Exception ex) {
			System.out.println("Error occured while capturing the browser window handles" + "\n" + ex);
			Assert.fail();
		}
		return getDriver().getWindowHandles();
	}

	public void SwitchToWindow(int index) {
		try {
			var windowsId = new LinkedList<String>(getWindowHandles());
			if (index < 0 || index > windowsId.size())
				throw new IllegalArgumentException("Window handle has invalid index : " + index);
			getDriver().switchTo().window(windowsId.get(index));
			System.out.println("The control switched to window with index : " + index);
		} catch (Exception ex) {
			System.out.println("Error occured while control switch between windows" + "\n" + ex);
			Assert.fail();
		}
	}

	public void switchToParentWindow() {
		try {
			var windowsId = new LinkedList<String>(getWindowHandles());
			getDriver().switchTo().window(windowsId.get(0));
			System.out.println("The control switched to the parent window");
		} catch (Exception ex) {
			System.out.println("Error occured while the control switch to parent window" + "\n" + ex);
			Assert.fail();
		}
	}

	public void switchToParentWithChildClose() {
		try {
			switchToParentWindow();
			var windowsId = new LinkedList<String>(getWindowHandles());
			for (var i = 1; i < windowsId.size(); i++) {
				System.out.println("Child window id : " + windowsId.get(i));
				getDriver().switchTo().window(windowsId.get(i));
				getDriver().close();
			}
			switchToParentWindow();
		} catch (Exception ex) {
			System.out.println("Error occured while the control switch to parent window" + "\n" + ex);
			Assert.fail();
		}
	}

	public void switchToFrame(String nameOrid) {
		try {
			getDriver().switchTo().frame(nameOrid);
			System.out.println("The control switch to frame with name or id : " + nameOrid);
		} catch (Exception ex) {
			System.out.println("Error occured while the control switch to frame" + "\n" + ex);
			Assert.fail();
		}
	}

	public String getCurrentPageUrl() {
		String url = null;
		try {
			url = getDriver().getCurrentUrl();
			System.out.println("The browser current page url : " + url);
		} catch (Exception ex) {
			System.out.println("Error occured while the get the current page URL" + "\n" + ex);
			Assert.fail();
		}
		return url;
	}
}
