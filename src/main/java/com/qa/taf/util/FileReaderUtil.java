package com.qa.taf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.qa.taf.constant.ConstantUtil;

public class FileReaderUtil {

	private static Logger log = LogManager.getFormatterLogger(FileReaderUtil.class);
	public static Properties properties = new Properties();

	public static void loadPropertyFile() {
		
		try (FileInputStream fileInputStream = new FileInputStream(
				new File(System.getProperty("user.dir") + ConstantUtil.CONFIG_FILE_PATH))) {
			properties.load(fileInputStream);
			log.info("Configuration property file loaded !!");
		} catch (FileNotFoundException ex) {
			log.error("The Configuration property file not found on the given path " + ConstantUtil.CONFIG_FILE_PATH
					+ "\n" + ex);
		} catch (IOException ex) {
			log.error("Error occured while load the Configuration property file " + "\n" + ex);
		}
	}

	public static String getDataFromPropFile(String key) {
		
		String data = null;
		try {
			if (Optional.ofNullable(key).isPresent()) {
				data = properties.getProperty(key).strip();
				log.info("The " + "'" + data + "'" + " data fetched from the configuration property file");
			}
		} catch (NullPointerException ex) {
			Assert.fail("The key - " + "'" + key + "'" + " - is not present in the configuration property file" + "\n"
					+ ex.getMessage());
		}
		return data;
	}
}
