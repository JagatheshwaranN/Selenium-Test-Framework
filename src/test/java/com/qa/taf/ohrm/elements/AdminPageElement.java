package com.qa.taf.ohrm.elements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.taf.ohrm.objects.AdminPageObject;

public class AdminPageElement extends AdminPageObject {

	public AdminPageObject adminPageObject;

	public AdminPageElement() {

		this.adminPageObject = new AdminPageObject();
		PageFactory.initElements(getDriver(), this.adminPageObject);
	}

	public WebElement getAdminHeader() {
		return adminPageObject.adminHeader;
	}

	public String getAdminHeaderLabel() {
		return adminPageObject.adminHeaderLabel;
	}

	public WebElement getAdminSearchUserName() {
		return adminPageObject.adminSearchUserName;
	}

	public String getAdminSearchUserNameLabel() {
		return adminPageObject.adminSearchUserNameLabel;
	}

	public WebElement getAdminUserRoleDropDown() {
		return adminPageObject.adminUserRoleDropDown;
	}

	public String getAdminUserRoleDropDownLabel() {
		return adminPageObject.adminUserRoleDropDownLabel;
	}

	public List<WebElement> getAdminUserRoleDropDownOptions() {
		return adminPageObject.adminUserRoleDropDownOptions;
	}

	public String getAdminUserRoleDropDownOptionsLabel() {
		return adminPageObject.adminUserRoleDropDownOptionsLabel;
	}

	public WebElement getAdminUserStatusDropDown() {
		return adminPageObject.adminUserStatusDropDown;
	}

	public String getAdminUserStatusDropDownLabel() {
		return adminPageObject.adminUserStatusDropDownLabel;
	}

	public List<WebElement> getAdminUserStatusDropDownOptions() {
		return adminPageObject.adminUserStatusDropDownOptions;
	}

	public String getAdminUserStatusDropDownOptionsLabel() {
		return adminPageObject.adminUserStatusDropDownOptionsLabel;
	}

	public WebElement getAdminSearch() {
		return adminPageObject.adminSearch;
	}

	public String getAdminSearchLabel() {
		return adminPageObject.adminSearchLabel;
	}

	public WebElement getAdminSearchResultSection() {
		return adminPageObject.adminSearchResultSection;
	}

	public String getAdminSearchResultSectionLabel() {
		return adminPageObject.adminSearchResultSectionLabel;
	}

	public WebElement getAdminSearchResultData() {
		return adminPageObject.adminSearchResultData;
	}

	public String getAdminSearchResultDataLabel() {
		return adminPageObject.adminSearchResultDataLabel;
	}
}
