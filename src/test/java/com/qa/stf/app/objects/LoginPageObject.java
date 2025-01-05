package com.qa.stf.app.objects;

import com.qa.stf.base.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

	public LoginPageObject(DriverManager driverManager) {
        PageFactory.initElements(driverManager.getDriver(), this);
	}

	@FindBy(xpath = "//input[@name='username']")
	protected WebElement userName;
	protected String userNameLabel = "UserName";

	@FindBy(xpath = "//input[@name='password']")
	protected WebElement passWord;
	protected String passWordLabel = "Password";

	@FindAll({ @FindBy(xpath = "//button[@type='submit']"),
			@FindBy(css = ".oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button") })
	protected WebElement login;
	protected String loginLabel = "Login";

	@FindBy(xpath = "//p[contains(@class,'login-forgot-header')]")
	protected WebElement forgotPasswordLink;
	protected String forgotPasswordLinkLabel = "ForgotPasswordLink";

	@FindBy(xpath = "//input[@name='username']")
	protected WebElement fplUserName;
	protected String fplUserNameLabel = "FPLUserName";

	@FindBy(xpath = "//button[contains(@class,'orangehrm-forgot-password-button--reset')]")
	protected WebElement fplReset;
	protected String fplResetLabel = "FPLReset";

}

