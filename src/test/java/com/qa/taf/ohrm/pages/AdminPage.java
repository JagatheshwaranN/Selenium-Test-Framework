package com.qa.taf.ohrm.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.taf.ohrm.objects.AdminPageElement;



public class AdminPage extends DashboardPage {

	public AdminPageElement adminPageElement;

	public AdminPage() {

		this.adminPageElement = new AdminPageElement();
//		AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver,
//				Integer.parseInt(Constants.WebDriverWaitTime));
		PageFactory.initElements(driver, this.adminPageElement);
	}

	public void pfSearchUser(String user, String status) {

		isElementPresent(adminPageElement.adminHeader, adminPageElement.adminHeaderLabel);
		elementType(adminPageElement.adminSearchUserName, user, adminPageElement.adminSearchUserNameLabel);
		elementSelect(adminPageElement.adminUserRoleDropDown, adminPageElement.adminUserRoleDropDownOptions, user,
				adminPageElement.adminUserRoleDropDownLabel);
		elementSelect(adminPageElement.adminUserStatusDropDown, adminPageElement.adminUserStatusDropDownOptions, status,
				adminPageElement.adminUserStatusDropDownLabel);
		elementClick(adminPageElement.adminSearch, adminPageElement.adminSearchLabel);
		isElementPresent(adminPageElement.adminSearchResultSection, adminPageElement.adminSearchResultSectionLabel);
		isElementPresent(adminPageElement.adminSearchResultData, adminPageElement.adminSearchResultDataLabel);
	}
}
