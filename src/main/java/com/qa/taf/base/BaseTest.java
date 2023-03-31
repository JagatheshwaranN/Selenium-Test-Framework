package com.qa.taf.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.taf.util.ConstantUtil;
import com.qa.taf.util.DriverType;
import com.qa.taf.util.EnvType;

public class BaseTest extends DriverManager {

	private static String browser;

	public static String getBrowser() {
		return browser;
	}

	public static void setBrowser(String browser) {
		BaseTest.browser = browser;
	}

	@BeforeMethod
	public void testStartUp() {
		loadPropertyFile();
		if (System.getenv(ConstantUtil.BROWSER) != null && !System.getenv(ConstantUtil.BROWSER).isEmpty()) {
			setBrowser(System.getenv(ConstantUtil.BROWSER));
		} else {
			setBrowser(getDataFromPropFile(ConstantUtil.BROWSER));
		}
		properties.setProperty(ConstantUtil.BROWSER, getBrowser());
		launchBrowser(getBrowser());
	}

	@AfterMethod
	public void testTearDown() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}

	public DriverType getBrowser1() {
		var browser = getDataFromPropFile(ConstantUtil.BROWSER);
		if (browser.equalsIgnoreCase("Chrome"))
			return DriverType.CHROME;
		else if (browser.equalsIgnoreCase("Firefox"))
			return DriverType.FIREFOX;
		else if (browser.equalsIgnoreCase("Edge"))
			return DriverType.EDGE;
		else
			throw new RuntimeException(browser + " value is not found in the Configuration Property file");
	}

	public EnvType getEnvType() {
		var env = getDataFromPropFile(ConstantUtil.ENV);
		if (env.equalsIgnoreCase("Local"))
			return EnvType.LOCAL;
		else if (env.equalsIgnoreCase("Remote"))
			return EnvType.REMOTE;
		else
			throw new RuntimeException(env + " value is not found in the Configuration Property file");
	}

}
