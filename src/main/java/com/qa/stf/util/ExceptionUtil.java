package com.qa.stf.util;

public class ExceptionUtil extends RuntimeException {

    public ExceptionUtil(String message) {
        super(message);
    }

    public ExceptionUtil(String message, Throwable cause) {
        super(message, cause);
    }

    public static class OptionNotFoundException extends ExceptionUtil {

        public OptionNotFoundException(String value, String elementLabel) {
            super("Option '" + value + "' not found in the '" + elementLabel + "' dropdown.");

        }

        public OptionNotFoundException(String value, String elementLabel, Throwable throwable) {
            super("Option '" + value + "' not found in the '" + elementLabel + "' dropdown.", throwable);
        }
    }

    public static class DropDownException extends ExceptionUtil {

        public DropDownException(String value, Throwable cause) {
            super(value, cause);

        }

    }

    public static class ElementNotFoundException extends ExceptionUtil {

        public ElementNotFoundException(String elementLabel) {
            super("Element '" + elementLabel + "' is not found / modified on the DOM.");
        }

        public ElementNotFoundException(String elementLabel, Throwable throwable) {
            super("Element '" + elementLabel + "' is not found / modified on the DOM.", throwable);
        }
    }

    public static class ConfigTypeException extends ExceptionUtil {

        public ConfigTypeException(String config) {
            super("The '" + config + "' config type is not valid. Please check the configuration.");
        }
    }

    public static class InvalidDataException extends ExceptionUtil {

        public InvalidDataException(String data) {
            super("The '" + data + "' value is not valid. Please check the data.");
        }
    }

    public static class AlertNotFoundException extends ExceptionUtil {

        public AlertNotFoundException(Throwable throwable) {
            super("Alert was not present on the page.", throwable);
        }
    }

    public static class NavigationException extends ExceptionUtil {

        public NavigationException(String data, Throwable throwable) {
            super(data, throwable);
        }
    }

    public static class WindowException extends ExceptionUtil {

        public WindowException(String data, Throwable throwable) {
            super(data, throwable);
        }
    }

    public static class FrameException extends ExceptionUtil {

        public FrameException(String data, Throwable throwable) {
            super(data, throwable);
        }
    }

    public static class JavascriptExecutorException extends ExceptionUtil {

        public JavascriptExecutorException(String message) {
            super(message);
        }

        public JavascriptExecutorException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class InteractionException extends ExceptionUtil {

        public InteractionException(String message) {
            super(message);
        }

        public InteractionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
