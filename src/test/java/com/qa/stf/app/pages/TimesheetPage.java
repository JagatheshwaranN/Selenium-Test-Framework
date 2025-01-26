package com.qa.stf.app.pages;

import com.qa.stf.app.elements.TimesheetPageElement;
import com.qa.stf.base.BasePage;
import com.qa.stf.base.DriverManager;
import com.qa.stf.handler.*;

import static com.qa.stf.constant.TestConstants.*;
import static com.qa.stf.app.constant.AppConstants.*;


public class TimesheetPage extends TimesheetPageElement {

    PageManager pageManager = new PageManager();
    BasePage basePage = pageManager.getPageComponent();
    DateTimeHandler dateTimeHandler = pageManager.getDatePickerHandler();
    DropDownHandler dropDownHandler = pageManager.getDropDownHandler();
    InteractionHandler interactionHandler = pageManager.getInteractionHandler();
    VerificationHandler verificationHandler = pageManager.getVerificationHelper();
    WaitHandler waitHandler = pageManager.getWaitHandler();

    public TimesheetPage(DriverManager driverManager) {
        super(driverManager);
    }

    private final String day = pageManager.getDatePickerHandler().getDay();
    private final String month = pageManager.getDatePickerHandler().getMonth(MONTH_FORMAT);
    private final String year = pageManager.getDatePickerHandler().getYear();
    private String timeInHour = pageManager.getDatePickerHandler().getCustomizedTime(TIME_HR_FORMAT);

    public String verifyTimeSheetPageHeader() {
        waitHandler.waitForPresenceOfElements(getTimesheetPageLoadCheck(), getTimesheetPageLoadCheckLabel());
        return basePage.getPageHeader(getTimesheetHeader(), getTimesheetHeaderLabel());
    }

    public void navigateToPunchInOutSection() {
        dropDownHandler.selectDropdownOption(getAttendanceTopNavDropDown(), getAttendanceTopNavDropDownLayout(), getAttendanceTopNavDropDownOptions(), "Punch In/Out", getAttendanceTopNavDropDownLabel());
    }

    public void addPunchInDetail(String notes) {
        waitHandler.waitForDOMToBeStable();
        waitHandler.waitForPresenceOfElements(getPunchInSectionLoadCheck(), getPunchInSectionLoadCheckLabel());
        verificationHandler.isTextEqualTo(getPunchInOutSectionHeader(), TIMESHEET_PAGE_PUNCH_IN_HEADER, getPunchInOutSectionHeaderLabel());
        waitHandler.waitForPresenceOfElements(getPunchInOutLayoutLoadCheck(), getPunchInOutLayoutLoadCheckLabel());
        dateTimeHandler.selectDateFromSingleDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                day, month, year, getDatePickerDetailSectionLabel());
        basePage.clickElement(getTimePicker(), getTimePickerLabel());
        interactionHandler.clearElement(getTimePickerHourInput(), getTimePickerHourInputLabel());
        basePage.typeText(getTimePickerHourInput(), timeInHour, getTimePickerHourInputLabel());
        basePage.clickElement(getTimePickerAMInput(), getTimePickerAMInputLabel());
        basePage.typeText(getNoteSection(), notes, getNoteSectionLabel());
        basePage.clickElement(getSubmitButton(), getSubmitButtonLabel());
    }

    public void addPunchOutDetail(String notes) {
        waitHandler.waitForPageToLoad();
        waitHandler.waitForPresenceOfElements(getPunchOutSectionLoadCheck(), getPunchOutSectionLoadCheckLabel());
        verificationHandler.isTextEqualTo(getPunchInOutSectionHeader(), TIMESHEET_PAGE_PUNCH_OUT_HEADER, getPunchInOutSectionHeaderLabel());
        waitHandler.waitForPresenceOfElements(getPunchInOutLayoutLoadCheck(), getPunchInOutLayoutLoadCheckLabel());
        //pageManager.getWaitHandler().waitForDOMToBeStable();
        dateTimeHandler.selectDateFromSingleDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                day, month, year, getDatePickerDetailSectionLabel());
        basePage.clickElement(getTimePicker(), getTimePickerLabel());
        interactionHandler.clearElement(getTimePickerHourInput(), getTimePickerHourInputLabel());
        timeInHour = String.valueOf(Integer.parseInt(timeInHour) + 1);
        basePage.typeText(getTimePickerHourInput(), timeInHour, getTimePickerHourInputLabel());
        basePage.clickElement(getTimePickerPMInput(), getTimePickerPMInputLabel());
        basePage.typeText(getNoteSection(), notes, getNoteSectionLabel());
        basePage.clickElement(getSubmitButton(), getSubmitButtonLabel());
        waitHandler.waitForPageToLoad();
    }

    public void navigateToEmployeeRecordsSection() {
        dropDownHandler.selectDropdownOption(getAttendanceTopNavDropDown(), getAttendanceTopNavDropDownLayout(), getAttendanceTopNavDropDownOptions(), "Employee Records", getAttendanceTopNavDropDownLabel());
    }

    public boolean searchEmployeeAttendanceRecord(String userName) {
        waitHandler.waitForDOMToBeStable();
        waitHandler.waitForPresenceOfElements(getEmployeeAttendanceSectionLoadCheck(), getEmployeeAttendanceSectionLoadCheckLabel());
        verificationHandler.isElementDisplayed(getEmployeeAttendanceRecordsHeader(), getEmployeeAttendanceRecordsHeaderLabel());
        basePage.typeTextInSequence(getEmployeeNameInput(), userName, getEmployeeNameInputLabel());
        waitHandler.waitForElementVisible(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        verificationHandler.isElementDisplayed(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        basePage.clickElement(getEmployeeNameSuggestion(), getEmployeeNameSuggestionLabel());
        dateTimeHandler.selectDateFromSingleDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                day, month, year, getDatePickerDetailSectionLabel());
        basePage.clickElement(getViewButton(), getViewButtonLabel());
        // Temp check
        waitHandler.waitForSeconds();
        return verificationHandler.isElementDisplayed(getEmployeeResultSection(), getEmployeeResultSectionLabel());
    }

}
