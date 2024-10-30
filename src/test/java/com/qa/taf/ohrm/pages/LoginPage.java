package com.qa.taf.ohrm.pages;

import com.qa.taf.ohrm.elements.LoginPageElement;

public class LoginPage extends LoginPageElement {

	PageManager pageManager = new PageManager();

	public DashboardPage doLogin(String username, String password) {

		pageManager.getVerificationHelper().verifyElementPresent(getUserName(), getUserNameLabel());
		typeElement(getUserName(), username, getUserNameLabel());
		typeElement(getPassWord(), password, getPassWordLabel());
		clickElement(getLogin(), getLoginLabel());
		return new DashboardPage();
	}
}

