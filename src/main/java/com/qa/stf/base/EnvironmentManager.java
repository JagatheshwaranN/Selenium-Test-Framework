package com.qa.stf.base;

import com.qa.stf.constant.EnvType;
import com.qa.stf.constant.TestConstants;
import com.qa.stf.util.ExceptionHub;
import com.qa.stf.util.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvironmentManager extends FileReader {

    private static final Logger log = LogManager.getLogger(EnvironmentManager.class);

    private String env;

    /**
     * Sets the environment type for the test execution.
     * <p>
     * This method assigns the specified environment to the instance variable
     * `env`, which will be used later to determine the environment type for
     * test execution.
     * </p>
     *
     * @param env The environment name to set (e.g., "local" or "remote").
     */
    public void setEnv(String env) {
        this.env = env;
    }

    /**
     * Retrieves the environment type currently set for the test execution.
     * <p>
     * This method returns the environment that has been set using the `setEnv`
     * method. It returns values like "local" or "remote".
     * </p>
     *
     * @return The name of the currently set environment.
     */
    public String getEnv() {
        return env;
    }

    /**
     * Retrieves the EnvType enumeration for the environment set for the test
     * execution.
     * <p>
     * This method fetches the environment type from either environment variables
     * or a property file, then determines the corresponding `EnvType` enum (such
     * as LOCAL or REMOTE). The environment type is logged for informational purposes.
     * </p>
     *
     * @return The corresponding `EnvType` for the currently set environment.
     * @throws ExceptionHub.ConfigTypeException If the environment type is invalid or
     *                                          not recognized.
     */
    public EnvType getEnvType() {
        setEnv(getValue(EnvType.ENV.getEnvName())); // Set environment from value
        properties.setProperty(EnvType.ENV.getEnvName(), getEnv()); // Store environment in properties

        // Validate and return the appropriate environment type
        if (getEnv() == null || getEnv().isEmpty()) {
            log.error("Environment type is not specified or is empty.");
            throw new ExceptionHub.ConfigTypeException("Environment type is not specified.");
        }

        return switch (getEnv().toUpperCase()) {
            case TestConstants.LOCAL -> {
                log.info("Local Environment is opted for test execution");
                yield EnvType.LOCAL;
            }
            case TestConstants.REMOTE -> {
                log.info("Remote Environment is opted for test execution");
                yield EnvType.REMOTE;
            }
            default -> {
                log.error("Invalid environment type: {}", getEnv());
                throw new ExceptionHub.ConfigTypeException("Invalid environment type: " + getEnv());
            }
        };
    }

    /**
     * Retrieves the value associated with the provided key from either environment
     * variables or a property file.
     * <p>
     * This method first checks if the key exists in the environment variables, and
     * if not, it fetches the value from the property file.
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
            log.warn("Value for key {} not found in environment or property file.", key);
        }
        return value;
    }

}
