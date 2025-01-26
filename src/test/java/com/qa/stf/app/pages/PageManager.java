package com.qa.stf.app.pages;

import com.qa.stf.base.BasePage;
import com.qa.stf.base.DriverManager;
import com.qa.stf.handler.*;
import com.qa.stf.util.EncryptionManager;

public class PageManager {

	private BasePage basePage;

	private DateTimeHandler datePickerHandler;

	private DropDownHandler dropDownHandler;

	private InteractionHandler interactionHandler;

	private VerificationHandler verificationHelper;

	private WaitHandler waitHandler;

	private EncryptionManager encryptionManager;

	private LoginPage loginPage;

	private DashboardPage dashboardPage;

	private AdminPage adminPage;

	private TimesheetPage timesheetPage;

	public BasePage getPageComponent() {
		return (basePage == null) ? basePage = new BasePage(DriverManager.getInstance()) : basePage;
	}

	public DateTimeHandler getDatePickerHandler() {
		return (datePickerHandler == null) ? datePickerHandler = new DateTimeHandler(DriverManager.getInstance(), new VerificationHandler()) : datePickerHandler;
	}

	public DropDownHandler getDropDownHandler() {
		return (dropDownHandler == null) ? dropDownHandler = new DropDownHandler(new VerificationHandler()) : dropDownHandler;
	}

	public InteractionHandler getInteractionHandler() {
		return (interactionHandler == null) ? interactionHandler = new InteractionHandler(DriverManager.getInstance(), new VerificationHandler()) : interactionHandler;
	}

	public VerificationHandler getVerificationHelper() {
		return (verificationHelper == null) ? verificationHelper = new VerificationHandler() : verificationHelper;
	}

	public WaitHandler getWaitHandler() {
		return (waitHandler == null) ? waitHandler = new WaitHandler(DriverManager.getInstance()) : waitHandler;
	}

	public EncryptionManager getEncryptionManager() {
		return (encryptionManager == null) ? encryptionManager = new EncryptionManager() : encryptionManager;
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

	public TimesheetPage getTimesheetPage() {
		return (timesheetPage == null) ? timesheetPage = new TimesheetPage(DriverManager.getInstance()) : timesheetPage;
	}

}

