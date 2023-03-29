package com.qa.taf.ohrm.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPageObject extends LoginPageObject {

	public DashboardPageObject() {
		PageFactory.initElements(driverManager.getDriver(), this);
	}

	@FindBys({ @FindBy(css = ".oxd-topbar-header-userarea"), @FindBy(css = ".oxd-userdropdown-tab") })
	protected WebElement userDropDown;
	protected String userDropDownLabel = "UserDropDown";

	@FindBy(css = ".oxd-dropdown-menu")
	protected WebElement userDropDownMenu;
	protected String userDropDownMenuLabel = "UserDropDownMenu";

	@FindBy(xpath = "//a[contains(@href,'logout')]")
	protected WebElement logout;
	protected String logoutLabel = "Logout";

	@FindBy(xpath = "//a[contains(@href,'admin/viewAdminModule')]")
	protected WebElement adminSection;
	protected String adminSectionLabel = "AdminSection";

	@FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-quick-launch-card']")
	protected List<WebElement> quickLaunchCards;
	protected String quickLaunchCardsLabel = "QuickLaunchCards";
}
