package com.qa.taf.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.taf.constant.ConstantUtil;
import com.qa.taf.constant.DriverType;
import com.qa.taf.constant.EnvType;
import com.qa.taf.util.FileReaderUtil;

public class BrowserManager extends FileReaderUtil {

	private static Logger log = LogManager.getFormatterLogger(BrowserManager.class);
	private static String browser;
	private static String env;

	public static String getBrowser() {
		return browser;
	}

	public static void setBrowser(String browser) {
		BrowserManager.browser = browser;
	}

	public static String getEnv() {
		return env;
	}

	public static void setEnv(String env) {
		BrowserManager.env = env;
	}

	public DriverType getBrowserType() {

		if (System.getenv(ConstantUtil.BROWSER) != null && !System.getenv(ConstantUtil.BROWSER).isEmpty()) {
			setBrowser(System.getenv(ConstantUtil.BROWSER));
		} else {
			setBrowser(getDataFromPropFile(ConstantUtil.BROWSER));
		}
		properties.setProperty(ConstantUtil.BROWSER, getBrowser());

		return switch (getBrowser().toString()) {
		case "Chrome" -> {
			log.info("Chrome browser is set for test execution");
			yield DriverType.CHROME;
		}
		case "Firefox" -> {
			log.info("Firefox browser is set for test execution");
			yield DriverType.FIREFOX;
		}
		case "Edge" -> {
			log.info("Edge browser is set for test execution");
			yield DriverType.EDGE;
		}
		default -> throw new IllegalArgumentException(
				getBrowser().toString() + " value is not found in the Configuration Property file");
		};
	}

	public EnvType getEnvType() {

		if (System.getenv(ConstantUtil.ENV) != null && !System.getenv(ConstantUtil.ENV).isEmpty()) {
			setEnv(System.getenv(ConstantUtil.ENV));
		} else {
			setEnv(getDataFromPropFile(ConstantUtil.ENV));
		}
		properties.setProperty(ConstantUtil.ENV, getEnv());

		if (getEnv().equalsIgnoreCase(ConstantUtil.LC_ENV)) {
			log.info("Local Environment is opted for test execution");
			return EnvType.LOCAL;
		} else if (getEnv().equalsIgnoreCase(ConstantUtil.RE_ENV)) {
			log.info("Remote Environment is opted for test execution");
			return EnvType.REMOTE;
		} else
			throw new RuntimeException(getEnv() + " value is not found in the Configuration Property file");
	}
}
