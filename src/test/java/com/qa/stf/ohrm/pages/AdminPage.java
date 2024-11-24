package com.qa.stf.ohrm.pages;

import com.qa.stf.ohrm.elements.AdminPageElement;

public class AdminPage extends AdminPageElement {

	PageManager pageManager = new PageManager();

	public void searchUser(String user, String status) {

		pageManager.getVerificationHelper().isElementDisplayed(getAdminHeader(), getAdminHeaderLabel());
		typeElement(getAdminSearchUserName(), user, getAdminSearchUserNameLabel());
		pageManager.getDropDownHandler().selectDropdownOption(getAdminUserRoleDropDown(), getAdminUserRoleDropDownOptions(),
				user, getAdminUserRoleDropDownLabel());
		pageManager.getDropDownHandler().selectDropdownOption(getAdminUserStatusDropDown(), getAdminUserStatusDropDownOptions(),
				status, getAdminUserStatusDropDownLabel());
		clickElement(getAdminSearch(), getAdminSearchLabel());
		waitForElementVisible(getAdminSearchResultSection(), getAdminSearchResultSectionLabel());
		pageManager.getVerificationHelper().isElementDisplayed(getAdminSearchResultSection(),
				getAdminSearchResultSectionLabel());
		pageManager.getVerificationHelper().isElementDisplayed(getAdminSearchResultData(),
				getAdminSearchResultDataLabel());
	}
}

