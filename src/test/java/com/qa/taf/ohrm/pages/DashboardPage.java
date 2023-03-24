package com.qa.taf.ohrm.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.taf.ohrm.objects.DashboardPageElement;



public class DashboardPage extends LoginPage {

	public DashboardPageElement dashboardPageElement;

	public DashboardPage() {

		this.dashboardPageElement = new DashboardPageElement();
//		AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver,
//				Integer.parseInt(Constants.WebDriverWaitTime));
		PageFactory.initElements(driver, this.dashboardPageElement);
	}

	public void pfUserDropDown() {

		elementClick(dashboardPageElement.userDropDown, dashboardPageElement.userDropDownLabel);
		isElementPresent(dashboardPageElement.userDropDownMenu, dashboardPageElement.userDropDownMenuLabel);
	}

	public void pfDoLogout() {

		pfUserDropDown();
		elementClick(dashboardPageElement.logout, dashboardPageElement.logoutLabel);
	}

	public AdminPage pfNavigateToAdminPage() {

		elementClick(dashboardPageElement.adminSection, dashboardPageElement.adminSectionLabel);
		return new AdminPage();
	}

	public List<WebElement> getQuickLaunchCards() {
		return dashboardPageElement.quickLaunchCards;
	}
}
