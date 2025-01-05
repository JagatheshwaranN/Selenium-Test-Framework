package com.qa.stf.app.objects;

import com.qa.stf.base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimePageObjects {

    public TimePageObjects(DriverManager driverManager) {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='oxd-topbar-header-title']//span")
    protected WebElement timeSheetHeader;
    protected String timeSheetHeaderLabel = "TimeSheets Header";

    @FindBy(xpath = "//nav[@class='oxd-topbar-body-nav']//li//span[contains(text(),'Attendance')]")
    protected WebElement attendanceTopNavDropDown;
    protected String attendanceTopNavDropDownLabel = "Attendance Dropdown";

    @FindBy(xpath = "//ul[@class='oxd-dropdown-menu']")
    protected WebElement attendanceTopNavDropDownOptions;
    protected String attendanceTopNavDropDownOptionsLabel = "Attendance Dropdown Option";

    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")
    protected WebElement punchInOutSectionHeader;
    protected String punchInOutSectionHeaderLabel = "Punch In / Out Header";

    @FindBy(xpath = "//div[@class='oxd-date-input']")
    protected WebElement datePicker;
    protected String datePickerLabel = "Date Input";

    @FindBy(xpath = "//div[@class='oxd-date-input-calendar']")
    protected WebElement datePickerDetailSection;
    protected String datePickerDetailSectionLabel = "Date Details Section";

    @FindBy(xpath = "//li[@class='oxd-calendar-selector-month']")
    protected WebElement monthDetail;
    protected String monthDetailLabel = "Month Detail";

    @FindBy(xpath = "//li[@class='oxd-calendar-selector-year']")
    protected WebElement yearDetail;
    protected String yearDetailLabel = "Year Detail";

    @FindBy(xpath = "//button[@class='oxd-icon-button']//i[@class='oxd-icon bi-chevron-right']")
    protected WebElement monthNavigator;
    protected String monthNavigatorLabel = "Month Navigator";

    protected By dayOfMonth = By.xpath("//div[contains(@class,'oxd-calendar-date-wrapper')]//div[contains(@class,'oxd-calendar-date')][text()='%s']");

    @FindBy(xpath = "//div[@class='oxd-time-input']")
    protected WebElement timePicker;
    protected String timePickerLabel = "Time Input";

    @FindBy(xpath = "//div[@class='oxd-time-picker']")
    protected WebElement timePickerDetailSection;
    protected String timePickerDetailSectionLabel = "Time Details Section";

    @FindBy(xpath = "//input[contains(@class,'oxd-time-hour-input-text')]")
    protected WebElement timePickerHourInput;
    protected String timePickerHourInputLabel = "Hour Input";

    @FindBy(xpath = "//input[@name='am']")
    protected WebElement timePickerAMInput;
    protected String timePickerAMInputLabel = "AM Button";

    @FindBy(xpath = "//input[@name='pm']")
    protected WebElement timePickerPMInput;
    protected String timePickerPMInputLabel = "PM Button";

    @FindBy(xpath = "//textarea[@placeholder='Type here']")
    protected WebElement noteSection;
    protected String noteSectionLabel = "Note Section";

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    protected WebElement submitButton;
    protected String submitButtonLabel = "PunchIn/Out Button";

    @FindBy(xpath = "//h5[@class='oxd-text oxd-text--h5 oxd-table-filter-title']")
    protected WebElement employeeAttendanceRecordsHeader;
    protected String employeeAttendanceRecordsHeaderLabel = "Employee Attendance Records Header";

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    protected WebElement employeeNameInput;
    protected String employeeNameInputLabel = "Employee Name";

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement viewButton;
    protected String viewButtonLabel = "View Button";

    @FindBy(xpath = "//div[@class='orangehrm-container']//div[@class='oxd-table']")
    protected WebElement employeeResultSection;
    protected String employeeResultSectionLabel = "Employee Result Section";

}
