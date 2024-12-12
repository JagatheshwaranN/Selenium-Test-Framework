package com.qa.stf.ohrm.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import com.qa.stf.ohrm.elements.DashboardPageElement;

public class DashboardPage extends DashboardPageElement {

	PageManager pageManager = new PageManager();

	public void userDropDown() {
		pageManager.getComponent().clickElement(getUserDropDown(), getUserDropDownLabel());
		pageManager.getVerificationHelper().isElementDisplayed(getUserDropDownMenu(), getUserDropDownMenuLabel());
	}

	public void pfDoLogout() {
		userDropDown();
		pageManager.getComponent().clickElement(getLogout(), getLogoutLabel());
	}

	public AdminPage navigateToAdminPage() {
		pageManager.getComponent().clickElement(getAdminSection(), getAdminSectionLabel());
		return pageManager.getAdminPage();
	}

	public List<WebElement> getQuickLaunchCards() {
		return getQuickLaunchCards();
	}
}

