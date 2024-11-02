package com.qa.stf.ohrm.pages;

import com.qa.stf.helper.DropDownHelper;
import com.qa.stf.helper.VerificationHelper;

public class PageManager {

	private LoginPage loginPage;

	private DashboardPage dashboardPage;

	private AdminPage adminPage;

	private VerificationHelper verificationHelper;

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

	public VerificationHelper getVerificationHelper() {
		return (verificationHelper == null) ? verificationHelper = new VerificationHelper() : verificationHelper;
	}

	public DropDownHelper getDropDownHelper() {
		return (dropDownHelper == null) ? dropDownHelper = new DropDownHelper() : dropDownHelper;
	}
}

