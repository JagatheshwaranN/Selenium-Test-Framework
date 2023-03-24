package com.qa.taf.ohrm.pages;

import com.qa.taf.ohrm.elements.LoginPageElement;

import com.qa.taf.reuse.ReusableComponent;

public class LoginPage extends ReusableComponent {

	LoginPageElement loginPageElement = new LoginPageElement();

	public DashboardPage doLogin(String username, String password) {

		elementType(loginPageElement.getUserName(), username, loginPageElement.getUserNameLabel());
		elementType(loginPageElement.getPassWord(), password, loginPageElement.getPassWordLabel());
		elementClick(loginPageElement.getLogin(), loginPageElement.getLoginLabel());
		return new DashboardPage();
	}
}
