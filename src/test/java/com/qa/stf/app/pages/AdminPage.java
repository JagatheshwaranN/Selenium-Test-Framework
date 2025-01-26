package com.qa.stf.app.pages;

import com.qa.stf.base.BasePage;
import com.qa.stf.base.DriverManager;
import com.qa.stf.app.elements.AdminPageElement;
import com.qa.stf.handler.DropDownHandler;
import com.qa.stf.handler.VerificationHandler;
import com.qa.stf.handler.WaitHandler;

public class AdminPage extends AdminPageElement {

	PageManager pageManager = new PageManager();
	BasePage basePage = pageManager.getPageComponent();
	DropDownHandler dropDownHandler = pageManager.getDropDownHandler();
	VerificationHandler verificationHandler = pageManager.getVerificationHelper();
	WaitHandler waitHandler = pageManager.getWaitHandler();

	public AdminPage(DriverManager driverManager) {
		super(driverManager);
	}

	public String verifyAdminPageHeader() {
		waitHandler.waitForPresenceOfElements(getAdminPageLoadCheck(), getAdminPageLoadCheckLabel());
		return basePage.getPageHeader(getAdminHeader(), getAdminHeaderLabel());
	}

	public boolean searchUser(String user, String status) {
		basePage.getPageHeader(getAdminHeader(), getAdminHeaderLabel());
		basePage.typeText(getAdminSearchUserName(), user, getAdminSearchUserNameLabel());
		dropDownHandler.selectDropdownOption(getAdminUserRoleDropDown(), getAdminSearchDropDownLayout(), getAdminUserRoleDropDownOptions(),
				user, getAdminUserRoleDropDownLabel());
		dropDownHandler.selectDropdownOption(getAdminUserStatusDropDown(), getAdminSearchDropDownLayout(), getAdminUserStatusDropDownOptions(),
				status, getAdminUserStatusDropDownLabel());
		basePage.clickElement(getAdminSearch(), getAdminSearchLabel());
		verificationHandler.isElementDisplayed(getAdminSearchResultSection(), getAdminSearchResultSectionLabel());
		return verificationHandler.isElementDisplayed(getAdminSearchResultData(),
				getAdminSearchResultDataLabel());
	}

}

