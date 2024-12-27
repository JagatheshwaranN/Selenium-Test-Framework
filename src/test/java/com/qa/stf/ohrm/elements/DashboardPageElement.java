package com.qa.stf.ohrm.elements;

import java.util.List;

import com.qa.stf.base.DriverManager;
import org.openqa.selenium.WebElement;

import com.qa.stf.ohrm.objects.DashboardPageObject;

public class DashboardPageElement extends DashboardPageObject {

	public DashboardPageElement(DriverManager driverManager) {
		super(driverManager);
	}

	public WebElement getDashboardHeader() {
		return dashboardHeader;
	}

	public String getDashboardHeaderLabel() {
		return dashboardHeaderLabel;
	}

	public WebElement getUserDropDown() {
		return userDropDown;
	}

	public String getUserDropDownLabel() {
		return userDropDownLabel;
	}

	public WebElement getUserDropDownMenu() {
		return userDropDownMenu;
	}

	public String getUserDropDownMenuLabel() {
		return userDropDownMenuLabel;
	}

	public WebElement getLogout() {
		return logout;
	}

	public String getLogoutLabel() {
		return logoutLabel;
	}

	public WebElement getAdminSection() {
		return adminSection;
	}

	public String getAdminSectionLabel() {
		return adminSectionLabel;
	}

	public List<WebElement> getQuickLaunchTiles() {
		return quickLaunchTiles;
	}

	public String getQuickLaunchTilesLabel() {
		return quickLaunchTilesLabel;
	}

}

