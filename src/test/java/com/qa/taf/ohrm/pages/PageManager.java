package com.qa.taf.ohrm.pages;

public class PageManager {

	private LoginPage loginPage;

	private DashboardPage dashboardPage;

	private AdminPage adminPage;

	public LoginPage getLoginPage() {

		return (loginPage == null) ? loginPage = new LoginPage() : loginPage;
	}

	public DashboardPage getDashboardPage() {

		return (dashboardPage == null) ? dashboardPage = new DashboardPage() : dashboardPage;
	}

	public AdminPage getAdminPage() {

		return (adminPage == null) ? adminPage = new AdminPage() : adminPage;
	}

}
