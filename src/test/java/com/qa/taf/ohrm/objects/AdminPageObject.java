package com.qa.taf.ohrm.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPageObject {

	@FindBy(css = ".oxd-topbar-header-breadcrumb")
	public WebElement adminHeader;
	public String adminHeaderLabel = "AdminHeader";

	@FindBy(css = ".oxd-form .oxd-input.oxd-input--active")
	public WebElement adminSearchUserName;
	public String adminSearchUserNameLabel = "AdminSearchUserName";

	@FindBy(xpath = "(//div[contains(@class,'oxd-select-text--after')])[1]")
	public WebElement adminUserRoleDropDown;
	public String adminUserRoleDropDownLabel = "AdminUserRoleDropDown";

	@FindBy(xpath = "//div[@role='listbox']//div[@role='option']//span")
	public List<WebElement> adminUserRoleDropDownOptions;
	public String adminUserRoleDropDownOptionsLabel = "AdminUserRoleDropDownOptions";

	@FindBy(xpath = "(//div[contains(@class,'oxd-select-text--after')])[2]")
	public WebElement adminUserStatusDropDown;
	public String adminUserStatusDropDownLabel = "AdminUserStatusDropDown";

	@FindBy(xpath = "//div[@role='listbox']//div[@role='option']//span")
	public List<WebElement> adminUserStatusDropDownOptions;
	public String adminUserStatusDropDownOptionsLabel = "AdminUserStatusDropDownOptions";

	@FindBy(xpath = "//button[contains(@class,'oxd-button--secondary orangehrm-left-space')]")
	public WebElement adminSearch;
	public String adminSearchLabel = "AdminSearch";

	@FindBy(css = ".oxd-table-card")
	public WebElement adminSearchResultSection;
	public String adminSearchResultSectionLabel = "AdminSearchResultSection";

	@FindBy(css = ".oxd-table-cell.oxd-padding-cell:nth-child(2)")
	public WebElement adminSearchResultData;
	public String adminSearchResultDataLabel = "AdminSearchResultData";

	
	

}
