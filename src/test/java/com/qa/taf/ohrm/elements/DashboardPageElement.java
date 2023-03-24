package com.qa.taf.ohrm.elements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.taf.ohrm.objects.DashboardPageObject;

public class DashboardPageElement extends DashboardPageObject {

	public DashboardPageObject dashboardPageObject;

	public DashboardPageElement() {

		this.dashboardPageObject = new DashboardPageObject();
		PageFactory.initElements(getDriver(), this.dashboardPageObject);
	}

	public WebElement getUserDropDown() {
		return dashboardPageObject.userDropDown;
	}

	public String getUserDropDownLabel() {
		return dashboardPageObject.userDropDownLabel;
	}

	public WebElement getUserDropDownMenu() {
		return dashboardPageObject.userDropDownMenu;
	}

	public String getUserDropDownMenuLabel() {
		return dashboardPageObject.userDropDownMenuLabel;
	}

	public WebElement getLogout() {
		return dashboardPageObject.logout;
	}

	public String getLogoutLabel() {
		return dashboardPageObject.logoutLabel;
	}

	public WebElement getAdminSection() {
		return dashboardPageObject.adminSection;
	}

	public String getAdminSectionLabel() {
		return dashboardPageObject.adminSectionLabel;
	}

	public List<WebElement> getQuickLaunchCards() {
		return dashboardPageObject.quickLaunchCards;
	}

	public String getQuickLaunchCardsLabel() {
		return dashboardPageObject.quickLaunchCardsLabel;
	}

}
