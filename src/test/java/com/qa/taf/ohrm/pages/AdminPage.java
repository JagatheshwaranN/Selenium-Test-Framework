package com.qa.taf.ohrm.pages;

import com.qa.taf.ohrm.elements.AdminPageElement;

public class AdminPage extends AdminPageElement {

	public void searchUser(String user, String status) {

		isElementPresent(getAdminHeader(), getAdminHeaderLabel());
		typeElement(getAdminSearchUserName(), user, getAdminSearchUserNameLabel());
		elementSelect(getAdminUserRoleDropDown(), getAdminUserRoleDropDownOptions(), user,
				getAdminUserRoleDropDownLabel());
		elementSelect(getAdminUserStatusDropDown(), getAdminUserStatusDropDownOptions(), status,
				getAdminUserStatusDropDownLabel());
		clickElement(getAdminSearch(), getAdminSearchLabel());
		isElementPresent(getAdminSearchResultSection(), getAdminSearchResultSectionLabel());
		isElementPresent(getAdminSearchResultData(), getAdminSearchResultDataLabel());
	}
}
