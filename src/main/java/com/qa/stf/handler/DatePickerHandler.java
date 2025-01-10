package com.qa.stf.handler;

import com.qa.stf.base.DriverManager;
import com.qa.stf.report.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.*;
import java.time.format.DateTimeFormatter;

// version 1.3

public class DatePickerHandler {

    // Logger instance for the DropDownHandler class to enable logging during the execution
    private static final Logger log = LogManager.getLogger(DatePickerHandler.class);

    // Instance of DriverManager to manage the WebDriver for interacting with the browser
    private final DriverManager driverManager;

    // Instance of VerificationHandler to perform verification actions on dropdown elements
    private final VerificationHandler verificationHandler;

    // Instance of ExtentReportManager to manage the extent report
    protected ExtentReportManager extentReportManager;

    public DatePickerHandler(DriverManager driverManager, VerificationHandler verificationHandler) {
        if (driverManager == null) {
            throw new IllegalArgumentException("DriverManager cannot be null.");
        }
        this.driverManager = driverManager;
        this.verificationHandler = verificationHandler;
        extentReportManager = ExtentReportManager.getInstance();
    }

    public DatePickerHandler() {
        driverManager = null;
        verificationHandler = null;
    }

    public void selectDateFromSingleDatePicker(WebElement datePicker, WebElement dateDetailSection, WebElement monthDetail, WebElement yearDetail, WebElement monthNavigator, By dayLocator, String day, String month, String year, String dateDetailSectionLabel) {
        datePicker.click();
        verificationHandler.isElementDisplayed(dateDetailSection, dateDetailSectionLabel);
        String monthText = monthDetail.getText();
        String yearText = yearDetail.getText();
        while (!(monthText.equalsIgnoreCase(month) && yearText.equalsIgnoreCase(year))) {
            monthNavigator.click();
            monthText = monthDetail.getText();
            yearText = yearDetail.getText();
        }
        new InteractionHandler(driverManager, verificationHandler).clickElement(dayLocator, day, "Day");
    }

    public String getDate() {
        // YYYY-MM-dd - Default Format
        return LocalDate.now().toString();
    }

    public String getCustomizedDate(String pattern) {
        // dd-MM-yyyy - 01-01-2025
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return currentDate.format(dateTimeFormatter);
    }

    public String getDay() {
        LocalDate currentDate = LocalDate.now();
        return String.valueOf(currentDate.getDayOfMonth());
    }

    public String getMonth(String format) {
        // MMM - Jan & MMMM - January
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return currentDate.format(dateTimeFormatter);
    }

    public String getYear() {
        LocalDate currentDate = LocalDate.now();
        return String.valueOf(currentDate.getYear());
    }
    public String getTime() {
        return LocalTime.now().toString();
    }

    public String getCustomizedTime(String pattern) {
        // hh:mm:ss a - 09:10:40 AM
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return currentTime.format(dateTimeFormatter);
    }

    public String getTimeHour(String pattern) {
        return getCustomizedTime(pattern);
    }

    public String getDateTime() {
        return LocalDateTime.now().toString();
    }

    public String getZonedDateTime() {
        return ZonedDateTime.now().toString();
    }

    public String getTimeStamp() {
        return Instant.now().toString();
    }

    public static void main(String[] args) {
        DatePickerHandler datePickerHandler = new DatePickerHandler();
        System.out.println(datePickerHandler.getDate());
        datePickerHandler.getMonth("MMM");
        datePickerHandler.getYear();
        datePickerHandler.getDay();
        System.out.println(datePickerHandler.getTime());
        System.out.println(datePickerHandler.getDateTime());
        System.out.println(datePickerHandler.getZonedDateTime());
        System.out.println(datePickerHandler.getTimeStamp());

    }

}
