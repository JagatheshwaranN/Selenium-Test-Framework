<<<<<<< HEAD
package com.qa.taf.ohrm.pages;

import com.qa.taf.ohrm.elements.AdminPageElement;

public class AdminPage extends AdminPageElement {

	PageManager pageManager = new PageManager();

	public void searchUser(String user, String status) {

		pageManager.getVerificationHelper().verifyElementPresent(getAdminHeader(), getAdminHeaderLabel());
		typeElement(getAdminSearchUserName(), user, getAdminSearchUserNameLabel());
		pageManager.getDropDownHelper().elementSelect(getAdminUserRoleDropDown(), getAdminUserRoleDropDownOptions(),
				user, getAdminUserRoleDropDownLabel());
		pageManager.getDropDownHelper().elementSelect(getAdminUserStatusDropDown(), getAdminUserStatusDropDownOptions(),
				status, getAdminUserStatusDropDownLabel());
		clickElement(getAdminSearch(), getAdminSearchLabel());
		waitForElementVisible(getAdminSearchResultSection(), getAdminSearchResultSectionLabel());
		pageManager.getVerificationHelper().verifyElementPresent(getAdminSearchResultSection(),
				getAdminSearchResultSectionLabel());
		pageManager.getVerificationHelper().verifyElementPresent(getAdminSearchResultData(),
				getAdminSearchResultDataLabel());
	}
}
=======
package com.qa.taf.ohrm.pages;

import com.qa.taf.ohrm.elements.AdminPageElement;

public class AdminPage extends AdminPageElement {

	PageManager pageManager = new PageManager();

	public void searchUser(String user, String status) {

		pageManager.getVerificationHelper().verifyElementPresent(getAdminHeader(), getAdminHeaderLabel());
		typeElement(getAdminSearchUserName(), user, getAdminSearchUserNameLabel());
		pageManager.getDropDownHelper().elementSelect(getAdminUserRoleDropDown(), getAdminUserRoleDropDownOptions(),
				user, getAdminUserRoleDropDownLabel());
		pageManager.getDropDownHelper().elementSelect(getAdminUserStatusDropDown(), getAdminUserStatusDropDownOptions(),
				status, getAdminUserStatusDropDownLabel());
		clickElement(getAdminSearch(), getAdminSearchLabel());
		waitForElementVisible(getAdminSearchResultSection(), getAdminSearchResultSectionLabel());
		pageManager.getVerificationHelper().verifyElementPresent(getAdminSearchResultSection(),
				getAdminSearchResultSectionLabel());
		pageManager.getVerificationHelper().verifyElementPresent(getAdminSearchResultData(),
				getAdminSearchResultDataLabel());
	}
}
>>>>>>> 3a5b23362e15d382589ba9ebe11d0cd0a0c77f68
