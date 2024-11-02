package com.qa.stf.util;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.stf.constant.ConstantUtil;

public class ExtentUtil {

	private static Logger log = LogManager.getFormatterLogger(ExtentUtil.class);
	private static ExtentSparkReporter extentSparkReporter;
	private static ExtentReports extentReports;

	public static ExtentReports getInstance() {
		if (extentSparkReporter == null) {
			extentSparkReporter = new ExtentSparkReporter(
					System.getProperty("user.dir") + ConstantUtil.EXTENT_REPORT_PATH);
			try {
				extentSparkReporter.loadXMLConfig(
						new File(System.getProperty("user.dir") + ConstantUtil.EXTENT_REPORT_CONFIG_FILE_PATH));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		log.info("Extent Report instance is created !!");
		return extentReports;
	}
}

