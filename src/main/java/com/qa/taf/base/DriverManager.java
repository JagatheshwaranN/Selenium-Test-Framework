package com.qa.taf.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.taf.util.ConstantUtil;
import com.qa.taf.util.ExcelReaderUtil;

public class DriverManager extends BrowserManager {

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

	public void launchBrowser() {
		driver = createDriver();
		setDriver(driver);
		page = new BasePage();
		driver.get(getDataFromPropFile(ConstantUtil.APP_URL));
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private WebDriver createDriver() {
		return driver = switch (getEnvType().toString()) {
		case "LOCAL" -> {
			yield driver = createLocalDriver();
		}
		case "REMOTE" -> {
			yield driver = createRemoteDriver();
		}
		default -> throw new IllegalArgumentException("Unexpected Value : " + getEnvType().toString());
		};
	}

	private WebDriver createLocalDriver() {
		return driver = switch (getBrowserType().toString()) {
		case "CHROME" -> {
			options = new ChromeOptions();
			options.addArguments(ConstantUtil.CHROME_LAUNCH_OPTION1);
			options.addArguments(ConstantUtil.CHROME_LAUNCH_OPTION2);
			yield driver = new ChromeDriver(options);
		}
		case "FIREFOX" -> {
			yield driver = new FirefoxDriver();
		}
		case "EDGE" -> {
			yield driver = new EdgeDriver();
		}
		default -> throw new IllegalArgumentException("Unexpected Value : " + getBrowserType().toString());
		};
	}

	private WebDriver createRemoteDriver() {
		// TODO Auto-generated method stub
		return null;
	}

}
