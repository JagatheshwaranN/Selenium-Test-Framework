package com.qa.stf.handler;

import com.qa.stf.base.DriverManager;
import com.qa.stf.util.ExceptionHub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

// version 1.3

public class DatePickerHandler {

    // Logger instance for the DropDownHandler class to enable logging during the execution
    private static final Logger log = LogManager.getLogger(DatePickerHandler.class);

    private static final String DAY_LABEL = "Day";

    // Instance of VerificationHandler to perform verification actions on dropdown elements
    private final VerificationHandler verificationHandler;

    private final InteractionHandler interactionHandler;

    public DatePickerHandler(DriverManager driverManager, VerificationHandler verificationHandler) {
        if (driverManager == null) {
            throw new IllegalArgumentException("DriverManager cannot be null.");
        }
        this.verificationHandler = verificationHandler;
        this.interactionHandler = new InteractionHandler(driverManager, this.verificationHandler);
    }

    public void selectDateFromSingleDatePicker(WebElement datePicker, WebElement dateDetailSection, WebElement monthDetail, WebElement yearDetail, WebElement monthNavigator, By dayLocator, String day, String month, String year, String dateDetailSectionLabel) {
        int maxAttempts = 12;
        if (day == null || month == null || year == null) {
            throw new ExceptionHub.DatePickerException("Day, Month, and Year must not be null.");
        }
        try {
            LocalDate.of(Integer.parseInt(year), Month.valueOf(month.toUpperCase()), Integer.parseInt(day));
        } catch (DateTimeException | IllegalArgumentException ex) {
            throw new ExceptionHub.DatePickerException("Invalid date: " + day + "/" + month + "/" + year);
        }
        log.info("Selecting date: {}/{}/{}", day, month, year);
        datePicker.click();
        verificationHandler.isElementDisplayed(dateDetailSection, dateDetailSectionLabel);
        String monthAfterTrim = month.trim();
        String yearAfterTrim = year.trim();
        String monthText = monthDetail.getText();
        String yearText = yearDetail.getText();
        while (!(monthText.trim().equalsIgnoreCase(monthAfterTrim) && yearText.trim().equalsIgnoreCase(yearAfterTrim))) {
            maxAttempts--;
            log.info("Navigating to Month: {}, Year: {}", monthText, yearText);
            monthNavigator.click();
            monthText = monthDetail.getText();
            yearText = yearDetail.getText();
            if (maxAttempts <= 0) {
                throw new ExceptionHub.DatePickerException(String.format("Could not find the desired month and year: %s & %s", month, year));
            }
        }
        log.info("Selecting day: {}", day);
        interactionHandler.clickElement(dayLocator, day, DAY_LABEL);
    }

    public void selectDateFromDualDatePicker(WebElement datePicker, WebElement dateDetailSection, List<WebElement> monthYearDetailList, WebElement monthNavigator, By dayLocator, String day, String monthYear, String dateDetailSectionLabel) {
        int maxAttempts = 12;
        if (day == null || monthYear == null) {
            throw new ExceptionHub.DatePickerException("Day, Month, and Year must not be null.");
        }
        log.info("Selecting date: {} / {}", day, monthYear);
        datePicker.click();
        verificationHandler.isElementDisplayed(dateDetailSection, dateDetailSectionLabel);
        String monthYearAfterTrim = monthYear.trim();
        String leftMonthYearDetail = monthYearDetailList.get(0).getText();
        String rightMonthYearDetail = monthYearDetailList.get(1).getText();
        while (!(leftMonthYearDetail.trim().equalsIgnoreCase(monthYearAfterTrim) &&
                rightMonthYearDetail.trim().equalsIgnoreCase(monthYearAfterTrim))) {
            maxAttempts--;
            log.info("Navigating to Month & Year: {}", monthYear);
            monthNavigator.click();
            leftMonthYearDetail = monthYearDetailList.get(0).getText();
            rightMonthYearDetail = monthYearDetailList.get(1).getText();
            if (maxAttempts <= 0) {
                throw new ExceptionHub.DatePickerException("Could not find the desired month and year: " + monthYear);
            }
        }
        log.info("Selecting day: {}", day);
        interactionHandler.clickElement(dayLocator, day, DAY_LABEL);
    }

    public String getDate() {
        // YYYY-MM-dd - Default Format
        return LocalDate.now().toString();
    }

    public String getCustomizedDate(String format) {
        if (format == null || format.isEmpty()) {
            format = "dd-MM-yyyy";
        }
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return currentDate.format(dateTimeFormatter);
    }

    public String getDay() {
        LocalDate currentDate = LocalDate.now();
        return String.valueOf(currentDate.getDayOfMonth());
    }

    public String getMonth(String format) {
        // MMM - Jan & MMMM - January
        if (format == null || format.isEmpty()) {
            format = "MMMM";
        }
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

    public String getCustomizedTime(String format) {
        // hh:mm:ss a - 09:10:40 AM
        if (format == null || format.isEmpty()) {
            format = "hh:mm:ss a";
        }
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return currentTime.format(dateTimeFormatter);
    }

    public String getDateTime() {
        return LocalDateTime.now().toString();
    }

    public String getZonedDateTime(ZoneId zoneId) {
        return ZonedDateTime.now(zoneId).toString();
    }

    public String getTimeStamp() {
        return Instant.now().toString();
    }

}
