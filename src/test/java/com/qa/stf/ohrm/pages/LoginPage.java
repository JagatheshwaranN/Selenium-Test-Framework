package com.qa.stf.ohrm.pages;

import com.qa.stf.base.DriverManager;
import com.qa.stf.ohrm.elements.LoginPageElement;

public class LoginPage extends LoginPageElement {

	PageManager pageManager = new PageManager();

	public LoginPage(DriverManager driverManager) {
		super(driverManager);
	}

	public DashboardPage doLogin(String username, String password) {

		pageManager.getVerificationHelper().isElementDisplayed(getUserName(), getUserNameLabel());
		pageManager.getComponent().typeText(getUserName(), username, getUserNameLabel());
		pageManager.getComponent().typeText(getPassWord(), password, getPassWordLabel());
		pageManager.getComponent().clickElement(getLogin(), getLoginLabel());
		return new DashboardPage();
	}
}

