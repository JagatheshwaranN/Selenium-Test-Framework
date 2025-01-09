package com.qa.stf.app.pages;

import com.qa.stf.app.elements.TimesheetPageElement;
import com.qa.stf.base.DriverManager;

import static com.qa.stf.app.constant.AppConstants.*;


public class TimesheetPage extends TimesheetPageElement {

    PageManager pageManager = new PageManager();

    public TimesheetPage(DriverManager driverManager) {
        super(driverManager);
    }

    public String verifyTimeSheetPageHeader() {
        pageManager.getWaitHandler().waitForPresenceOfElements(getTimesheetPageLoadCheck(), getTimesheetPageLoadCheckLabel());
        return pageManager.getPageComponent().getPageHeader(getTimesheetHeader(), getTimesheetHeaderLabel());
    }

    public void navigateToPunchInOutSection() {
        pageManager.getDropDownHandler().selectDropdownOption(getAttendanceTopNavDropDown(), getAttendanceTopNavDropDownLayout(), getAttendanceTopNavDropDownOptions(), "Punch In/Out", getAttendanceTopNavDropDownLabel());
    }

    public void addPunchInDetail() {
        pageManager.getWaitHandler().waitForDOMToBeStable();
        pageManager.getWaitHandler().waitForPresenceOfElements(getPunchInSectionLoadCheck(), getPunchInSectionLoadCheckLabel());
        pageManager.getVerificationHelper().isTextEqualTo(getPunchInOutSectionHeader(), TIMESHEET_PAGE_PUNCH_IN_HEADER, getPunchInOutSectionHeaderLabel());
        pageManager.getDatePickerHandler().selectDateFromDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                "11", "January", "2025", getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getTimePicker(), getTimePickerLabel());
        pageManager.getInteractionHandler().clearElement(getTimePickerHourInput(), getTimePickerHourInputLabel());
        pageManager.getPageComponent().typeText(getTimePickerHourInput(), "08", getTimePickerHourInputLabel());
        pageManager.getPageComponent().clickElement(getTimePickerPMInput(), getTimePickerPMInputLabel());
        pageManager.getPageComponent().typeText(getNoteSection(), "Attendance", getNoteSectionLabel());
        pageManager.getPageComponent().clickElement(getSubmitButton(), getSubmitButtonLabel());
    }

    public void addPunchOutDetail() {
        pageManager.getWaitHandler().waitForDOMToBeStable();
        pageManager.getWaitHandler().waitForPresenceOfElements(getPunchOutSectionLoadCheck(), getPunchOutSectionLoadCheckLabel());
        pageManager.getVerificationHelper().isTextEqualTo(getPunchInOutSectionHeader(), TIMESHEET_PAGE_PUNCH_OUT_HEADER, getPunchInOutSectionHeaderLabel());
        pageManager.getDatePickerHandler().selectDateFromDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                "11", "January", "2025", getDatePickerDetailSectionLabel());
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
        pageManager.getWaitHandler().waitForPresenceOfElements(getEmployeeAttendanceSectionLoadCheck(), getEmployeeAttendanceSectionLoadCheckLabel());
        pageManager.getVerificationHelper().isElementDisplayed(getEmployeeAttendanceRecordsHeader(), getEmployeeAttendanceRecordsHeaderLabel());
        pageManager.getPageComponent().typeTextInSequence(getEmployeeNameInput(), userName, getEmployeeNameInputLabel());
        pageManager.getVerificationHelper().isElementDisplayed(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        pageManager.getPageComponent().clickElement(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        pageManager.getDatePickerHandler().selectDateFromDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                "11", "January", "2025", getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getViewButton(), getViewButtonLabel());
        // Temp check
        pageManager.getWaitHandler().waitForSeconds();
        return pageManager.getVerificationHelper().isElementDisplayed(getEmployeeResultSection(), getEmployeeResultSectionLabel());
    }

}
