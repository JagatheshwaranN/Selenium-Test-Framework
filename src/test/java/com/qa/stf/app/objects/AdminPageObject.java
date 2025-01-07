package com.qa.stf.app.objects;

import java.util.List;

import com.qa.stf.base.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPageObject {

    public AdminPageObject(DriverManager driverManager) {
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

    @FindBy(xpath = "//div[@role='listbox']")
    protected WebElement adminSearchDropDownLayout;
    protected String adminSearchDropDownLayoutLabel = "AdminSearchDropDownLayout";

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

