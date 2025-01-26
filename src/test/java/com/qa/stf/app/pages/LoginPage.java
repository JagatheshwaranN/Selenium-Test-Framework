package com.qa.stf.app.pages;

import com.qa.stf.base.BasePage;
import com.qa.stf.base.DriverManager;
import com.qa.stf.app.elements.LoginPageElement;
import com.qa.stf.handler.DropDownHandler;
import com.qa.stf.handler.VerificationHandler;
import com.qa.stf.handler.WaitHandler;

public class LoginPage extends LoginPageElement {

	PageManager pageManager = new PageManager();
	BasePage basePage = pageManager.getPageComponent();
	WaitHandler waitHandler = pageManager.getWaitHandler();

	public LoginPage(DriverManager driverManager) {
		super(driverManager);
	}

	public String verifyLoginPageHeader() {
		waitHandler.waitForPresenceOfElements(getLoginPageLoadCheck(), getLoginPageLoadCheckLabel());
		return basePage.getPageHeader(getLoginHeader(), getLoginHeaderLabel());
	}

	public void doLogin(String username, String password) {
		basePage.typeText(getUserName(), username, getUserNameLabel());
		basePage.typeText(getPassWord(), password, getPassWordLabel());
		basePage.clickElement(getLogin(), getLoginLabel());
	}

}

