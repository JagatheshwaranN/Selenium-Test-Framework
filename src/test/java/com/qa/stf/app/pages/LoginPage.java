package com.qa.stf.app.pages;

import com.qa.stf.base.DriverManager;
import com.qa.stf.app.elements.LoginPageElement;

public class LoginPage extends LoginPageElement {

	PageManager pageManager = new PageManager();

	public LoginPage(DriverManager driverManager) {
		super(driverManager);
	}

	public String verifyLoginPageHeader() {
		pageManager.getWaitHandler().waitForPresenceOfElements(getLoginPageLoadCheck(), getLoginPageLoadCheckLabel());
		return pageManager.getPageComponent().getPageHeader(getLoginHeader(), getLoginHeaderLabel());
	}

	public void doLogin(String username, String password) {
		pageManager.getPageComponent().typeText(getUserName(), username, getUserNameLabel());
		pageManager.getPageComponent().typeText(getPassWord(), password, getPassWordLabel());
		pageManager.getPageComponent().clickElement(getLogin(), getLoginLabel());
	}

}

