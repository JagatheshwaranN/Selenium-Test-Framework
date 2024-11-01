package com.qa.taf.constant;

public enum BrowserType {

    BROWSER("Browser"),
    CHROME("Chrome"),
    FIREFOX("Firefox"),
    EDGE("MicrosoftEdge");

    private final String name;

    BrowserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
