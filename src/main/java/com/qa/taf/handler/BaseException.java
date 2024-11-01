package com.qa.taf.handler;

public class BaseException extends RuntimeException {

    public BaseException(String message){
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class OptionNotFoundException extends BaseException {

        public OptionNotFoundException(String value, String elementLabel) {
            super("Option '" + value + "' not found in the '" + elementLabel + "' dropdown.");
        }
    }

    public static class ElementNotFoundException extends BaseException {

        public ElementNotFoundException(String elementLabel) {
            super("Element '" + elementLabel + "' is not found / modified on the DOM.");
        }
    }
}
