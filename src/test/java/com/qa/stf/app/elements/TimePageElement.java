package com.qa.stf.app.elements;

import com.qa.stf.app.objects.TimePageObjects;
import com.qa.stf.base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TimePageElement extends TimePageObjects {

    public TimePageElement(DriverManager driverManager) {
        super(driverManager);
    }

    public WebElement getTimeSheetHeader() {
        return timeSheetHeader;
    }

    public String getTimeSheetHeaderLabel() {
        return timeSheetHeaderLabel;
    }

    public WebElement getAttendanceTopNavDropDown() {
        return attendanceTopNavDropDown;
    }

    public String getAttendanceTopNavDropDownLabel() {
        return attendanceTopNavDropDownLabel;
    }

    public WebElement getAttendanceTopNavDropDownOptions() {
        return attendanceTopNavDropDownOptions;
    }

    public String getAttendanceTopNavDropDownOptionsLabel() {
        return attendanceTopNavDropDownOptionsLabel;
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

}
