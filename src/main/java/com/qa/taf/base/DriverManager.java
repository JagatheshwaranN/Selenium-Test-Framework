package com.qa.taf.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverManager {

	private WebDriver driver;
	private ChromeOptions options;

	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();

	public WebDriver getDriver() {
		return driverLocal.get();
	}

	public void setDriver(WebDriver driver) {
		driverLocal.set(driver);
	}

	@BeforeMethod
	public void invokeBrowser() {
		// if (browser.equalsIgnoreCase("chrome")) {
		options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		setDriver(driver);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
