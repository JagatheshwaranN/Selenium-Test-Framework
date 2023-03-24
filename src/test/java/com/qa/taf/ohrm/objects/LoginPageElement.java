package com.qa.taf.ohrm.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class LoginPageElement {

	/**
	 * @FindAll - It is used to identify an element on the page with more than one
	 *          locator. For example, in below code for login button, it has two
	 *          locators such as xpath and css. If incase anyone of the locator
	 *          didn't work then the element can be identified by the other locator.
	 */

	@FindBy(xpath = "//input[@name='username']")
	public WebElement userName;
	public String userNameLabel = "UserName";

	@FindBy(xpath = "//input[@name='password']")
	public WebElement passWord;
	public String passWordLabel = "Password";

//	@FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
//	public WebElement login;

	@FindAll({ @FindBy(xpath = "//button[@type='submit']"),
			@FindBy(css = ".oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button") })
	public WebElement login;
	public String loginLabel = "Login";

	@FindBy(xpath = "//p[contains(@class,'login-forgot-header')]")
	public WebElement forgotPasswordLink;
	public String forgotPasswordLinkLabel = "ForgotPasswordLink";

	@FindBy(xpath = "//input[@name='username']")
	public WebElement fplUserName;
	public String fplUserNameLabel = "FPLUserName";

	@FindBy(xpath = "//button[contains(@class,'orangehrm-forgot-password-button--reset')]")
	public WebElement fplReset;
	public String fplResetLabel = "FPLReset";

}
