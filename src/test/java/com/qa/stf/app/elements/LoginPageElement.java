package com.qa.stf.app.elements;

import com.qa.stf.base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.stf.app.objects.LoginPageObject;

public class LoginPageElement extends LoginPageObject {

	public LoginPageElement(DriverManager driverManager) {
		super(driverManager);
	}

	public By getLoginPageLoadCheck() {
		return loginPageLoadCheck;
	}

	public String getLoginPageLoadCheckLabel() {
		return loginPageLoadCheckLabel;
	}

	public WebElement getLoginHeader() {
		return loginHeader;
	}

	public String getLoginHeaderLabel() {
		return loginHeaderLabel;
	}

	public WebElement getUserName() {
		return userName;
	}

	public String getUserNameLabel() {
		return userNameLabel;
	}

	public WebElement getPassWord() {
		return passWord;
	}

	public String getPassWordLabel() {
		return passWordLabel;
	}

	public WebElement getLogin() {
		return login;
	}

	public String getLoginLabel() {
		return loginLabel;
	}

	public WebElement getForgotPasswordLink() {
		return forgotPasswordLink;
	}

	public String getForgotPasswordLinkLabel() {
		return forgotPasswordLinkLabel;
	}

	public WebElement getFplUserName() {
		return fplUserName;
	}

	public String getFplUserNameLabel() {
		return fplUserNameLabel;
	}

	public WebElement getFplReset() {
		return fplReset;
	}

	public String getFplResetLabel() {
		return fplResetLabel;
	}
}

