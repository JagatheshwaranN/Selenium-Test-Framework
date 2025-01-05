package com.qa.stf.app.pages;

import java.util.List;

import com.qa.stf.base.DriverManager;
import org.openqa.selenium.WebElement;
import com.qa.stf.app.elements.DashboardPageElement;

public class DashboardPage extends DashboardPageElement {

	PageManager pageManager = new PageManager();

	public DashboardPage(DriverManager driverManager) {
		super(driverManager);
	}

	public void verifyDashboardHeader() {
		pageManager.getVerificationHelper().isElementDisplayed(getDashboardHeader(), getDashboardHeaderLabel());
	}

	public void userDropDown() {
		pageManager.getPageComponent().clickElement(getUserDropDown(), getUserDropDownLabel());
		pageManager.getVerificationHelper().isElementDisplayed(getUserDropDownMenu(), getUserDropDownMenuLabel());
	}

	public void pfDoLogout() {
		userDropDown();
		pageManager.getPageComponent().clickElement(getLogout(), getLogoutLabel());
	}

	public void navigateToAdminPage() {
		pageManager.getPageComponent().clickElement(getAdminSection(), getAdminSectionLabel());
		// return pageManager.getAdminPage();
	}

	public void navigateToTimePage() {
		pageManager.getPageComponent().clickElement(getTimeSection(), getTimeSectionLabel());
	}

	public List<WebElement> getQuickLaunchCards() {
		return getQuickLaunchTiles();
	}
}

