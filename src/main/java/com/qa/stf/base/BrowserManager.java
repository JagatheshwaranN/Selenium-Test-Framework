package com.qa.stf.base;

import com.qa.stf.constant.BrowserType;
import com.qa.stf.constant.TestConstants;
import com.qa.stf.util.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.stf.util.FileReaderUtil;

public class BrowserManager extends FileReaderUtil {

    private static final Logger log = LogManager.getFormatterLogger(BrowserManager.class);

    private String browser;

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowser() {
        return browser;
    }

    public BrowserType getBrowserType() {
        setBrowser(getValue(BrowserType.BROWSER.getName()));
        properties.setProperty(BrowserType.BROWSER.getName(), getBrowser());
        return switch (getBrowser()) {
            case TestConstants.CHROME -> {
                log.info("Chrome browser is set for test execution");
                yield BrowserType.CHROME;
            }
            case TestConstants.FIREFOX -> {
                log.info("Firefox browser is set for test execution");
                yield BrowserType.FIREFOX;
            }
            case TestConstants.EDGE -> {
                log.info("Edge browser is set for test execution");
                yield BrowserType.EDGE;
            }
            default ->
                    throw new ExceptionUtil.ConfigTypeException(getBrowser());
        };
    }

    private String getValue(String key) {
        return System.getenv(key) != null && !System.getenv(key).isEmpty()
                ? System.getenv(key)
                : getDataFromPropFile(key);
    }

}