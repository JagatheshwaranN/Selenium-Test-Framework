package com.qa.taf.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.taf.util.ConstantUtil;
import com.qa.taf.util.ExcelReaderUtil;

public class DriverManager extends BrowserManager {

	private WebDriver driver;
	private ChromeOptions gcOptions;
	private FirefoxOptions ffOptions;
	private EdgeOptions meOptions;
	private DesiredCapabilities capabilities = new DesiredCapabilities();
	public static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
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
			gcOptions = new ChromeOptions();
			gcOptions.addArguments(ConstantUtil.CHROME_LAUNCH_OPTION1);
			gcOptions.addArguments(ConstantUtil.CHROME_LAUNCH_OPTION2);
			yield driver = new ChromeDriver(gcOptions);
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

		switch (getBrowserType().toString()) {
		case "CHROME" -> {
			capabilities.setPlatform(Platform.ANY);
			capabilities.setBrowserName(ConstantUtil.GC_BROWSER);
			gcOptions = new ChromeOptions();
			gcOptions.addArguments(ConstantUtil.CHROME_LAUNCH_OPTION1);
			gcOptions.addArguments(ConstantUtil.CHROME_LAUNCH_OPTION2);
			gcOptions.merge(capabilities);
		}
		case "FIREFOX" -> {
			capabilities.setPlatform(Platform.ANY);
			capabilities.setBrowserName(ConstantUtil.FF_BROWSER);
			ffOptions = new FirefoxOptions();
			ffOptions.merge(capabilities);
		}
		case "EDGE" -> {
			capabilities.setPlatform(Platform.ANY);
			capabilities.setBrowserName(ConstantUtil.ME_BROWSER);
			meOptions = new EdgeOptions();
			meOptions.merge(capabilities);
		}
		default -> throw new IllegalArgumentException("Unexpected Value : " + getBrowserType().toString());
		}
		;
		try {
			driver = new RemoteWebDriver(new URL("http://192.168.1.10:4444"), capabilities);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
		return driver;
	}
}
