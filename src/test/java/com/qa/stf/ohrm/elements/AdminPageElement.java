package com.qa.stf.ohrm.elements;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.qa.stf.ohrm.objects.AdminPageObject;

public class AdminPageElement extends AdminPageObject {

	public WebElement getAdminHeader() {
		return adminHeader;
	}

	public String getAdminHeaderLabel() {
		return adminHeaderLabel;
	}

	public WebElement getAdminSearchUserName() {
		return adminSearchUserName;
	}

	public String getAdminSearchUserNameLabel() {
		return adminSearchUserNameLabel;
	}

	public WebElement getAdminUserRoleDropDown() {
		return adminUserRoleDropDown;
	}

	public String getAdminUserRoleDropDownLabel() {
		return adminUserRoleDropDownLabel;
	}

	public List<WebElement> getAdminUserRoleDropDownOptions() {
		return adminUserRoleDropDownOptions;
	}

	public String getAdminUserRoleDropDownOptionsLabel() {
		return adminUserRoleDropDownOptionsLabel;
	}

	public WebElement getAdminUserStatusDropDown() {
		return adminUserStatusDropDown;
	}

	public String getAdminUserStatusDropDownLabel() {
		return adminUserStatusDropDownLabel;
	}

	public List<WebElement> getAdminUserStatusDropDownOptions() {
		return adminUserStatusDropDownOptions;
	}

	public String getAdminUserStatusDropDownOptionsLabel() {
		return adminUserStatusDropDownOptionsLabel;
	}

	public WebElement getAdminSearch() {
		return adminSearch;
	}

	public String getAdminSearchLabel() {
		return adminSearchLabel;
	}

	public WebElement getAdminSearchResultSection() {
		return adminSearchResultSection;
	}

	public String getAdminSearchResultSectionLabel() {
		return adminSearchResultSectionLabel;
	}

	public WebElement getAdminSearchResultData() {
		return adminSearchResultData;
	}

	public String getAdminSearchResultDataLabel() {
		return adminSearchResultDataLabel;
	}
}

