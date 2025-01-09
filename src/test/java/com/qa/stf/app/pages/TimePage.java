package com.qa.stf.app.pages;

import com.qa.stf.app.elements.TimePageElement;
import com.qa.stf.base.DriverManager;

import static com.qa.stf.app.constant.AppConstants.*;


public class TimePage extends TimePageElement {

    PageManager pageManager = new PageManager();

    public TimePage(DriverManager driverManager) {
        super(driverManager);
    }

    public boolean verifyTimeSheetPageHeader() {
        return pageManager.getVerificationHelper().isElementDisplayed(getTimeSheetHeader(), getTimeSheetHeaderLabel());
    }

    public void navigateToPunchInOutSection() {
        pageManager.getDropDownHandler().selectDropdownOption(getAttendanceTopNavDropDown(), getAttendanceTopNavDropDownLayout(), getAttendanceTopNavDropDownOptions(), "Punch In/Out", getAttendanceTopNavDropDownLabel());
    }

    public void addPunchInDetail() {
        pageManager.getVerificationHelper().isTextEqualTo(getPunchInOutSectionHeader(), TIMESHEET_PAGE_PUNCH_IN_HEADER, getPunchInOutSectionHeaderLabel());
        pageManager.getWaitHandler().waitForDOMToBeStable();
        pageManager.getDatePickerHandler().selectDateFromDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                "14", "February", "2025", getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getTimePicker(), getTimePickerLabel());
        pageManager.getInteractionHandler().clearElement(getTimePickerHourInput(), getTimePickerHourInputLabel());
        pageManager.getPageComponent().typeText(getTimePickerHourInput(), "08", getTimePickerHourInputLabel());
        pageManager.getPageComponent().clickElement(getTimePickerPMInput(), getTimePickerPMInputLabel());
        pageManager.getPageComponent().typeText(getNoteSection(), "Attendance", getNoteSectionLabel());
        pageManager.getPageComponent().clickElement(getSubmitButton(), getSubmitButtonLabel());
    }

    public void addPunchOutDetail() {
        pageManager.getVerificationHelper().isTextEqualTo(getPunchInOutSectionHeader(), TIMESHEET_PAGE_PUNCH_OUT_HEADER, getPunchInOutSectionHeaderLabel());
        pageManager.getWaitHandler().waitForDOMToBeStable();
        pageManager.getWaitHandler().waitForSeconds();
        pageManager.getDatePickerHandler().selectDateFromDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                "14", "February", "2025", getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getTimePicker(), getTimePickerLabel());
        pageManager.getInteractionHandler().clearElement(getTimePickerHourInput(), getTimePickerHourInputLabel());
        pageManager.getPageComponent().typeText(getTimePickerHourInput(), "09", getTimePickerHourInputLabel());
        pageManager.getPageComponent().clickElement(getTimePickerPMInput(), getTimePickerPMInputLabel());
        pageManager.getPageComponent().typeText(getNoteSection(), "Attendance", getNoteSectionLabel());
        pageManager.getPageComponent().clickElement(getSubmitButton(), getSubmitButtonLabel());
        pageManager.getWaitHandler().waitForDOMToBeStable();
        pageManager.getWaitHandler().waitForSeconds();
    }

    public void navigateToEmployeeRecordsSection() {
        pageManager.getDropDownHandler().selectDropdownOption(getAttendanceTopNavDropDown(), getAttendanceTopNavDropDownLayout(), getAttendanceTopNavDropDownOptions(), "Employee Records", getAttendanceTopNavDropDownLabel());
    }

    public boolean searchEmployeeAttendanceRecord(String userName) {
        pageManager.getWaitHandler().waitForDOMToBeStable();
        pageManager.getVerificationHelper().isElementDisplayed(getEmployeeAttendanceRecordsHeader(), getEmployeeAttendanceRecordsHeaderLabel());
        pageManager.getPageComponent().typeText(getEmployeeNameInput(), userName, getEmployeeNameInputLabel());
        pageManager.getVerificationHelper().isElementDisplayed(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        pageManager.getPageComponent().clickElement(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        pageManager.getDatePickerHandler().selectDateFromDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                "13", "February", "2025", getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getViewButton(), getViewButtonLabel());
        return pageManager.getVerificationHelper().isElementDisplayed(getEmployeeResultSection(), getEmployeeResultSectionLabel());
    }

}
