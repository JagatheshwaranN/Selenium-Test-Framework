<<<<<<< HEAD
package com.qa.taf.ohrm.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPageObject extends DashboardPageObject {

	public AdminPageObject() {
		PageFactory.initElements(driverManager.getDriver(), this);
	}

	@FindBy(css = ".oxd-topbar-header-breadcrumb")
	protected WebElement adminHeader;
	protected String adminHeaderLabel = "AdminHeader";

	@FindBy(css = ".oxd-form .oxd-input.oxd-input--active")
	protected WebElement adminSearchUserName;
	protected String adminSearchUserNameLabel = "AdminSearchUserName";

	@FindBy(xpath = "(//div[contains(@class,'oxd-select-text--after')])[1]")
	protected WebElement adminUserRoleDropDown;
	protected String adminUserRoleDropDownLabel = "AdminUserRoleDropDown";

	@FindBy(xpath = "//div[@role='listbox']//div[@role='option']//span")
	protected List<WebElement> adminUserRoleDropDownOptions;
	protected String adminUserRoleDropDownOptionsLabel = "AdminUserRoleDropDownOptions";

	@FindBy(xpath = "(//div[contains(@class,'oxd-select-text--after')])[2]")
	protected WebElement adminUserStatusDropDown;
	protected String adminUserStatusDropDownLabel = "AdminUserStatusDropDown";

	@FindBy(xpath = "//div[@role='listbox']//div[@role='option']//span")
	protected List<WebElement> adminUserStatusDropDownOptions;
	protected String adminUserStatusDropDownOptionsLabel = "AdminUserStatusDropDownOptions";

	@FindBy(xpath = "//button[contains(@class,'oxd-button--secondary orangehrm-left-space')]")
	protected WebElement adminSearch;
	protected String adminSearchLabel = "AdminSearch";

	@FindBy(css = ".oxd-table-card")
	protected WebElement adminSearchResultSection;
	protected String adminSearchResultSectionLabel = "AdminSearchResultSection";

	@FindBy(css = ".oxd-table-cell.oxd-padding-cell:nth-child(2)")
	protected WebElement adminSearchResultData;
	protected String adminSearchResultDataLabel = "AdminSearchResultData";
}
=======
package com.qa.taf.ohrm.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPageObject extends DashboardPageObject {

	public AdminPageObject() {
		PageFactory.initElements(driverManager.getDriver(), this);
	}

	@FindBy(css = ".oxd-topbar-header-breadcrumb")
	protected WebElement adminHeader;
	protected String adminHeaderLabel = "AdminHeader";

	@FindBy(css = ".oxd-form .oxd-input.oxd-input--active")
	protected WebElement adminSearchUserName;
	protected String adminSearchUserNameLabel = "AdminSearchUserName";

	@FindBy(xpath = "(//div[contains(@class,'oxd-select-text--after')])[1]")
	protected WebElement adminUserRoleDropDown;
	protected String adminUserRoleDropDownLabel = "AdminUserRoleDropDown";

	@FindBy(xpath = "//div[@role='listbox']//div[@role='option']//span")
	protected List<WebElement> adminUserRoleDropDownOptions;
	protected String adminUserRoleDropDownOptionsLabel = "AdminUserRoleDropDownOptions";

	@FindBy(xpath = "(//div[contains(@class,'oxd-select-text--after')])[2]")
	protected WebElement adminUserStatusDropDown;
	protected String adminUserStatusDropDownLabel = "AdminUserStatusDropDown";

	@FindBy(xpath = "//div[@role='listbox']//div[@role='option']//span")
	protected List<WebElement> adminUserStatusDropDownOptions;
	protected String adminUserStatusDropDownOptionsLabel = "AdminUserStatusDropDownOptions";

	@FindBy(xpath = "//button[contains(@class,'oxd-button--secondary orangehrm-left-space')]")
	protected WebElement adminSearch;
	protected String adminSearchLabel = "AdminSearch";

	@FindBy(css = ".oxd-table-card")
	protected WebElement adminSearchResultSection;
	protected String adminSearchResultSectionLabel = "AdminSearchResultSection";

	@FindBy(css = ".oxd-table-cell.oxd-padding-cell:nth-child(2)")
	protected WebElement adminSearchResultData;
	protected String adminSearchResultDataLabel = "AdminSearchResultData";
}
>>>>>>> 3a5b23362e15d382589ba9ebe11d0cd0a0c77f68
