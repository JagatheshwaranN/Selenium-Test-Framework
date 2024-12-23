package com.qa.stf.base;

import com.qa.stf.constant.BrowserType;
import com.qa.stf.util.ExceptionHub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.stf.util.FileReader;

import static com.qa.stf.constant.TestConstants.*;


/**
 * The BrowserManager class handles browser-related configurations for test execution
 * using Selenium WebDriver. It manages the browser type (e.g., Chrome, Firefox, Edge)
 * based on environment variables or property files and provides utility methods for
 * setting and retrieving the browser type.
 *
 * <p>Features:
 * <ul>
 *     <li>Set and get the browser type for test execution.</li>
 *     <li>Retrieve the corresponding BrowserType enumeration based on the browser set.</li>
 *     <li>Fetch browser configuration values from environment variables or property files.</li>
 * </ul>
 *
 * <p>Exception Handling:
 * <ul>
 *   <li>Custom exceptions from the {@link ExceptionHub.ConfigTypeException} class
 *       are thrown for unrecognized or invalid browser types.</li>
 *   <li>Detailed logging is provided for browser configurations and error scenarios.</li>
 * </ul>
 *
 * <p>Note:
 * The class assumes proper WebDriver setup and configuration. The user must handle
 * WebDriver initialization and termination separately.
 *
 * <p>Example:
 * <pre>
 * {@code
 * BrowserManager browserManager = new BrowserManager();
 * browserManager.setBrowser("chrome");
 * BrowserType browserType = browserManager.getBrowserType();
 * }
 * </pre>
 *
 * @author Jagatheshwaran N
 * @version 1.3
 */
public class BrowserManager extends FileReader {

    // Logger instance for the BrowserManager class to enable logging during the execution
    private static final Logger log = LogManager.getLogger(BrowserManager.class);

    // Instance variable to store the browser configuration (e.g., Chrome, Firefox, etc.)
    private String browser;

    /**
     * Sets the browser type for the test execution.
     * <p>
     * This method assigns the specified browser to the instance variable
     * `browser`, which will be used later to determine the browser type
     * for test execution.
     * </p>
     *
     * @param browser The name of the browser to set.
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * Retrieves the browser type currently set for the test execution.
     * <p>
     * This method returns the name of the browser that has been set using the
     * `setBrowser` method. The value is returned as a string.
     * </p>
     *
     * @return The name of the currently set browser.
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * Retrieves the BrowserType enumeration for the browser set for the test
     * execution.
     * <p>
     * This method fetches the browser type from either environment variables
     * or a property file, then determines the corresponding `BrowserType` enum
     * (such as CHROME, FIREFOX, or EDGE). The browser type is logged for
     * informational purposes.
     * </p>
     *
     * @return The corresponding `BrowserType` for the currently set browser.
     * @throws ExceptionHub.ConfigTypeException If the browser type is invalid or
     *                                          not recognized.
     */
    public BrowserType getBrowserType() {
        setBrowser(getValue(BrowserType.BROWSER.getBrowserName()));
        return switch (getBrowser()) {
            case CHROME -> {
                log.info("Chrome browser is set for test execution");
                yield BrowserType.CHROME;
            }
            case FIREFOX -> {
                log.info("Firefox browser is set for test execution");
                yield BrowserType.FIREFOX;
            }
            case EDGE -> {
                log.info("Edge browser is set for test execution");
                yield BrowserType.EDGE;
            }
            default -> {
                log.error("Invalid browser type: '{}'", getBrowser());
                throw new ExceptionHub.ConfigTypeException(getBrowser());
            }
        };
    }

    /**
     * Retrieves the value associated with the provided key from either environment
     * variables or a property file.
     * <p>
     * This method first checks if the key exists in the environment variables, and
     * if not, it fetches the value from the property file. A warning is logged if
     * the key is not found in either location.
     * </p>
     *
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the provided key.
     */
    private String getValue(String key) {
        String value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        value = getDataFromPropFile(key);
        if (value == null || value.isEmpty()) {
            log.warn("Value for key '{}' not found in environment or property file.", key);
        }
        return value;
    }

}
