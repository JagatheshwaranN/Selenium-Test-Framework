package com.qa.taf.ohrm.elements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.taf.ohrm.objects.DashboardPageObject;

public class DashboardPageElement extends DashboardPageObject {

	public DashboardPageElement(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
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

	public List<WebElement> getQuickLaunchCards() {
		return quickLaunchCards;
	}

	public String getQuickLaunchCardsLabel() {
		return quickLaunchCardsLabel;
	}

}
