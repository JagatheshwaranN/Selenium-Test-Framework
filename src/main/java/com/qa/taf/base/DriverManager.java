package com.qa.taf.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.taf.util.ConstantUtil;
import com.qa.taf.util.ExcelReaderUtil;
import com.qa.taf.util.FileReaderUtil;

public class DriverManager extends FileReaderUtil {

	private WebDriver driver;
	private ChromeOptions options;
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
	public static ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil(
			System.getProperty("user.dir") + ConstantUtil.Excel_File_Path);
	public static Page page;

	public WebDriver getDriver() {
		return driverLocal.get();
	}

	public void setDriver(WebDriver driver) {
		driverLocal.set(driver);
	}

	@BeforeMethod
	public void invokeBrowser() {
		loadPropertyFile();
		if (getDataFromPropFile("browser").equalsIgnoreCase("Chrome")) {
			options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			setDriver(driver);
			page = new BasePage(getDriver());
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
