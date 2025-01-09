package com.qa.stf.app.pages;

import com.qa.stf.base.DriverManager;
import com.qa.stf.app.elements.LoginPageElement;

public class LoginPage extends LoginPageElement {

	PageManager pageManager = new PageManager();

	public LoginPage(DriverManager driverManager) {
		super(driverManager);
	}

	public void doLogin(String username, String password) {
		pageManager.getPageComponent().getPageHeader(getLoginHeader(), getLoginHeaderLabel());
		pageManager.getPageComponent().typeText(getUserName(), username, getUserNameLabel());
		pageManager.getPageComponent().typeText(getPassWord(), password, getPassWordLabel());
		pageManager.getPageComponent().clickElement(getLogin(), getLoginLabel());
	}

}

