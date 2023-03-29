package com.qa.taf.ohrm.pages;

import com.qa.taf.ohrm.elements.LoginPageElement;

public class LoginPage extends LoginPageElement {

	public DashboardPage doLogin(String username, String password) {

		typeElement(getUserName(), username, getUserNameLabel());
		typeElement(getPassWord(), password, getPassWordLabel());
		clickElement(getLogin(), getLoginLabel());
		return new DashboardPage();
	}
}
