package com.qa.stf.ohrm.pages;

import com.qa.stf.base.BasePage;
import com.qa.stf.base.DriverManager;
import com.qa.stf.handler.DropDownHandler;
import com.qa.stf.handler.VerificationHandler;
import com.qa.stf.report.ExtentReportManager;

public class PageManager {

	private LoginPage loginPage;

	private DashboardPage dashboardPage;

	private AdminPage adminPage;

	private VerificationHandler verificationHelper;

	private DropDownHandler dropDownHandler;

	private BasePage basePage;

	public BasePage getPageComponent() {
		return (basePage == null) ? basePage = new BasePage(DriverManager.getInstance()) : basePage;
	}

	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage(DriverManager.getInstance()) : loginPage;
	}

	public DashboardPage getDashboardPage() {
		return (dashboardPage == null) ? dashboardPage = new DashboardPage(DriverManager.getInstance()) : dashboardPage;
	}

	public AdminPage getAdminPage() {
		return (adminPage == null) ? adminPage = new AdminPage(DriverManager.getInstance()) : adminPage;
	}

	public VerificationHandler getVerificationHelper() {
		return (verificationHelper == null) ? verificationHelper = new VerificationHandler() : verificationHelper;
	}

	public DropDownHandler getDropDownHandler() {
		return (dropDownHandler == null) ? dropDownHandler = new DropDownHandler(new VerificationHandler()) : dropDownHandler;
	}
}

