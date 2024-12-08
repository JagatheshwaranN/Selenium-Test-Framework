package com.qa.stf.util;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.stf.constant.TestConstants;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public class ExtentUtil {

    private static final Logger log = LogManager.getLogger(ExtentUtil.class);

    private static ExtentSparkReporter extentSparkReporter;

    @Deprecated
    public static ExtentReports getInstance() {
        if (extentSparkReporter == null) {
            extentSparkReporter = new ExtentSparkReporter(
                    System.getProperty("user.dir") + TestConstants.EXTENT_REPORT_PATH);
        }
        try {
            extentSparkReporter.loadXMLConfig(
                    new File(System.getProperty("user.dir") + TestConstants.EXTENT_REPORT_CONFIG_FILE_PATH));
            ExtentReports extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);
            log.info("Extent Report setup completed successfully.");
            return extentReports;
        } catch (IOException ex) {
            log.error("Error occurred during Extent Report setup: {}", ex.getMessage(), ex);
            throw new ExceptionUtil.ExtentException("Error setting up Extent Report: " + ex.getMessage(), ex);
        }
    }

}

