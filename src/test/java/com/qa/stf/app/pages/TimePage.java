package com.qa.stf.app.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import com.qa.stf.app.elements.TimePageElement;
import com.qa.stf.base.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TimePage extends TimePageElement {

    PageManager pageManager = new PageManager();

    public TimePage(DriverManager driverManager) {
        super(driverManager);
    }

    public void verifyTimeSheetHeader() {
        pageManager.getVerificationHelper().isElementDisplayed(getTimeSheetHeader(), getTimeSheetHeaderLabel());
    }

    public void navigateToPunchInOutSection() {
        pageManager.getDropDownHandler().selectDropdownOption(getAttendanceTopNavDropDown(), getAttendanceTopNavDropDownLayout(), getAttendanceTopNavDropDownOptions(), "Punch In/Out", getAttendanceTopNavDropDownLabel());
    }

    public void addPunchInDetail() {
        pageManager.getVerificationHelper().isTextEqualTo(getPunchInOutSectionHeader(), "Punch In", getPunchInOutSectionHeaderLabel());
        pageManager.getPageComponent().waitForDOMToBeStable();
        pageManager.getDatePickerHandler().selectDateFromDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                "13", "February", "2025", getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getTimePicker(), getTimePickerLabel());
        pageManager.getInteractionHandler().clearElement(getTimePickerHourInput(), getTimePickerHourInputLabel());
        pageManager.getPageComponent().typeText(getTimePickerHourInput(), "08", getTimePickerHourInputLabel());
        pageManager.getPageComponent().clickElement(getTimePickerPMInput(), getTimePickerPMInputLabel());
        pageManager.getPageComponent().typeText(getNoteSection(), "Attendance", getNoteSectionLabel());
        pageManager.getPageComponent().clickElement(getSubmitButton(), getSubmitButtonLabel());
    }

    public void addPunchOutDetail() {
        pageManager.getVerificationHelper().isTextEqualTo(getPunchInOutSectionHeader(), "Punch Out", getPunchInOutSectionHeaderLabel());
        pageManager.getPageComponent().waitForDOMToBeStable();
        pageManager.getPageComponent().waitForSeconds();
        pageManager.getDatePickerHandler().selectDateFromDatePicker(getDatePicker(), getDatePickerDetailSection(),
                getMonthDetail(), getYearDetail(), getMonthNavigator(), getDayOfMonth(),
                "13", "February", "2025", getDatePickerDetailSectionLabel());
        pageManager.getPageComponent().clickElement(getTimePicker(), getTimePickerLabel());
        pageManager.getInteractionHandler().clearElement(getTimePickerHourInput(), getTimePickerHourInputLabel());
        pageManager.getPageComponent().typeText(getTimePickerHourInput(), "09", getTimePickerHourInputLabel());
        pageManager.getPageComponent().clickElement(getTimePickerPMInput(), getTimePickerPMInputLabel());
        pageManager.getPageComponent().typeText(getNoteSection(), "Attendance", getNoteSectionLabel());
        pageManager.getPageComponent().clickElement(getSubmitButton(), getSubmitButtonLabel());
        pageManager.getPageComponent().waitForDOMToBeStable();
        pageManager.getPageComponent().waitForSeconds();
    }

    public void navigateToEmployeeRecordsSection() {
        pageManager.getDropDownHandler().selectDropdownOption(getAttendanceTopNavDropDown(), getAttendanceTopNavDropDownLayout(), getAttendanceTopNavDropDownOptions(), "Employee Records", getAttendanceTopNavDropDownLabel());
    }
}
