package com.qa.stf.constant;

/**
 * Enum representing various types of browsers supported in the application.
 * Each enum constant has an associated browser name.
 */
public enum BrowserType {

    /**
     * Represents a generic browser type.
     */
    BROWSER("Browser"),

    /**
     * Represents the Google Chrome browser.
     */
    CHROME("Chrome"),

    /**
     * Represents the Mozilla Firefox browser.
     */
    FIREFOX("Firefox"),

    /**
     * Represents the Microsoft Edge browser.
     */
    EDGE("MicrosoftEdge");

    /**
     * The name of the browser as a string.
     */
    private final String browserName;

    /**
     * Constructor to initialize the browser type with its name.
     *
     * @param browserName the name of the browser.
     */
    BrowserType(String browserName) {
        if (browserName == null || browserName.isEmpty()) {
            throw new IllegalArgumentException("Browser name cannot be null or empty");
        }
        this.browserName = browserName;
    }

    /**
     * Retrieves the name of the browser.
     *
     * @return the browser name as a string.
     */
    public String getBrowserName() {
        return browserName;
    }
}
