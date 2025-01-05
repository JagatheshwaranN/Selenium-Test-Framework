package com.qa.stf.handler;

import com.qa.stf.base.DriverManager;
import com.qa.stf.report.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DataPickerHandler {

    // Logger instance for the DropDownHandler class to enable logging during the execution
    private static final Logger log = LogManager.getLogger(DataPickerHandler.class);

    // Instance of DriverManager to manage the WebDriver for interacting with the browser
    private final DriverManager driverManager;


    // Instance of VerificationHandler to perform verification actions on dropdown elements
    private final VerificationHandler verificationHandler;

    // Instance of ExtentReportManager to manage the extent report
    protected ExtentReportManager extentReportManager;

    /**
     * Constructs a DropDownHandler instance and initializes it with the provided
     * VerificationHandler.
     * <p>
     * This constructor assigns the given VerificationHandler to the instance variable,
     * which is used for performing verification operations related to dropdown elements.
     * </p>
     *
     * @param verificationHandler The VerificationHandler instance to be used for handling
     *                            verification tasks.
     */
    public DataPickerHandler(DriverManager driverManager, VerificationHandler verificationHandler) {
        if (driverManager == null) {
            throw new IllegalArgumentException("DriverManager cannot be null.");
        }
        this.driverManager = driverManager;
        this.verificationHandler = verificationHandler;
        extentReportManager = ExtentReportManager.getInstance();
    }

    public void selectDateFromDatePicker(WebElement datePicker, WebElement monthDetail, WebElement yearDetail, WebElement monthNavigator, By dayLocator, String day, String month, String year) {
        datePicker.click();
        String monthText = monthDetail.getText();
        String yearText = yearDetail.getText();
        System.out.println(monthText);
        System.out.println(yearText);
        while (!(monthText.equalsIgnoreCase(month) && yearText.equalsIgnoreCase(year))) {
            monthNavigator.click();
            monthText = monthDetail.getText();
            yearText = yearDetail.getText();
            System.out.println(monthText);
            System.out.println(yearText);
        }
        try {
         new InteractionHandler(driverManager, verificationHandler).clickElement(dayLocator, day, "Day");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
