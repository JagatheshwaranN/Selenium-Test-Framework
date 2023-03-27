package com.qa.taf.ohrm.testcases;

import org.testng.annotations.Test;

import com.qa.taf.base.DriverManager;
import com.qa.taf.ohrm.pages.AdminPage;
import com.qa.taf.ohrm.pages.DashboardPage;
import com.qa.taf.ohrm.pages.LoginPage;

public class AdminPageTest extends DriverManager {

	@Test()
	public void adminPageTest() throws InterruptedException {

		LoginPage loginPage = new LoginPage();
		DashboardPage dashboardPage = loginPage.doLogin("Admin", "admin123");
		Thread.sleep(5000);
		AdminPage adminPage = dashboardPage.navigateToAdminPage();
		Thread.sleep(5000);
		adminPage.searchUser("Admin", "Enabled");
	}
}
