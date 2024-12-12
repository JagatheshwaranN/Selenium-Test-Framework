package com.qa.stf.ohrm.pages;

import com.qa.stf.base.DriverManager;
import com.qa.stf.ohrm.elements.AdminPageElement;

public class AdminPage extends AdminPageElement {

	PageManager pageManager = new PageManager();

	public AdminPage(DriverManager driverManager) {
		super(driverManager);
	}

	public void searchUser(String user, String status) {

		pageManager.getVerificationHelper().isElementDisplayed(getAdminHeader(), getAdminHeaderLabel());
		pageManager.getComponent().typeText(getAdminSearchUserName(), user, getAdminSearchUserNameLabel());
		pageManager.getDropDownHandler().selectDropdownOption(getAdminUserRoleDropDown(), getAdminUserRoleDropDownOptions(),
				user, getAdminUserRoleDropDownLabel());
		pageManager.getDropDownHandler().selectDropdownOption(getAdminUserStatusDropDown(), getAdminUserStatusDropDownOptions(),
				status, getAdminUserStatusDropDownLabel());
		pageManager.getComponent().clickElement(getAdminSearch(), getAdminSearchLabel());
		pageManager.getVerificationHelper().isElementDisplayed(getAdminSearchResultSection(), getAdminSearchResultSectionLabel());
		pageManager.getVerificationHelper().isElementDisplayed(getAdminSearchResultSection(),
				getAdminSearchResultSectionLabel());
		pageManager.getVerificationHelper().isElementDisplayed(getAdminSearchResultData(),
				getAdminSearchResultDataLabel());
	}
}

