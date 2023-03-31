package com.qa.taf.ohrm.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.taf.base.BasePage;
import com.qa.taf.base.DriverManager;
import com.qa.taf.util.FileReaderUtil;

public class BaseTest extends FileReaderUtil {

	private WebDriver driver;
	private ChromeOptions options;
	public static BasePage page;
	DriverManager driverManager = new DriverManager();

	@BeforeMethod
	public void invokeBrowser() {
		loadPropertyFile();
		if (getDataFromPropFile("browser").equalsIgnoreCase("Chrome")) {
			options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			driverManager.setDriver(driver);
			page = new BasePage();
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
