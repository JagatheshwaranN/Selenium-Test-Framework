<<<<<<< HEAD
package com.qa.taf.ohrm.pages;

import com.qa.taf.helper.DropDownHelper;
import com.qa.taf.helper.VerificationHelper;

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
=======
package com.qa.taf.ohrm.pages;

import com.qa.taf.helper.DropDownHelper;
import com.qa.taf.helper.VerificationHelper;

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
>>>>>>> 3a5b23362e15d382589ba9ebe11d0cd0a0c77f68
