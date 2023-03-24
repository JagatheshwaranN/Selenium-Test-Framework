package com.qa.taf.ohrm.pages;

import org.openqa.selenium.support.PageFactory;


import com.qa.taf.ohrm.objects.AdminPageElement;
import com.qa.taf.ohrm.objects.LoginPageElement;

public class LoginPage {

	public LoginPageElement loginPageElement;

	public LoginPage() {

		this.loginPageElement = new LoginPageElement();
//		AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver,
//				Integer.parseInt(Constants.WebDriverWaitTime));
		PageFactory.initElements(driver, this.loginPageElement);
	}

	public DashboardPage pfDoLogin(String username, String password) {

		elementType(loginPageElement.userName, username, loginPageElement.userNameLabel);
		elementType(loginPageElement.passWord, password, loginPageElement.passWordLabel);
		elementClick(loginPageElement.login, loginPageElement.loginLabel);
		return new DashboardPage();
	}
}
