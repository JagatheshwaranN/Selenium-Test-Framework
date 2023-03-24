package com.qa.taf.ohrm.pages;

import com.qa.taf.ohrm.elements.AdminPageElement;

public class AdminPage extends DashboardPage {

	AdminPageElement adminPageElement = new AdminPageElement();

	public void searchUser(String user, String status) {

		isElementPresent(adminPageElement.getAdminHeader(), adminPageElement.getAdminHeaderLabel());
		elementType(adminPageElement.getAdminSearchUserName(), user, adminPageElement.getAdminSearchUserNameLabel());
		elementSelect(adminPageElement.getAdminUserRoleDropDown(), adminPageElement.getAdminUserRoleDropDownOptions(),
				user, adminPageElement.getAdminUserRoleDropDownLabel());
		elementSelect(adminPageElement.getAdminUserStatusDropDown(),
				adminPageElement.getAdminUserStatusDropDownOptions(), status,
				adminPageElement.getAdminUserStatusDropDownLabel());
		elementClick(adminPageElement.getAdminSearch(), adminPageElement.getAdminSearchLabel());
		isElementPresent(adminPageElement.getAdminSearchResultSection(),
				adminPageElement.getAdminSearchResultSectionLabel());
		isElementPresent(adminPageElement.getAdminSearchResultData(), adminPageElement.getAdminSearchResultDataLabel());
	}
}
