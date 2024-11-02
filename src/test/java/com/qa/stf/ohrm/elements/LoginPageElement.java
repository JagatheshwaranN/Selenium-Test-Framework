package com.qa.stf.ohrm.elements;

import org.openqa.selenium.WebElement;

import com.qa.stf.ohrm.objects.LoginPageObject;

public class LoginPageElement extends LoginPageObject {

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

