package com.qa.stf.constant;

public enum BrowserType {

    BROWSER("Browser"),
    CHROME("Chrome"),
    FIREFOX("Firefox"),
    EDGE("MicrosoftEdge");

    private final String browser;

    BrowserType(String browser) {
        this.browser = browser;
    }

    public String getName() {
        return browser;
    }

}
