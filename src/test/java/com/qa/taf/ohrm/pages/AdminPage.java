package com.qa.taf.ohrm.pages;

import org.openqa.selenium.WebDriver;

import com.qa.taf.ohrm.elements.AdminPageElement;

public class AdminPage extends AdminPageElement {

	public AdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

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
