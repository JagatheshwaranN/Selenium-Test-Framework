package com.qa.taf.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.qa.taf.util.ConstantUtil;
import com.qa.taf.util.ExcelReaderUtil;
import com.qa.taf.util.FileReaderUtil;

public class DriverManager extends FileReaderUtil {

	private WebDriver driver;
	private ChromeOptions options;
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
	public static ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil(
			System.getProperty("user.dir") + ConstantUtil.EXCEL_FILE_PATH);
	public static BasePage page;

	public WebDriver getDriver() {
		return driverLocal.get();
	}

	public void setDriver(WebDriver driver) {
		driverLocal.set(driver);
	}

	public void launchBrowser(String browser) {
		if (getDataFromPropFile(ConstantUtil.BROWSER).equalsIgnoreCase(browser)) {
			options = new ChromeOptions();
			options.addArguments(ConstantUtil.CHROME_LAUNCH_OPTION);
			driver = new ChromeDriver(options);
			setDriver(driver);
			page = new BasePage();
			driver.get(getDataFromPropFile(ConstantUtil.APP_URL));
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
