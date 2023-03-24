package com.qa.taf.ohrm.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import com.qa.taf.ohrm.elements.DashboardPageElement;

public class DashboardPage extends DashboardPageElement {

	DashboardPageElement dashboardPageElement = new DashboardPageElement();

	public void userDropDown() {

		elementClick(dashboardPageElement.getUserDropDown(), dashboardPageElement.getUserDropDownLabel());
		isElementPresent(dashboardPageElement.getUserDropDownMenu(), dashboardPageElement.getUserDropDownMenuLabel());
	}

	public void pfDoLogout() {

		userDropDown();
		elementClick(dashboardPageElement.getLogout(), dashboardPageElement.getLogoutLabel());
	}

	public AdminPage navigateToAdminPage() {

		elementClick(dashboardPageElement.getAdminSection(), dashboardPageElement.getAdminSectionLabel());
		return new AdminPage();
	}

	public List<WebElement> getQuickLaunchCards() {
		return dashboardPageElement.getQuickLaunchCards();
	}
}
