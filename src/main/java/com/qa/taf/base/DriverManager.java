package com.qa.taf.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

	private WebDriver driver;

	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();

	public WebDriver getDriver() {
		return driverLocal.get();
	}

	public void setDriver(WebDriver driver) {
		driverLocal.set(driver);
	}

	public void invokeBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			setDriver(driver);
		}
	}
}
