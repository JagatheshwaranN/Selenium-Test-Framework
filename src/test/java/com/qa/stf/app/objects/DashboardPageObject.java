package com.qa.stf.app.objects;

import com.qa.stf.base.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPageObject {

	public DashboardPageObject(DriverManager driverManager) {
		PageFactory.initElements(driverManager.getDriver(), this);
	}

	@FindBy(xpath = "//div[@class='oxd-topbar-header-title']//span//h6")
	protected WebElement dashboardHeader;
	protected String dashboardHeaderLabel = "Dashboard Header";

	@FindBy(css = ".oxd-dropdown-menu")
	protected WebElement userDropDownMenu;
	protected String userDropDownMenuLabel = "UserDropDownMenu";

	@FindBys({ @FindBy(css = ".oxd-topbar-header-userarea"), @FindBy(css = ".oxd-userdropdown-tab") })
	protected WebElement userDropDown;
	protected String userDropDownLabel = "UserDropDown";

	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	protected WebElement userName;
	protected String userNameLabel = "UserName";

	@FindBy(xpath = "//a[contains(@href,'logout')]")
	protected WebElement logout;
	protected String logoutLabel = "Logout";

	@FindBy(xpath = "//a[contains(@href,'admin/viewAdminModule')]")
	protected WebElement adminSection;
	protected String adminSectionLabel = "AdminSection";

	@FindBy(xpath = "//a[contains(@href,'time/viewTimeModule')]")
	protected WebElement timeSection;
	protected String timeSectionLabel = "AdminSection";

	@FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-quick-launch-card']")
	protected List<WebElement> quickLaunchTiles;
	protected String quickLaunchTilesLabel = "QuickLaunchTiles";
}

