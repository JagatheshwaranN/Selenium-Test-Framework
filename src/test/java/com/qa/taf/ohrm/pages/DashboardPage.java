<<<<<<< HEAD
package com.qa.taf.ohrm.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import com.qa.taf.ohrm.elements.DashboardPageElement;

public class DashboardPage extends DashboardPageElement {

	PageManager pageManager = new PageManager();

	public void userDropDown() {
		clickElement(getUserDropDown(), getUserDropDownLabel());
		pageManager.getVerificationHelper().verifyElementPresent(getUserDropDownMenu(), getUserDropDownMenuLabel());
	}

	public void pfDoLogout() {
		userDropDown();
		clickElement(getLogout(), getLogoutLabel());
	}

	public AdminPage navigateToAdminPage() {
		clickElement(getAdminSection(), getAdminSectionLabel());
		return new AdminPage();
	}

	public List<WebElement> getQuickLaunchCards() {
		return getQuickLaunchCards();
	}
}
=======
package com.qa.taf.ohrm.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import com.qa.taf.ohrm.elements.DashboardPageElement;

public class DashboardPage extends DashboardPageElement {

	PageManager pageManager = new PageManager();

	public void userDropDown() {
		clickElement(getUserDropDown(), getUserDropDownLabel());
		pageManager.getVerificationHelper().verifyElementPresent(getUserDropDownMenu(), getUserDropDownMenuLabel());
	}

	public void pfDoLogout() {
		userDropDown();
		clickElement(getLogout(), getLogoutLabel());
	}

	public AdminPage navigateToAdminPage() {
		clickElement(getAdminSection(), getAdminSectionLabel());
		return new AdminPage();
	}

	public List<WebElement> getQuickLaunchCards() {
		return getQuickLaunchCards();
	}
}
>>>>>>> 3a5b23362e15d382589ba9ebe11d0cd0a0c77f68
