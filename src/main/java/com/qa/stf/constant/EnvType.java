package com.qa.stf.constant;

/**
 * Enum representing various types of environments supported in the application.
 * Each enum constant has an associated environment name.
 */
public enum EnvType {

    /**
     * Represents a generic environment type.
     */
    ENV("Env"),

    /**
     * Represents a local environment.
     */
    LOCAL("Local"),

    /**
     * Represents a remote environment.
     */
    REMOTE("Remote");

    /**
     * The name of the environment as a string.
     */
    private final String envName;

    /**
     * Constructor to initialize the environment type with its name.
     *
     * @param envName the name of the environment.
     */
    EnvType(String envName) {
        if (envName == null || envName.isEmpty()) {
            throw new IllegalArgumentException("Environment name cannot be null or empty");
        }
        this.envName = envName;
    }

    /**
     * Retrieves the name of the environment.
     *
     * @return the environment name as a string.
     */
    public String getEnvName() {
        return envName;
    }

}
