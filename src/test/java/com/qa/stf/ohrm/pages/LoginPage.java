package com.qa.stf.ohrm.pages;

import com.qa.stf.ohrm.elements.LoginPageElement;

public class LoginPage extends LoginPageElement {

	PageManager pageManager = new PageManager();

	public DashboardPage doLogin(String username, String password) {

		pageManager.getVerificationHelper().isElementDisplayed(getUserName(), getUserNameLabel());
		typeElement(getUserName(), username, getUserNameLabel());
		typeElement(getPassWord(), password, getPassWordLabel());
		clickElement(getLogin(), getLoginLabel());
		return new DashboardPage();
	}
}

