package com.qa.stf.app.elements;

import com.qa.stf.app.objects.TimesheetPageObject;
import com.qa.stf.base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TimesheetPageElement extends TimesheetPageObject {

    public TimesheetPageElement(DriverManager driverManager) {
        super(driverManager);
    }

    public By getTimesheetPageLoadCheck() {
        return timesheetPageLoadCheck;
    }

    public String getTimesheetPageLoadCheckLabel() {
        return timesheetPageLoadCheckLabel;
    }

    public WebElement getTimesheetHeader() {
        return timesheetHeader;
    }

    public String getTimesheetHeaderLabel() {
        return timesheetHeaderLabel;
    }

    public WebElement getAttendanceTopNavDropDown() {
        return attendanceTopNavDropDown;
    }

    public String getAttendanceTopNavDropDownLabel() {
        return attendanceTopNavDropDownLabel;
    }

    public WebElement getAttendanceTopNavDropDownLayout() {
        return attendanceTopNavDropDownLayout;
    }

    public String getAttendanceTopNavDropDownLayoutLabel() {
        return attendanceTopNavDropDownLayoutLabel;
    }

    public List<WebElement> getAttendanceTopNavDropDownOptions() {
        return attendanceTopNavDropDownOptions;
    }

    public String getAttendanceTopNavDropDownOptionsLabel() {
        return attendanceTopNavDropDownOptionsLabel;
    }

    public By getPunchInSectionLoadCheck() {
        return punchInSectionLoadCheck;
    }

    public String getPunchInSectionLoadCheckLabel() {
        return punchInSectionLoadCheckLabel;
    }

    public By getPunchOutSectionLoadCheck() {
        return punchOutSectionLoadCheck;
    }

    public String getPunchOutSectionLoadCheckLabel() {
        return punchOutSectionLoadCheckLabel;
    }

    public WebElement getPunchInOutSectionHeader() {
        return punchInOutSectionHeader;
    }

    public String getPunchInOutSectionHeaderLabel() {
        return punchInOutSectionHeaderLabel;
    }

    public WebElement getDatePicker() {
        return datePicker;
    }

    public String getDatePickerLabel() {
        return datePickerLabel;
    }

    public By getDatePickerLocator() {
        return datePickerLocator;
    }

    public WebElement getDatePickerDetailSection() {
        return datePickerDetailSection;
    }

    public String getDatePickerDetailSectionLabel() {
        return datePickerDetailSectionLabel;
    }

    public WebElement getMonthDetail() {
        return monthDetail;
    }

    public String getMonthDetailLabel() {
        return monthDetailLabel;
    }

    public WebElement getYearDetail() {
        return yearDetail;
    }

    public String getYearDetailLabel() {
        return yearDetailLabel;
    }

    public WebElement getMonthNavigator() {
        return monthNavigator;
    }

    public String getMonthNavigatorLabel() {
        return monthNavigatorLabel;
    }

    public By getDayOfMonth() {
        return dayOfMonth;
    }

    public WebElement getTimePicker() {
        return timePicker;
    }

    public String getTimePickerLabel() {
        return timePickerLabel;
    }

    public WebElement getTimePickerDetailSection() {
        return timePickerDetailSection;
    }

    public String getTimePickerDetailSectionLabel() {
        return timePickerDetailSectionLabel;
    }

    public WebElement getTimePickerHourInput() {
        return timePickerHourInput;
    }

    public String getTimePickerHourInputLabel() {
        return timePickerHourInputLabel;
    }

    public WebElement getTimePickerHourInputFocus() {
        return timePickerHourInputFocus;
    }

    public String getTimePickerHourInputFocusLabel() {
        return timePickerHourInputFocusLabel;
    }

    public WebElement getTimePickerAMInput() {
        return timePickerAMInput;
    }

    public String getTimePickerAMInputLabel() {
        return timePickerAMInputLabel;
    }

    public WebElement getTimePickerPMInput() {
        return timePickerPMInput;
    }

    public String getTimePickerPMInputLabel() {
        return timePickerPMInputLabel;
    }

    public WebElement getNoteSection() {
        return noteSection;
    }

    public String getNoteSectionLabel() {
        return noteSectionLabel;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public String getSubmitButtonLabel() {
        return submitButtonLabel;
    }

    public By getEmployeeAttendanceSectionLoadCheck() {
        return employeeAttendanceSectionLoadCheck;
    }

    public String getEmployeeAttendanceSectionLoadCheckLabel() {
        return employeeAttendanceSectionLoadCheckLabel;
    }

    public WebElement getEmployeeAttendanceRecordsHeader() {
        return employeeAttendanceRecordsHeader;
    }

    public String getEmployeeAttendanceRecordsHeaderLabel() {
        return employeeAttendanceRecordsHeaderLabel;
    }

    public WebElement getEmployeeNameInput() {
        return employeeNameInput;
    }

    public String getEmployeeNameInputLabel() {
        return employeeNameInputLabel;
    }

    public WebElement getEmployeeNameSuggestion() {
        return employeeNameSuggestion;
    }

    public String getEmployeeNameSuggestionLabel() {
        return employeeNameSuggestionLabel;
    }

    public WebElement getViewButton() {
        return viewButton;
    }

    public String getViewButtonLabel() {
        return viewButtonLabel;
    }

    public WebElement getEmployeeResultSection() {
        return employeeResultSection;
    }

    public String getEmployeeResultSectionLabel() {
        return employeeResultSectionLabel;
    }

    public WebElement getLoadSpinner() {
        return loadSpinner;
    }

    public String getLoadSpinnerLabel() {
        return loadSpinnerLabel;
    }

}
