package com.qa.stf.base;

import java.net.MalformedURLException;
import java.net.URI;

import com.qa.stf.constant.BrowserType;
import com.qa.stf.listener.TestListener;
import com.qa.stf.util.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.stf.constant.TestConstants;
import com.qa.stf.util.ExcelReaderUtil;
import com.qa.stf.util.ExtentUtil;

public class DriverManager extends BrowserManager {

    private static final Logger log = LogManager.getFormatterLogger(DriverManager.class);

    private WebDriver driver;
    private ChromeOptions gcOptions;
    private FirefoxOptions ffOptions;
    private EdgeOptions meOptions;
    public static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil(
            System.getProperty("user.dir") + TestConstants.EXCEL_FILE_PATH);

    public static ExtentReports report = ExtentUtil.getInstance();

//    public static ExtentTest test;

    public static BasePage page;

    private final EnvironmentManager environmentManager;

    public DriverManager() {
        this.environmentManager = new EnvironmentManager();
    }

    public void setDriver(WebDriver driver) {
        driverLocal.set(driver);
    }

    public WebDriver getDriver() {
        return driverLocal.get();
    }

    public ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public void setExtentTest(ExtentTest extentTest) {
        DriverManager.extentTest.set(extentTest);
    }

    public void closeExtentTest(){
        extentTest.remove();
    }

    public void launchBrowser() {
        driver = createDriver();
        setDriver(driver);
        new TestListener(new DriverManager());
        page = new BasePage();
        driver.get(getDataFromPropFile(TestConstants.APP_URL));
    }

    private WebDriver createDriver() {
        return driver = switch (environmentManager.getEnvType().toString()) {
            case "LOCAL" -> createLocalDriver();
            case "REMOTE" -> {
                try {
                    yield createRemoteDriver();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> throw new ExceptionUtil.InvalidDataException(environmentManager.getEnvType().toString());
        };
    }

    private WebDriver createLocalDriver() {

        return driver = switch (getBrowserType().toString()) {
            case "CHROME" -> {
                log.info("Chrome driver is initialized for local test execution");
                gcOptions = new ChromeOptions();
                //gcOptions.addArguments(ConstantUtil.CHROME_REMOTE_ORIGIN);
                gcOptions.addArguments(TestConstants.BROWSER_MAXIMIZE);
                yield new ChromeDriver(gcOptions);
            }
            case "FIREFOX" -> {
                log.info("Firefox driver is initialized for local test execution");
                ffOptions = new FirefoxOptions();
                ffOptions.addArguments(TestConstants.BROWSER_MAXIMIZE);
                yield new FirefoxDriver(ffOptions);
            }
            case "EDGE" -> {
                log.info("Edge driver is initialized for local test execution");
                meOptions = new EdgeOptions();
                meOptions.addArguments(TestConstants.BROWSER_MAXIMIZE);
                yield new EdgeDriver(meOptions);
            }
            default -> throw new ExceptionUtil.InvalidDataException(getBrowserType().toString());
        };
    }

    private WebDriver createRemoteDriver() throws MalformedURLException {

        return driver = switch (getBrowserType().toString()) {
            case "Chrome" -> {
                gcOptions = new ChromeOptions();
                gcOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
                gcOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME.getName());
                gcOptions.addArguments(TestConstants.CHROME_REMOTE_ORIGIN);
                gcOptions.addArguments(TestConstants.BROWSER_MAXIMIZE);
                yield new RemoteWebDriver(URI.create(getDataFromPropFile("RemoteUrl")).toURL(), gcOptions);
            }
            case "Firefox" -> {
                ffOptions = new FirefoxOptions();
                ffOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
                ffOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX.getName());
                yield new RemoteWebDriver(URI.create(getDataFromPropFile("RemoteUrl")).toURL(), ffOptions);
            }
            case "MicrosoftEdge" -> {
                meOptions = new EdgeOptions();
                meOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
                meOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.EDGE.getName());
                meOptions.addArguments(TestConstants.BROWSER_MAXIMIZE);
                yield new RemoteWebDriver(URI.create(getDataFromPropFile("RemoteUrl")).toURL(), meOptions);
            }
            default -> throw new ExceptionUtil.InvalidDataException(getBrowserType().toString());
        };
    }

}

