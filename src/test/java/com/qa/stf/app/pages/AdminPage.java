package com.qa.stf.app.pages;

import com.qa.stf.base.DriverManager;
import com.qa.stf.app.elements.AdminPageElement;

public class AdminPage extends AdminPageElement {

	PageManager pageManager = new PageManager();

	public AdminPage(DriverManager driverManager) {
		super(driverManager);
	}

	public boolean verifyAdminPageHeader() {
		return pageManager.getVerificationHelper().isElementDisplayed(getAdminHeader(), getAdminHeaderLabel());
	}

	public boolean searchUser(String user, String status) {
		pageManager.getVerificationHelper().isElementDisplayed(getAdminHeader(), getAdminHeaderLabel());
		pageManager.getPageComponent().typeText(getAdminSearchUserName(), user, getAdminSearchUserNameLabel());
		pageManager.getDropDownHandler().selectDropdownOption(getAdminUserRoleDropDown(), getAdminSearchDropDownLayout(), getAdminUserRoleDropDownOptions(),
				user, getAdminUserRoleDropDownLabel());
		pageManager.getDropDownHandler().selectDropdownOption(getAdminUserStatusDropDown(), getAdminSearchDropDownLayout(), getAdminUserStatusDropDownOptions(),
				status, getAdminUserStatusDropDownLabel());
		pageManager.getPageComponent().clickElement(getAdminSearch(), getAdminSearchLabel());
		pageManager.getVerificationHelper().isElementDisplayed(getAdminSearchResultSection(), getAdminSearchResultSectionLabel());
		return pageManager.getVerificationHelper().isElementDisplayed(getAdminSearchResultData(),
				getAdminSearchResultDataLabel());
	}

}

