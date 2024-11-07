package com.qa.stf.handler;

public class BaseException extends RuntimeException {

    public BaseException(String message) {
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

        public ElementNotFoundException(String elementLabel, Throwable throwable) {
            super("Element '" + elementLabel + "' is not found / modified on the DOM.", throwable);
        }
    }

    public static class ConfigTypeException extends BaseException {

        public ConfigTypeException(String config) {
            super("The '" + config + "' config type is not valid. Please check the configuration.");
        }
    }

    public static class InvalidDataException extends BaseException {

        public InvalidDataException(String data) {
            super("The '" + data + "' value is not valid. Please check the data.");
        }
    }
}
