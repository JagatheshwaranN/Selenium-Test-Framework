package com.qa.stf.ohrm.pages;

import com.qa.stf.helper.DropDownHelper;
import com.qa.stf.helper.VerificationHandler;

public class PageManager {

	private LoginPage loginPage;

	private DashboardPage dashboardPage;

	private AdminPage adminPage;

	private VerificationHandler verificationHelper;

	private DropDownHelper dropDownHelper;

	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage() : loginPage;
	}

	public DashboardPage getDashboardPage() {
		return (dashboardPage == null) ? dashboardPage = new DashboardPage() : dashboardPage;
	}

	public AdminPage getAdminPage() {
		return (adminPage == null) ? adminPage = new AdminPage() : adminPage;
	}

	public VerificationHandler getVerificationHelper() {
		return (verificationHelper == null) ? verificationHelper = new VerificationHandler() : verificationHelper;
	}

	public DropDownHelper getDropDownHelper() {
		return (dropDownHelper == null) ? dropDownHelper = new DropDownHelper() : dropDownHelper;
	}
}

