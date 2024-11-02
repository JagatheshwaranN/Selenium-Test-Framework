package com.qa.stf.constant;

public enum EnvType {

    ENV("Env"),
    LOCAL("Local"),
    REMOTE("Remote");

    private final String env;

    EnvType(String env) {
        this.env = env;
    }

    public String getName() {
        return env;
    }

}

