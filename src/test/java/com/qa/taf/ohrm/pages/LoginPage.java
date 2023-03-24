package com.qa.taf.ohrm.pages;

import com.qa.taf.ohrm.elements.LoginPageElement;

public class LoginPage extends LoginPageElement {

	public DashboardPage doLogin(String username, String password) {

		elementType(getUserName(), username, getUserNameLabel());
		elementType(getPassWord(), password, getPassWordLabel());
		elementClick(getLogin(), getLoginLabel());
		return new DashboardPage();
	}
}
