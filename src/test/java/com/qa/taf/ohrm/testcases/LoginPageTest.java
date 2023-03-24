package com.qa.taf.ohrm.testcases;

import org.testng.annotations.Test;

import com.qa.taf.base.DriverManager;
import com.qa.taf.ohrm.pages.LoginPage;

public class LoginPageTest extends DriverManager {

	@Test
	public void loginPageTest() {
		LoginPage loginPage = new LoginPage();
		loginPage.doLogin("Admin", "admin123");
	}

}
