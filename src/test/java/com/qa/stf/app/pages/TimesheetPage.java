package com.qa.stf.app.pages;

import com.qa.stf.app.elements.TimesheetPageElement;
import com.qa.stf.base.DriverManager;

import static com.qa.stf.constant.TestConstants.*;
import static com.qa.stf.app.constant.AppConstants.*;


public class TimesheetPage extends TimesheetPageElement {

    PageManager pageManager = new PageManager();

    public TimesheetPage(DriverManager driverManager) {
        super(driverManager);
    }

    private final String day = pageManager.getDatePickerHandler().getDay();
    private final String month = pageManager.getDatePickerHandler().getMonth(MONTH_FORMAT);
    private final String year = pageManager.getDatePickerHandler().getYear();
    private String timeInHour = pageManager.getDatePickerHandler().getTimeHour(TIME_HR_FORMAT);

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
        pageManager.getWaitHandler().waitForPresenceOfElements(getPunchInOutLayoutLoadCheck(), getPunchInOutLayoutLoadCheckLabel());
        pageManager.getDatePickerHandler().selectDateFromSingleDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                day, month, year, getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getTimePicker(), getTimePickerLabel());
        pageManager.getInteractionHandler().clearElement(getTimePickerHourInput(), getTimePickerHourInputLabel());
        pageManager.getPageComponent().typeText(getTimePickerHourInput(), timeInHour, getTimePickerHourInputLabel());
        pageManager.getPageComponent().clickElement(getTimePickerAMInput(), getTimePickerAMInputLabel());
        pageManager.getPageComponent().typeText(getNoteSection(), "Attendance", getNoteSectionLabel());
        pageManager.getPageComponent().clickElement(getSubmitButton(), getSubmitButtonLabel());
    }

    public void addPunchOutDetail() {
        pageManager.getWaitHandler().waitForPageToLoad();
        pageManager.getWaitHandler().waitForPresenceOfElements(getPunchOutSectionLoadCheck(), getPunchOutSectionLoadCheckLabel());
        pageManager.getVerificationHelper().isTextEqualTo(getPunchInOutSectionHeader(), TIMESHEET_PAGE_PUNCH_OUT_HEADER, getPunchInOutSectionHeaderLabel());
        pageManager.getWaitHandler().waitForPresenceOfElements(getPunchInOutLayoutLoadCheck(), getPunchInOutLayoutLoadCheckLabel());
        //pageManager.getWaitHandler().waitForDOMToBeStable();
        pageManager.getDatePickerHandler().selectDateFromSingleDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                day, month, year, getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getTimePicker(), getTimePickerLabel());
        pageManager.getInteractionHandler().clearElement(getTimePickerHourInput(), getTimePickerHourInputLabel());
        timeInHour = String.valueOf(Integer.parseInt(timeInHour) + 1);
        pageManager.getPageComponent().typeText(getTimePickerHourInput(), timeInHour, getTimePickerHourInputLabel());
        pageManager.getPageComponent().clickElement(getTimePickerPMInput(), getTimePickerPMInputLabel());
        pageManager.getPageComponent().typeText(getNoteSection(), "Attendance", getNoteSectionLabel());
        pageManager.getPageComponent().clickElement(getSubmitButton(), getSubmitButtonLabel());
        pageManager.getWaitHandler().waitForPageToLoad();
    }

    public void navigateToEmployeeRecordsSection() {
        pageManager.getDropDownHandler().selectDropdownOption(getAttendanceTopNavDropDown(), getAttendanceTopNavDropDownLayout(), getAttendanceTopNavDropDownOptions(), "Employee Records", getAttendanceTopNavDropDownLabel());
    }

    public boolean searchEmployeeAttendanceRecord(String userName) {
        pageManager.getWaitHandler().waitForDOMToBeStable();
        pageManager.getWaitHandler().waitForPresenceOfElements(getEmployeeAttendanceSectionLoadCheck(), getEmployeeAttendanceSectionLoadCheckLabel());
        pageManager.getVerificationHelper().isElementDisplayed(getEmployeeAttendanceRecordsHeader(), getEmployeeAttendanceRecordsHeaderLabel());
        pageManager.getPageComponent().typeTextInSequence(getEmployeeNameInput(), userName, getEmployeeNameInputLabel());
        pageManager.getWaitHandler().waitForElementVisible(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        pageManager.getVerificationHelper().isElementDisplayed(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        pageManager.getPageComponent().clickElement(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        pageManager.getDatePickerHandler().selectDateFromSingleDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                day, month, year, getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getViewButton(), getViewButtonLabel());
        // Temp check
        pageManager.getWaitHandler().waitForSeconds();
        return pageManager.getVerificationHelper().isElementDisplayed(getEmployeeResultSection(), getEmployeeResultSectionLabel());
    }

}
