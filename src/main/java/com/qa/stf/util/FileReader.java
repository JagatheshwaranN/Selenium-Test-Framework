package com.qa.stf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.stf.constant.TestConstants;


public class FileReader {

    private static final Logger log = LogManager.getLogger(FileReader.class);

    public static Properties properties = new Properties();

    /**
     * Loads the configuration property file into the {@code properties} object.
     * <p>
     * This method attempts to read the configuration file from the specified path and
     * load its key-value pairs into the {@code properties} object. If the file is not
     * found or an error occurs during the loading process, it throws a custom exception
     * with relevant details.
     * </p>
     *
     * @throws ExceptionUtil.ConfigTypeException If the file is not found or an error
     *                                           occurs while loading it.
     */
    public static void loadPropertyFile() {
        try (FileInputStream fileInputStream = new FileInputStream(TestConstants.CWD + TestConstants.CONFIG_FILE_PATH)) {
            properties.load(fileInputStream);
            log.info("The configuration file is loaded!!");
        } catch (FileNotFoundException ex) {
            log.error("The configuration file not found on the given path " + TestConstants.CONFIG_FILE_PATH, ex);
            throw new ExceptionUtil.ConfigTypeException(TestConstants.CONFIG_FILE_PATH, ex);
        } catch (IOException ex) {
            log.error("Error occurred while loading the configuration file", ex);
            throw new ExceptionUtil.ConfigTypeException("Error occurred while loading configuration file", ex);
        }
    }

    /**
     * Retrieves the value associated with the specified key from the property file.
     * <p>
     * This method fetches the value corresponding to the given key from the loaded configuration
     * property file. If the key or its value is not present, it logs an error and throws a custom
     * exception. The fetched value is stripped of leading and trailing spaces before being returned.
     * </p>
     *
     * @param key The key to search for in the property file.
     * @return The stripped value associated with the key, or {@code null} if the key is not provided.
     * @throws ExceptionUtil.InvalidDataException If the key is not found in the property file.
     */
    public static String getDataFromPropFile(String key) {
        String data = null;
        if (key != null) {
            data = properties.getProperty(key);
            if (data != null) {
                data = data.strip();
                log.info("The '{}' data fetched from the configuration file", data);
            } else {
                log.error("The key '{}' is not present in the configuration file", key);
                throw new ExceptionUtil.InvalidDataException(key, new NullPointerException());
            }
        }
        return data;
    }
    
}


