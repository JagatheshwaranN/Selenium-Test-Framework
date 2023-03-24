package com.qa.taf.ohrm.elements;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

import com.qa.taf.ohrm.objects.LoginPageObject;
import com.qa.taf.ohrm.pages.LoginPage;

public class LoginPageElement extends LoginPage {

	public LoginPageObject loginPageObject;

	public LoginPageElement() {
		
		this.loginPageObject = new LoginPageObject();
		PageFactory.initElements(getDriver(), this.loginPageObject);
	}

	public WebElement getUserName() {
		return loginPageObject.userName;
	}

	public String getUserNameLabel() {
		return loginPageObject.userNameLabel;
	}

	public WebElement getPassWord() {
		return loginPageObject.passWord;
	}

	public String getPassWordLabel() {
		return loginPageObject.passWordLabel;
	}

	public WebElement getLogin() {
		return loginPageObject.login;
	}

	public String getLoginLabel() {
		return loginPageObject.loginLabel;
	}

	public WebElement getForgotPasswordLink() {
		return loginPageObject.forgotPasswordLink;
	}

	public String getForgotPasswordLinkLabel() {
		return loginPageObject.forgotPasswordLinkLabel;
	}

	public WebElement getFplUserName() {
		return loginPageObject.fplUserName;
	}

	public String getFplUserNameLabel() {
		return loginPageObject.fplUserNameLabel;
	}

	public WebElement getFplReset() {
		return loginPageObject.fplReset;
	}

	public String getFplResetLabel() {
		return loginPageObject.fplResetLabel;
	}

}
