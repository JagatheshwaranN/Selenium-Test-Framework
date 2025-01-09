package com.qa.stf.handler;

import com.qa.stf.base.DriverManager;
import com.qa.stf.report.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// version 1.2

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

    public void selectDateFromDatePicker(WebElement datePicker, WebElement dateDetailSection, WebElement monthDetail, WebElement yearDetail, WebElement monthNavigator, By dayLocator, String day, String month, String year, String dateDetailSectionLabel) {
        datePicker.click();
        verificationHandler.isElementDisplayed(dateDetailSection, dateDetailSectionLabel);
        String monthText = monthDetail.getText();
        String yearText = yearDetail.getText();
//        System.out.println(monthText);
//        System.out.println(yearText);
        while (!(monthText.equalsIgnoreCase(month) && yearText.equalsIgnoreCase(year))) {
            monthNavigator.click();
            monthText = monthDetail.getText();
            yearText = yearDetail.getText();
//            System.out.println(monthText);
//            System.out.println(yearText);
        }
        new InteractionHandler(driverManager, verificationHandler).clickElement(dayLocator, day, "Day");
    }

    public void getDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String currentDateInFormat = currentDate.format(dateTimeFormatter);
        System.out.println(currentDateInFormat);
    }

    public void getMonth(String format) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        System.out.println(currentDate.format(dateTimeFormatter));
    }

    public void getYear() {
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate.getYear());
    }

    public static void main(String[] args) {
        DatePickerHandler datePickerHandler = new DatePickerHandler();
        datePickerHandler.getDate();
        datePickerHandler.getMonth("MMM");
        datePickerHandler.getYear();
    }

}
