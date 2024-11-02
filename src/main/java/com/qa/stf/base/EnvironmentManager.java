package com.qa.stf.base;

import com.qa.stf.constant.BrowserType;
import com.qa.stf.constant.EnvType;
import com.qa.stf.constant.TestConstants;
import com.qa.stf.handler.BaseException;
import com.qa.stf.util.FileReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvironmentManager extends FileReaderUtil  {

    private static final Logger log = LogManager.getFormatterLogger(BrowserManager.class);

    private String env;

    public void setEnv(String env) {
        this.env = env;
    }

    public String getEnv() {
        return env;
    }

    public EnvType getEnvType() {
        setEnv(getValue(EnvType.ENV.getName()));
        properties.setProperty(EnvType.ENV.getName(), getEnv());
        if (getEnv().equalsIgnoreCase(EnvType.LOCAL.getName())) {
            log.info("Local Environment is opted for test execution");
            return EnvType.LOCAL;
        } else if (getEnv().equalsIgnoreCase(EnvType.REMOTE.getName())) {
            log.info("Remote Environment is opted for test execution");
            return EnvType.REMOTE;
        } else
            throw new BaseException.ConfigTypeException(getEnv() + " value is not found in the Configuration Property file");
    }

    private String getValue(String key) {
        return System.getenv(key) != null && !System.getenv(key).isEmpty()
                ? System.getenv(key)
                : getDataFromPropFile(key);
    }

}
