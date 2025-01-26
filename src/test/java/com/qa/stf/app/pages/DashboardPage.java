package com.qa.stf.app.pages;

import java.util.List;

import com.qa.stf.base.BasePage;
import com.qa.stf.base.DriverManager;
import com.qa.stf.handler.DropDownHandler;
import com.qa.stf.handler.VerificationHandler;
import com.qa.stf.handler.WaitHandler;
import org.openqa.selenium.WebElement;
import com.qa.stf.app.elements.DashboardPageElement;

public class DashboardPage extends DashboardPageElement {

	PageManager pageManager = new PageManager();
	BasePage basePage = pageManager.getPageComponent();
	VerificationHandler verificationHandler = pageManager.getVerificationHelper();
	WaitHandler waitHandler = pageManager.getWaitHandler();


	public DashboardPage(DriverManager driverManager) {
		super(driverManager);
	}

	public String verifyDashboardPageHeader() {
		waitHandler.waitForPresenceOfElements(getDashboardPageLoadCheck(), getDashboardPageLoadCheckLabel());
		return basePage.getPageHeader(getDashboardHeader(), getDashboardHeaderLabel());
	}

	public void userDropDown() {
		basePage.clickElement(getUserDropDown(), getUserDropDownLabel());
		verificationHandler.isElementDisplayed(getUserDropDownMenu(), getUserDropDownMenuLabel());
	}

	public String fetchUserNameFromDropDown() {
		return verificationHandler.readTextValueFromElement(getUserName(), getUserNameLabel());
	}

	public void logout() {
		userDropDown();
		basePage.clickElement(getLogout(), getLogoutLabel());
	}

	public void navigateToAdminPage() {
		basePage.clickElement(getAdminSection(), getAdminSectionLabel());
	}

	public void navigateToTimePage() {
		basePage.clickElement(getTimeSection(), getTimeSectionLabel());
	}

	public List<WebElement> getQuickLaunchCards() {
		return getQuickLaunchTiles();
	}
}

